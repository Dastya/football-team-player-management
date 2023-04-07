package football.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import football.model.Player;
import football.model.Team;
import football.repository.PlayerRepository;
import football.repository.TeamRepository;
import football.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    @Override
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team get(Long id) {
        return teamRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format("Can't find team by id: %i", id)));
    }

    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }
}
