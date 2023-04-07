package football.model.dto.responce;

import lombok.Data;

@Data
public class PlayerResponseDto {
    private Long id;
    private String name;
    private int age;
    private int experience;
}
