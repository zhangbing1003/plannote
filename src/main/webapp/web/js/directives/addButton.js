/**
 * 添加按钮
 */
define(['app'], function (app) {
    app.directive('addButton', ['$window',function ($window) {
    return {
            restrict: 'A',
            link: function (scope, elem, attrs) {
                elem.bind('click', function () {
                    $window.location = "plannote/#/addNewPlan";
                });
            }             
    }
  }])

})

