define(['app','telBox'], function(app){

	app.controller('addNewPlanCtrl', ['$scope','$rootScope','$http','$sce', function ($scope,$rootScope,$http,$sce) {

		$rootScope.headTitle = $rootScope.title = "添加新计划";
		$rootScope.favBol = false;
		$rootScope.backBol = true;
		$scope.formData = {'title':'张三','content':'dasd4sad5as45d'};
		$scope.submitAddNewPlan = function() {

			$http.post('plannote/addNewPlanNote.do', $scope.formData)
			
			.success(function(data) {
				alert(data.success);
				if (!data.success) {
					// if not successful, bind errors to error variables
					/*$scope.errorName = data.errors.name;
					$scope.errorSuperhero = data.errors.superheroAlias;*/
				} else {
					// if successful, bind success message to message
					/*$scope.message = data.message;*/
				}


			});

		}
	}])

})