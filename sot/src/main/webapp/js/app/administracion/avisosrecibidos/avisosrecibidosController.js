app.controller("avisosRecibidosCtrl", ["$scope", "recibidosFactory", "suscripcionFactory", "notaPedidoFactory", "avisosFactory", "toaster", "$timeout", "$modal", function($scope, recibidosFactory, suscripcionFactory, notaPedidoFactory, avisosFactory, toaster, $timeout, $modal){
	
	$scope.nuevo = false;
	$scope.recibido = {};
	$scope.code = {};
	$scope.ver = false;
	$scope.deshabilitaLimpiar = true;
	$scope.avisoRec = {};
	
	$scope.init = function(){
		$scope.traerDistribuidores();
	}
	
	$scope.toDate = function(fecha){
		if(!(fecha==null)){
			return new Date(fecha).toLocaleDateString();
		}
	};
	$scope.limpiar=function(){		
		$scope.recibido.codigo="";
		$scope.ver=false;
	}
	
	$scope.obtenerRegional = function(){
		$scope.recibido.regional = $scope.distribuidor.regionalBean.nombre;
	};
	
	$scope.traerDistribuidores = function(){
		recibidosFactory.list().then(function(r){
			$scope.distribuidores = r;
		})
	};
	
	$scope.traerSuscripciones = function(){
		recibidosFactory.suscripciones($scope.recibido.codigo).then(function(r){
			$scope.listaSuscripciones = r;
			$scope.ver = true;
		})
	};
	
	$scope.mostrar = function(){
		$scope.analizarCodigo();
	};
	
	$scope.analizarCodigo = function() {
		$scope.codigo = $scope.recibido.codigo.split(".");
		$scope.codigoRecibido = $scope.codigo[0] + "." + $scope.codigo[1];
		recibidosFactory.analizar($scope.codigoRecibido).then(function(r){
			$scope.code = r;
			$scope.optenerAviso();
			
		})
	}
	
	$scope.optenerAviso = function(){
		avisosFactory.porid($scope.codigoRecibido).then(function(r){
			$scope.aviso = r
			if($scope.aviso[0] != null){
				if($scope.aviso[0].estado.id != 23){
					$scope.traerSuscripciones();
				}else{
					toaster.pop("info", "Aviso", "El registro ya ha sido procesado");
				}
			}else{
				toaster.pop("error", "Avisos Recibidos", "Un error se ha sucitado al procesar el aviso recibido, por favor verificar el código ingresado");
			}
		})
	}
	
	$scope.guardarAviso=function(){
		$scope.avisoRec.aviso = $scope.aviso[0];
		$scope.avisoRec.fechaEntrega = $scope.aviso[0].fechaEntrega;
		avisosFactory.modificar($scope.avisoRec).then(function(r){
			if(r != null && r.estado == true){
				$scope.ver = false;
				$scope.distribuidor = null;
				$scope.recibido = null;
				$scope.deshabilitaLimpiar = false;
				toaster.pop("success", "Avisos Recibidos", "Se ha guardado el aviso exitosamente...");
			}else{
				toaster.pop("error", "Avisos Recibidos", "Un error se ha sucitado al guardar el aviso...");
			}
		})
	}	
	
}]);