package football.model;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "teams")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String country;
    @NonNull
    private BigDecimal moneyAccount;
    @NonNull
    private int commissionInPercent;
    @NonNull
    @OneToMany(fetch = FetchType.LAZY/*, mappedBy = "id"*/)
    @JoinColumn(name = "team_id")
    private List<Player> players;
}
