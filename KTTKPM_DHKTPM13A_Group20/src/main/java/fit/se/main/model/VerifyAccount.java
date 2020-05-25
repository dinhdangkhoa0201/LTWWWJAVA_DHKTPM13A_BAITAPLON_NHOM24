package fit.se.main.model;

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

@Entity
public class VerifyAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "token", columnDefinition = "text", unique = true)
	private String token;

	@Column
	private LocalDateTime createOn; 

	@Column
	private LocalDateTime expiredDataToken;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "khachhang_id")
	private KhachHang khachHang;

	public VerifyAccount(KhachHang khachHang) {
		this.khachHang = khachHang;
		createOn = LocalDateTime.now();

	}
	
	public VerifyAccount() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
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