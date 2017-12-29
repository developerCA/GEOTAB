app.controller('permisosCtrl', function($scope, $timeout, permisosResource,
		cooperativaFactory, toaster) {

	treedata_avm = [ {
		"label" : "Cargando...",
		data : {
			description : "Procesando elementos..."
		},
		id : 3
	} ]
	$scope.my_data = treedata_avm;

	var idPermPadre;
	var isHijo = false;
	$scope.edicion = false;

	$scope.armarArbol = function() {
		permisosResource.traerArbol().then(function(r) {

			treedata_avm = r;
			$scope.my_data = treedata_avm;
			$scope.edicion = false;

		})
	};

	$scope.crearPermisoPadre = function() {
		$scope.nuevo = true;
		$scope.permisoPadre = null;
		$scope.cancelarPermiso();
	};
	$scope.cancelarPermisoPadre = function() {
		$scope.nuevo = false;
		$scope.armarArbol();
	};
	$scope.cancelarPermiso = function() {
		$scope.edicion = false;
		$scope.armarArbol();
	};

	var apple_selected, tree, treedata_avm, treedata_geography;
	$scope.nuevo = false;
	$scope.my_tree = tree = {};
	$scope.permiso = {};
	$scope.permisoPadre = {};

	$scope.my_tree_handler = function(branch) {

		$scope.edicion = true;
		if (branch.id != null) {
			permisosResource.getPermiso(branch.id).then(function(r) {
				$scope.permiso = r;
			})
		} else {
			$scope.permiso = {};
		}

	};
	apple_selected = function(branch) {
		return $scope.output = "APPLE! : " + branch.label;
	};

	iniciar = function() {
		cooperativaFactory.list().then(function(r) {
			$scope.productos = r;
			
			$scope.idProducto = $scope.productos[0].id;
			$scope.armarArbol($scope.productos[0].id);
		})
	};

	iniciar();

	$scope.guardarPermisoPadre = function() {
		$scope.permisoPadre.idEmpresa = $scope.idProducto;
		item = $scope.permisoPadre;

		permisosResource.create($scope.permisoPadre).then(
				function(r) {
					$scope.nuevo = false;
					$scope.status = !r.estado;
					$scope.cancelarPermiso();
					if (r.estado == true) {
						toaster.pop("success", "Permisos",
								"Registro guardado satisfactoriamente");
						$scope.nuevo = false;
						$scope.msg = "Guardó satisfactoriamente";
					} else {
						$scope.msg = "Error: " + r.mensaje;
						toaster.pop("danger", "Permisos", r.mensaje);
					}

				});
	}

	$scope.guardarPermiso = function() {
		var item;

		if ($scope.permisoPadre != null && $scope.permisoPadre != undefined) {
			$scope.permisoPadre.idEmpresa = $scope.idProducto;
			item = $scope.permisoPadre;
		}
		if ($scope.permiso != null && $scope.permiso != undefined) {
			$scope.permiso.idEmpresa = $scope.idProducto;
			$scope.permiso.permisos = null;
			item = $scope.permiso;
		}

		if (isHijo) {
			if (idPermPadre != null && idPermPadre != undefined) {
				$scope.permiso.idPermisoPadre = idPermPadre;
				item.idPermisoPadre = idPermPadre;
			}
		}

		permisosResource.create($scope.permiso).then(
				function(r) {
					$scope.status = !r.estado;
					if (r.estado == true) {
						toaster.pop("success", "Permisos",
								"Registro guardado satisfactoriamente");
						$scope.nuevo = false;
						$scope.msg = "Guardó satisfactoriamente";
					} else {
						$scope.msg = "Error: " + r.mensaje;
						toaster.pop("danger", "Permisos", r.mensaje);
					}
					$scope.nuevo = false;
					$scope.cancelarPermiso();
					isHijo = false;
					idPermPadre = null;
					$scope.permiso = null;
				});

	};

	$scope.try_async_load = function() {
		$scope.my_data = [];
		$scope.doing_async = true;
		return $timeout(function() {
			if (Math.random() < 0.5) {
				$scope.my_data = treedata_avm;
			} else {
				$scope.my_data = treedata_avm;
			}
			$scope.doing_async = false;
			return tree.expand_all();
		}, 1000);
	};
	return $scope.try_adding_a_branch = function() {
		var b;
		b = tree.get_selected_branch();
		idPermPadre = b.id;
		isHijo = true;
		return tree.add_branch(b, {
			label : 'Nuevo Permiso',
			id : null,
			children : null
		});
	};

});