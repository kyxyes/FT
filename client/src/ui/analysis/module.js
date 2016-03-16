/**
 * Created by yuxinkang on 11/1/15.
 */
define(["angular"], function(angular) {
    console.log("module.js from analysis was called");
    return angular.module('FinancialTracker.app.analysis',["highcharts-ng"])
    .config(['$stateProvider', '$urlRouterProvider','highchartsNGProvider','$windowProvider',function($stateProvider, $urlRouterProvider,highchartsNGProvider,$windowProvider) {
        highchartsNGProvider.lazyLoad();
        $stateProvider.state('analysis',{
            url:"/analysis",
            views: {
                "main": {
                    templateUrl: "./src/ui/analysis/analysis.html",
                    controller: "AnalysisCtrl"
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