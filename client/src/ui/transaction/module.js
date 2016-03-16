/**
 * Created by yuxinkang on 11/1/15.
 */
define(["angular"], function(angular) {
    return angular.module('FinancialTracker.app.transaction',['ui.grid','ui.grid.pagination'])
        .config(['$stateProvider', '$urlRouterProvider','$windowProvider', function($stateProvider, $urlRouterProvider,$windowProvider) {
            $stateProvider.state('transaction',{
                url:"/transaction",
                views: {
                    "main": {
                        templateUrl: "./src/ui/transaction/transaction.html",
                        controller: "TransactionCtrl"
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