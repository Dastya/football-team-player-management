package football.service.impl;

import football.model.Player;
import football.repository.PlayerRepository;
import football.service.PlayerService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player get(Long id) {
        return playerRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format("Can't find player by id: %i", id)));
    }

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        playerRepository.deleteById(id);
    }
}
