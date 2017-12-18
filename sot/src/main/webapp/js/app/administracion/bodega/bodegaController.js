app.controller("bodegaCtrl",["$scope", "bodegasFactory","$timeout","toaster",
	function($scope, bodegasFactory, $timeout, toaster) {
	
	$scope.nuevo = false;
	$scope.bodegaSeleccionada;
	
	$scope.init = function(){
		$scope.traerBodegas();
		$scope.traerbodegasKoynor();
	}
	
	$scope.traerBodegas = function(){
		bodegasFactory.listBodegas().then(function(r){
			$scope.bodegas = r;
		})
	};
	
	$scope.nuevoItem = function(){
		$scope.item = null;
		$scope.nuevo = true;
// $scope.editar = false;
	};
	
	$scope.cancelarItem=function(){
		$scope.nuevo = false;
	};
	
	$scope.traerbodegasKoynor = function() {
		bodegasFactory.listbodegasKoynor().then(function(r) {
			$scope.listbodegasKoynor = r;
		});
		
	};
	

	$scope.bod = {
			nombre: null,codigoKoynor:null
		};
	
	
	$scope.guardarItem=function(){
		
		$scope.bod.codigoKoynor=$scope.item.codigoKohinor;
	
		$scope.bod.nombre=$scope.item.nombre;


		bodegasFactory.createBodega($scope.bod).then(function(r) {
			$scope.nuevo = false;
			$scope.edicion = false;
			$scope.traerBodegas();
			if(r.estado){
				toaster
				.pop(
						"success",
						"Bodega",
						"Registro guardado satisfactoriamente");
				
			}else{
				toaster
				.pop(
						"error",
						"Bodega",
						"Un error se ha sucitado al guardar el registro...");
				
			}
			
			});
	};
	
	
	
	$scope.editarItem=function(item){
	
		$scope.item = item;			
		$scope.nuevo = true;
		$scope.editar = true;

					
	};
	$scope.actualizarItem=function(){
		
		$scope.bod.id=$scope.item.id;
		$scope.bod.codigoKoynor=$scope.item.codigoKohinor;
		
		$scope.bod.nombre=$scope.item.nombre;
		
		bodegasFactory.actualiza($scope.bod).then(function(r) {
		
				
				$scope.status = !r.estado;

				if (r.estado == true) {
	
					$scope.nuevo = false;
					$scope.edicion = false;
					$scope.traerBodegas();
					
					toaster
					.pop(
							"success",
							"Bodega",
							"Registro actualizado satisfactoriamente");
				} else {
					toaster.pop("error",
							"Productos",
							r.mensaje);
				}				

			});
	};
	
	
	$scope.eliminarItem=function(item){
		
		

		bodegasFactory.eliminar(item).then(function(r) {

			$scope.nuevo = false;
			
			$scope.status = !r.estado;

			if (r.estado == true) {

				$scope.nuevo = false;
				$scope.edicion = false;
				$scope.traerBodegas();

				
				toaster
				.pop(
						"success",
						"Productos",
						"Registro actualizado satisfactoriamente");
			} else {
				toaster.pop("error",
						"Productos",
						r.mensaje);
			}

		});
	};
	
	
}]);
