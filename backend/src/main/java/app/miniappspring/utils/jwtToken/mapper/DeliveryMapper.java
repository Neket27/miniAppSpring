package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.dto.delivey.UpdateDeliveryDataUser;
import app.miniappspring.entity.DeliveryDataUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class DeliveryMapper {
   public abstract DeliveryDataUser toEntity(UpdateDeliveryDataUser updateDeliveryDataUser);

    public DeliveryDataUser updateData(DeliveryDataUser deliveryDataUser, UpdateDeliveryDataUser updateDeliveryDataUser) {
        deliveryDataUser.setCity(updateDeliveryDataUser.getCity());
        deliveryDataUser.setAddress(updateDeliveryDataUser.getAddress());
        deliveryDataUser.setBuildingOfHouse(updateDeliveryDataUser.getBuildingOfHouse());
        deliveryDataUser.setFlat(updateDeliveryDataUser.getFlat());
        return deliveryDataUser;
    }

    public abstract UpdateDeliveryDataUser toUpdateDto(DeliveryDataUser deliveryDataUser);
}
