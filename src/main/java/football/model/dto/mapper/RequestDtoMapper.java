package football.model.dto.mapper;

public interface RequestDtoMapper<D, M> {
    M toModel(D dto);
}
