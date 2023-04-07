package football.controller;

import java.util.List;
import football.model.Team;
import football.model.dto.mapper.RequestDtoMapper;
import football.model.dto.mapper.ResponseDtoMapper;
import football.model.dto.request.TeamRequestDto;
import football.model.dto.responce.PlayerResponseDto;
import football.model.dto.responce.TeamResponseDto;
import football.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;
    private final RequestDtoMapper<TeamRequestDto, Team> requestDtoMapper;
    private final ResponseDtoMapper<TeamResponseDto, Team> responseDtoMapper;

    @PostMapping
    public TeamResponseDto add(@RequestBody TeamRequestDto requestDto) {
        Team team = teamService.save(requestDtoMapper.toModel(requestDto));
        return responseDtoMapper.toDto(team);
    }

    @GetMapping("/{id}")
    public TeamResponseDto get(@PathVariable Long id) {
        Team team = teamService.get(id);
        return responseDtoMapper.toDto(team);
    }

    @PutMapping("/{id}")
    public TeamResponseDto update(@RequestBody TeamRequestDto requestDto,
                                  @PathVariable Long id) {
        Team team = requestDtoMapper.toModel(requestDto);
        team.setId(id);
        return responseDtoMapper.toDto(teamService.save(team));
    }

    @GetMapping
    public List<TeamResponseDto> getAll() {
        return teamService.getAll()
                .stream()
                .map(responseDtoMapper::toDto)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teamService.delete(id);
    }
}
