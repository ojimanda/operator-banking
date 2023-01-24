package id.transferapp.service.admin;

import java.util.List;
import java.util.Optional;

import id.transferapp.entity.BankProvider;

public interface BankService {

	public List<BankProvider> getAll();
	public void save(BankProvider bank);
	public BankProvider getBankByName(String bankname);
	public void delete(BankProvider bank);
	public Optional<BankProvider> getBankById(Long Id);
}
