package football.service;

import football.model.Team;
import java.util.List;

public interface TeamService {
    Team save(Team team);

    Team get(Long id);

    List<Team> getAll();

    void delete(Long id);
}
