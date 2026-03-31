package com.example.b5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.b5.model.Product;
import com.example.b5.repository.CategoryRepository;
import com.example.b5.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Page<Product> findPaginated(int pageNo, int pageSize, String keyword, String sortField, String sortDir, Integer categoryId) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        
        boolean hasKeyword = (keyword != null && !keyword.isEmpty());
        boolean hasCategory = (categoryId != null && categoryId > 0);

        if (hasKeyword && hasCategory) {
            return productRepository.findByNameContainingAndCategoryId(keyword, categoryId, pageable);
        } else if (hasKeyword) {
            return productRepository.findByNameContaining(keyword, pageable);
        } else if (hasCategory) {
            return productRepository.findByCategoryId(categoryId, pageable);
        }
        
        return productRepository.findAll(pageable);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContaining(keyword, Pageable.unpaged()).getContent();
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
