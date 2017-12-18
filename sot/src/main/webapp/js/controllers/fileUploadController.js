app.controller('UploaderCtrl', ['$scope', 'FileUploader', 'commonService', 
                                function($scope, FileUploader, commonService) {
	
    var uploader = $scope.uploader = new FileUploader({
        url: 'upload/'
    });

    // FILTERS

    uploader.filters.push({
        name: 'customFilter',
        fn: function(item /*{File|FileLikeObject}*/, options) {
            return this.queue.length < 10;
        }
    });

    // CALLBACKS

    uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
        console.info('onWhenAddingFileFailed', item, filter, options);
    };
    uploader.onAfterAddingFile = function(fileItem) {
        console.info('onAfterAddingFile', fileItem);
    };
    uploader.onAfterAddingAll = function(addedFileItems) {
        console.info('onAfterAddingAll', addedFileItems);
    };
    uploader.onBeforeUploadItem = function(item) {
    	var formData = commonService.getVar("formData");
    	console.log(">" + formData);
    	if(!(formData==null)){
    		item.formData.push(formData);
    	}    	
        console.info('onBeforeUploadItem', item);
    };
    uploader.onProgressItem = function(fileItem, progress) {
        try{
    	console.info('onProgressItem', fileItem, progress);
        }catch(ex){ alert(ex); }
    };
    uploader.onProgressAll = function(progress) {
        try{
    	console.info('onProgressAll', progress);
        }catch(ex){ alert(ex); }
    };
    uploader.onSuccessItem = function(fileItem, response, status, headers) {
    	try{
    	console.info('onSuccessItem', fileItem, response, status, headers);
    	}catch(ex){ alert(ex); }
    };
    uploader.onErrorItem = function(fileItem, response, status, headers) {
    	try{
        console.info('onErrorItem', fileItem, response, status, headers);
    	}catch(ex){ alert(ex); }
    };
    uploader.onCancelItem = function(fileItem, response, status, headers) {
    	try{
        console.info('onCancelItem', fileItem, response, status, headers);
    	}catch(ex){ alert(ex); }
    };
    uploader.onCompleteItem = function(fileItem, response, status, headers) {
        console.info('onCompleteItem', fileItem, response, status, headers);
    };
    uploader.onCompleteAll = function() {
    	console.info('onCompleteAll');
    	var modal = commonService.getVar("modalUploader");
    	modal.closeModal();
    	commonService.broadcastEvent("handleArchivosOK", null);
    };

    console.info('uploader', uploader);
    
}]);