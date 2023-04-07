package football.model.dto.mapper;

public interface ResponseDtoMapper<D, M> {
    D toDto(M model);
}
