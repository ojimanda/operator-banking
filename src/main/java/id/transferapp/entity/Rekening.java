package id.transferapp.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Rekening {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank @NotNull @NotEmpty @Column(unique = true, nullable = false)
	private String noRek;
	private double saldo;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "rek_nasabah",
			joinColumns = @JoinColumn(
					name="nasabah_id", referencedColumnName = "id"
					),
			inverseJoinColumns = @JoinColumn(
					name="rekening_id", referencedColumnName = "id"
					)
			)
	private Nasabah nasabah;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "rek_bank",
			joinColumns = @JoinColumn(
					name="rekening_id", referencedColumnName = "id"
					),
			inverseJoinColumns = @JoinColumn(
					name="bank_id", referencedColumnName = "id"
					)
			)
	private BankProvider bank;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "rekPengirim")
	private List<Transfer> rek_pengirim;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "rekPenerima")
	private List<Transfer> rek_penerima;
	
//	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(
//			name="transPenerima",
//			joinColumns = @JoinColumn(
//					name="transfer_id", referencedColumnName = "id"
//					),
//			inverseJoinColumns = @JoinColumn(
//					name="penerima_id", referencedColumnName = "id"
//					)
//			)
//	private Transfer rekPenerima;
}
