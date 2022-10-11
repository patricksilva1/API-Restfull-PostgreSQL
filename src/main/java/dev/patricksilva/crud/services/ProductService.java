package dev.patricksilva.crud.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.patricksilva.crud.model.Product;
import dev.patricksilva.crud.model.exception.ResourceNotFoundException;
import dev.patricksilva.crud.repository.ProductRepository;
import dev.patricksilva.crud.shared.ProductDTO;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(product -> new ModelMapper()
                .map(product, ProductDTO.class))
                .collect(Collectors.toList());

    }

    public Optional<ProductDTO> findById(Integer id) {
        // Getting optional of product by id
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new ResourceNotFoundException(" Id:" + id + "not found!");
        }
        ProductDTO dto = new ModelMapper().map(product.get(), ProductDTO.class);
        return Optional.of(dto);
    }

    public ProductDTO addProduct(ProductDTO productDto) {
        productDto.setId(null);

        ModelMapper mapper = new ModelMapper();

        Product product = mapper.map(productDto, Product.class);

        product = productRepository.save(product);

        productDto.setId(product.getId());

        return productDto;
    }

    public void delete(Integer id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Could not delete this Product with id: " + id + " product does not exist");
        }
        productRepository.deleteById(id);

    }

    public ProductDTO update(Integer id, ProductDTO productDto) {
        productDto.setId(id);

        ModelMapper mapper = new ModelMapper();

        Product product = mapper.map(productDto, Product.class);

        productRepository.save(product);

        return productDto;

    }

}
