
define(["angular",
    "ui-router",
    "angular-animate",
    "angular-aria",
    "angular-material",
    "daypilot",
    "angular-ui-grid",
    "angular-resource",
    "highcharts",
    "highcharts-ng",
    './src/ui/home/index',
    './src/ui/login/index',
    './src/ui/overview/index',
    './src/ui/signup/index',
    './src/ui/transaction/index',
    './src/ui/analysis/index',
    './src/api/index'], function(angular) {
    var app = angular.module("app", ["ui.router",
        "ngAnimate",
        // "highcharts",
        "highcharts-ng",
        "ngAria",
        "ngMaterial",
        "daypilot",
        'ui.grid',
        "ngResource",
        'FinancialTracker.app.home',
        'FinancialTracker.app.login',
        'FinancialTracker.app.overview',
        'FinancialTracker.app.signup',
        'FinancialTracker.app.transaction',
        'FinancialTracker.app.analysis',
         'Watchdog.api'])
        .config(['$mdThemingProvider','$urlRouterProvider',function($mdThemingProvider,$urlRouterProvider){
            $mdThemingProvider.theme('default')
                .accentPalette('blue');
           $urlRouterProvider.when('/','home');
           // $urlRouterProvider.otherwise('/')
        }]);
    return app;
});