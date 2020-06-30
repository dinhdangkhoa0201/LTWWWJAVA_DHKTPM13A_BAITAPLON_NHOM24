function buttonClickPlus(a) {
	var i = document.getElementById('inc'+a).value;
	i++; 
	document.getElementById('inc'+a).value = i;


	var b = document.getElementById('btnPlus'+a);
	b.setAttribute('href', '/www/ShoppingCart/UpdateProduct?iproductid='+a+'&quantity='+i);
}

function buttonClickMinus(a) {
	var i = document.getElementById('inc'+a).value;
	if(i>1) {
		i=i-1;
		document.getElementById('inc'+a).value = i;   
		var b = document.getElementById('btnMinus'+a);
		b.setAttribute('href', '/www/ShoppingCart/UpdateProduct?iproductid='+a+'&quantity='+i);
	}
	else
	{
		RemoveToCart(a);
	}
}
function buttonClickPlusdetail() {
	var i = document.getElementById('inc').value;
	i++; 
	document.getElementById('inc').value = i;

}

function buttonClickMinuskdetail() {
	var i = document.getElementById('inc').value;
	if(i>1) {
		i=i-1;         
	}
	else
		i=1;
	document.getElementById('inc').value = i;   

}
function checkOutPayment(){
	var x = document.getElementById('btnCheckout');
	var y = document.getElementById('productid').value;
	x.setAttribute('href', '/www/Thongtindathang?product_id='+y);
}
function addToCart(a){
	var x = document.getElementById(a);
	var y = document.getElementById('productid').value;
	var quantity= document.getElementById('inc').value;
	x.setAttribute('href', '/www/ShoppingCart/AddModel?product_id='+y+'&quatityby='+quantity);
}

function RemoveToCart(a){
	if(confirm("Ban co chac chan muon xoa?"))
	{
		window.location.href='/www/ShoppingCart/Remove?iproductid='+a;
	}
}