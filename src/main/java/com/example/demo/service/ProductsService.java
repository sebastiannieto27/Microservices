package com.example.demo.service;

import com.example.demo.entity.Products;
import com.example.demo.repository.ProductsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductsService {

    private ProductsRepository productsRepository;

    @Transactional
    public List<Products> findAllProducts(){
        return  productsRepository.findAll();
    }

    @Transactional
    public  Products findByIdProducts(Long id){
        return  productsRepository.findById(id).get();
    }

    @Transactional
    public Products save(Products products){
        return productsRepository.save(products);
    }

    @Transactional
    public void deleteById(Long id){
        Products byIdProducts = productsRepository.findById(id).get();
        productsRepository.delete(byIdProducts);
    }

    @Transactional
    public Products update(Products products, Long id){
        Products byIdProducts = productsRepository.findById(id).get();
        byIdProducts.setName(products.getName());
        byIdProducts.setPrice(products.getPrice());
        byIdProducts.setCreateAt(products.getCreateAt());
        return  productsRepository.save(byIdProducts);
    }


}
