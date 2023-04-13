package football.service;

import football.model.Player;
import java.util.List;

public interface PlayerService {
    Player save(Player player);

    Player get(Long id);

    List<Player> getAll();

    void delete(Long id);
}
