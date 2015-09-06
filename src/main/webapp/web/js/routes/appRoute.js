/**
 * 路由
 */
define(['app'], function(app){
  
   return app.config(['$routeProvider',function($routeProvider) {
            $routeProvider
              .when('/', {
                templateUrl: 'plannote/web/js/views/wd/list.html',
                controller: 'indexListCtrl'
              })
              .when('/detail', {
                templateUrl: 'plannote/web/js/views/wd/detail.html',
                controller: 'detailCtrl'
              })
              .when('/sh', {
                templateUrl: 'plannote/web/js/views/sh/list.html',
                controller: 'shListCtrl'
              })
              .when('/shxq', {
                templateUrl: 'plannote/web/js/views/sh/xq.html',
                controller: 'shxqCtrl'
              })
              .when('/listimg', {
                templateUrl: 'plannote/web/js/views/sh/listimg.html',
                controller: 'listimgCtrl'
              })
              .when('/jr', {
                templateUrl: 'plannote/web/js/views/jr/list.html',
                controller: 'jrListCtrl'
              })
              .when('/lcxq', {
                templateUrl: 'plannote/web/js/views/jr/lcxq.html',
                controller: 'lcxqCtrl'
              })
              .when('/jjxq', {
                templateUrl: 'plannote/web/js/views/jr/jjxq.html',
                controller: 'jjxqCtrl'
              })
              .when('/addNewPlan', {
                templateUrl: 'plannote/web/js/views/add/addNewPlan.html',
                controller: 'addNewPlanCtrl'
              })
              .otherwise({ redirectTo: '/' });

              //$locationProvider.html5Mode(true).hashPrefix('!');

   }])
   
  
})