var routeApp = angular.module('routeApp',['ngRoute']);  
routeApp.config(['$routeProvider',function ($routeProvider) {  
    $routeProvider  
        .when('/list', {  
            templateUrl: 'web/rote/list.jsp',  
            controller: 'RouteListCtl'  
        })  
        .when('/list/:id', {  
            templateUrl: 'web/rote/detail.jsp',  
            controller: 'RouteDetailCtl'  
        })  
        .otherwise({  
            redirectTo: '/list'  
        });  
}]);  