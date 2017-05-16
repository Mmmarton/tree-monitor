mainApp.controller('editController', function ($scope, $http) {
  $http.get("/api/tree/" + $scope.id).then(function (response) {
    $scope.tree = response.data;
  });

  $scope.save = function () {
    var request = $http.post('/api/tree/' + $scope.id, $scope.tree);
    request.then(function (response) {
      console.log(response);
      if (response.data.status == 200 || response.data.message == "success") {
        $scope.load();
        $scope.dialog.close();
      }
    });
  }
})