define(['./module'],function(module){
    return module.controller("TransactionCtrl",['$scope','$rootScope','$mdDialog','walletdogAPIs','$window', '$state',
        function($scope,$rootScope,$mdDialog,walletdogAPIs,$window,$state) {

        function findCategoryName(array, id){
             var name = 'N/A';
              angular.forEach(array,function(a){
                  if(a.categoryid == id){
                      name =  a.name;
                  }
              });
            return name;
        };

        $scope.gridOption_MyTransaction = {
            enablePaginationControls: false,
            paginationPageSize: 25,
            columnDefs: [
                {field: 'Date'},
                {field: 'Amount'},
                {field: 'Location'},
                {field: 'Category'},
                {field: 'Description'}
            ]//,
            //data: [
            //    {
            //        Date: '18/10/2015',
            //        Amount: '$43',
            //        Location: 'KFC',
            //        Category: 'food',
            //        Description: 'dinner with friend'
            //    }
            //]
        }

        $scope.gridOption_MyTransaction.onRegisterApi = function(gridApi){
            $scope.gridApiMT = gridApi;
        }

        //$scope.categories = [{'name':'food','description':'happy'},{'name':'travel','description':'Metro'},{'name':'entertainment','description':'happy2'},{'name':'entertainment','description':'happy2'}];
        $scope.categories = [];
        $scope.createNewExpense = function(){
             $mdDialog.show({
                 controller:'newTransactionCtrl',
                 templateUrl:'/src/ui/transaction/new/newTransaction.html',
                 locals:{
                     categories:$scope.categories    //pass categories array to newTransactionCtrl
                 }
             }).then(function(data){
                 $scope.gridOption_MyTransaction.data.push(
                     {
                         Date:data.date,
                         Amount: data.amount,
                         Location: data.location,
                         Category:findCategoryName($scope.categories,data.categoryid),
                         Description: data.description
                     }
                 );
             });
        }

        var initialExpenseList = function(){
            var userid = $window.sessionStorage.userid;
            walletdogAPIs.getAllExpense.getAll({id:userid}).$promise.then(function(data){
                console.log('Get users '+userid +' expense:');
                console.log(data);
                angular.forEach(data,function(d){
                    var expense = {
                        Date: d.date,
                        Amount: d.amount,
                        Location: d.location,
                        Category: findCategoryName($scope.categories, d.categoryid),
                        Description: d.description
                    };
                    $scope.gridOption_MyTransaction.data.push(expense);
                });
            },function(error){
                console.log('Bad get users '+userid +' expense');
                console.log(error);
            })
        }

        var initialCategoryList = function(){
           return walletdogAPIs.getAllCategory.getAll().$promise.then(function(data){
                angular.forEach(data,function(d){
                    var category = {
                        categoryid: d.categoryid,
                        name: d.name,
                        description: d.description
                    }
                    $scope.categories.push(category);
                });
            },function(error){
                console.log('Bad initialize Category')
            })
        }
        var initialTransactionPage = function(){
                initialCategoryList().then(function(){
                    initialExpenseList();  //do this after initialize categories list
                });
        }

        initialTransactionPage();

        $scope.createNewCategory = function(){
            $mdDialog.show({
                controller:'newCategory',
                templateUrl:'/src/ui/transaction/new/newCategory.html'
            }).then(function(data){
                var newCategory = {
                    name: data.name,
                    description: data.description
                }
                $scope.categories.push(newCategory);
            })
        }
    }])
});