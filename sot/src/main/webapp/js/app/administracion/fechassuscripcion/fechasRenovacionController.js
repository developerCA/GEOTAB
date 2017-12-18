app.controller("fechasRenovacionCtrl", ["$scope", "fechasFactory", "toaster", "$timeout", function($scope, fechasFactory, toaster, $timeout){
	$scope.init = function(){
		$scope.traerFechas();
		$scope.ultimaFechaRenovacion();
		$scope.fechaSugerir();
	}
	
	$scope.nuevo = false;
	$scope.editar = false;
	
	$scope.fechaRenovacion = {};
	$scope.fechasValidadas;
	
	$scope.traerFechas = function(){
		fechasFactory.list().then(function(r) {
			$scope.fechasRenovacion = r;
		});
	}
	
	$scope.toDate = function(fecha){
		if(!(fecha==null)){
			return new Date(fecha).toLocaleDateString();
		}
	};	
	
	$scope.nuevoItem = function(){
		$scope.fechaRenovacion = {};
		$scope.nuevo = true;
	}
	
	$scope.cancelarItem = function(){
		$scope.nuevo = false;
	}
	

	
	$scope.guardarItem = function(){
		
		fechasFactory.fechaValidacion($scope.fechaRenovacion).then(function(r){
			$scope.fechasValidadas = r;
			
			if($scope.fechasValidadas == true){
				fechasFactory.create($scope.fechaRenovacion).then(function(r){
					console.log(r);
					if (r.estado==true){
						$scope.traerFechas();
						$scope.nuevo = false;
						toaster.pop("success", "Fechas de Renovación", "El registro se guardó exitosamente");
					}else{
						toaster.pop("error", "Fechas de Renovación", r.mensaje);
					
					}
					
				})
				
			}else{
				toaster.pop("error", "Fechas de Renovación", "Un error se ha sucitado, por favor verifique que la fecha fin debe ser mayor que la fecha de Inicio");
			}						
		})
		
	}
	
	$scope.ultimaFechaRenovacion = function(){
		fechasFactory.ultimaFechaRenovacion().then(function(r){
			$scope.ultimaFecha = r;
		})
	}	
	
	$scope.eliminarItem = function(item){
		fechasFactory.eliminar(item).then(function(r){
			$scope.traerFechas();
		})
	}
	
	$scope.activarItem = function(item){
		fechasFactory.activar(item).then(function(r){
			if (r.estado==true){
				$scope.traerFechas();
				$scope.nuevo = false;
				toaster.pop("success", "Fechas de Renovación", "La fecha para el proceso de avisos de renovación se activó exitosamente");
			}else{
				toaster.pop("error", "Fechas de Renovación", r.mensaje);
			
			}
			$scope.traerFechas();
		})
	}
	
	$scope.finalizarItem = function(item){
		fechasFactory.finalizar(item).then(function(r){
			if (r.estado==true){
				$scope.traerFechas();
				$scope.nuevo = false;
				toaster.pop("success", "Fechas de Renovación", "La fecha para el proceso de avisos de renovación se finalizó exitosamente");
			}else{
				toaster.pop("error", "Fechas de Renovación", r.mensaje);
			
			}
			$scope.traerFechas();
		})
	}
	
	$scope.fechaSugerir = function(){
		fechasFactory.fechaSugerencia().then(function(r){
			console.log($scope.toDate(r));
			if(r != null){
				$scope.sugerirFecha = r;
			}
		})
	}
	
}]);