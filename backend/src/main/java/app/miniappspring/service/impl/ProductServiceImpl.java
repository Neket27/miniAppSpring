package app.miniappspring.service.impl;

import app.miniappspring.arguments.CreateProductArgument;
import app.miniappspring.arguments.UpdateProductArgument;
import app.miniappspring.dto.image.ImageDto;
import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.dto.product.ProductDetailDto;
import app.miniappspring.dto.product.UpdateProductDto;
import app.miniappspring.dto.product.category.CategoryDto;
import app.miniappspring.dto.product.category.CategoryProductDto;
import app.miniappspring.dto.product.category.NumberOfProductsInThisCategory;
import app.miniappspring.entity.*;
import app.miniappspring.exception.ErrorException;
import app.miniappspring.repository.CategoryRepo;
import app.miniappspring.repository.ProductRepo;
import app.miniappspring.service.ImageProductService;
import app.miniappspring.service.ProductService;
import app.miniappspring.utils.jwtToken.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final ProductMapper productMapper;
    private final CharacteristicMapper characteristicMapper;
    private final CategoryMapper categoryMapper;
    private final ProductArgumentMapper productArgumentMapper;
    private final ImageProductService imageProductService;

    @Override
    @Transactional
    public ProductCardDto getProductCard(Long id) {
        Product product=productRepo.findById(id).orElseThrow(()->new ErrorException("В базе данных нет карточки товара с id= "+id));
        return productMapper.toProductCardDto(product);
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
       productDetailDto.setCategory(product.getCategoryProduct().getCategory().getRussianValue());
        return  productDetailDto;
    }

    @Override
    @Transactional
    public List<ProductCardDto> addProduct(CreateProductArgument createProductArgument){
        Product product=productMapper.toProduct(createProductArgument);
        CharacteristicProduct characteristic=  productArgumentMapper.toCharacteristicProduct(createProductArgument);
        CategoryProduct categoryProduct =categoryMapper.toCategoryProduct(createProductArgument);
        product.setCategoryProduct(categoryProduct);
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
    public NumberOfProductsInThisCategory getCategories(){
        Map<String,Integer> countProductThisCategory = Arrays.stream(app.miniappspring.entity.Category.values())
                .collect(Collectors.toMap(category -> category.getRussianValue(),category->categoryRepo.countByCategory(category)));
        return new NumberOfProductsInThisCategory(countProductThisCategory);
    }

    @Override
    @Transactional
    public List<ProductCardDto> getProductsByCategory(CategoryDto category){
        List<Product>products;
        if(category.getCategoryProduct().equals("Все категории"))
            products = productRepo.findAll();
        else
            products= productRepo.findByCategoryProduct_StringValueCategoryContainingIgnoreCase(category.getCategoryProduct()).orElse(Collections.emptyList());

        return products.stream().map(product -> productMapper.toProductCardDto(product)).toList();
    }

    @Override
    @Transactional
    public List<CategoryProductDto> searchProductByCategory(String category){

        if (!category.isEmpty()) {
            List<Product> productList = productRepo.findByNameContainsIgnoreCase(category).orElse(Collections.emptyList());

            Set<CategoryProduct> categoryProductSet = categoryRepo.findAllByStringValueCategoryContainingIgnoreCase(category).orElse(Collections.emptySet());

            CategoryProduct product1 = CategoryProduct.builder()
                    .id(1L)
                    .category(Category.TV)
                    .build();
            CategoryProduct product2 = CategoryProduct.builder()
                    .id(2L)
                    .category(Category.COMPUTERS)
                    .build();
            CategoryProduct product3 = CategoryProduct.builder()
                    .id(1L)
                    .category(Category.TV)
                    .build();


            Set<CategoryProduct> productSet = new HashSet<>();
            productSet.add(product1);
            productSet.add(product2);
            productSet.add(product3);

            System.out.println("Размер исходного множества: " + productSet.size());

            for (CategoryProduct product : productSet)
                System.out.println(product.getCategory().getRussianValue() + ": " + product.getSubcategory());


            Set<CategoryProduct>categorySet=new HashSet<>(categoryProductSet);
            List<CategoryProductDto> categoryList = categoryMapper.toListCategoryProductDto(categorySet);
            categoryList.addAll(categoryMapper.toListCategoryProductDto(productList));
            return categoryList;
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public UpdateProductDto updateProduct(UpdateProductArgument updateProductArgument) {
        Product product = productRepo.findById(updateProductArgument.getId()).orElseThrow(()->new ErrorException("Продукт с id= "+updateProductArgument.getId()+" не найден"));

        UpdateProductDto updateProductDto = productMapper.toUpdateProductDto(updateProductArgument,product.getCharacteristicProduct());

        product = productMapper.updateProduct(product,updateProductDto);

        CharacteristicProduct characteristicProduct =characteristicMapper.toCharacteristicProduct(updateProductDto.getCharacteristic());
        product.getCharacteristicProduct().setProducerCountry(characteristicProduct.getProducerCountry());
        product.getCharacteristicProduct().setSellerWarranty(characteristicProduct.getSellerWarranty());
        categoryRepo.save(product.getCategoryProduct());

        List<Image> imageList= imageProductService.saveAllAndGetListImage2(updateProductDto.getUpdateImageDtoList(), product);
        product.setImageList(imageList);
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
