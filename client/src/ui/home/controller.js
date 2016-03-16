define(["./module"], function(module) {
    return module.controller("HomeCtrl", ["$scope", function($scope) {
        console.log('Hi, this is controller.....');

            console.log('run');
            $scope.imagePath = '/src/pic/home-pic.png';

    }]);
});