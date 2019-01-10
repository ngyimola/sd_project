var donatorHome = angular.module('donatorHome', ["ngRoute"]);

donatorHome.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "donatorData.htm",
        controller: "donatorDataCtrl"
    })
    .when("/createAppointment", {
        templateUrl : "createAppointment.htm",
        controller: "createAppointmentCtrl"
    })
    .when("/viewAppointment", {
        templateUrl : "appointmentsForUser.htm",
        controller:"viewAppointmentCtrl"
    })
    .when("/viewDonations", {
        templateUrl : "viewDonations.htm",
        controller:"viewDonationsCtrl"
    });
});

donatorHome.controller('logOutCtrl', function($scope) {
  $scope.logOut = function(){
    localStorage.setItem("loggedInUser", null);
    window.location.replace("login.html");
  }
});

donatorHome.controller('donatorDataCtrl', function($scope) {
  $scope.userData = JSON.parse(localStorage.getItem("loggedInDonatorData"));
  $scope.loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"));
});

donatorHome.controller('createAppointmentCtrl', function($scope) {
  $scope.loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"));
  $scope.appointmentDate = "";
  $scope.appointmentHour = "";
  $scope.availableHours = [];
  $scope.getAvailableHours = function(){
    $.ajax({
              type: 'GET',
              url: 'http://localhost:9090/getAvailableAppointments',
              contentType: 'application/json',
              async: true,
              data:{
                  "date": $scope.appointmentDate
              },

              success: function(response) {
                $scope.availableHours = response;
              },
              error: function(response) {
                  alert("Error");
              }
          });
  };
  $scope.addAppointment = function(){
    $.ajax({
              type: 'POST',
              url: 'http://localhost:9090/createAppointment',
              contentType: 'application/json',
              async: true,
              data:JSON.stringify({
                  "username" : $scope.loggedInUser.username,
                  "date": $scope.appointmentDate,
                  "time": $scope.appointmentHour
              }),

              success: function(response) {
                alert("Appointment created successfuly!");
                $scope.appointmentDate = "";
                $scope.appointmentHour = "";
              },
              error: function(response) {
                  alert("Error");
              }
          });
  };
});

donatorHome.controller('viewAppointmentCtrl', function($scope) {
  $scope.loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"));
  $scope.appointments = [{"id":1,"date":"2019-01-10T07:15:00.000+0000","donatorId":1}];
  $scope.getAppointments = function(username){

    $.ajax({
            type: 'GET',
            url: 'http://localhost:9090/allAppointmentsForUser',
            contentType: 'application/json',
            async: true,
            data: {
                "username": $scope.loggedInUser.username
            },

            success: function(response) {
              $scope.appointments = response;
            },
            error: function(response) {
                alert("Error");
            }
        });
    };

      $scope.removeAppointment = function(id){
        $.ajax({
                  type: 'POST',
                  url: 'http://localhost:9090/deleteAppointment',
                  contentType: 'application/json',
                  async: true,
                  data:JSON.stringify({
                      "id" : id
                  }),

                  success: function(response) {
                    alert("Appointment deleted successfuly!");
                    $scope.getAppointments($scope.loggedInUser.username);
                  },
                  error: function(response) {
                      alert("Error");
                  }
              });
      };
});

donatorHome.controller('viewDonationsCtrl', function($scope) {
  $scope.loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"));
  $scope.donations = [];
  $scope.getDonations = function(){

    $.ajax({
            type: 'GET',
            url: 'http://localhost:9090/getDonationsForUser',
            contentType: 'application/json',
            async: true,
            data: {
                "username": $scope.loggedInUser.username
            },

            success: function(response) {
              $scope.donations = response;
            },
            error: function(response) {
                alert("Error");
            }
        });
    };
});
