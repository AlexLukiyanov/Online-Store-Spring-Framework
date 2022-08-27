angular.module('market-front').controller('AOPController', function ($scope, $rootScope, $http) {
    const contextPath = 'http://localhost:8189/app/api/v1';

    $scope.loadStatistic = function () {
        $http.get(contextPath + '/statistic')
            .then(function (response) {
                $scope.MyStatistic = response.data;
            });
    };

    $scope.loadStatistic();
});