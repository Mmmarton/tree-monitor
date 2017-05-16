mainApp.controller('indexController', function ($scope, $http, ngDialog) {
  $scope.load = function () {
    $http.get("/api/tree/").then(function (response) {
      $scope.trees = response.data;
    });
  };
  $scope.load();

  $scope.orderBy = function (field) {
    $scope.orderField = field;
  }

  $scope.openAddModal = function () {
    $scope.dialog = ngDialog.open({
      template: 'add.html',
      className: 'ngdialog-theme-default',
      scope: $scope
    });
  };

  $scope.openEditModal = function (id) {
    $scope.id = id;
    $scope.dialog = ngDialog.open({
      template: 'edit.html',
      className: 'ngdialog-theme-default',
      scope: $scope
    });
  };

  $scope.delete = function (id) {
    console.log(id);
    var request = $http.delete('/api/tree/' + id);
    request.then(function (response) {
      console.log(response);
      if (response.data.status == 200 || response.data.message == "success") {
        $scope.load();
      }
    });
  }
})