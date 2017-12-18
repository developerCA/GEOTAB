app.factory("bodegasFactory", [ "Restangular", function(Restangular) {

	var service = Restangular.service("/bodega");

	return {

		list : function() {
			return service.getList();
		},
		listBodegas : function() {
			return Restangular.allUrl("bodega/lista").getList();
		},
		listbodegasKoynor : function() {
			return Restangular.allUrl("bodega/listaBodega").getList();
		},
		createBodega : function(item) {
			return service.post(item);
		},
		actualiza : function(item) {
			return Restangular.allUrl("bodega/actualizar").customPUT(item);
		}
		,
        eliminar:function(item){
       	 return Restangular.allUrl("bodega/eliminar").customPUT(item);
        }
	}
} ]);