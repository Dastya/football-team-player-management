package football.model.dto.mapper.impl;

import football.model.Player;
import football.model.dto.mapper.RequestDtoMapper;
import football.model.dto.mapper.ResponseDtoMapper;
import football.model.dto.request.PlayerRequestDto;
import football.model.dto.responce.PlayerResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper implements RequestDtoMapper<PlayerRequestDto, Player>,
        ResponseDtoMapper<PlayerResponseDto, Player> {
    @Override
    public Player toModel(PlayerRequestDto dto) {
        Player player = new Player();
        player.setAge(dto.getAge());
        player.setExperience(dto.getExperience());
        player.setName(dto.getName());
        return player;
    }

    @Override
    public PlayerResponseDto toDto(Player model) {
        PlayerResponseDto responseDto = new PlayerResponseDto();
        responseDto.setId(model.getId());
        responseDto.setAge(model.getAge());
        responseDto.setExperience(model.getExperience());
        responseDto.setName(model.getName());
        return responseDto;
    }
}
