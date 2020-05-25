package fit.se.main.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "khachhang")
public class KhachHang implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = 	GenerationType.AUTO)
	@Column(name = "makh", nullable = false)
	private Long khachhangId;

	@Column(name = "hoten", nullable = false)
	private String hoTen;

	@Column(name = "ngaysinh")
	private LocalDate ngaySinh;

	@Column(name = "gioitinh")
	private String gioiTinh;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "sodienthoai", nullable = false)
	private String soDT;

	@Column(name = "matkhau", nullable = false)
	private String matKhau;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "khachhang_role", joinColumns = {
			@JoinColumn(name = "khachhang_id")},
			inverseJoinColumns = 
			@JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "khachHang", cascade = CascadeType.ALL)
	private List<VerifyAccount> verificationTokens;
	
	private boolean isEnabled;


	public KhachHang(Long maKH, String hoTen, LocalDate ngaySinh, String gioiTinh, String email, String soDT,
			String matKhau, List<Role> khachHang_Roles, boolean isEnabled) {
		this.khachhangId = maKH;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.email = email;
		this.soDT = soDT;
		this.matKhau = matKhau;
		this.isEnabled = isEnabled;
		khachHang_Roles = new ArrayList<Role>();
	}

	public KhachHang(long maKH, String hoTen, LocalDate ngaySinh, String gioiTinh, String email, String soDT) {
		this.khachhangId = maKH;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.email = email;
		this.soDT = soDT;
	}

	public KhachHang(String hoTen, String email, String soDT, String matKhau) {
		this.hoTen = hoTen;
		this.email = email;
		this.soDT = soDT;
		this.matKhau = matKhau;
	}

	public KhachHang() {
	}
	
	public Long getKhachhangId() {
		return khachhangId;
	}

	public void setKhachhangId(Long khachhangId) {
		this.khachhangId = khachhangId;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<VerifyAccount> getVerificationTokens() {
		return verificationTokens;
	}

	public void setVerificationTokens(List<VerifyAccount> verificationTokens) {
		this.verificationTokens = verificationTokens;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSoDT() {
		return soDT;
	}

	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	public Set<Role> addRole(Role role){
		if(this.roles == null) {
			this.roles = new HashSet<Role>();
		}
		roles.add(role);
		return roles;
	}

}
