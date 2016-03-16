/**@description
 * this will be wraped as a resouce class data,
 *  xx.getAuth.get(authUrl,param)
 *  if authUrl already be initialed, then pass id to it
 *  param is the data to post
 *  check https://docs.angularjs.org/api/ngResource/service/$resource
 */
define(['./module'],function(module){
    return module.service('walletdogService',['$rootScope','$resource','$window',function($rootScope,$resource,$window){
          function urljoin() {
             return [].slice.call(arguments).join('/');    //we can't do var a = arguments.slice();  check http://stackoverflow.com/questions/7056925/how-does-array-prototype-slice-call-work
          }


          this.getAuthUrl = function(path,param,authToken){
              if(typeof authToken != 'undefined'){
                  $rootScope.authToken =  authToken;
                  $window.sessionStorage.token = authToken
              }else{
                  authToken = $window.sessionStorage.token;   //store token in sessionStorage
              }
              var  host = 'http://localhost:8080/application';
              var   authUrl = urljoin(host,path);
              return $resource(
                  authUrl,
                  param,
                  {
                      'get':{
                          method: 'GET',
                          headers:{'Authorization':authToken}
                      },
                      'update':{
                          method:'PUT',
                          headers: {'Authorization':authToken}
                      },
                      'save':{
                          method: 'POST',
                          headers: {'Authorization':authToken}
                      },
                      'delete':{
                          method: 'DELETE',
                          headers: {'Authorization':authToken}
                      },
                      'query':{
                          method: 'GET',
                          isArray: true,
                          headers: {'Authorization':authToken}
                      },
                      getAll: {
                          method: 'GET',
                          isArray: true,
                          headers: {'Authorization':authToken}
                      }
                  }
              )
          }
    }]);
});


