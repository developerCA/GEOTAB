app.controller("prefacturarCtrl",["$scope", "prefacturarFactory", "modalService", "commonService", "toaster", "$timeout","$modal",
	function($scope, prefacturarFactory, modalService, commonService, toaster, $timeout,$modal) {
	
	$scope.init = function(){
		$scope.traerNotasPedido();		
	}
	
	$scope.nuevo = false;
	$scope.datosKohinor = {};
	$scope.bodegaSelected;
	$scope.sucursalesYBodegas = {};
	$scope.notaPedidoBodega = {};
	
	$scope.traerNotasPedido = function(){
		prefacturarFactory.prefacturar().then(function(r){
			$scope.listaPedidos = r;
		})
	}
	
	$scope.traerDetallesNotaPedido = function(id){
		prefacturarFactory.detalles(id).then(function(r){
			$scope.detalleList = r;
			console.log($scope.detalleList);
			$scope.datosKohinor.detalle = $scope.detalleList;
		})
	}
	
	$scope.traerTotalesDetallePedido = function(id){
		prefacturarFactory.totales(id).then(function(r){
			$scope.totales = r;
			$scope.datosKohinor.totales = $scope.totales;
		})
	}
	
	$scope.toDate = function(fecha){
		if(!(fecha==null)){
			return new Date(fecha).toLocaleDateString();
		}
	}
	
	$scope.decimales = function(numero){		
		return parseFloat(Math.round(numero * 100) / 100).toFixed(2);
	}	
	
	$scope.enviar = function(item){
		$scope.notaPedido = item;
		$scope.traerDetallesNotaPedido(item.id);
		$scope.traerTotalesDetallePedido(item.id);
		$scope.traerBodegas();
		$scope.nuevo = true;
	}
	
	$scope.enviarPrefactura = function(){
		$scope.datosKohinor.bodega = $scope.bodegaSelected;
		if($scope.datosKohinor.bodega != null && $scope.verificarHomologacion.objeto){
			prefacturarFactory.ordenpedido($scope.datosKohinor).then(function(r){
					if(r.estado){
						$scope.traerNotasPedido();
						toaster.pop("success", "Enviar Prefactura", "El envío de la prefactura se ha realizado exitosamente...");
						$scope.nuevo = false;
					}else{
						toaster.pop("error", "Enviar Prefactura", "Un error se ha sucitado en el envío de la prefactura....");
					}
			})			
		}else{
			toaster.pop("error", "Enviar Prefactura", "Verifique que ha seleccionado una bodega o que la información ha sido homologada");
		}
	}
		
	$scope.cancelarItem = function(){
		$scope.nuevo = false;
	}
	
	$scope.traerBodegas = function(){
		prefacturarFactory.bodegas().then(function(r){
			$scope.bodegas = r;
		})
	}
	
	$scope.verificar = function(){
		$scope.verificarHomologacionKohinor();
	}
	
	$scope.traerSucursalesYBodegas = function(){
		prefacturarFactory.sucursalesbodegas($scope.bodegaSelected).then(function(r){
			if(r.estado){
				toaster.pop("success", "Enviar Prefactura", "Prefactura enviada exitosamente...");
			}else{
				toaster.pop("error", "Enviar Prefactura", "Error en el envío de la prefactura");
			}
		})
	}
	
	$scope.verificarHomologacionKohinor = function(){
		$scope.notaPedidoBodega.bodegaId = $scope.bodegaSelected;
		$scope.notaPedidoBodega.notaPedido = $scope.notaPedido;
		
		prefacturarFactory.verificarHomologacionOrdenPedido($scope.notaPedidoBodega).then(function(r){
			$scope.verificarHomologacion = r;
			console.log($scope.verificarHomologacion.objeto);
		})
	}
	
}])