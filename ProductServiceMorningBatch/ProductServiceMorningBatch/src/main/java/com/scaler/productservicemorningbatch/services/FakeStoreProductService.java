package com.scaler.productservicemorningbatch.services;

import com.scaler.productservicemorningbatch.dtos.FakeStoreProductDto;
import com.scaler.productservicemorningbatch.exceptions.InvalidProductIdException;
import com.scaler.productservicemorningbatch.modles.Category;
import com.scaler.productservicemorningbatch.modles.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class FakeStoreProductService implements ProductService{

   private RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

   public Product convertFakeStoreDtoToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product=new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());

        Category category=new Category();
        category.setTitle(fakeStoreProductDto.getTitle());
        product.setCategory(category);
        return  product;
   }
    @Override
    public Product getProductById(Long id) throws InvalidProductIdException {
        FakeStoreProductDto fakeStoreProductDto=
                restTemplate.getForObject("https://fakestoreapi.com/products/"+id,FakeStoreProductDto.class);//FakeStoreProductDto.class ==this is the response type
        if(fakeStoreProductDto==null){
           throw new InvalidProductIdException(id,"Invalid productID passed");

        }
       return convertFakeStoreDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
FakeStoreProductDto[] fakeStoreProductDtos=restTemplate.getForObject(
        "https://fakestoreapi.com/products/", FakeStoreProductDto[].class);
//if(fakeStoreProductDtos==null){
//    return null;
//}

      List<Product> products=new ArrayList<>();
      for(FakeStoreProductDto fakeStoreProductDto:fakeStoreProductDtos){
          products.add(convertFakeStoreDtoToProduct(fakeStoreProductDto));
      }
      return products;
    }

    @Override
    public Product updateProduct(Long id) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id,Product product) {
        //PUT method
        //low level method
        //replace the product with the given input product
        //and return the updaete product in the output

        //homework and pending(1 48)hrs
        //Convert the input in the right request parameter
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class,
                restTemplate.getMessageConverters());
        FakeStoreProductDto fakeStoreProductDto=restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);

        if(fakeStoreProductDto==null){
            return null;
        }
        return convertFakeStoreDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product createProduct() {
        return null;
    }

    @Override
    public void deleteProduct() {

    }
}
