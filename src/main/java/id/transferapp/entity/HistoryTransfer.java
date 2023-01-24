package id.transferapp.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "transferhistory")
public class HistoryTransfer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String rekPengirim;
	String rekPenerima;
	Date tglKirim;
}
