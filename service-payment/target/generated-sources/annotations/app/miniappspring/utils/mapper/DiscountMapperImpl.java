package app.miniappspring.utils.mapper;

import app.miniappspring.dto.discount.DiscountCreateDto;
import app.miniappspring.dto.discount.DiscountDto;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-21T16:17:56+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class DiscountMapperImpl implements DiscountMapper {

    @Override
    public DiscountDto toDiscountDto(Discount discount) {
        if ( discount == null ) {
            return null;
        }

        DiscountDto discountDto = new DiscountDto();

        discountDto.setId( discount.getId() );
        discountDto.setName( discount.getName() );
        discountDto.setAmount( discount.getAmount() );
        discountDto.setCity( discount.getCity() );

        return discountDto;
    }

    @Override
    public Discount toEntity(DiscountCreateDto discountCreateDto) {
        if ( discountCreateDto == null ) {
            return null;
        }

        Discount discount = new Discount();

        discount.setName( discountCreateDto.getName() );
        discount.setAmount( discountCreateDto.getAmount() );
        discount.setCity( discountCreateDto.getCity() );

        return discount;
    }
}
