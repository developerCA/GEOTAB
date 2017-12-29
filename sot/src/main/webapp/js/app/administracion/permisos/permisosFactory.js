app.factory("permisosResource", [
		"Restangular",
		function(Restangular) {

			var service = Restangular.service("/permiso");

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

				traerArbol : function() {
					var lista = Restangular.allUrl(
							"permiso/traeArbol" ).getList();
					return lista;
				},

				getPermiso : function(id) {
					return Restangular.oneUrl('permiso/traePermiso/' + id)
							.get();
				}

			}

		} ]);