package fit.se.main.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "verify_account")
public class VerifyAccount implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "token", unique = true, length = 512)
	private String token;

	@Column(name = "create_on")
	private LocalDateTime createOn; 

	@Column(name = "expire_data_token")
	private LocalDateTime expiredDataToken;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id")
	private Account account;

	public VerifyAccount(Account account) {
		this.account = account;
		createOn = LocalDateTime.now();

	}
	
	public VerifyAccount() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getCreateOn() {
		return createOn;
	}

	public void setCreateOn(LocalDateTime createOn) {
		this.createOn = createOn;
	}

	public LocalDateTime getExpiredDataToken() {
		return expiredDataToken;
	}

	public void setExpiredDataToken(LocalDateTime expiredDataToken) {
		this.expiredDataToken = expiredDataToken;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setExpiredDataToken(int minutes) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, minutes);
		this.expiredDataToken = now.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

	}

	public boolean isExpired() {
		return LocalDateTime.now().isAfter(expiredDataToken);
	}
}
