package app.miniappspring.service.impl;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.arguments.UpdateProductArgument;
import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.dto.product.ProductDetailDto;
import app.miniappspring.dto.product.UpdateProductDto;
import app.miniappspring.dto.product.category.CategoryDto;
import app.miniappspring.dto.product.category.SearchProductDto;
import app.miniappspring.dto.product.category.NumberOfProductsInThisCategory;
import app.miniappspring.entity.CategoryItem;
import app.miniappspring.entity.CharacteristicProduct;
import app.miniappspring.entity.Image;
import app.miniappspring.entity.Product;
import app.miniappspring.exception.ErrorException;
import app.miniappspring.repository.CategoryItemRepo;
import app.miniappspring.repository.ProductRepo;
import app.miniappspring.service.ImageProductService;
import app.miniappspring.service.ProductService;
import app.miniappspring.utils.jwtToken.mapper.CharacteristicMapper;
import app.miniappspring.utils.jwtToken.mapper.ProductArgumentMapper;
import app.miniappspring.utils.jwtToken.mapper.ProductMapper;
import app.miniappspring.utils.jwtToken.mapper.SearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final SearchMapper searchMapper;
    private final CategoryItemRepo categoryItemRepo;
    private final ProductMapper productMapper;
    private final CharacteristicMapper characteristicMapper;
//    private final CategoryMapper categoryMapper;
    private final ProductArgumentMapper productArgumentMapper;
    private final ImageProductService imageProductService;

    @Override
    @Transactional
    public ProductCardDto getProductCard(Long id) {
        Product product=productRepo.findById(id).orElseThrow(()->new ErrorException("В базе данных нет карточки товара с id= "+id));
        ProductCardDto productCardDto = productMapper.toProductCardDto(product);


//        productCardDto.setDiscount();
        return productCardDto;
    }


    @Override
    @Transactional
    public Product findProduct(Long id){
        return productRepo.findById(id).orElseThrow(()->new ErrorException("В базе данных нет карточки товара с id= "+id));
    }

    @Override
    @Transactional
    public List<ProductCardDto> getListCardProduct(){
       List<Product>products=productRepo.findAll();
       if(products.isEmpty())
           return new ArrayList<>();

        return products.stream().map(productMapper::toProductCardDto).toList();
    }

    @Override
    @Transactional
    public ProductDetailDto getProductDetailDto(Long id){
        Product product =findProduct(id);
       ProductDetailDto productDetailDto = productMapper.toProductDetailDto(product);
       //productDetailDto.setCategory(product.getCategoryProduct().getCategory().getRussianValue());
       productDetailDto.setCategory(product.getCategoryItem().getName());
        return  productDetailDto;
    }

    @Override
    @Transactional
    public List<ProductCardDto> addProduct(CreateProductArgument createProductArgument){
        Product product=productMapper.toProduct(createProductArgument);
        CharacteristicProduct characteristic=  productArgumentMapper.toCharacteristicProduct(createProductArgument);
//        CategoryProduct categoryProduct =categoryMapper.toCategoryProduct(createProductArgument);
        CategoryItem categoryItem = categoryItemRepo.findByName(createProductArgument.getCategory()).orElseThrow(()->new RuntimeException("Категория не найдена"));
        product.setCategoryItem(categoryItem);
        categoryItem.getProductList().add(product);
        product.setCharacteristicProduct(characteristic);
        Product productFromBD = productRepo.save(product);
        List<Image> images =  imageProductService.saveAllAndGetListImage(createProductArgument.getCreateImageDtoList(),productFromBD);
       productFromBD.setImageList(images);
       productRepo.save(productFromBD);
       // product.setImageList(imageMapper.toImageList(createProductArgument.getCreateImageDtoList()));

       return getListCardProduct();
       // return new ArrayList<>();
    }

    @Override
    @Transactional
    public void addPhotoCardProduct(Long idCardPhoto, MultipartFile photoCardProduct) throws IOException {
        Product product = findProduct(idCardPhoto);
        if(product.getImageList()==null)
            product.setImageList(new ArrayList<>());

        Image image = Image.builder()
                .bytes(photoCardProduct.getBytes())
                .build();

        List<Image>images=product.getImageList();
        images.add(image);
        product.setImageList(images);
        productRepo.save(product);
    }



    @Override
    @Transactional
    public List<ProductCardDto> getProductsByCategory(CategoryDto categoryDto){
        List<Product>products;
        if(categoryDto.getCategoryProduct().equals("Все категории"))
            products = productRepo.findAll();
        else
            products= productRepo.findByCategoryItem_NameContainingIgnoreCase(categoryDto.getCategoryProduct()).orElse(Collections.emptyList());

        return products.stream().map(product -> productMapper.toProductCardDto(product)).toList();
    }

    @Override
    @Transactional
    public List<SearchProductDto> searchProductByName(String searchText){
        List<Product> productDtoList =  productRepo.findByNameContainsIgnoreCase(searchText).orElse(null);
            if(productDtoList!=null) {
               List<SearchProductDto> searchProductDtoList = productDtoList.stream().map(product -> searchMapper.toSearchProductDto(product)).toList();
                return searchProductDtoList;
            }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public UpdateProductDto updateProduct(UpdateProductArgument updateProductArgument) {
        Product product = productRepo.findById(updateProductArgument.getId()).orElseThrow(()->new ErrorException("Продукт с id= "+updateProductArgument.getId()+" не найден"));

        UpdateProductDto updateProductDto = productMapper.toUpdateProductDto(updateProductArgument,product.getCharacteristicProduct());
        product = productMapper.updateProduct(product,updateProductDto);
        List<Image> imageList = imageProductService.saveAllAndGetListImage2(updateProductDto.getUpdateImageDtoList(),product);
        product.getImageList().clear();
        product.getImageList().addAll(imageList);
        CharacteristicProduct characteristicProduct =characteristicMapper.toCharacteristicProduct(updateProductDto.getCharacteristic());
        product.getCharacteristicProduct().setProducerCountry(characteristicProduct.getProducerCountry());
        product.getCharacteristicProduct().setSellerWarranty(characteristicProduct.getSellerWarranty());
        CategoryItem categoryItem = categoryItemRepo.findByName(updateProductArgument.getCategory()).orElseThrow(()->new RuntimeException("Категория не найдена"));
        product.setCategoryItem(categoryItem);
        categoryItemRepo.save(categoryItem);
        productRepo.save(product);
        return updateProductDto;
    }

    @Override
    @Transactional
    public void deleteProduct(long productId) {
        productRepo.deleteById(productId);
    }

    @Override
    @Transactional
    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    @Transactional
    public void changeRating(float evaluation, Long idProduct, int countFeedback) {
        Product product = productRepo.findById(idProduct).orElseThrow(()->new RuntimeException("Продукт не найтен"));
        product.setRating((product.getRating()+evaluation)/countFeedback);
    }

    @Override
    @Transactional
    public List<ProductCardDto> getProductsWithStock(String city) {
        List<Product> products = productRepo.findByDiscountList_City(city).orElse(Collections.emptyList());
        if(products.isEmpty())
            return new ArrayList<>();

        return products.stream().map(product -> productMapper.toProductCardDto(product)).toList();
    }


}
