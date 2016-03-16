define(["./module"], function(module) {
    return module.controller("OverViewCtrl", ["$scope",'$window','walletdogAPIs','$filter', function($scope,$window,walletdogAPIs,$filter) {
        $scope.events = [];
        $scope.dayConfig = {
            viewType:"day",
            startDate: new DayPilot.Date()
        }

        $scope.weekConfig = {
            viewType: "Week",
            startDate: new DayPilot.Date()
        }

        $scope.monthConfig = {
            //startDate: $filter('date')(new Date(),'yyyy-MM-dd')
            startDate: new DayPilot.Date()
        }

        $scope.calPrevious = function(time){
            switch(time){
                case 'day':
                    $scope.dayConfig.startDate = $scope.dayConfig.startDate.addDays(-1);
                case 'week':
                    $scope.weekConfig.startDate = $scope.weekConfig.startDate.addDays(-7);
                case'month':
                    $scope.monthConfig.startDate = $scope.monthConfig.startDate.addDays(-30);

            }
        }

        $scope.calNext = function(time){
            switch(time){
                case 'day':
                    $scope.dayConfig.startDate = $scope.dayConfig.startDate.addDays(1);
                case 'week':
                    $scope.weekConfig.startDate = $scope.weekConfig.startDate.addDays(7);
                case'month':
                    $scope.monthConfig.startDate = $scope.monthConfig.startDate.addDays(30);

            }
        }

        $scope.calCurrent = function(){

        }

        $scope.data = $scope.categories = [];

        $scope.gridOption = {
            columnDefs: [
                {displayName:'Category',field: 'category'},
                {displayName:'Expense', field: 'amount'}
            ]
        }
        $scope.monthModel = $scope.yearModel ='';
        $scope.yearValue = ['2013','2014','2015'];
        $scope.monthValue = ['January','February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];


       var countExpense = function(array){
           var total_expense = 0;
           angular.forEach(array,function(a){
               total_expense += a.amount;
           });
           return total_expense;
       }

        /**
         * count the user's expense at same month
         */
        var combined_exp_array = [];
        var countMouthExpense = function(data){

           angular.forEach($scope.monthValue,function(month,index){
               var filtered_exp_array = data.filter(function(d){return (d.month == month)});
               //console.log(filtered_exp_array);
               angular.forEach($scope.categories,function(category){
                   var filtered_exp_array2 = filtered_exp_array.filter(function(a){return a.categoryid == category.categoryid});
                 //  console.log(filtered_exp_array2);
                   if(filtered_exp_array2.length != 0){
                       var total_expense = countExpense(filtered_exp_array2);
                       var array = {
                           category:filtered_exp_array2[0].category,
                           amount: total_expense,
                           month:filtered_exp_array2[0].month
                       };
                       combined_exp_array.push(array);
                   }

               })
           });

            console.log(combined_exp_array);
        }

        /**
         * add 'category' and 'month' to array
         * @param data: expense array
         */
        var pushAttributes = function(data){
            var promise = walletdogAPIs.getAllCategory.getAll().$promise;
            promise.then(function(categoryList){
                $scope.categories = categoryList;
                angular.forEach(data,function(d){
                    d['category'] = walletdogAPIs.findCategoryName(categoryList, d.categoryid);
                    d['month'] = $scope.monthValue[new Date(d.date).getMonth()];    //add 'Month' attribute to the object
                    //console.log(d);
                });
                countMouthExpense(data);
            },function(err){

            });

        }

        var populateCalendar = function(data){
            angular.forEach(data,function(d){
                var new_event = {
                    start:$filter('date')(new Date(d.date), 'yyyy-MM-ddTHH:mm:ss'),
                    end:$filter('date')(new Date(d.date), 'yyyy-MM-ddTHH:mm:ss'),
                    text:d.description
                };
                $scope.events.push(new_event);
            })

        }

        var initialSelectList = function(year){
            var user_expense_array = [];
            var userid = $window.sessionStorage.userid;
            walletdogAPIs.getAllExpense.getAll({id:userid}).$promise.then(function(data){
                populateCalendar(data);
                console.log('Ok to get select list data');
                var choosedYearData = data.filter(function(d){return new Date(d.date).getFullYear() == year});
                pushAttributes(choosedYearData);
                console.log(data);
                //$scope.gridOption.data = $scope.data = data;
            },function(error){
                console.log(error);
                console.log('bad to get select list data!')
            });
        }

        var cleanCache = function(){
            $scope.events = [];
            $scope.gridOption.data = [];
            combined_exp_array = [];

        }

        /**
         * watch the select for month
         */
        $scope.$watch('monthModel',function(new_value, old_value){
            //console.log(new_value);
            $scope.gridOption.data = combined_exp_array.filter(function(d){
                return d.month == new_value;
            })
        });

        $scope.$watch('yearModel',function(new_value,old_value){
           // $scope.gridOption.date = '';
            if(new_value!=''){
                cleanCache();
                var year = new_value;
                initialSelectList(year);
            }

        })

    }]);
});