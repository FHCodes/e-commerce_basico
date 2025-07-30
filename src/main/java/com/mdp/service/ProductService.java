package com.mdp.service;

import com.mdp.dto.request.ProductRequestDTO;
import com.mdp.dto.response.ProductResponseDTO;
import com.mdp.entity.product.Product;
import com.mdp.exceptions.customExceptions.ProductNotFoundException;
import com.mdp.mapper.ProductMapper;
import com.mdp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductResponseDTO> getAllProductsList() {
        return ProductMapper.toProductDTOList(productRepository.findAll());
    }

    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public ProductResponseDTO getProductDTOById(Long id) {
        return ProductMapper.toProductDTO(productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id)));
    }

    @Transactional
    public ProductResponseDTO registerProduct(ProductRequestDTO productDTO) {
        return ProductMapper.toProductDTO(productRepository.save(new Product(productDTO)));
    }

    @Transactional
    public ProductResponseDTO updateProduct(ProductRequestDTO productDTO) {
        if (productDTO.id() == null) {
            throw new ProductNotFoundException(productDTO.id());
        }

        Product existingProduct = getProductById(productDTO.id());

        if (productDTO.name() != null && !productDTO.name().isBlank()) {
            existingProduct.setName(productDTO.name());
        }
        if (productDTO.description() != null && !productDTO.description().isBlank()) {
            existingProduct.setDescription(productDTO.description());
        }
        if (productDTO.price() != null) {
            existingProduct.setPrice(productDTO.price());
        }
        if (productDTO.stock() != null) {
            existingProduct.setStock(productDTO.stock());
        }

       return ProductMapper.toProductDTO(productRepository.save(existingProduct));
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
