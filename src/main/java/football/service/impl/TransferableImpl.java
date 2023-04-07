package football.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import football.model.Player;
import football.model.Team;
import football.service.PlayerService;
import football.service.TeamService;
import football.service.Transferable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferableImpl implements Transferable {
    private final TeamService teamService;
    private final PlayerService playerService;

    @Override
    public String transfer(Long senderId, Long receiverId, Long playerId) {
        Player player = playerService.get(playerId);
        Team sender = teamService.get(senderId);
        Team receiver = teamService.get(receiverId);

        if (player == null) {
            throw new RuntimeException("Player with id " + playerId + " not found");
        }
        if (sender == null) {
            throw new RuntimeException("Team with id " + senderId + " not found");
        }
        if (receiver == null) {
            throw new RuntimeException("Team with id " + receiverId + " not found");
        }
        if (!sender.getPlayers().contains(player)) {
            throw new RuntimeException("Team " + sender.getName()
                    + " does not have player " + player.getName());
        }

        BigDecimal amount = BigDecimal.valueOf(player.getExperience())
                .multiply(BigDecimal.valueOf(100000L))
                .divide(BigDecimal.valueOf(player.getAge()), RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100 + receiver.getCommissionInPercent()))
                .divide(BigDecimal.valueOf(100));

        BigDecimal senderMoneyAccount = sender.getMoneyAccount();
        BigDecimal receiversMoneyAccount = receiver.getMoneyAccount();

        if (amount.compareTo(senderMoneyAccount) > 0) {
            throw new RuntimeException("Not enough money on "
                    + sender.getName() + " money account");
        }

        senderMoneyAccount = senderMoneyAccount.add(amount);
        receiversMoneyAccount = receiversMoneyAccount.subtract(amount);

        sender.setMoneyAccount(senderMoneyAccount);
        receiver.setMoneyAccount(receiversMoneyAccount);

        List<Player> senderPlayers = sender.getPlayers();
        senderPlayers.remove(player);
        sender.setPlayers(senderPlayers);
        teamService.save(sender);

        List<Player> receiverPlayers = receiver.getPlayers();
        receiverPlayers.add(player);
        receiver.setPlayers(receiverPlayers);
        teamService.save(receiver);

        return String.format("Transferred player %s from %s to %s for %s",
                player.getName(), sender.getName(), receiver.getName(), amount);
    }
}
