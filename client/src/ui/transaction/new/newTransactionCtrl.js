define(['./../module'],function(module){
    return module.controller('newTransactionCtrl',['$scope','$rootScope','$mdDialog','$filter','walletdogAPIs','$window','categories' ,
        function($scope,$rootScope,$mdDialog,$filter,walletdogAPIs,$window,categories){

            function changeDateFormat(date){
              return $filter('date')(date,'yyyy-MM-dd HH:mm:ss');
             }

            function checkDirty(data){
                $scope.errorMessage = {};
                if(typeof data.userid=='undefined'&&
                    typeof data.amount=='undefined'&&
                    typeof data.categoryid=='undefined'&&
                    typeof data.location=='undefined'&&
                    typeof data.description=='undefined'&&
                    typeof data.date=='undefined'
                    ){
                    return false;
                }
                if(data.date==null||typeof data.date=='undefined'){
                    $scope.errorMessage['Datefailed'] = 'Please input right date';
                    return false;
                }
                return true;
            }

           console.log('User Id is:'+ $window.sessionStorage.userid);
           $scope.categories = categories;
           $scope.confirm = function(){
               var expense_payload = {
                   userid: $window.sessionStorage.userid,
                   amount:$scope.transactionAmount,
                   date: changeDateFormat($scope.transactionDate),
                   categoryid:$scope.categorySelect,
                   location: $scope.transactionLocation,
                   description: $scope.transactionDescription
               };
              if(checkDirty(expense_payload)){
                   walletdogAPIs.createExpense.save({id:expense_payload.userid},expense_payload).$promise.then(function(data){
                       console.log('expense save successfully:');
                       console.log(data);
                       $mdDialog.hide(data);  //pass back data
                   },function(error){
                       console.log('bad save');
                   });
                   }
           }
          $scope.cancel = function(){
            $mdDialog.cancel();
          }

    }])
})