/**
 * @description : walletdogAPIs
 * initial the getAuthUrl function according to different actions
 * then pass params to 'get/save/update...'when do call
 * https://docs.angularjs.org/api/ngResource/service/$resource
 * This refers to Ke's API https://github.com/GWU-KIM-CSCI/financial-tracker/blob/master/doc/api-design.md
 */


define(['./module'],function(module){
    function WalletdogAPIs(walletdogService){
        return{
            getUser: walletdogService.getAuthUrl('api/v1/user/:id',{id:'@id'}), //get
            addUser: walletdogService.getAuthUrl('api/v1/user'),   //post
            updateUser: walletdogService.getAuthUrl('api/v1/user/user/:id',{id:'@id'}),  //put
            createCategory: walletdogService.getAuthUrl('api/v1/category'),  //post
            getAllCategory: walletdogService.getAuthUrl('api/v1/category'),  //get
            updateCategory: walletdogService.getAuthUrl('api/v1/category/:id',{id:'@id'}), //put
            createExpense: walletdogService.getAuthUrl('api/v1/:id/expense',{id:'@id'}), //post
            getAllExpense: walletdogService.getAuthUrl('api/v1/:id/expense',{id:'@id'}), //get
            updateExpense: walletdogService.getAuthUrl('api/v1/:id/expense',{id:'@id'}),  //put


            findCategoryName:function(array,id){
                var name = 'N/A';
                angular.forEach(array,function(a){
                    if(a.categoryid == id){
                        name =  a.name;
                    }
                });
                return name;
            }
        }
    }

    return module.service('walletdogAPIs',['walletdogService',WalletdogAPIs]);
})