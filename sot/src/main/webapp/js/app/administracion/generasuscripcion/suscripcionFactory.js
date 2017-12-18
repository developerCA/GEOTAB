app.factory("suscripcionFactory", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/notaPedido");
	
	return{
		
		
         eliminar:function(item){
        	 return Restangular.allUrl("notaPedido/eliminar").customPUT(item);
         },
         cancelar:function(item){
        	 return Restangular.allUrl("notaPedido/cancelar").customPUT(item);
         },
         aprobar:function(item){
        	 return Restangular.allUrl("notaPedido/aprobar").customPUT(item);
         },         
         listAprobadas:function(item){
        	 return Restangular.allUrl("notaPedido/aprobadas").getList();
         }         
	}	
}]);