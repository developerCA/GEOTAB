app.factory("portadaFactory", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/suscripcion");
	
	return{
		
		cantSuscripciones:function(){
        	 return Restangular.allUrl("suscripcion/cantSuscripcionesLatentes").customPUT();
        	
         },
         cantNotaPedido:function(){
        	 return Restangular.allUrl("suscripcion/cantNotaPedido").customPUT();
        	
         },
         cantFacturasPorEnviar:function(){
        	 return Restangular.allUrl("suscripcion/cantFacturasPorEnviar").customPUT();
        	
         },
         cantNotaPedidoEnviadas:function(){
        	 return Restangular.allUrl("suscripcion/cantNotaPedidoEnviadas").customPUT();
        	
         },
         traerProcesos:function(){
        	 return Restangular.allUrl("suscripcion/traerProcesos").getList();	;
        	 },
        traerLosMasVendidos:function(){
            	 return  Restangular.allUrl("suscripcion/traerLosMasVendidos").getList();
            },
        ventasAnuales:function(item){
                	 return  Restangular.allUrl("suscripcion/ventasAnuales").customPUT(item);
                	 }
        
        	 
        
        
	}	
}]);
