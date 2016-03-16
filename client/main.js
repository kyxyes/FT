
require.config({
    baseUrl: '/',
    paths: {
        "angular": '/src/ui/bower_components/angular/angular',
        "ui-router": '/src/ui/bower_components/angular-ui-router/release/angular-ui-router',
        "jquery": './src/ui/bower_components/jquery/dist/jquery',
        "angular-animate": '/src/ui/bower_components/angular-animate/angular-animate',
        "angular-aria":'/src/ui/bower_components/angular-aria/angular-aria',
        "angular-material":'/src/ui/bower_components/angular-material/angular-material',
        "angular-resource":'/src/ui/bower_components/angular-resource/angular-resource',
        "daypilot": "/src/ui/external/daypilot/daypilot-all.min",
        "angular-ui-grid":"/src/ui/bower_components/angular-ui-grid/ui-grid",
        "highcharts":"/src/ui/bower_components/highcharts/highcharts",
        "highcharts-ng":"/src/ui/bower_components/highcharts-ng/dist/highcharts-ng"
    },
    shim: {
        "angular": {
            exports: "angular",
            deps: ["jquery"]
        },
        "ui-router": {
            exports: "ui-router",
            deps: ["angular"]
        },
        "jquery": {
            exports: "jquery"
        },
        "daypilot":{
            exports:"daypilot",
            deps: ["angular"]
        },
        "angular-aria":['angular'],
        "angular-animate":['angular'],
        "angular-material":['angular'],
        "angular-resource":['angular'],
        "angular-ui-grid": {
            exports:"angular",
            deps: [
                'angular'
            ]
        },
        "highcharts":{
            exports: "highcharts",
            deps:["angular"]
        },
        "highcharts-ng":{
            exports: "highcharts-ng",
            deps: ["highcharts","jquery"]
        }


    }//,
    // kick start application
    //deps: ['app']
});



require(["jquery",
    "angular",
    "app",
    "./appCtrl"    //load this after loading app.js
], function($, angular) {
    $(function() {
        angular.bootstrap(document, ["app"]);
    });
});
