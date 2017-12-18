app.factory("sucursalesFactory", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/sucursal");
	
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
         desactivar:function(item){
        	 return Restangular.allUrl("sucursal/desactivar").customPUT(item);
         },
         traerSucursal:function(){
        	 return Restangular.allUrl("sucursal/usuario").customPUT();
         },
         listPorRegion:function(idRegion){
             return Restangular.allUrl("sucursal/porRegion").getList({idRegion:idRegion});
         }
	}
	
}]);