// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
angular.module('starter', ['ionic'])

.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    if(window.cordova && window.cordova.plugins.Keyboard) {
      // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
      // for form inputs)
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);

      // Don't remove this line unless you know what you are doing. It stops the viewport
      // from snapping when text inputs are focused. Ionic handles this internally for
      // a much nicer keyboard experience.
      cordova.plugins.Keyboard.disableScroll(true);
    }
    if(window.StatusBar) {
      StatusBar.styleDefault();
    }
  });
})
.controller('lg',function ($scope,$window) {
$scope.validate=function(){
    
    var x=$scope.use;
    var y=$scope.pas;
    
    if(x===undefined|y===undefined)
        {
            alert("Missing details");
        }
    else
        {
            if(x===localStorage.getItem('us'+x) & y===localStorage.getItem('pass'+y))
                {
                    $window.location.href='../www/home.html';
                }
                
            else
                {
                    alert("Enter details correctly");
                }
        }

    
}
$scope.register=function(){
    $window.location.href='../www/register.html';
}
});

angular.module('sign', ['ionic'])
.controller('con',function($scope,$window){
            $scope.sub=function(){
                var a=$scope.u;
                var b=$scope.em;
                var c=$scope.p;
                var d=$scope.rp;
                if(a===undefined|b===undefined|c===undefined|d===undefined)
                    {
                        alert("Enter all your details correctly");
                    }
                else
                    {
                        if(c!==d)
                            {
                                alert("Password Mismatch,enter your password field correctly")
                            }
                        else
                            {
                               localStorage.setItem('us'+a,a);
                                 localStorage.setItem('e'+b,b);
                                 localStorage.setItem('pass'+c,c);
                                 localStorage.setItem('retype'+d,d);
    $window.location.href='../www/index.html'; 
                            }
                    }
}
            });

angular.module('hm', ['ionic'])
.controller('web',function($scope,$window,$http){
    $scope.sea=function()
    {
          var i = $scope.input;
        var z = i.replace(" ", "+");
        var handler = $http.get("https://kgsearch.googleapis.com/v1/entities:search?query="+z+"&key=AIzaSyA1Sy0t-uNoi-9pnR5ETJnTBGDNvoi6YhE&limit=1&indent=True")
        handler.success(function (data) {
            if (data != null) {
                $scope.final = {
                    "link": data.itemListElement[0].result.image.contentUrl,
                    "detailed": data.itemListElement[0].result.detailedDescription.articleBody,
                
                }
                document.getElementById('se').style.display = 'block';
                document.getElementById('te').style.display = 'block';
            }
        })
        handler.error(function (data) {
         alert("There was some error processing your request. Please try after some time.");
    })
    }
    
       $scope.translate=function (y) {
        var hand=$http.get("https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20170206T054715Z.79fffb75ffe99e6e.ee7075626f5e0a731fc17fee095054467a4c665c&text="+y+"&lang=ru&callback=");
        hand.success(function (res)
        {

            $scope.tra={"text":res.text[0]};
        })
        hand.error(function (data) {
         alert("There was some error processing your request. Please try after some time.");
    })

    }
});