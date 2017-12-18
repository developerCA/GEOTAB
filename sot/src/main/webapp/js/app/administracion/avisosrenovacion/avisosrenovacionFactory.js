app.factory("avisosFactory", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/aviso");
	
	return{
		
		 avisofechaProceso: function() {
			 return Restangular.allUrl("aviso/avisoFechaProceso").customPUT();
         },
         create: function(item){
             return service.post(item);
         },
         update:function(item){
        	 return item.put();
         },
         eliminar:function(item){
        	 return Restangular.allUrl("aviso/eliminar").customPUT(item);
         },
         fechas:function(item){
        	 return Restangular.allUrl("aviso/porFechas").customPUT(item.id);
         },
		 procesar:function(item){
			 return Restangular.allUrl("aviso/procesar").customPOST(item);
		 },
		 avisos:function(){
			 return Restangular.allUrl("aviso/avisos").getList();
		 },
		 porid:function(item){
			 return Restangular.allUrl("aviso/codigo").customPUT(item);
		 },
		 modificar:function(item){
			 return Restangular.allUrl("aviso/modificar").customPUT(item);
		 }
	}
	
}]);