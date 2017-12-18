(function() {
	var module;

	module = angular.module('flotchart', []);
	
	module.service('chartService', function() {
		this.returnType = function(type, data, args) {
			if (type == 'pie') {
	        	return {	        		
	        		data:data,
	        		attributes:{}	        		
	        	}
			} else if (type == 'bar') {
				return {					
					data:{color: "orange", bars: {show: true, align: "center", barWidth: 0.25}, data: data, label: "data4"},
					attributes:{}
				}
			}
			else if (type == 'spline') {
				
				if(args==undefined) return null;
				
				return {
					
					data:[{ data: data, points: { show: true, radius: 6}, splines: { show: true, tension: 0.45, lineWidth: 5, fill: 0 }}],
					attributes:{}					
				}
				
			}
		}
	});

	module.directive('flotChart', function(chartService) {
		return {
			require : "ngModel",
			restrict : 'E',
			replace : true,
			template : '<div></div>',
			scope: {
				ngModel: '=',
				chartType: '@',
				chartHeight: '@',
				chartArgs: '@',
			},
			link : function(scope, element, attrs) {
				
				scope.$watch('ngModel', function(model){
					
					var args = scope.$eval(scope.chartArgs);
														
					if(model) {
						try{
							scope.chartOptions = chartService.returnType(scope.chartType, model, scope.chartArgs);
							
							element.css('height', scope.chartHeight);
							
							if(scope.chartOptions==null){
							
								element.html("Error al crear el gráfico. Posiblemente algún argumento no fue especificado.");
								
							}else{
								if(scope.chartType=="pie"){
			                    	$.plot(element, scope.chartOptions.data, {
			            				series: {
			            					pie: { 
			            						innerRadius: 0.7,
			            						show: true,
			            						stroke: { width: 0 }		            						
			            					},
			            					tooltip: true, 
			            					tooltipOpts: { content: '%s: %p.0%' }
			            				}
			            			});			                    	
			                    }else if(scope.chartType=="bar"){
			                    	$.plot(element, { data: model, bars: {show: true, align: "center", barWidth: 0.25}}, {
			                			legend: {
			                				position: "sw",
			                				show: true
			                			},
			                			series: {
			                				lines: {
			                					show: false
			                				}
			                			},
			                			xaxis: {
			                				min: 0.6,
			                				max: 3.1
			                			},
			                			yaxis: {
			                				min: 0,
			                				max: 3.5
			                			},
			                			zoom: {
			                				interactive: true
			                			},
			                			pan: {
			                				interactive: true
			                			}
			                		});
			                    }else{
			                    	//$.plot(element, scope.chartOptions.data, scope.attributes);
			                    	$.plot(element, scope.chartOptions.data, {
			                    		colors: [args.colors], 
                                        series: { shadowSize: 3 },
                                        xaxis:{ 
                                        font: { color: '#ccc' },
                                        position: 'bottom',
                                        ticks: args.ticks
                                        },
                                        yaxis:{ font: { color: '#ccc' } },
                                        grid: { hoverable: true, clickable: true, borderWidth: 0, color: '#ccc' },
                                        tooltip: true,
                                        tooltipOpts: { content: ' Las ventas para el mes %x.0  fueron de : %y.2',  defaultTheme: false, shifts: { x: 0, y: 20 } }
                                      
                                                      
			                    	});
			                    }	
								
							}
							element.show();
							
						}catch(ex){
							console.error(ex);
							element.html("Error al crear el gráfico");
							
						}
	                }
	            });							
					
			}

		};

	})
}).call(this);