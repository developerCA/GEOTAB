app.controller('asignacionPermisosCtrl', function($scope, $timeout,
		asignacioPermisoResource, toaster) {

	treedata_avm = [ {
		"label" : "Cargando...",
		data : {
			description : "Procesando elementos..."
		},
		id : 3
	} ]

	treedata_avm_2 = [ {
		"label" : "Cargando Arbol Permiso Perfil...",
		data : {
			description : "Procesando elementos perfil..."
		},
		id : 3
	} ]

	$scope.my_data = treedata_avm;
	$scope.my_data_2 = treedata_avm_2;

	var idPermPadre;
	$scope.edicion = false;

	$scope.armarArbol = function() {
		asignacioPermisoResource.traerArbol($scope.idProducto).then(
				function(r) {

					treedata_avm = r;
					$scope.my_data = treedata_avm;
					$scope.edicion = false;
				})
	};  

	$scope.armarArbol2 = function() {
		asignacioPermisoResource.traerArbolPermisos($scope.idPerfilProducto)
				.then(function(r) {

					treedata_avm_2 = r;
					$scope.my_data_2 = treedata_avm_2;
					// try_async_load()
				})
	};

	var apple_selected, tree, treedata_avm, treedata_geography;
	var apple_selected_2, tree_2, treedata_avm_2;

	$scope.nuevo = false;
	$scope.my_tree = tree = {};
	$scope.my_tree_2 = tree_2 = {};
	$scope.guardarBoton = true;
	$scope.eliminarBoton = true;
	$scope.item = {};
	$scope.my_tree_handler = function(branch) {
		$scope.output = "Haz escogido: " + branch.id + " " + branch.label;
		$scope.edicion = true;
		$scope.guardarBoton = false;
		if (branch.id != null) {
			$scope.idArbol = branch.id;

		} else {
			$scope.idArbol = null;
			$scope.guardarBoton = true;
		}
		// $scope.permiso = asignacioPermisoResource.getPermiso(branch.id);
	};

	$scope.my_tree_handler2 = function(branch) {
		$scope.output = "Haz escogido a2: " + branch.id + " " + branch.label;
		$scope.eliminarBoton = false;
		if (branch.id != null) {
			$scope.idArbol2 = branch.id;

		} else {
			$scope.idArbol2 = null;
			$scope.eliminarBoton = true;
		}
		// $scope.permiso = asignacioPermisoResource.getPermiso(branch.id);
	};

	$scope.cargarPerfiles = function() {
		asignacioPermisoResource.traerPerfiles($scope.idProducto).then(
				function(t) {
					$scope.perfiles = t;
					$scope.idPerfilProducto = $scope.perfiles[0].id;
					$scope.armarArbol2();
				});
	}

	iniciar = function() {
		asignacioPermisoResource.list().then(function(r) {
			$scope.productos = r;
			$scope.idProducto = $scope.productos[0].id;
			$scope.armarArbol();
			$scope.cargarPerfiles();
		});

	};

	iniciar();

	$scope.cambiaProducto = function() {
		asignacioPermisoResource.traerPerfiles($scope.idProducto).then(
				function(r) {
					$scope.perfiles = r;
					$scope.armarArbol();
					$scope.idPerfilProducto = $scope.perfiles[0].id;
					$scope.armarArbol2();
				})
	};

	// guardar

	$scope.guardarItem = function() {

		$scope.item = {
			idPermisoPerfil : $scope.idPerfilProducto,
			idPermiso : $scope.idArbol
		};

		asignacioPermisoResource.create($scope.item).then(
				function(r) {
					$scope.armarArbol2();
					$scope.status = !r.estado;

					if (r.estado == true) {
						toaster.pop("info", "Asignacion",
								"Registro guardado satisfactoriamente");
						

						$scope.nuevo = false;
					} else {
						toaster.pop("error", "Asignacion", r.mensaje);

					}

				});

	};

	// eliminar

	$scope.eliminarItem = function() {

		$scope.item = {
			id : $scope.idArbol2
		};
		
		console.log($scope.item);

		asignacioPermisoResource.eliminarAsignacion($scope.item).then(
				function(r) {
					$scope.armarArbol2();
				});

	};

});