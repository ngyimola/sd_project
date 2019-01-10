var app1 = angular.module('app1', []);

app1.controller('ctrl1', function($scope) {
  $scope.username = "";
  $scope.password = "";

  $scope.errorMessage ="";

  $scope.tryToLogIn = function() {
    $.ajax({
                type: 'GET',
                url: 'http://localhost:9090/login',
                contentType: 'application/json',
                async: true,
                data:{
                    "username": $scope.username,
                    "password": $scope.password
                },

                success: function(response) {
                    if(response.type === "nurse") {
                      localStorage.setItem("loggedInUser", JSON.stringify(response));
                      window.location.replace("nurseHome.html");
                    } else if (response.type === "donator"){
                      localStorage.setItem("loggedInUser", JSON.stringify(response));

                    } else {
                      localStorage.setItem("loggedInUser", null);
                      $scope.errorMessage = "Something went wrong."
                    }

                },
                error: function(response) {
                    alert("Error");
                },
                complete: function() {
                  $scope.loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"));
                  if($scope.loggedInUser.type === "donator") {
                    $.ajax({
                                type: 'GET',
                                url: 'http://localhost:9090/donatorData',
                                contentType: 'application/json',
                                async: true,
                                data:{
                                    "username": $scope.loggedInUser.username
                                },

                                success: function(response) {
                                  localStorage.setItem("loggedInDonatorData", JSON.stringify(response));
                                },
                                error: function(response) {
                                    alert("Error");
                                },
                                complete: function(){
                                  window.location.replace("donatorHome.html");
                                }

                            });
                  }
                }
            });
  };



  $scope.saveUser = function(userInfo) {
    if($scope.registerForm.$valid) {
      var u = userInfo.username;
      $.ajax({
        type: 'POST',
        url: 'http://localhost:9090/register',
        contentType: 'application/json',
        async: true,
        data:JSON.stringify({
            "username": u,
            "password": userInfo.password,
            "name": userInfo.name,
            "bloodType": userInfo.bloodType,
            "rh": userInfo.rh,
            "dateOfBirth": userInfo.dateOfBirth,
            "phoneNumber": userInfo.phoneNumber
        }),

        success: function(response) {
            if(response === true) {
              alert("Successful registration");
              $("registerForm").trigger("reset");
            } else {
              alert("Something went wrong. Please check your connection.");
            }

        },
        error: function(response) {
            alert("Error");
        }
    });
    }
  };



});
