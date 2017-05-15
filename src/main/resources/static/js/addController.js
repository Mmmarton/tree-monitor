mainApp.controller('addController', function ($scope, $http) {
  $scope.save = function () {
    console.log("2");
    var request = {
      method: 'POST',
      url: '"/api/tree/"',
      headers: {
        'Content-Type': 'application/json'
      },
      data: $scope.tree
    }
    $http.post(request).then(function (response) {
      alert(response);
    });
  }
})