define(["angular"],function(angular){
    return angular.module('Watchdog.api',['ui.router'])
        .config(['$stateProvider',function($stateProvider){
            $stateProvider.state('watchdog-api',{
                url:'',
                views: {
                    "main": {
                        templateUrl: "",
                        controller: ""
                    }
                },
                resolve:{

                }
            })
        }]);
})