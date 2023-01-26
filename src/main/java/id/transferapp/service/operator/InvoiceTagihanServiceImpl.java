package id.transferapp.service.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.transferapp.entity.InvoiceTagihan;
import id.transferapp.entity.Rekening;
import id.transferapp.repo.InvoiceTagihanRepo;
import id.transferapp.repo.RekeningRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class InvoiceTagihanServiceImpl implements InvoiceTagihanService{

	@Autowired
	InvoiceTagihanRepo invoiceTagihanRepo;
	
	@Autowired
	RekeningRepo rekeningRepo;
	
	@Override
	public void save(InvoiceTagihan invoiceTagihan) {
		// TODO Auto-generated method stub
		Rekening rekening = rekeningRepo.findByNoRek(invoiceTagihan.getNoRek());
		rekening.setSaldo(rekening.getSaldo() - invoiceTagihan.getHarga());
		rekeningRepo.saveAndFlush(rekening);
		this.invoiceTagihanRepo.save(invoiceTagihan);
	}

}
