define(['./../module'],function(module){
    return module.controller('newCategory',['$scope','$rootScope','$mdDialog','walletdogAPIs', function($scope,$rootScope,$mdDialog,walletdogAPIs){

        $scope.confirm = function(){
            var category_payload = {
                name:$scope.categoryName,
                description: $scope.categoryDescription
            };

            walletdogAPIs.createCategory.save(category_payload).$promise.then(function(data){
                console.log('category save successfully:');
                console.log(data);
                $mdDialog.hide(data);  //pass back data
            },function(error){
                console.log('bad save');
            });
        }

        $scope.cancel = function(){
            $mdDialog.cancel();
        }

    }])
})