package football.service;

import java.util.List;
import football.model.Team;

public interface TeamService {
    Team save(Team team);
    Team get(Long id);
    List<Team> getAll();
    void delete(Long id);
}
