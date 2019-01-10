var app3 = angular.module('app3', ["ngRoute"]);

app3.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "allAppointments.htm",
        controller: "allAppointmentsCtrl"
    })
    .when("/donators", {
        templateUrl : "allDonators.htm",
        controller: "allDonatorsCtrl"
    })
    .when("/donations", {
        templateUrl : "allDonations.htm",
        controller:"allDonationsCtrl"
    });
});

app3.controller('logOutCtrl', function($scope) {
  $scope.logOut = function(){
    localStorage.setItem("loggedInUser", null);
    window.location.replace("login.html");
  }
});

app3.controller('allAppointmentsCtrl', function($scope) {
    //$scope.loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"));
    $scope.appointments = [];
    $scope.getAllAppointments = function(){

      $.ajax({
              type: 'GET',
              url: 'http://localhost:9090/allAppointments',
              contentType: 'application/json',
              async: true,
              success: function(response) {
                $scope.appointments = response;
              },
              error: function(response) {
                  alert("Error");
              }
          });
      };

        $scope.rejectAppointment = function(id){
          $.ajax({
                    type: 'POST',
                    url: 'http://localhost:9090/rejectAppointment',
                    contentType: 'application/json',
                    async: true,
                    data:JSON.stringify({
                        "id" : id
                    }),

                    success: function(response) {
                      alert("The appointment was rejected");
                      $scope.getAllAppointments();
                    },
                    error: function(response) {
                        alert("Error");
                    }
                });
        };

        $scope.acceptedAppDivHidden = true;
        $scope.acceptedAppointmentId = 0;

        $scope.acceptAppointment = function(id) {
          $scope.acceptedAppDivHidden = false;
          $scope.acceptedAppointmentId = id;
        };

        $scope.saveDonation = function(quantity) {
          if($scope.addDonationFromApp.$valid) {
            $.ajax({
              type: 'POST',
              url: 'http://localhost:9090/addDonation',
              contentType: 'application/json',
              async: true,
              data:JSON.stringify({
                  "id": $scope.acceptedAppointmentId,
                  "quantity": quantity
              }),

              success: function(response) {
                alert("Donation added.");
              },
              error: function(response) {
                  alert("Error");
              }
          });
          }
        };
});

app3.controller('allDonatorsCtrl', function($scope) {
  $scope.typeFilter = "all";
  $scope.rhFilter = "all";

  $scope.donators = [];
  $scope.filterDonators = function(){
    if($scope.typeFilter === "all" && $scope.rhFilter === "all") {
      $.ajax({
        type: 'GET',
        url: 'http://localhost:9090/allDonators',
        contentType: 'application/json',
        async: true,
        success: function(response) {
          $scope.donators = response;
        },
        error: function(response) {
            alert("Error");
        }
    });
  } else if ($scope.typeFilter === "all" && $scope.rhFilter !== "all") {
      $.ajax({
        type: 'GET',
        url: 'http://localhost:9090/filterByRh',
        contentType: 'application/json',
        async: true,
        data:{
            "rh": $scope.rhFilter
        },
        success: function(response) {
          $scope.donators = response;
        },
        error: function(response) {
            alert("Error");
        }
      });
  } else if ($scope.typeFilter !== "all" && $scope.rhFilter === "all") {
      $.ajax({
        type: 'GET',
        url: 'http://localhost:9090/filterByType',
        contentType: 'application/json',
        async: true,
        data:{
            "bloodType": $scope.typeFilter
        },
        success: function(response) {
          $scope.donators = response;
        },
        error: function(response) {
            alert("Error");
        }
      });
  } else {
      $.ajax({
        type: 'GET',
        url: 'http://localhost:9090/filterByTypeAndRh',
        contentType: 'application/json',
        async: true,
        data:{
            "bloodType": $scope.typeFilter,
            "rh": $scope.rhFilter
        },
        success: function(response) {
          $scope.donators = response;
        },
        error: function(response) {
            alert("Error");
        }
      });
  }
};

$scope.updateDonator = function(item) {
  $.ajax({
    type: 'POST',
    url: 'http://localhost:9090/updateDonator',
    contentType: 'application/json',
    async: true,
    data:JSON.stringify({
        "donator": item
    }),

    success: function(response) {
      $scope.filterDonators();
    },
    error: function(response) {
        alert("Error");
    }
});
};

$scope.notifyAllDonators = function(){
  for(var i = 0; i < $scope.donators.length; i++) {
    $.ajax({
      type: 'POST',
      url: 'http://localhost:9090/notifyDonator',
      contentType: 'application/json',
      async: true,
      data:JSON.stringify({
          "name": $scope.donators[i].name
      }),
      error: function(response) {
          alert("Error");
      }
  });
  }
  alert("The notifications were sent.");
};

$scope.notifyDonator = function(name){
  $.ajax({
      type: 'POST',
      url: 'http://localhost:9090/notifyDonator',
      contentType: 'application/json',
      async: true,
      data:JSON.stringify({
          "name": name
      }),
      success: function(response) {
          alert("The notification was sent.");
      },
      error: function(response) {
          alert("Error");
      }
  });
};

});

app3.controller('allDonationsCtrl', function($scope) {
  $scope.donations = [];
  $scope.getAllDonations = function(){

    $.ajax({
            type: 'GET',
            url: 'http://localhost:9090/allDonations',
            contentType: 'application/json',
            async: true,
            success: function(response) {
              $scope.donations = response;
            },
            error: function(response) {
                alert("Error");
            }
        });
    };

  $scope.updateDonation = function(item) {
    $.ajax({
            type: 'POST',
            url: 'http://localhost:9090/updateDonation',
            contentType: 'application/json',
            async: true,
            data:JSON.stringify({
                "donationId": item.id,
                "state": item.state,
                "message": item.message
            }),
            error: function(response) {
                alert("Error");
            }
        });
  };

});
