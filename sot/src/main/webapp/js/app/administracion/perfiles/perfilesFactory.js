app.factory("perfilesFactory", [ "Restangular", function(Restangular) {

	var service = Restangular.service("/perfil");

	return {

		list : function() {
			return service.getList();
		},

		create : function(item) {
			return service.post(item);
		},

		update : function(item) {
			return item.put();
		},

		desactivar : function(item) {
			return Restangular.allUrl("perfil/desactivar").customPUT(item);
		}

	}

} ]);