package id.transferapp.service.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.transferapp.entity.InvoicepaketData;
import id.transferapp.repo.InvoicePaketDataRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class InvoicePaketDataServiceImpl implements InvoicePaketDataService{
	
	@Autowired
	InvoicePaketDataRepo invoicePaketDataRepo;

	@Override
	public void save(InvoicepaketData invoicePaket) {
		// TODO Auto-generated method stub
		this.invoicePaketDataRepo.save(invoicePaket);
	}

	
}
