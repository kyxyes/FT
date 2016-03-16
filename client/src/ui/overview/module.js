/**
 * Created by yuxinkang on 11/1/15.
 */
define(["angular"], function(angular) {
    return angular.module('FinancialTracker.app.overview',[])
    .config(['$stateProvider', '$urlRouterProvider','$windowProvider' ,function($stateProvider, $urlRouterProvider,$windowProvider) {
        $stateProvider.state('overview',{
            url:"/overview",
            views: {
                "main": {
                    templateUrl: "./src/ui/overview/overview.html",
                    controller: "OverViewCtrl"
                }
            },
            // pre-load init before loading this module make sure login first
            resolve:{
                init: function(){
                    var $window = $windowProvider.$get();
                    if(typeof $window.sessionStorage.userid == 'undefined' ){
                        $window.location.href = '#login';
                    }

                }

            }
        });
        $urlRouterProvider.otherwise('/');
    }]);
});