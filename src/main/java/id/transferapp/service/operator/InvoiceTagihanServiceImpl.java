package id.transferapp.service.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.transferapp.entity.InvoiceTagihan;
import id.transferapp.repo.InvoiceTagihanRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class InvoiceTagihanServiceImpl implements InvoiceTagihanService{

	@Autowired
	InvoiceTagihanRepo invoiceTagihanRepo;
	
	@Override
	public void save(InvoiceTagihan invoiceTagihan) {
		// TODO Auto-generated method stub
		this.invoiceTagihanRepo.save(invoiceTagihan);
	}

}
