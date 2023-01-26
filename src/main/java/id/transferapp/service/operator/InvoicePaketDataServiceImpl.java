package id.transferapp.service.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.transferapp.entity.InvoicepaketData;
import id.transferapp.entity.Rekening;
import id.transferapp.repo.InvoicePaketDataRepo;
import id.transferapp.repo.RekeningRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class InvoicePaketDataServiceImpl implements InvoicePaketDataService {

	@Autowired
	InvoicePaketDataRepo invoicePaketDataRepo;

	@Autowired
	RekeningRepo rekeningRepo;

	@Override
	public void save(InvoicepaketData invoicePaket) {
		// TODO Auto-generated method stub
		Rekening rekening = rekeningRepo.findByNoRek(invoicePaket.getNoRek());
		rekening.setSaldo(rekening.getSaldo() - invoicePaket.getPrice());
		rekeningRepo.saveAndFlush(rekening);
		this.invoicePaketDataRepo.save(invoicePaket);
	}

}
