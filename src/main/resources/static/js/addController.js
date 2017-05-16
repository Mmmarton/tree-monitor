mainApp.controller('addController', function ($scope, $http) {
  $scope.save = function () {
    var request = $http.post('/api/tree/', $scope.tree);
    request.then(function (response) {
      console.log(response);
    });
  }
})