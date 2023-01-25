package id.transferapp.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import id.transferapp.entity.Product;

@Service
public interface ProductService {

    public List<Product> getAllProduct();

    public Product geProductById(Long id);

    public Product saveProduct(Product product);

    public void deleteProduct(Long id);
}
