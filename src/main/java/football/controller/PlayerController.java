package football.controller;

import java.util.List;
import football.model.Player;
import football.model.dto.mapper.RequestDtoMapper;
import football.model.dto.mapper.ResponseDtoMapper;
import football.model.dto.request.PlayerRequestDto;
import football.model.dto.responce.PlayerResponseDto;
import football.service.PlayerService;
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
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;
    private final RequestDtoMapper<PlayerRequestDto, Player> requestDtoMapper;
    private final ResponseDtoMapper<PlayerResponseDto, Player> responseDtoMapper;

    @PostMapping
    public PlayerResponseDto add(@RequestBody PlayerRequestDto requestDto) {
        Player player = playerService.save(requestDtoMapper.toModel(requestDto));
        return responseDtoMapper.toDto(player);
    }

    @GetMapping("/{id}")
    public PlayerResponseDto get(@PathVariable Long id) {
        Player player = playerService.get(id);
        return responseDtoMapper.toDto(player);
    }

    @PutMapping("/{id}")
    public PlayerResponseDto update(@RequestBody PlayerRequestDto requestDto,
                                    @PathVariable Long id) {
        Player player = requestDtoMapper.toModel(requestDto);
        player.setId(id);
        return responseDtoMapper.toDto(playerService.save(player));
    }

    @GetMapping
    public List<PlayerResponseDto> getAll() {
        return playerService.getAll()
                .stream()
                .map(responseDtoMapper::toDto)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        playerService.delete(id);
    }
}
