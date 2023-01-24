package id.transferapp.service.operator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.transferapp.entity.HistoryTransfer;
import id.transferapp.entity.Rekening;
import id.transferapp.entity.Transfer;
import id.transferapp.repo.HistoryTransactionRepo;
import id.transferapp.repo.RekeningRepo;
import id.transferapp.repo.TransferRepo;
import id.transferapp.service.cs.RekeningService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransferServiceImpl implements TransferService{
	
	@Autowired
	TransferRepo transferRepo;
	
	@Autowired
	RekeningRepo rekeningRepo;
	
	@Autowired
	HistoryTransactionRepo historyTransactionRepo;
	
	@Autowired
	RekeningService rekeningService;
	

	@Override
	public List<Transfer> getAll() {
		// TODO Auto-generated method stub
		return this.transferRepo.findAll();
	}

	@Override
	public void save(Transfer trf) {
		// TODO Auto-generated method stub
		Rekening rekeningPengirim = rekeningService.findByNoRek(trf.getRekPengirim().getNoRek());
		Rekening rekeningPenerima = rekeningService.findByNoRek(trf.getRekPenerima().getNoRek());
		Long idPengirim = rekeningPengirim.getId();
		Long idPenerima = rekeningPenerima.getId();
		Optional<Rekening> pengirim = this.rekeningService.getRekById(idPengirim);
		Optional<Rekening> penerima = this.rekeningService.getRekById(idPenerima);
		Rekening rekPengirim = pengirim.get();
		Rekening rekPenerima = penerima.get();
		String bankAsal = rekPengirim.getBank().getBankname();
		String bankTuju = rekPenerima.getBank().getBankname();
		Transfer transfer = new Transfer();
		HistoryTransfer history = new HistoryTransfer();
		Date tglKirim = Date.valueOf(LocalDate.now());
		transfer.setTglKirim(tglKirim);
		transfer.setAmount(trf.getAmount());
		if(bankAsal.equals(bankTuju)) {
			transfer.setFee(0);
			rekPengirim.setSaldo(rekPengirim.getSaldo()-(transfer.getAmount()));
			transfer.setRekPengirim(rekPengirim);
			transfer.setRekPenerima(rekPenerima);
			rekPenerima.setSaldo(rekPenerima.getSaldo() + (transfer.getAmount()));
			history.setRekPenerima(rekPenerima.getNoRek());
			history.setRekPengirim(rekeningPengirim.getNoRek());
			history.setTglKirim(tglKirim);
			this.historyTransactionRepo.save(history);
			this.rekeningRepo.save(rekPengirim);
			this.rekeningRepo.save(rekPenerima);
			this.transferRepo.save(transfer);
		}
		else if(bankAsal != bankTuju){
			transfer.setFee(6500);
			rekPengirim.setSaldo(rekPengirim.getSaldo() -(transfer.getAmount()+6500));
			transfer.setRekPengirim(rekPengirim);
			transfer.setRekPenerima(rekPenerima);
			rekPenerima.setSaldo(rekPenerima.getSaldo() +(transfer.getAmount()));
			history.setRekPenerima(rekPenerima.getNoRek());
			history.setRekPengirim(rekeningPengirim.getNoRek());
			history.setTglKirim(tglKirim);
			this.historyTransactionRepo.save(history);
			this.rekeningRepo.save(rekPengirim);
			this.rekeningRepo.save(rekPenerima);
			this.transferRepo.save(transfer);
		}
		
	}

	@Override
	public Optional<Transfer> getTransferById(Long Id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
