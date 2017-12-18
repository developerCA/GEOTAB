app.factory("recibidosFactory", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/recibidos");
	
	return{
		
		 list: function() {
             return service.getList();
         },
         create: function(item){
             return service.post(item);
         },
         update:function(item){
        	 return item.put();
         },
         eliminar:function(item){
        	 return Restangular.allUrl("recibidos/eliminar").customPUT(item);
         },
         fechas:function(){
        	 return Restangular.allUrl("recibidos/porFechas").getList();
         },
		 procesar:function(item){
			 return Restangular.allUrl("recibidos/procesar").customPOST(item);
		 },
		 suscripciones:function(item){
			 return Restangular.allUrl("recibidos/suscripciones").customPUT(item);
		 },
		 analizar:function(item){
			 return Restangular.allUrl("recibidos/analizar").customPUT(item);
		 },
	}
	
}]);