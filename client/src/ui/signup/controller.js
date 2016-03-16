define(["./module"],function(module){
    return module.controller("SignUpCtrl",["$scope","$state","walletdogAPIs",function($scope,$state,walletdogAPIs){

        $scope.signup = function(){
            var user = {
                email: $scope.email,
                username: $scope.username,
                pw: $scope.password
            }
            var payload = {"email":user.email,"password":user.pw,"username":user.username}
            walletdogAPIs.addUser.save(payload).$promise.then(function(data){
                console.log('login successfully :');
                console.log(data);
                //jump to login page
                $state.go('login');
            },function(err){

            });
        }
    }])
})