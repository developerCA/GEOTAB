'use strict';

angular.module('modal.Service', [])
	.service('modalService', ['$modal', '$modalStack',
    function ($modal, $modalStack) {

        var modalDefaults = {
            backdrop: true,
            keyboard: true,
            modalFade: true,
            templateUrl: '/tpl/modals/modalConfirm.html'
        };

        var modalOptions = {
            closeButtonText: 'Cerrar',
            actionButtonText: 'Aceptar',
            headerText: 'Proceder?',
            bodyText: 'Realizar esta acción?',
            type: 'info',
            templateUrl: '/tpl/modals/modalConfirm.html'
        };

        this.closeModal = function () {
        	$modalStack.dismissAll(function(){
        		
        		alert("se cerró!!!");
        		
        	});
        };
        
        /*==========MENSAJES POR DEFECTO=========>*/
        
        this.messagesDefault = {
        	
        	updateConfirm : "<h4><i class='fa fa-question-circle'></i> Confirma actualizar el registro?</h4>",
        	deleteConfirm : "<h4><i class='fa fa-warning'></i> Confirma eliminar el registro?</h4>"
        		
        };
        
        /*<=========MENSAJES POR DEFECTO==========*/
        
        this.showModal = function (customModalDefaults, customModalOptions) {
            if (!customModalDefaults) customModalDefaults = {};
            customModalDefaults.backdrop = 'static';

            if (!(customModalOptions.templateUrl==undefined)) customModalDefaults.templateUrl=customModalOptions.templateUrl;
            return this.show(customModalDefaults, customModalOptions);
        };

        this.show = function (customModalDefaults, customModalOptions) {
            //Create temp objects to work with since we're in a singleton service
            var tempModalDefaults = {};
            var tempModalOptions = {};
            var rnd = Math.floor((Math.random() * 1000) + 1);
            
            if(!(customModalOptions.size==undefined)) tempModalDefaults.size = customModalOptions.size;
            
            //Map angular-ui modal custom defaults to modal defaults defined in service
            angular.extend(tempModalDefaults, modalDefaults, customModalDefaults);

            //Map modal.html $scope custom properties to defaults defined in service
            angular.extend(tempModalOptions, modalOptions, customModalOptions);

            if (!tempModalDefaults.controller) {
                tempModalDefaults.controller = function ($scope, $modalInstance) {
                    $scope.modalOptions = tempModalOptions;
                    $scope.modalOptions.ok = function (result) {
                        $modalInstance.close(result);
                    };
                    $scope.modalOptions.close = function (result) {
                        $modalInstance.dismiss('cancel');
                        $modalInstance.close(result);
                        //$modalInstance.close();
                    };
                }
            }
            
            //tempModalDefaults.templateUrl = tempModalDefaults.templateUrl + "?r=" + rnd; 
            
            if(customModalOptions.callback!=undefined){
            	tempModalDefaults.resolve = { callback: customModalOptions.callback()};
            	return $modal.open(tempModalDefaults).result;
            
            }else{
            	
            	return $modal.open(tempModalDefaults).result;
            }
        };

    }]);