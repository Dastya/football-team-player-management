package football.model.dto.mapper.impl;

import football.model.Player;
import football.model.Team;
import football.model.dto.mapper.RequestDtoMapper;
import football.model.dto.mapper.ResponseDtoMapper;
import football.model.dto.request.TeamRequestDto;
import football.model.dto.responce.TeamResponseDto;
import football.service.PlayerService;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper implements ResponseDtoMapper<TeamResponseDto, Team>,
        RequestDtoMapper<TeamRequestDto, Team> {
    private final PlayerService playerService;

    public TeamMapper(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public TeamResponseDto toDto(Team model) {
        TeamResponseDto responseDto = new TeamResponseDto();
        responseDto.setId(model.getId());
        responseDto.setName(model.getName());
        responseDto.setCountry(model.getCountry());
        responseDto.setMoneyAccount(model.getMoneyAccount());
        responseDto.setCommissionInPercent(model.getCommissionInPercent());
        responseDto.setPlayerIds(model.getPlayers()
                .stream()
                .map(Player::getId)
                .toList());
        return responseDto;
    }

    @Override
    public Team toModel(TeamRequestDto dto) {
        Team team = new Team();
        team.setName(dto.getName());
        team.setCountry(dto.getCountry());
        team.setMoneyAccount(dto.getMoneyAccount());
        team.setCommissionInPercent(dto.getCommissionInPercent());
        team.setPlayers(dto.getPlayerIds()
                .stream()
                .map(playerService::get)
                .toList());
        return team;
    }
}
