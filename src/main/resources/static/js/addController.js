mainApp.controller('addController', function ($scope, $http) {
  $scope.date = new Date();
  $scope.save = function () {
    var request = $http.post('/api/tree/', $scope.tree);
    request.then(function (response) {
      console.log(response);
      if (response.data.status == 200 || response.data.message == "success") {
        $scope.load();
        $scope.dialog.close();
      }
    });
  }
})