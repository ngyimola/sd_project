var createApp = angular.module('createApp', []);

createApp.controller('mainCtrl', function($scope) {
  $scope.loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"));
  $scope.appointmentDate;
  $scope.appointmentHour;


});
