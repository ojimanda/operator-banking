package id.transferapp.service.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.transferapp.entity.InvoiceTiketPesawat;
import id.transferapp.repo.InvoiceTiketPesawatRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class InvoiceTiketPesawatServiceImpl implements InvoiceTiketPesawatService{
	
	@Autowired
	InvoiceTiketPesawatRepo invoiceTiketPesawatRepo;

	@Override
	public void save(InvoiceTiketPesawat invoiceTiketPesawat) {
		// TODO Auto-generated method stub
		this.invoiceTiketPesawatRepo.save(invoiceTiketPesawat);
	}

}
