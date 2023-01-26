package id.transferapp.service.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.transferapp.entity.InvoiceTiketPesawat;
import id.transferapp.entity.Rekening;
import id.transferapp.repo.InvoiceTiketPesawatRepo;
import id.transferapp.repo.RekeningRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class InvoiceTiketPesawatServiceImpl implements InvoiceTiketPesawatService{
	
	@Autowired
	InvoiceTiketPesawatRepo invoiceTiketPesawatRepo;
	
	@Autowired
	RekeningRepo rekeningRepo;

	@Override
	public void save(InvoiceTiketPesawat invoiceTiketPesawat) {
		// TODO Auto-generated method stub
		Rekening rekening = rekeningRepo.findByNoRek(invoiceTiketPesawat.getNoRek());
		rekening.setSaldo(rekening.getSaldo() - invoiceTiketPesawat.getHarga());
		rekeningRepo.saveAndFlush(rekening);
		this.invoiceTiketPesawatRepo.save(invoiceTiketPesawat);
	}

}
