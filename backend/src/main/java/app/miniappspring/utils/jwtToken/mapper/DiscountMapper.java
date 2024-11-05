package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.dto.discount.DiscountCreateDto;
import app.miniappspring.dto.discount.DiscountDto;
import app.miniappspring.entity.Discount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscountMapper {
    DiscountDto toDiscountDto(Discount discount);
    Discount toEntity(DiscountCreateDto discountCreateDto);
}
