package id.transferapp.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "transaction")
public class Transfer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
//	@NotBlank @NotNull @NotEmpty
	private Date tglKirim;
	
	private double amount;
	
	private double fee;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "rekPengirim_id", referencedColumnName = "id")
	private Rekening rekPengirim;
//	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "rekPenerima_id", referencedColumnName = "id")
	private Rekening rekPenerima;
	
}
