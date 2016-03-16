/**
 * Created by yuxinkang on 11/1/15.
 */
define(["angular"], function(angular) {
    return angular.module('FinancialTracker.app.login',[])
        .config(['$stateProvider', '$urlRouterProvider','$windowProvider' ,function($stateProvider, $urlRouterProvider,$windowProvider) {
            $stateProvider.state('login',{
                url:"/login",
                views: {
                    "main": {
                        templateUrl: "./src/ui/login/login.html",
                        controller: "LoginCtrl"
                    }
                },
                resolve:{
                    init: function(){
                        var $window = $windowProvider.$get();
                        //if($window.location.href)
                    }
                }
            });
            $urlRouterProvider.otherwise('/');
        }]);
});