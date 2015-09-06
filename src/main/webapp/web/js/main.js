/**
 * 入口文件
 * 2014-11-30 mon
 */
require.config({
    baseUrl: "plannote/web/js/",
    paths: {
      "jquery": "libs/jquery203",
      "fastclick" : "libs/fastclick",
      "blocksit" : "libs/blocksit",
      "idTabs" : "libs/idTabs",
      "stickUp" : "libs/stickUp",
      "angular" : "libs/angular.min",
      "angular-route" : "libs/angular-route.min",
      "angular-sanitize" : "libs/angular-sanitize.min",

      "telBox" : "directives/telBox",
      "fav" : "directives/fav",
      "imgList" : "directives/imgList",
      "geo" : "directives/geo",
      "backButton" : "directives/backButton",
      "addButton" : "directives/addButton",
      "fliterBox" : "directives/fliterBox",
      "jrTab" : "directives/jrTab",
      "fixBar" : "directives/fixBar",
      
      "geoFactory" : "services/geoFactory",

      "app" : "controllers/app",
      "loadingInterceptor" : "controllers/loadingInterceptor",
      "indexListCtrl" : "controllers/indexListCtrl",
      "detailCtrl" : "controllers/detailCtrl",
      "shxqCtrl" : "controllers/shxqCtrl",
      "shListCtrl" : "controllers/shListCtrl",
      "listimgCtrl" : "controllers/listimgCtrl",
      "jrListCtrl" : "controllers/jrListCtrl",
      "lcxqCtrl" : "controllers/lcxqCtrl",
      "jjxqCtrl" : "controllers/jjxqCtrl",
      "addNewPlanCtrl" : "controllers/addNewPlanCtrl",
      "route" : "routes/appRoute",
    },
    shim: {
       'angular': {
          exports: 'angular'
       },
       'angular-route':{
          deps: ["angular"],
          exports: 'angular-route'
       },
       'angular-sanitize':{
          deps: ["angular"],
          exports: 'angular-sanitize'
       },
       'blocksit':{
          deps: ["jquery","angular"],
          exports: 'blocksit'
       },
       'idTabs': {
          deps: ['jquery'],
          exports: 'idTabs'
       },
       'stickUp': {
          deps: ['jquery'],
          exports: 'stickUp'
       }
    }
});


require(['jquery','angular','angular-route','angular-sanitize','app','loadingInterceptor','route','telBox','fav','geo','geoFactory','indexListCtrl','detailCtrl','shxqCtrl','shListCtrl','listimgCtrl','jrListCtrl','lcxqCtrl','jjxqCtrl','blocksit','backButton','addButton','fliterBox','idTabs','jrTab','stickUp','fixBar','addNewPlanCtrl'],function ($,angular){

      $(function () {

            angular.bootstrap(document,["pinganApp"]);

      })

});