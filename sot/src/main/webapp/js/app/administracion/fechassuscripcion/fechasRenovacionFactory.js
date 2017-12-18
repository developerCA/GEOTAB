app.factory("fechasFactory", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/fechas");
	
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
        	 return Restangular.allUrl("fechas/eliminar").customPUT(item);
         },
         activar:function(item){
        	 return Restangular.allUrl("fechas/activar").customPUT(item);
         },
         finalizar:function(item){
        	 return Restangular.allUrl("fechas/finalizar").customPUT(item);
         },
         
		 ultimaFechaRenovacion:function(){
			 return Restangular.allUrl("fechas/ultimaFechaRenovacion").getList();
		 },	
		 fechaSugerencia:function(){
			return  Restangular.allUrl("fechas/fechaSugerida").customPUT();
		 },
		 fechaValidacion:function(item){
			 return Restangular.allUrl("fechas/validarFechas").customPUT(item);
		 },
		 fechasProcesadas:function(){
			return Restangular.allUrl("fechas/fechasProcesadas").getList();
		 },
		 
		 fechasActivasFinalizadas:function(){
				return Restangular.allUrl("fechas/fechasActivas").getList();
		 },
	}
	
}]);