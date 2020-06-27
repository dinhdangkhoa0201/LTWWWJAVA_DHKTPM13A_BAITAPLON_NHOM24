package fit.se.main.model;

import java.util.ArrayList;
import java.util.List;

public class CartInfo {
	private double total;
	private ArrayList<CartLineInfo> cartLineItem = new ArrayList<CartLineInfo>();

	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public ArrayList<CartLineInfo> getCartLineItem() {
		return cartLineItem;
	}
	public void setCartLineItem(ArrayList<CartLineInfo> cartLineItem) {
		this.cartLineItem = cartLineItem;
	}


	public int getLineItemCount() {
		return cartLineItem.size();
	}

	public void addProduct(CartLineInfo cartLineInfo) {
		boolean flash = true;
		for (CartLineInfo cartLineInfo1 : cartLineItem) {
			if(cartLineInfo.getIproductid()==cartLineInfo1.getIproductid()) {
				flash=false;
				break;
			}
		}
		if(flash) {
			cartLineItem.add(cartLineInfo);
			calculateOrderDetail();
		}

	}
	public void updateProduct(CartLineInfo cartLineInfo) {
		int i=0;
		for (CartLineInfo cartLineInfo1 : cartLineItem ) {

			if(cartLineInfo.getIproductid()==cartLineInfo1.getIproductid()) {
				cartLineItem.set(i,cartLineInfo);
				calculateOrderDetail();
				break;
			}
			else
				i++;
		}


	}

	public CartLineInfo getCartLineInfo(int iproductid) {
		CartLineInfo cartLineInfo =null;
		for (CartLineInfo cartLineInfo1 : cartLineItem) {
			if(cartLineInfo1.getIproductid()==iproductid) {
				cartLineInfo=cartLineInfo1;
				//				cartLineInfo.setbImage(cartLineInfo1.getbImage());
				//				cartLineInfo.setCategory(cartLineInfo1.getCategory());
				//				cartLineInfo.setDbSellingPrice(cartLineInfo1.getDbSellingPrice());
				//				cartLineInfo.setIproductid(cartLineInfo1.getIproductid());
				//				cartLineInfo.setiQuantityinCart(cartLineInfo1.getiQuantityinCart());
				//				cartLineInfo.setStrProductName(cartLineInfo1.getStrProductName());
				//				cartLineInfo.setSupplier(cartLineInfo1.getSupplier());
				//				cartLineInfo.setTotalInCat(cartLineInfo1.getTotalInCat());
				break;
			}

		}
		return cartLineInfo;
	}

	public void deleteCartItem(int iproductid) {
		boolean flash = true;
		CartLineInfo cartLineInfo =null;
		for (CartLineInfo cartLineInfo1 : cartLineItem) {
			if(cartLineInfo1.getIproductid()==iproductid) {
				cartLineInfo = cartLineInfo1;
				flash=false;
				break;
			}

		}
		if(flash==false) {
			try {
				cartLineItem.remove(cartLineInfo) ;
				calculateOrderDetail();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	protected void calculateOrderDetail() {
		double total= 0;
		for(CartLineInfo cartLineInfo : cartLineItem) {
			total =total+ cartLineInfo.getTotalInCat();
			System.out.println("tong dây là: "+total);
		}
		setTotal(total);
	}
	@Override
	public String toString() {
		return "CartInfo [total=" + total + ", cartLineItem=" + cartLineItem + "]";
	}


}
