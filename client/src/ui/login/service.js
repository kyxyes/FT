define(['./module'],function(module){
    return module.service('loginService',['$rootScope','walletdogService','$window', function($rootScope,walletdogService,$window){

        var authToken;
        this.setAuth = function(email,pw){
            var basicAuthValue = window.btoa(unescape(encodeURIComponent(email+":"+pw)));
            authToken = 'Basic ' + basicAuthValue;
        }

        this.checkLogin = function(email,callback,callbackError){
            walletdogService.getAuthUrl('api/v1/user/:id',{id:email},authToken).get().$promise.then(function(data){
                $rootScope.userid = data.userid;
                $window.sessionStorage.userid = data.userid;
                callback(data);
            },function(err){
                callbackError(err);
            });
        }
    }]);
})