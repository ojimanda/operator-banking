package id.transferapp.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.transferapp.entity.Product;
import id.transferapp.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo productRepo;

    @Override
    public List<Product> getAllProduct() {
        // TODO Auto-generated method stub
        return productRepo.findAll();
    }

    @Override
    public Product geProductById(Long id) {
        // TODO Auto-generated method stub
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {
        // TODO Auto-generated method stub
        return productRepo.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        // TODO Auto-generated method stub
        productRepo.deleteById(id);
    }

}
