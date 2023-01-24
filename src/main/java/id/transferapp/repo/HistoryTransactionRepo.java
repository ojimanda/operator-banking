package id.transferapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import id.transferapp.entity.HistoryTransfer;

public interface HistoryTransactionRepo extends JpaRepository<HistoryTransfer, Long>{

}
