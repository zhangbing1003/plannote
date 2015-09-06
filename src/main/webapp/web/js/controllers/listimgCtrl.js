define(['app','imgList'], function(app){
      
    app.controller('listimgCtrl', ['$scope','$rootScope','$http', function ($scope,$rootScope,$http) {
            $rootScope.backBol = true;
            $rootScope.favBol = false;

            $http.get('plannote/web/json/listimg.json').
              success(function(data) {

                $scope.pics = data;
                $rootScope.title = $scope.pics.shop_name;
              });
        }])

})