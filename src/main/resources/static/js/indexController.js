mainApp.controller('indexController', function ($scope, $http, ngDialog) {
  $http.get("/api/tree/").then(function (response) {
    $scope.trees = response.data;
  });
  $scope.orderBy = function (field) {
    $scope.orderField = field;
  }

  $scope.openAddModal = function () {
    ngDialog.open({template: 'add.html', className: 'ngdialog-theme-default'});
  };

  $scope.delete = function (id) {
    console.log(id);
    var request = $http.delete('/api/tree/' + id);
    request.then(function (response) {
      console.log(response);
    });
  }
})