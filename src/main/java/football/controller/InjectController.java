package football.controller;

import java.math.BigDecimal;
import java.util.List;
import football.model.Player;
import football.model.Team;
import football.service.PlayerService;
import football.service.TeamService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject")
@RequiredArgsConstructor
public class InjectController {
    private final PlayerService playerService;
    private final TeamService teamService;

    @GetMapping
    @ApiOperation(value = "inject test data")
    public String injectData() {
        Player bob = new Player("Bob", 30, 5);
        Player alice = new Player("Alice", 42, 16);
        playerService.save(bob);
        playerService.save(alice);
        teamService.save(new Team("Dream Team", "Italy",
                BigDecimal.valueOf(50000000), 4, List.of(bob)));
        teamService.save(new Team("Old team", "Ukraine",
                BigDecimal.valueOf(85000000), 6, List.of(alice)));
        return "Done!";
    }
}
