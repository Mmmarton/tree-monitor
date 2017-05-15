mainApp.controller('indexController', function ($scope, $http, ngDialog) {
  $http.get("http://localhost:8080/api/tree/").then(function (response) {
    $scope.trees = response.data;
  });
  $scope.orderBy = function (field) {
    $scope.orderField = field;
  }

  $scope.clickToOpen = function () {
    ngDialog.open({template: 'add.html', className: 'ngdialog-theme-default'});
  };
})