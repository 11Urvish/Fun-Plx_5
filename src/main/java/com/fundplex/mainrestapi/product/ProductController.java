package com.fundplex.mainrestapi.product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fundplex.mainrestapi.exceptions.ApiResponse;
import com.fundplex.mainrestapi.response.ProductResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/product")

public class ProductController {

    @Autowired
    public ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody Product product) {
        this.productService.createProduct(product);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Role Added Successfully!!", product, true),
                HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody Product product) {
        this.productService.updateProduct(product, product.id);
        return new ResponseEntity<>(new ApiResponse("Product Updated Successfully!!!", product, true), HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id") Long id) {
        this.productService.deleteProduct(id);
        return new ResponseEntity<>(new ApiResponse("Product Deleted Successfully!!!", null, true), HttpStatus.OK);
    }

    @PostMapping("/findAll")
    public ResponseEntity<ApiResponse> getAllProducts(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = "ascending", required = false) String sortDirection) {

        ProductResponse productResponse = this.productService.getAllProducts(pageNumber, pageSize, sortBy,
                sortDirection);
        return new ResponseEntity<>(new ApiResponse("List of Products", productResponse, true), HttpStatus.OK);
    }

    @PostMapping("/findById/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable("id") Long id) {
        Optional<Product> product = this.productService.getProductById(id);
        return new ResponseEntity<>(new ApiResponse("Product Found with this Id", product, true), HttpStatus.OK);
    }
}
