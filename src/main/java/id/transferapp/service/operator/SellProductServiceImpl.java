package id.transferapp.service.operator;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.transferapp.entity.Rekening;
import id.transferapp.entity.SellProduct;
import id.transferapp.repo.RekeningRepo;
import id.transferapp.repo.SellProductRepo;

@Service
public class SellProductServiceImpl implements SellProductService {

    @Autowired
    SellProductRepo sellProductRepo;

    @Autowired
    RekeningRepo rekeningRepo;

    @Override
    public List<SellProduct> getAllProductSell() {
        // TODO Auto-generated method stub
        return sellProductRepo.findAll();
    }

    @Override
    public SellProduct buyProduct(SellProduct product) {
        // TODO Auto-generated method stub
        Date date = new Date();
        product.setTanggalBeli(date);
        double total = product.getPrice() * product.getQty();
        Rekening rekening = rekeningRepo.findByNoRek(product.getNoRek());
        rekening.setSaldo(rekening.getSaldo() - total);
        rekeningRepo.saveAndFlush(rekening);
        sellProductRepo.save(product);
        return product;
    }

}
