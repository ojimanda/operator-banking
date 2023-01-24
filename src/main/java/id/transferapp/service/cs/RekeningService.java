package id.transferapp.service.cs;

import java.util.List;
import java.util.Optional;

import id.transferapp.entity.BankProvider;
import id.transferapp.entity.Rekening;

public interface RekeningService {
	public List<Rekening> getAll();
	public void save(Rekening rek);
	public void delete(Rekening rek);
	public Rekening findByNoRek(String norek);
	public Optional<Rekening> getRekById(Long Id);

}
