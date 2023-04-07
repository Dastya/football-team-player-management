package football.service;

import java.util.List;
import football.model.Player;

public interface PlayerService {
    Player save(Player player);
    Player get(Long id);
    List<Player> getAll();
    void delete(Long id);
}
