define(["angular"], function(angular) {
    return angular.module('FinancialTracker.app.home',[])
    .config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
        $stateProvider.state('home', {
            url:"/home",
            views: {
                "main": {
                    templateUrl: "./src/ui/home/home.html",
                    controller: "HomeCtrl"
                }
            }
        });
        $urlRouterProvider.otherwise('/');
    }]);
});