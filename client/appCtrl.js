/**
 * Created by yuxinkang on 12/2/15.
 */
define(['./app'],function(app){
   app.config(['$urlRouterProvider',function($urlRouterProvider){
       console.log(location);
       if(location.hash == ''){
           location.hash = '#/home';
       }
   }])
});