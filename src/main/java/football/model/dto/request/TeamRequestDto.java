package football.model.dto.request;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class TeamRequestDto {
    private String name;
    private String country;
    private List<Long> playerIds;
    private BigDecimal moneyAccount;
    private int commissionInPercent;
}
