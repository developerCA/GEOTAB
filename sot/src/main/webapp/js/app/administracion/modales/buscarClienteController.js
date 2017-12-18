app.controller("buscarClienteCtrl", ["$scope", "modalService", "clienteFactory", "commonService", "$timeout",
    function($scope, modalService, clienteFactory, commonService, $timeout){
	
	/*======INIT========*/
	$scope.init = function(){
	};
	/*======INIT========*/
	
	$scope.datosCliente = {
		cedula: null,
		nombres: null,
		apellidos: null
	};
	
	/*======MODAL========*/
	$scope.modalOptions = {
			headerText : 'Buscar Cliente',
			bodyText : '',
			type:'info'
	};
	/*======MODAL========*/	
	
	/*======TRAER LISTAS========*/
	$scope.traerClientes = function(){
		clienteFactory.listPorAtributos($scope.datosCliente.cedula, $scope.datosCliente.nombres, $scope.datosCliente.apellidos).then(function(r){
		})		
	}
	/*======TRAER LISTAS========*/	
	
	$scope.buscar = function(item){
		clienteFactory.listPorAtributos(item.cedula, item.nombres, item.apellidos).then(function(r){
			$scope.clientes = r;
		})
	};
	
	/*======ITEM SELECCIONADO========*/
	$scope.seleccionar = function(item){
		commonService.setVar("cliente", item);
	}
	/*======ITEM SELECCIONADO========*/
	
}]);