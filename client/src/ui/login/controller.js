define(['./module'],function(module){
    return module.controller('LoginCtrl',['$rootScope','$scope','$state','loginService','$window' ,function($rootScope, $scope, $state,loginService,$window){

        $scope.errorMessage = {};
        //$window.sessionStorage.loginStatus = false;
        $rootScope.loginStatus = false; //$window.sessionStorage.loginStatus;
        //$scope.loginForm = {};

        $scope.emailLogin = function(){
            var user = {
                email: $scope.email,
                pw: $scope.password
            }
            loginService.setAuth(user.email, user.pw);
            loginService.checkLogin(user.email,function(data){
                console.log('login successfully: ');
                console.log(data);
                $window.sessionStorage.loginStatus = true;
                $rootScope.loginStatus = $window.sessionStorage.loginStatus;
                $window.location.href = '#overview';
                $window.location.reload();
            },function(error){
                console.log('login badly: ');
                console.log(error);
               // $scope.loginForm['password'].$setValidity('server',false);
                $scope.errorMessage['loginfailed'] = 'Incorrect Email and Password!'
            });
        }

        $scope.fbLogin = function(){

        }
    }]);
})