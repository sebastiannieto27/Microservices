package com.example.demo.rest;

import com.example.demo.entity.Products;
import com.example.demo.service.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ProductsResource.URI_PREFIX)
@AllArgsConstructor
public class ProductsResource {

    static final String URI_PREFIX = "/api/products/" ;
    private static final String ENTITY_NAME = "Products" ;
    private ProductsService productsService;

    @GetMapping("/getAll")
    public ResponseEntity <List<Products>>getAll(){
        List<Products> productsList = productsService.findAllProducts();
        return ResponseEntity.ok().body(productsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getById(@PathVariable Long id){
        Products products = productsService.findByIdProducts(id);
        return ResponseEntity.ok().body(products);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Products> save(@RequestBody Products products){
        products = productsService.save(products);
        return ResponseEntity.created(URI.create(URI_PREFIX + products.getId()))
                .body(products);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public  ResponseEntity<Products> update(@RequestBody Products products, @PathVariable Long id){
        Products productsResult = productsService.update(products,id);
        return ResponseEntity.created(URI.create(URI_PREFIX + productsResult.getId()))
                .body(products);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        productsService.deleteById(id);
    }
}
