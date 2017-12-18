app.controller("gestionCtrl",["$scope", "gestionFactory","notaPedidoFactory", "fechasFactory","productoFactory","modalService", "commonService", "toaster", "productoFactory", "$timeout", "$modal",
	function($scope, gestionFactory,notaPedidoFactory,fechasFactory, productoFactory,modalService, commonService, toaster, productoFactory, $timeout, $modal) {
	
	$scope.init = function(){
		$scope.traerUsuario();
		$scope.traerProductos();
	}
	

	
	$scope.telerenovador=null;
	$scope.fecha=null;
	
	$scope.detallePedido={producto:null,visible:false};
	$scope.dto={notaPedido:null,detalles:null};
	$scope.fila={visible:false,waiting:false};
	$scope.isRenovar=true;
	
	/*======CONSTANTES========*/
	$scope.constantes = {
			confirmaCreacion : "<h4><i class='fa fa-question-circle'></i> Confirma actualizar registro cliente</h4>",
			confirmaEliminacion : "<h4><i class='icon-question'></i> Confirma eliminar esta suscripci&oacute;n</h4>",
	}
	
	/*======MODAL========*/
	$scope.modalOptions = {
			headerText : "Confirmar eliminación",
			bodyText : '',
			type:'info'
	};
	
	$scope.filaMostrar=function(){
		$scope.fila.visible=true;
	};
	
	
	$scope.traerUsuario = function(){
		gestionFactory.usuario().then(function(r){
			
			$scope.telerenovador = r;
			
			if ($scope.telerenovador!=null){
				
				$scope.traerFechas();
			}
			
		})
	}
	
	$scope.toDate = function(fecha){
		if(!(fecha==null)){
			return new Date(fecha).toLocaleDateString();
		}
	};
	
	$scope.traerFechas=function(){
		
		fechasFactory.fechasActivasFinalizadas().then(function(r){
			$scope.fechas=r;
			
			
		})

	};
	
	$scope.traerProductos = function() {
		productoFactory.listProductos().then(function(r) {
			$scope.productos = r;
	    });						
	};
	
	
	
	$scope.buscarSuscripciones=function(){
		
		gestionFactory.detalle($scope.fecha,$scope.telerenovador).then(function(r){
			console.log(r);
			$scope.listaGestion=r;
		})
	}
	$scope.ingresarPedido=function(gestion){
		
		$scope.listaRenovados=[];
		
		for (var i=0;i<gestion.listaDistribuciones.length;i++){
			var objDistribucion=gestion.listaDistribuciones[i];
		
			if (objDistribucion.seleccion==true){
				$scope.listaRenovados.push(objDistribucion);
			}
		} 
		
		if ($scope.listaRenovados.length>0){
		
			gestion.pasoRenovacion=false;
			gestion.pasoNP=true;
			
			gestionFactory.generarNotaPedido(gestion).then(function(r){
				gestion.pedidoDto=r;
				console.log(gestion);
				
				gestion.isRenovar=false;
			})
			
		}else{
			toaster.pop("error","Gestion Comercial","No se encuentra seleccionado ninguna suscripción para su renovación");
			
		}
		
	};
	
	$scope.cancelarRenovacion=function(gestion){
		gestion.isRenovar=true;
		gestion.pasoRenovacion=true;
		gestion.pasoNP=false;
		$scope.listaRenovados=[];
		gestion={producto:null,visible:false};
		gestion.pedidoDto={};
		
	
	}
	
	
	$scope.decimales = function(numero){
		
		return parseFloat(Math.round(numero * 100) / 100).toFixed(2);
	}
	
	$scope.manejarProducto=function(gestion){
		
		
		gestion.detallePedido.costoUnitario=gestion.detallePedido.producto.costo;
		gestion.detallePedido.numeroMeses=gestion.detallePedido.producto.mesesVigencia;
		

		if(gestion.detallePedido.producto.aplicaAccesos){
			gestion.detallePedido.cantidad = 1;
			$scope.maximo=gestion.detallePedido.producto.maximoAccesos;
			$scope.minimo=gestion.detallePedido.producto.minimoAccesos;

		}else{
			gestion.detallePedido.cantidad = 0;
			
			$scope.maximo=0;
			$scope.minimo=0;
			gestion.detallePedido.numeroAccesos=0;
		}
	};

	$scope.validarMinMax = function(value){
      
    	return value>=$scope.minimo && value<=$scope.maximo?true:false;
    };
	
	$scope.agregarDetalle = function(gestion){
		gestion.detallePedido.visible=false;
		gestion.detallePedido.renovacion=false;
		gestion.pedidoDto.detalles.push(gestion.detallePedido);
		$scope.calcularCostos(gestion);
		
		gestion.detallePedido={producto:null,visible:false};
	}
	
	$scope.calcularCostos=function(gestion){
		console.log(gestion.pedidoDto);
		gestionFactory.calcular(gestion.pedidoDto).then(function(r) {
			gestion.pedidoDto=r;
			
		});
	};
	
	$scope.eliminarDetalle=function(gestion,indice){
		
		gestion.pedidoDto.detalles.splice(indice,1);
		$scope.calcularCostos(gestion);
		
	};
	
	$scope.actualizarNotaPedido=function(gestion,detalle){
		detalle.visible=false;
		detalle.descuentoAnt=detalle.descuento;
		$scope.calcularCostos(gestion);
	};
	
	$scope.editarDetalle=function(detalle){
		
		detalle.visible=true;
	};
	
	$scope.cancelaEdicion=function(detalle){
		detalle.visible=false;
		detalle.descuento=detalle.descuentoAnt;
	};


	
	$scope.renovar = function(gestion){
		$scope.modalOptions.bodyText = "Esta seguro de revovar las suscripciones seleccionadas ? ";
		$scope.modalOptions.templateUrl="tpl/modals/modalConfirm.html";
		$scope.modalOptions.headerText = "Confirmar renovación";
		
		modalService.showModal({}, $scope.modalOptions).then(function(r){
			
			console.log(gestion);
			
			gestionFactory.renovados(gestion).then(function(r){
				
				if(r.estado){
					$scope.cancelarRenovacion(gestion);
					$scope.buscarSuscripciones();
					toaster.pop("success","Gestion Comercial","La renovacion fue ejecutada exitosamente");
				}else{
					toaster.pop("error","Gestion Comercial","Un error se ha sucitado en la ejecución de la renovacion");
				}
			})	
		});
		
		
		
	
	
	}
			
	$scope.cancelada = function(listaDistribucion){
		
		gestionFactory.cancelado(listaDistribucion).then(function(r){
			if(r.estado){
				$scope.buscarSuscripciones();			
				toaster.pop("success","Gestion Comercial","Las suscripciones fueron declaradas como NO RENOVADAS de manera exitosa");
			}else{
				toaster.pop("error","Gestion Comercial","Un error se ha sucitado al intentar declarar la renovación como NO RENOVADA");
			}
		})
	}
	
    $scope.canceladaIndividual = function(distribucion){
		
		gestionFactory.canceladoIndividual(distribucion).then(function(r){
			if(r.estado){
		
				$scope.buscarSuscripciones();			
				toaster.pop("success","Gestión Comercial","La suscripción fue declarada como NO RENOVADA de manera exitosa");
			}else{
				toaster.pop("error","Gestión Comercial","Un error se ha sucitado al intentar declarar la suscripción como NO RENOVADA");
			}
		})
	};
	
	$scope.eliminarActualizacion=function(detalle){
		
		detalle.renovacionDetalle.accesosRenovacion=[];
		detalle.renovacionDetalle.renovacionDetalle.productoVenta=detalle.renovacionDetalle.renovacionDetalle.productoRenovado;
		gestionFactory.accesosRenovacionPorIdSuscripcion(detalle.distribucion.suscripcion.id).then(function(r){
		
			for (var i=0;i<r.length;i++){
				detalle.renovacionDetalle.accesosRenovacion.push(r[i]);
			}
		})
		
	};
	
	$scope.norenovar = function (listaDistribucion) {
	      var modalInstance = $modal.open({
	        templateUrl: 'tpl/app/administracion/gestioncomercial/modalMotivo.html',
	        controller: 'ModalInstanceCtrlMotivo',
	        size: 'lg',
	      });

	      modalInstance.result.then(function (selectedItem) {
	    	  
	    	  listaDistribucion.motivo=selectedItem;
	    	  
	    	  $scope.cancelada(listaDistribucion);
	      }, function () {
	        console.log('Modal dismissed at: ' + new Date());
	      });
	};
	
	$scope.norenovarIndividual = function (distribucion) {
		
		
	      var modalInstance = $modal.open({
	        templateUrl: 'tpl/app/administracion/gestioncomercial/modalMotivo.html',
	        controller: 'ModalInstanceCtrlMotivo',
	        size: 'lg',
	      });

	      
	      
	      modalInstance.result.then(function (selectedItem) {
	    	  
	    	  distribucion.motivo=selectedItem;
	    	  
	    	 $scope.canceladaIndividual(distribucion);
	      }, function () {
	        console.log('Modal dismissed at: ' + new Date());
	      });
	};
	
	$scope.cargaModalNueva = function (item) {
		
			var modalInstance = $modal.open({
				templateUrl: 'tpl/app/administracion/gestioncomercial/modalNueva.html',
		        controller: 'ModalInstanceCtrlNueva',
		        size: 'lg',
		        resolve: {
		        	detalles: function(){
		        		return item;
		        	}
		        }
			});

	      modalInstance.result.then(function (itemNuevos) {
	    	 
	    	 
	    	  for (i=0;i<itemNuevos.length;i++){
	    		  item.renovacionesDto.detalleRenovacionDtoList.push(itemNuevos[i]);
	    	  }
	    	
	    	  
	    	  $scope.newObject = item;
	    	  

	      }, function () {
	        console.log('Modal dismissed at: ' + new Date());
	      });
	};
	
	$scope.cargaModalAccesos = function (item) {
		
		console.log(item);
		
		var modalInstance = $modal.open({
			templateUrl: 'tpl/app/administracion/gestioncomercial/gestionaccesos.html',
	        controller: 'ModalInstanceCtrlAccesos',
	        size: 'lg',
	        resolve: {
	        	objetoRenovacion: function(){
	        		return item;
	        	}
	        }
		});
		modalInstance.result.then(function (selectedItem) {
			item=selectedItem;
			console.log(item);
			
		})
	};
	
		
    $scope.eliminar = function(index){
    	$scope.newObject.renovacionesDto.detalleRenovacionDtoList.splice(index, 1);
    }	
    

    
    	
}]);


app.controller('ModalInstanceCtrlMotivo', ['$scope', '$modalInstance', 'gestionFactory', '$timeout', function($scope, $modalInstance, gestionFactory, $timeout) {
	/*======INIT========*/
	$scope.init = function(){
	};
	
	$scope.motivo = "";
	/*======INIT========*/
		    
    $scope.aceptar = function(item){
    	$modalInstance.close($scope.motivo);
    }

    $scope.cancel = function () {
    	$modalInstance.dismiss('cancel');
    };
    
 }]);

app.controller('ModalInstanceCtrlNueva', ['$scope', '$modalInstance', 'gestionFactory', 'productoFactory', 'detalles', '$timeout', function($scope, $modalInstance, gestionFactory, productoFactory, detalles, $timeout) {
	
	$scope.init = function(){
		$scope.traerProductos();
	}
	$scope.detalle = detalles;
	$scope.avisoId = $scope.detalle.aviso.id;
	$scope.lista = [];
	$scope.ocultar = true;
	$scope.producto = {};
	$scope.listaTotal = [];
	
	$scope.traerProductos = function(){
		productoFactory.listProductos().then(function(r){
			$scope.productos = r;
		})
	}
	
    $scope.cancel = function () {
    	$modalInstance.dismiss('cancel');
    };
    
    $scope.buscarProductoPorId = function(idProducto){
    	for(i=0;i<$scope.productos.length;i++){
    		if($scope.productos[i].id == idProducto){
    			return $scope.productos[i];
    		}
    	}
    	return null;
    }
        
    $scope.agregar = function(){
    	$scope.registroClone = {};   	
    	$scope.registro.aviso = $scope.avisoId;
    	$scope.registroClone = angular.copy($scope.registro);
    	$scope.registroClone.producto = $scope.producto;
    	$scope.lista.push($scope.registroClone);
    	$scope.registro = {};
    }
        
    $scope.aceptar = function() {
    	
    	//$scope.detalle.nuevosProductosRenovacionList = $scope.lista;
    	
    	
    	gestionFactory.agregarProductosNuevos($scope.lista).then(function(r){
    		
//    		$scope.detalleActualizado = r;
//    		$scope.detalleActualizado.nuevaLista = $scope.lista
//    		//console.log($scope.detalleActualizado);
        	$modalInstance.close(r);
    	})
    }
    
    $scope.verificar = function(){
    	$scope.producto = {};
    	
    	$scope.producto = $scope.buscarProductoPorId($scope.registro.producto.id);
    	$scope.ocultar = !$scope.producto.aplicaAccesos;
    }
    
}]);

app.controller('ModalInstanceCtrlAccesos', ['$scope', '$modalInstance', 'modalService','gestionFactory', 'objetoRenovacion', "toaster", '$timeout', function($scope, $modalInstance, modalService,gestionFactory, objetoRenovacion, toaster, $timeout) {
	
	$scope.modalOptions = {
			headerText : "Confirmar eliminación",
			bodyText : '',
			type:'info'
	};
	
	$scope.renovacionObj = objetoRenovacion;
	$scope.maximo=0;
	$scope.minimo=0;
	$scope.accesosNuevos=null;
	
	$scope.init = function(){
		 $scope.traerProductosConAccesos();
		
	}
	
   
    $scope.traerProductosConAccesos = function(){
    	gestionFactory.productosConAccesos($scope.renovacionObj.distribucion.suscripcion.id).then(function(r){
    		$scope.mostrar = true;
    		$scope.productosAccesos = r;
    	});
		
    }
    
    $scope.seleccionarProducto = function(){
    	
    	$scope.minimo=$scope.producto.minimoAccesos;
    	$scope.maximo=$scope.producto.maximoAccesos;
    	$scope.accesosNuevos=null;
    
    }
    $scope.validarMinMax = function(value){
    	return value>=$scope.minimo && value<=$scope.maximo?true:false;
    };
    
    $scope.eliminarAcceso = function(indice){
    	$scope.modalOptions.bodyText = "Esta seguro de eliminar el acceso ? ";
		$scope.modalOptions.templateUrl="tpl/modals/modalConfirm.html";
		
		modalService.showModal({}, $scope.modalOptions).then(function(r){
			
			$scope.renovacionObj.renovacionDetalle.accesosRenovacion.splice(indice,1);
		});
    }
    
    $scope.generar = function(){
    	
    	var accesos=$scope.renovacionObj.renovacionDetalle.accesosRenovacion.length;
    	
    	if ($scope.accesosNuevos>accesos){
    		var cantidadAccesos=$scope.accesosNuevos-accesos;
    		
    		gestionFactory.agregarAccesos(cantidadAccesos).then(function(r){
    			for (var i=0;i<r.length;i++){
    				$scope.renovacionObj.renovacionDetalle.accesosRenovacion.push(r[i]);
        			console.log(r);	
    			}
        	})
    	}else{
    		toaster.pop("warning", "Gestión Accesos", "Los accesos ya estan generados con la suscripción seleccionada");
    	}
    	
    }
    
   
    
    $scope.aceptar = function(){
    	
    	var accesos=$scope.renovacionObj.renovacionDetalle.accesosRenovacion.length;
    	
    	if ($scope.accesosNuevos==accesos){
    		$scope.renovacionObj.renovacionDetalle.renovacionDetalle.numeroAccesos=accesos;
    		$scope.renovacionObj.renovacionDetalle.renovacionDetalle.productoVenta=$scope.producto;
    		
    		
    		$modalInstance.close($scope.renovacionObj);
    		
    	}else{
    		toaster.pop("error", "Gestión Accesos", "La cantidad de accesos no correspoden al número de accesos solicitados por el cliente");
    	}

    }
	
    $scope.cerrar = function () {
    	$modalInstance.dismiss('cancel');
    };	
}])