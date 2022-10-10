package dev.patricksilva.crud.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import dev.patricksilva.crud.model.Product;

@Repository
public class ProductRepository_old {

    private List<Product> products = new ArrayList<Product>();
    private Integer lastId = 0;

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(Integer id) {
        return products.stream().filter(product -> product.getId() == id).findFirst();
    }

    public Product addProduct(Product product) {
        lastId++;
        product.setId(lastId);
        products.add(product);

        return product;
    }

    public void delete(Integer id) {
        products.removeIf(product -> product.getId() == id);
    }

    public Product update(Product p) {
        Optional<Product> find = findById(p.getId());

        if (find.isEmpty()) {
            throw new ResourceNotFoundException("This product was not found");
        }

        delete(p.getId());

        products.add(p);

        return null;
    }

}
