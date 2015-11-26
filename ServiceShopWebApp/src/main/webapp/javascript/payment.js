$("#paymentButton").click(function() {
	if(sessionStorage.getItem("idClient") != null){
		  $.soap({
		    url: 'http://192.168.1.72:9763/services/ServiceShop/',
		    namespaceURL: 'http://servicesshop.alma.fr'
		});

		$.soap({
		  data:{client: sessionStorage.getItem("idClient") },
		  method:'commandProduct',
		  success:function(response) {
		  	alert("Payment Successfull !");
		  	sessionStorage.removeItem("idClient");
		  	setTimeout(function(){
		  		location.replace("index.jsp");
			},2000); 
	
		  	
		  }
		});
	}else{
		location.replace("index.jsp");
	}
});