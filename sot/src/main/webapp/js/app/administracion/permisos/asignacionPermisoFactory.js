app.factory("asignacioPermisoResource", [
		"Restangular",
		function(Restangular) {

			var service = Restangular.service("/permisoPerfilEmpresa");

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

				traerArbol : function(idProducto) {
					var lista = Restangular.allUrl(
							"permisoPerfilEmpresa/traeArbol/" + idProducto)
							.getList();
					return lista;
				},

				traerArbolPermisos : function(idPermisoPerfil) {
					var lista = Restangular.allUrl(
							"permisoPerfilEmpresa/traeArbolPermisoPerfilProducto/"
									+ idPermisoPerfil).getList();
					return lista;
				},

				traerPerfiles : function(idProducto) {
					var lista = Restangular.allUrl(
							"permisoPerfilEmpresa/traePerfiles/" + idProducto)
							.getList();
					return lista;
				},

				eliminarAsignacion : function(item) {
					return Restangular.allUrl("permisoPerfilEmpresa/eliminarAsignacion/" ).customPUT(item);
				}

			}

		} ]);