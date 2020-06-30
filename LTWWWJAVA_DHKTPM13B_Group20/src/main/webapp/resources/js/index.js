   function addToCart(a){
    var x = document.getElementById(a);
	var y = document.getElementById('productid').value;
	var quantity= document.getElementById('inc').value;
    x.setAttribute('href', '/www/ShoppingCart/AddModel/LisProduct?product_id='+y+'&quatityby='+1);
   }

 function buttonClicPlus(a) {
    	  	var i = document.getElementById('inc'+a).value;
          	i++; 
            document.getElementById('inc'+a).value = i;		
       }
       function buttonClickMinus(a) {
    	   var i = document.getElementById('inc'+a).value;
            if(i>1) {
              i=i-1;
            }
            else
            	i=1;
            document.getElementById('inc'+a).value = i;   		
       }

function choiseclick(a, m, n){
			var x = document.querySelectorAll(m);
			for (i = 0; i < x.length; ++i) {
				x[i].classList.remove(n);
			}
			document.getElementById(a).classList.add(n);
		}
		function activeclick(a){
			if(a == "btnchitiet")	
			{
				var x = document.getElementById(a);
				var y = document.getElementById('btnthugon');
				x.classList.add('is-active');
				y.classList.remove('is-active');
			}else{
				var x = document.getElementById(a);
				var y = document.getElementById('btnchitiet');
				x.classList.add('is-active');
				y.classList.remove('is-active');
			}
		}
		function clickhienthi(a, b){
			var x = document.getElementById(a);
			var y = document.getElementById(b);
			if(x.getAttribute('aria-label')=='Click to collapse'){
				x.classList.remove('XLw0c');
				y.classList.add('_2Zwwb');
				x.setAttribute('aria-label', 'Click to expand');
			}else{
				x.classList.add('XLw0c');
				y.classList.remove('_2Zwwb');
				x.setAttribute('aria-label', 'Click to collapse');
			}
		}
		function clickfilter(a, b){
			var x = document.getElementById(a);
			var y = document.getElementById(b);
			if(y.getAttribute('aria-label')=='Collapse filter'){
				x.classList.remove('_3riWF');
				x.classList.add('_1brOV');
				y.classList.remove('_3Gfh4');
				y.classList.add('_1ccGe');
				y.setAttribute('aria-label', 'Expand filter');
			}else{
				x.classList.add('_3riWF');
				x.classList.remove('_1brOV');
				y.classList.add('_3Gfh4');
				y.classList.remove('_1ccGe');
				y.setAttribute('aria-label', 'Collapse filter');
			}
		}
		function hienthi(x){
			x.classList.add('p4v9H');
		}
		function an(x){
			x.classList.remove('p4v9H');
		}
		function moveleft(){
			var x = document.getElementById('imagesection');
			var y = x.getElementsByTagName('img');

			if(y[0].getAttribute('style')=='left: 0%;'){
				var per = -400;
				for(var i=0; i<y.length; i++){
					y[i].setAttribute('style', 'left: '+ String(per) +'%;');
					per += 100;
				}
			}else{
				console.log(y[0].getAttribute('style').slice(6, 10));	
				var per = parseInt(y[0].getAttribute('style').slice(6, 10)) + 100;
				for(var i=0; i<y.length; i++){
					y[i].setAttribute('style', 'left: '+ String(per) +'%;');
					per += 100;
				}
			}
			
		}
		function moveright(){
			var x = document.getElementById('imagesection');
			var y = x.getElementsByTagName('img');

			if(y[0].getAttribute('style')=='left: -400%;'){
				var per = 0;
				for(var i=0; i<y.length; i++){
					y[i].setAttribute('style', 'left: '+ String(per) +'%;');
					per -= 100;
				}
			}else{
				var per = parseInt(y[0].getAttribute('style').slice(6, 10)) - 100;
				for(var i=0; i<y.length; i++){
					y[i].setAttribute('style', 'left: '+ String(per) +'%;');
					per += 100;
				}
			}
			
		}
		function searhByPrice(){
			var x = document.getElementById('Minimum_price').value;
			var y = document.getElementById('Maximum_price').value;
			var z = document.getElementById('btnPrice');
			z.setAttribute('href', '/price?pricemin=' + x +'&pricemax='+ y);
		}