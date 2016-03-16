define(["./module"], function(module) {
    return module.controller("AnalysisCtrl", ["$scope","walletdogAPIs","$window",function($scope,walletdogAPIs,$window) {
        console.log('Hi, this is analysis controller.....');
        $scope.categories = ['column','pie'];
        $scope.highchartsNG = {
            options: {
                chart: {
                    type: 'pie'
                }
            },
            series: [{
                name: 'Category',
                colorByPoint: true,
                data:[
                    ]
            }],
            title: {
                text: 'Analysis of spending data'
            },
            loading: false
        }
        // $http.get('data.json').success(function(response){$scope.highchartsNG.series[0].data = response.records});

        var countTotal = function(data){
            var total = 0;
            angular.forEach(data,function(d){
                total += d.amount;
            });
            return total;
        }

        var pushHighCharts = function(total,name){
            $scope.highchartsNG.series[0].data.push({
                name:name,
                y:total
            })
        }

        var filterCategory = function(data){
            var promise = walletdogAPIs.getAllCategory.getAll().$promise;
            promise.then(function(categories){
                angular.forEach(categories,function(category){
                    var matchedCategoryGroup = data.filter(function(d){return d.categoryid == category.categoryid});
                    var total = countTotal(matchedCategoryGroup);
                    var categoryname = walletdogAPIs.findCategoryName(categories,category.categoryid);
                    pushHighCharts(total,categoryname);
                });
            },function(err){})

        }

        var  populateHighChart = function (){
            var userid = $window.sessionStorage.userid;
            walletdogAPIs.getAllExpense.getAll({id:userid}).$promise.then(
                function(allExpense){
                    filterCategory(allExpense);
                },function(err){
                console.log('bad to populateHighChart');
                console.log(err);
                }
            );
        }
        populateHighChart();

        }
    ]);
});