package football.model.dto.responce;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class TeamResponseDto {
    private Long id;
    private String name;
    private String country;
    private List<Long> playerIds;
    private BigDecimal moneyAccount;
    private int commissionInPercent;
}
