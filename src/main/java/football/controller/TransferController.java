package football.controller;

import football.service.Transferable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {
    private final Transferable transferable;

    @GetMapping
    public String transferPLayerFromOneTeamToAnother(@RequestParam Long senderId,
                                                     @RequestParam Long receiverId,
                                                     @RequestParam Long playerId) {
        return transferable.transfer(senderId, receiverId, playerId);
    }
}
