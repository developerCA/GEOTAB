'use strict';

angular.module('common.Service', [])
	.service('commonService', function ($rootScope) {

			var hash = [];
			
			this.broadcastEvent = function(handler, data){
				$rootScope.$broadcast(handler, data);
			};
			
			this.emitEvent = function(handler, data){
				$rootScope.$emit(handler, data);
			};
			
			this.setVar = function(key, value){
				hash[key] = value;
			};
			this.getVar = function(key){
				return hash[key];
			};
			this.clearAll = function(){
				hash = [];
				return;
			};
			this.findByField = function(args){
				if(!(args.list==undefined)){
					for(var i=0; i<args.list.length;i++){
						//if(args.list[])
					}
				}
				return null;
			};

    });