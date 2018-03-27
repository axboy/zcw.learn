let app = angular.module('myApp', []);

app.service('testService', function () {
    this.println = (str) => {
        console.log(`testService.println() >>> ${str}`);
        //return str;
    }
});

app.filter('testFilter', (testService) => {
    return (str) => {
        testService.println(str)
        return str + "0";
    }
});

app.controller('myCtrl', ($scope, testService) => {
    let vm = this;
    vm.name1 = 'zcw';
    $scope.name = "Volvo";
    $scope.arr = ['a', 'b', 'c'];
    testService.println('asdasdas');
});
