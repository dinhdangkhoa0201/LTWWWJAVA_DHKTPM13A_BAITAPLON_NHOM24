 function buttonClicPlus(a) {
    	  	var i = document.getElementById('inc'+a).value;
		
			var y = document.getElementById('productid'+a).value;
          	i++; 
            document.getElementById('inc'+a).value = i;
			var b = document.getElementById('btnPlus'+a);
			b.setAttribute('href', '/www/ShoppingCart/UpdateProduct?iproductid='+y+'&quantity='+i);
       }
       function buttonClickMinus(a) {
    	   var i = document.getElementById('inc'+a).value;
			var y = document.getElementById('productid'+a).value;
            if(i>1) {
              i=i-1;
            }
            else
            	i=1;
            document.getElementById('inc'+a).value = i;   
			var b = document.getElementById('btnMinus'+a);
			b.setAttribute('href', '/www/ShoppingCart/UpdateProduct?iproductid='+y+'&quantity='+i);
       }
 function buttonClicPlusdetail() {
    	   var i = document.getElementById('inc').value;
          	i++; 
            document.getElementById('inc').value = i;
       }
       function buttonClicMinuskdetail() {
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
	if(confirm("Bạn có chắc chắn muốn xóa")){
		var x = document.getElementById('btnRemoveCart'+a);
    x.setAttribute('href', '/www/ShoppingCart/Remove?iproductid='+a);
	}    
   }


	
