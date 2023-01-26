package id.transferapp.service.operator;

import java.util.List;

import org.springframework.stereotype.Service;

import id.transferapp.entity.Product;
import id.transferapp.entity.SellProduct;

@Service
public interface SellProductService {

    public List<SellProduct> getAllProductSell();

    public SellProduct buyProduct(SellProduct product);

}
