package fit.se.main.dto;

import java.util.ArrayList;
import java.util.List;

public class SaleOrderHeaderCreateDTO {
	private int accountId;
	private double totalMoney;
	private String note;
	private List<ProductCreateDTO> products = new ArrayList<ProductCreateDTO>();

	public int getAccountId() {
		return accountId;
	}


	public double getTotalMoney() {
		return totalMoney;
	}


	public String getNote() {
		return note;
	}


	public List<ProductCreateDTO> getProducts() {
		return products;
	}


	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}


	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public void setProducts(List<ProductCreateDTO> products) {
		this.products = products;
	}


	@Override
	public String toString() {
		return "SaleOrderHeaderCreateDTO [accountId=" + accountId + ", totalMoney=" + totalMoney + ", note=" + note
				+ ", products=" + products + "]";
	}
	
}
