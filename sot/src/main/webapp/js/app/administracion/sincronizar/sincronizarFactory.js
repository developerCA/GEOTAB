app.factory("sincronizarFactory", ["Restangular",
	function (Restangular) {

	var service = Restangular.service("ruta");

	return{
		list: function() {
			return service.getList();
        },

        sincronizarGrupos:function(items){
       	 return Restangular.allUrl("geotab/sincronizarGrupos").customPOST(items);
        },
        
        sincronizarFechasLozalizaciones:function(){
          	 return Restangular.allUrl("geotab/fechasDispositivos").customPOST();
        },
        
        sincronizarLozalizaciones:function(item){
         	 return Restangular.allUrl("geotab/procesarDatos").customPOST(item);
       },
       
    
       sincronizarTablero:function(){
         	 return Restangular.allUrl("geotab/procesarTablero").customPOST();
       },
     
        sincronizarDispositivos:function(items){
      	 return Restangular.allUrl("geotab/sincronizarDispositivos").customPOST(items);
        },
        sincronizarZonas:function(itemDto){
        	
         	 return Restangular.allUrl("geotab/sincronizarZonas").customPOST(itemDto);
           },
        
      
	}
}]);