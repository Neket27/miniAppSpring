package app.miniappspring.service.impl.product;

import app.miniappspring.dto.jwtToken.JwtAuthenticationResponse;
import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.dto.product.ProductDetailDto;
import app.miniappspring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class AIProductService {

    private final ProductService productService;

    @Bean
    @Description("Описание функции на русском:" +
            "Функция для получения всех продуктов магазина. Она выдаёт информацию о каждом продукте, представленном в магазине, в формате ProductDetailDto. Информация включает ключевые данные о продукте, такие как:\n" +
            "ID продукта: уникальный идентификатор (id).\n" +
            "Категория: название категории, к которой принадлежит продукт (category).\n" +
            "Название: имя продукта (name).\n" +
            "Стоимость: цена продукта (cost).\n" +
            "Описание: краткое текстовое описание продукта (description).\n" +
            "Бренд: марка или производитель продукта (brand).\n" +
            "Заметки: дополнительные пометки или примечания (note).\n" +
            "Доступность: информация о том, доступен ли продукт для заказа (available).\n" +
            "Остаток на складе: количество продукта в наличии (stock).\n" +
            "Характеристики продукта: структурированные данные о характеристиках (characteristicProductDto).\n" +
            "Подробности: текстовые или структурированные подробности о продукте (detail).\n" +
            "Отзывы: список отзывов о продукте (feedbackDtoList).\n" +
            "Изображения: список связанных изображений продукта (imageDtoList)." +
            "Description of the function in English" +
            "A function for getting all the store's products. It provides information about each product presented in the store in the Productdetailco format. The information includes key product information such as:\n" +
            "Product ID: A unique identifier (id).\n" +
            "Category: The name of the category to which the product belongs (category).\n" +
            "Name: Product name.\n" +
            "Cost: the price of the product (cost).\n" +
            "Description: a short text description of the product.\n" +
            "Brand: the brand or manufacturer of the product (brand).\n" +
            "Notes: additional notes or notes.\n" +
            "Availability: Information about whether a product is available for order (available).\n" +
            "Stock balance: the quantity of the product in stock.\n" +
            "Product Characteristics: Structured data about characteristics (characteristicProductDto).\n" +
            "Details: text or structured product details (detail).\n" +
            "Reviews: A list of product reviews (feedbackDtoList).\n" +
            "Images: A list of related product images (imageDtoList).")
    public Supplier<List<ProductDetailDto>> getListProduct() {
        List<ProductDetailDto> productDetailDtoList = productService.getListProductDetail();
        productDetailDtoList.forEach(productDetailDto -> productDetailDto.getImageDtoList().clear());
        return () -> productDetailDtoList;
    }


    public List<ProductCardDto> showProductsUsingNeuralNetwork(List<Long> idProductList) {
        List<ProductCardDto> productCardDtoList = idProductList.stream().map(id -> productService.getProductCard(id)).toList();
        return productCardDtoList;
    }
}


