package id.transferapp.service.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.transferapp.entity.BankProvider;
import id.transferapp.repo.BankProviderRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BankServiceImpl implements BankService{
	
	@Autowired
	BankProviderRepo bankProviderRepo;

	@Override
	public List<BankProvider> getAll() {
		// TODO Auto-generated method stub
		return this.bankProviderRepo.findAll();
	}

	@Override
	public void save(BankProvider bank) {
		// TODO Auto-generated method stub
		String bankName = bank.getBankname();
		bank.setBankname(bankName.toUpperCase());
		this.bankProviderRepo.save(bank);
	}

	@Override
	public Optional<BankProvider> getBankById(Long Id) {
		// TODO Auto-generated method stub
		return this.bankProviderRepo.findById(Id);
	}

	@Override
	public void delete(BankProvider bank) {
		// TODO Auto-generated method stub
		this.bankProviderRepo.delete(bank);
	}

	@Override
	public BankProvider getBankByName(String bankname) {
		// TODO Auto-generated method stub
		return this.bankProviderRepo.findByBankname(bankname);
	}

}
