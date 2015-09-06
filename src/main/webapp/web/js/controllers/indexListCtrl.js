define(['app','geoFactory'], function(app,geoFactory){
      
   return app.controller('indexListCtrl', ['$scope','$rootScope','$http','geoFactory', function ($scope,$rootScope,$http,geoFactory) {

            $rootScope.headTitle = $rootScope.title = "未来管家";
            $rootScope.favBol = false;
            $rootScope.backBol = false;
            $rootScope.addBol = true;
            $scope.inde_img = "plannote/web/images/index_banner.jpg";
            //加载更多
            var indexMore = '';
            $scope.getMore = function(){
              $http.get('plannote/index.do').
              success(function(data) {
            	  var data1 = data.webpages;
            	  for(var i=1;i<data1.length;i++){
            		  indexMore+='<li ng-repeat="branch in branchs"><a href="plannote/#/wdxq">';
            		  indexMore+='<dl><dt class="search-key-img">';
            		  indexMore+='<img ng-src="'+data1[i].image+'" src="'+data1[i].image+'"></dt>';
            		  indexMore+='<dd class="search-key-title">';
            		  indexMore+='<p>'+data1[i].title+'</p></dd>';
            		  indexMore+='<dd class="search-key-info">';
            		  indexMore+='<p>'+data1[i].type+'</p></dd>';
            		  indexMore+='<dd class="search-key-tag">';
            		  indexMore+='<p>'+data1[i].summary+'</p>';
            		  indexMore+='</dl></a></li>';
            	  }
            	  angular.element('.list-box ul').append(indexMore);
              });
             
            }

            $http.get('plannote/index.do').
              success(function(data) {

                $scope.webpages = data.webpages;
                
              });

        }])

})