package id.transferapp.service.cs;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.transferapp.entity.BankProvider;
import id.transferapp.entity.Rekening;
import id.transferapp.repo.BankProviderRepo;
import id.transferapp.repo.NasabahRepo;
import id.transferapp.repo.RekeningRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RekeningServiceImpl implements RekeningService{
	
	@Autowired
	RekeningRepo rekeningRepo;
	
	@Autowired
	BankProviderRepo bankProviderRepo;
	
	@Autowired
	NasabahRepo nasabahRepo;

	@Override
	public List<Rekening> getAll() {
		// TODO Auto-generated method stub
		return this.rekeningRepo.findAll();
	}

	@Override
	public void save(Rekening rek) {
		// TODO Auto-generated method stub
		BankProvider bank = this.bankProviderRepo.findByBankname(rek.getBank().getBankname());
		
		Rekening rekening = new Rekening();
		rekening.setNoRek(rek.getNoRek());
		rekening.setBank(bank);
		rekening.setSaldo(1000000);
		rekening.setNasabah(rek.getNasabah());
		this.rekeningRepo.save(rekening);
	}

	@Override
	public void delete(Rekening rek) {
		// TODO Auto-generated method stub
		this.rekeningRepo.delete(rek);
	}

	@Override
	public Optional<Rekening> getRekById(Long Id) {
		// TODO Auto-generated method stub
		return this.rekeningRepo.findById(Id);
	}

	@Override
	public Rekening findByNoRek(String rekening) {
		// TODO Auto-generated method stub
		return this.rekeningRepo.findByNoRek(rekening);
	}

}
