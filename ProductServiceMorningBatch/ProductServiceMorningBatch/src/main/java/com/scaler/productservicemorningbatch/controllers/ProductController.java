package com.scaler.productservicemorningbatch.controllers;

import com.scaler.productservicemorningbatch.exceptions.InvalidProductIdException;
import com.scaler.productservicemorningbatch.exceptions.ProductControllerSpecificException;
import com.scaler.productservicemorningbatch.modles.Product;
import com.scaler.productservicemorningbatch.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    //localehost:8080/products/id
    @GetMapping("/{id}")
   public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws InvalidProductIdException {
       Product product=productService.getProductById(id);
       return new ResponseEntity<>(product, HttpStatus.FORBIDDEN);
       // return new Product();//controller should not be responsible to handle the exceptions.
        //even service will not handle any exception
   }
   @GetMapping
    public List<Product> getAllProducts(){

       // return new ArrayList<>();
       return productService.getAllProducts();
    }

    //partial update
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id,@RequestBody Product product){

        return new Product();
    }
    //replace a product
    @PutMapping("/{id}")
   public Product replaceProduct(@PathVariable("id") Long id,@RequestBody Product product){

      //  return  new Product();
        return productService.replaceProduct(id,product);
    }
    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return new Product();
    }
    @DeleteMapping("/{id}")
   public void deleteProduct(@PathVariable("id") Long id){
        return;
   }

   @ExceptionHandler(ProductControllerSpecificException.class)
   public ResponseEntity<Void> handleProductControllerSpecificException(){

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   }
}
