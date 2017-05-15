mainApp.controller('indexController', function($scope, $http) {
  $http.get("http://localhost:8080/api/tree/").then(function (response) {
    $scope.trees = response.data;
  });
})