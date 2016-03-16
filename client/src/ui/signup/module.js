/**
 * Created by yuxinkang on 11/1/15.
 */
define(["angular"], function(angular) {
    return angular.module('FinancialTracker.app.signup',[])
        .config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
            $stateProvider.state('signup',{
                url:"/signup",
                views: {
                    "main": {
                        templateUrl: "./src/ui/signup/signup.html",
                        controller: "SignUpCtrl"
                    }
                },
                resolve:{
                   //$title:['$rootScope',function($rootScope){
                   //    return 'API'
                   //}]
                }
            });
            $urlRouterProvider.otherwise('/');
        }]);
});