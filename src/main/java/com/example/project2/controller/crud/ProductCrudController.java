package com.example.project2.controller.crud;

import com.example.project2.dto.ProductDto;
import com.example.project2.model.Product;
import com.example.project2.repository.ProductRepository;
import com.example.project2.service.HdfsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductCrudController {
    private final ProductRepository productRepository;
    private final HdfsService hdfsService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ProductDto> createProduct(
            @RequestParam("name") String name,
            @RequestParam("supplier") String supplier,
            @RequestParam("price") Double price,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {

        try{
            String imagePath = null;

            if(imageFile != null && !imageFile.isEmpty()) {
                imagePath = hdfsService.uploadImage(imageFile);
            }

            Product product = new Product();
            product.setName(name);
            product.setSupplier(supplier);
            product.setPrice(price);
            product.setImagePath(imagePath);

            Product savedProduct = productRepository.save(product);

            ProductDto responseDto = new ProductDto(
                    savedProduct.getId(),
                    savedProduct.getName(),
                    savedProduct.getSupplier(),
                    savedProduct.getPrice(),
                    savedProduct.getImagePath()
            );

            return ResponseEntity.ok(responseDto);

        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
