package football.service.impl;

import football.model.Player;
import football.model.Team;
import football.service.PlayerService;
import football.service.TeamService;
import football.service.Transferable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
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

        validatePlayer(player);
        validateTeam(sender, senderId);
        validateTeam(receiver, receiverId);
        validatePlayerInTeam(sender, player);

        BigDecimal amount = calculateTransferAmount(player, receiver);
        updateMoneyAccounts(sender, receiver, amount);
        updatePlayersInTeams(sender, receiver, player);

        return String.format("Transferred player %s from %s to %s for %s",
                player.getName(), sender.getName(), receiver.getName(), amount);
    }

    private void validatePlayer(Player player) {
        if (player == null) {
            throw new RuntimeException("Player not found");
        }
    }

    private void validateTeam(Team team, Long teamId) {
        if (team == null) {
            throw new RuntimeException("Team with id " + teamId + " not found");
        }
    }

    private void validatePlayerInTeam(Team team, Player player) {
        if (!team.getPlayers().contains(player)) {
            throw new RuntimeException("Team " + team.getName()
                    + " does not have player " + player.getName());
        }
    }

    private BigDecimal calculateTransferAmount(Player player, Team receiver) {
        return BigDecimal.valueOf(player.getExperience())
                .multiply(BigDecimal.valueOf(100000L))
                .divide(BigDecimal.valueOf(player.getAge()), RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100 + receiver.getCommissionInPercent()))
                .divide(BigDecimal.valueOf(100));
    }

    private void updateMoneyAccounts(Team sender, Team receiver, BigDecimal amount) {
        if (amount.compareTo(sender.getMoneyAccount()) > 0) {
            throw new RuntimeException("Not enough money on "
                    + sender.getName() + " money account");
        }

        sender.setMoneyAccount(sender.getMoneyAccount().subtract(amount));
        receiver.setMoneyAccount(receiver.getMoneyAccount().add(amount));

        teamService.save(sender);
        teamService.save(receiver);
    }

    private void updatePlayersInTeams(Team sender, Team receiver, Player player) {
        List<Player> senderPlayers = sender.getPlayers();
        senderPlayers.remove(player);
        sender.setPlayers(senderPlayers);

        List<Player> receiverPlayers = receiver.getPlayers();
        receiverPlayers.add(player);
        receiver.setPlayers(receiverPlayers);

        teamService.save(sender);
        teamService.save(receiver);
    }
}
