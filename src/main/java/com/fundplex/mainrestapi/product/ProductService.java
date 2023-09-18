package com.fundplex.mainrestapi.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fundplex.mainrestapi.exceptions.ResourceNotFoundException;
import com.fundplex.mainrestapi.response.ProductResponse;

@Service
public class ProductService {

    @Autowired
    public ProductRepo productRepo;

    public void createProduct(Product product) {
        this.productRepo.save(product);
    }

    public void updateProduct(Product product, Long id) {
        this.productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "Id", id));
        product.setId(id);
        this.productRepo.save(product);
    }

    public void deleteProduct(Long id) {
        this.productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "Id", id));
        this.productRepo.deleteById(id);
    }

    public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {

        Sort sort = null;

        if (sortDirection.equalsIgnoreCase("ascending")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Product> pageProduct = this.productRepo.findAll(pageable);
        List<Product> allProducts = pageProduct.getContent();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(allProducts);
        productResponse.setTotalCount(productRepo.count());
        productResponse.setPageNumber(pageProduct.getNumber());
        productResponse.setPageSize(pageProduct.getSize());
        productResponse.setTotalElements(pageProduct.getNumberOfElements());
        productResponse.setTotalPages(pageProduct.getTotalPages());
        productResponse.setLastPage(pageProduct.isLast());

        return productResponse;
    }

    public Optional<Product> getProductById(Long id) {
        return this.productRepo.findById(id);
    }
}
