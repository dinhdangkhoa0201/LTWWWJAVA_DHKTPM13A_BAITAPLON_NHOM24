package fit.se.main.session;

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

	public void addProduct(CartLineInfo cartLineInfo, int quantity) {
		int i=0;
		boolean flash = true; 
		for (CartLineInfo cartLineInfo1 : cartLineItem) {
			if(cartLineInfo.getIproductid()==cartLineInfo1.getIproductid()&&flash==true) {
				System.out.println("cart line id   :"+cartLineInfo.getIproductid());
				System.out.println("cart line id 1 :"+cartLineInfo1.getIproductid());
				int quantitytemp = cartLineInfo1.getiQuantityinCart();
				//temp=cartLineInfo.getiQuantityinCart();
				System.out.println("quantity       :"+quantity);
				System.out.println("quantity temp  :"+quantitytemp);
				//	cartLineInfo.setiQuantityinCart(quantity+quantitytemp);
				System.out.println("cartline info  :"+cartLineInfo);
				if((quantity%2==0 && quantitytemp==0) ||(quantity%2!=0 && quantitytemp==0)) {
					cartLineInfo.setiQuantityinCart((quantity+quantitytemp));
				}

				else if((quantity%2==0 )) {
					quantity = (quantity/2);
					cartLineInfo.setiQuantityinCart(quantity+quantitytemp);
				}
				else if(quantity%2!=0 ) {
					int value = 0;
					if(quantity ==1) {
						value=2;
					}
					else {
						value = quantity/2 +2;
						System.out.println("Value     :" + value);
					}

					quantity =quantity+1;
					quantitytemp = quantitytemp+1;
					System.out.println("quantity      :"+quantity);
					System.out.println("quantitytemp       :"+quantitytemp);
					if(quantitytemp%2!=0) {
						value=value+1;
					}
					int qu = quantity+quantitytemp -value;
					System.out.println("Tong la   : " +qu);
					cartLineInfo.setiQuantityinCart(qu);
				}
				cartLineItem.set(i, cartLineInfo);
				System.out.println("cartline item  :"+cartLineItem);
				flash=false;
				break;
			}
			i++;
		}
		if(flash) {
			cartLineItem.add(cartLineInfo);
			cartLineInfo.setiQuantityinCart(0);
			cartLineItem.set(i, cartLineInfo);
			System.out.println("co chay vao day ko ta");
			calculateOrderDetail();
			flash=false;
		}

	}
	public void checkQuantity() {

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
