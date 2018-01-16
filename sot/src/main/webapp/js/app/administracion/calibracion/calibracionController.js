app.controller("calibracionCtrl",["$scope", "calibracionFactory",  "$timeout","toaster","$modal",'modalService',"sincronizarFactory",
    function($scope, calibracionFactory,  $timeout,toaster,$modal,modalService,sincronizarFactory) {
	
	$scope.nuevo = false;
	$scope.renovable = false;
	$scope.accesos = false;
	$scope.actualizable = false;
	$scope.dispositivos = null;
	
	$scope.modalOptions = {
			headerText : 'Instrumento',
			bodyText : '',
			type:'danger'
	};
	
	$scope.init = function(){
		
		$scope.cargarDispositivos();
	}
	
	$scope.cargarDispositivos=function(){
		calibracionFactory.listDispositivos().then(function(r) {
			$scope.dispositivos = r;
		});
	}
	
	
	$scope.sincronizarDispositivos = function(){
		
        api.call("Get", {
            typeName: "Device"
        }, function(result) {
		   
		    sincronizarFactory.sincronizarDispositivos(
				result
			).then(function(r) {
				
				$scope.cargarDispositivos();
				
			});

       }, function(error) {
            alert(error);
        });
	};

	
	$scope.exportar=function(){
		
		window.open('data:application/vnd.ms-excel,' + encodeURIComponent($('#dvData').html()));
	};
	
	

	$scope.nuevoItem = function(){
		$scope.item = null;
		$scope.nuevo = true;
		$scope.editar = false;
		
	}
	
	$scope.cancelarItem=function(){
		$scope.nuevo = false;
	}
	
	$scope.guardarItem=function(){

		calibracionFactory.crearItem($scope.item).then(function(r) {
				
						
					$scope.status = !r.estado;
	
					if (r.estado == true) {
						//$scope.traerProductosRenovacion();
						$scope.edicion = false;
						$scope.nuevo = false;
						$scope.traerInstrumentos();
						
						toaster.pop(
								"success",
								"Instrumento",
								"Registro guardado satisfactoriamente");
					} else {
						
								toaster.pop("error",
										"Instrumento",
										r.mensaje);
					}	
			});
	};
	
	$scope.editarItem=function(item){
				
		$scope.item = item;			
		$scope.nuevo = true;
		$scope.editar = true;
	
					
	};
		
	$scope.actualizarItem=function(){
		
		calibracionFactory.update($scope.item).then(function(r) {
			
				
				$scope.status = !r.estado;

				if (r.estado == true) {
					
					$scope.traerInstrumentos();
					$scope.nuevo = false;
					$scope.edicion = false;
					
					toaster
					.pop(
							"success",
							"Instrumento",
							"Registro actualizado satisfactoriamente");
				} else {
					toaster.pop("error",
							"Instrumento",
							r.mensaje);
				}				

			});
	}
	
	$scope.eliminarItem=function(item){
		
		$scope.modalOptions.bodyText = 'Desea eliminar el instrumento ? ';
		$scope.modalOptions.templateUrl="tpl/modals/modalConfirm.html";
	
		modalService.showModal({}, $scope.modalOptions).then(function(r){ 
			calibracionFactory.eliminar(item).then(function(r) {
				
				$scope.nuevo = false;
				
				$scope.status = !r.estado;

				if (r.estado == true) {
				
					$scope.edicion = false;
					$scope.nuevo = false;
					$scope.traerInstrumentos();
					
					toaster
					.pop(
							"success",
							"Instrumento",
							"Registro eliminado satisfactoriamente");
				} else {
					toaster.pop("error",
							"Instrumento",
							r.mensaje);
				}

			});

			
		});	
		
	};
	
	$scope.initConsulta=function(){
	
		calibracionFactory.listCalibraciones().then(function(r) {
			$scope.calibraciones = r;
			
			$scope.init();
		});
		
	};
	
	$scope.cal={fechaCalibracion:null,fechaCalibracionFutura:null,fechaVerificacionIntermedia:null,instrumento:null,archivo:null};
	
	$scope.guardarCalibracion=function(){
		
		
		calibracionFactory.crearCalibracion($scope.cal).then(function(r) {
			
			if (r.estado == true) {
				$scope.initConsulta();
				$scope.edicion = false;
				$scope.nuevo = false;
				$scope.cal={fechaCalibracion:null,fechaCalibracionFutura:null,fechaVerificacionIntermedia:null,instrumento:null,archivo:null};
					
				
				toaster.pop(
						"success",
						"Calibración",
						"Registro guardado satisfactoriamente");
			} else {
				
						toaster.pop("error",
								"Calibración",
								r.mensaje);
			}	
	    });
		
	};
	
	$scope.subirArchivoIntermedio=function(calibracion){
		
		var modalInstance = $modal.open({
	        templateUrl: 'tpl/app/administracion/suscripciones/modalCarga.html',
	        controller: 'ModalInstanceCargaDatosCtrl',
	        size: 'lg'
	   });
	   
	   modalInstance.result.then(function (respuesta) {
		  
		//$scope.cal.archivo=respuesta.name;
		   calibracion.calibracion.archivoVerificacion =respuesta.name;
		   
		   calibracionFactory.updateVerificacion(calibracion.calibracion).then(function(r) {
				
				if (r.estado == true) {
					
					
					toaster.pop(
							"success",
							"Calibración",
							"El archivo de verificacion se subio con exito");
				} else {
					
							toaster.pop("error",
									"Calibración",
									r.mensaje);
				}	
		    });
		   
		   
		   
		   console.log(calibracion.calibracion);
		
	   })
		
	};
	
	$scope.verCalibraciones=function(calibracion){
		$scope.dato= calibracion;
		
		var modalInstance = $modal.open({
		        templateUrl: 'tpl/app/administracion/calibracion/modal.html',
		        controller: 'ModalInstanceCtrl',
		        size: 'lg',
		        resolve: {
			        item: function () {
			            return $scope.dato;
			        }
		        }
		      });

		      modalInstance.result.then(function (selectedItem) {
		       
		        
		      }, function () {
		        console.log('Modal dismissed at: ' + new Date());
		      });
		
	};
	
	$scope.crearArchivo=function(){
		$scope.archivo;
		
		var modalInstance = $modal.open({
	        templateUrl: 'tpl/app/administracion/suscripciones/modalCarga.html',
	        controller: 'ModalInstanceCargaDatosCtrl',
	        size: 'lg'
	   });
	   
	   modalInstance.result.then(function (respuesta) {
		  
		$scope.cal.archivo=respuesta.name;
		$scope.archivo=respuesta.nameR;
		
		
	   })	
		
		
	};

}]); 



app.controller('ModalInstanceCargaDatosCtrl', ['$scope', '$modalInstance', 'FileUploader', 'toaster','$timeout',  
                                               function($scope, $modalInstance, FileUploader, toaster, $timeout) {
	
    var uploader = $scope.uploader = new FileUploader({
        url: 'api/upload/cargarArchivo'
    });
    
    $scope.lista = [];

    // FILTERS

    uploader.filters.push({
        name: 'customFilter',
        fn: function(item /*{File|FileLikeObject}*/, options) {
            return this.queue.length < 10;
        }
    });
    


    // CALLBACKS

    uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
//        console.info('onWhenAddingFileFailed', item, filter, options);
    };
    uploader.onAfterAddingFile = function(fileItem) {
//        console.info('onAfterAddingFile', fileItem);
    };
    uploader.onAfterAddingAll = function(addedFileItems) {
//        console.info('onAfterAddingAll', addedFileItems);
    };
    uploader.onBeforeUploadItem = function(item) {
    	var formData ={id:1};
    	if(!(formData==null)){
    		item.formData.push(formData);
    	}   
        console.info('onBeforeUploadItem', item);
    };
    uploader.onProgressItem = function(fileItem, progress) {
        try{
//    	console.info('onProgressItem', fileItem, progress);
        }catch(ex){ alert(ex); }
    };
    uploader.onProgressAll = function(progress) {
        try{
//    	console.info('onProgressAll', progress);
        }catch(ex){ alert(ex); }
    };
    uploader.onSuccessItem = function(fileItem, response, status, headers) {
    	try{
    		$modalInstance.close(response);
    	}catch(ex){ alert(ex); }
    };
    uploader.onErrorItem = function(fileItem, response, status, headers) {
    	try{
//        console.info('onErrorItem', fileItem, response, status, headers);
    	}catch(ex){ alert(ex); }
    };
    uploader.onCancelItem = function(fileItem, response, status, headers) {
    	try{
//        console.info('onCancelItem', fileItem, response, status, headers);
    	}catch(ex){ alert(ex); }
    };
    uploader.onCompleteItem = function(fileItem, response, status, headers) {
//        console.info('onCompleteItem', fileItem, response, status, headers);
    };
    uploader.onCompleteAll = function() {
//    	console.info('onCompleteAll');
    };

//    console.info('uploader', uploader);	
	
    $scope.cerrar = function () {
    	$modalInstance.dismiss('cancel');
    };

}])

app.controller('ModalInstanceCtrl', ['$scope', 'calibracionFactory','$modalInstance','$rootScope','item',  function($scope, calibracionFactory,$modalInstance,$rootScope,item) {
  
	$scope.cal=item;
	
	$scope.init=function(){
	
	$scope.version=$rootScope.versionApp; 

	calibracionFactory.listPorInstrumento($scope.cal.calibracion).then(function(r) {
			$scope.calibraciones = r;
			
			
	});
	
	};
	
    $scope.ok = function () {
    	
      $modalInstance.close();
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  }])
  ; 


