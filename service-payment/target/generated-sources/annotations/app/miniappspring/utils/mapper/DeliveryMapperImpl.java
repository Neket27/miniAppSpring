package app.miniappspring.utils.mapper;

import app.miniappspring.dto.delivey.UpdateDeliveryDataUser;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-21T16:17:56+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class DeliveryMapperImpl extends DeliveryMapper {

    @Override
    public DeliveryDataUser toEntity(UpdateDeliveryDataUser updateDeliveryDataUser) {
        if ( updateDeliveryDataUser == null ) {
            return null;
        }

        DeliveryDataUser deliveryDataUser = new DeliveryDataUser();

        deliveryDataUser.setId( updateDeliveryDataUser.getId() );
        deliveryDataUser.setCity( updateDeliveryDataUser.getCity() );
        deliveryDataUser.setAddress( updateDeliveryDataUser.getAddress() );
        deliveryDataUser.setBuildingOfHouse( updateDeliveryDataUser.getBuildingOfHouse() );
        deliveryDataUser.setFlat( updateDeliveryDataUser.getFlat() );

        return deliveryDataUser;
    }

    @Override
    public UpdateDeliveryDataUser toUpdateDto(DeliveryDataUser deliveryDataUser) {
        if ( deliveryDataUser == null ) {
            return null;
        }

        UpdateDeliveryDataUser updateDeliveryDataUser = new UpdateDeliveryDataUser();

        updateDeliveryDataUser.setId( deliveryDataUser.getId() );
        updateDeliveryDataUser.setCity( deliveryDataUser.getCity() );
        updateDeliveryDataUser.setAddress( deliveryDataUser.getAddress() );
        updateDeliveryDataUser.setBuildingOfHouse( deliveryDataUser.getBuildingOfHouse() );
        updateDeliveryDataUser.setFlat( deliveryDataUser.getFlat() );

        return updateDeliveryDataUser;
    }
}
