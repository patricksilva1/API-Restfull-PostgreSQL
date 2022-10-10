package dev.patricksilva.crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.ExceptionMessage;
import org.springframework.stereotype.Service;

import dev.patricksilva.crud.model.Product;
import dev.patricksilva.crud.repository.ProductRepository_old;

@Service
public class ProductService {
    @Autowired
    private ProductRepository_old productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        return productRepository.addProduct(product);
    }

    public void delete(Integer id) {
        productRepository.delete(id);
    }

    public Product update(Integer id, Product product) {
        product.setId(id);

        return productRepository.update(product);
    }

}
