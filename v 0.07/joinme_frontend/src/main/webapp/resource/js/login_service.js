app.service('login_service',['$http','$q','$rootScope','$location','$localStorage', function($http, $q, $rootScope,$location,$localStorage){
	var v= this;

	v.login = function (config) {
		

        var res = $http({
            url: 'http://localhost:8081/joinme/perform_login',
            method: 'POST',
            data: config,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'X-Requested-With': 'XMLHttpRequest'
              }
        
        
        });

        res.success(function(data, status, headers, config) {
        	console.log("loading mypage")
        	location.reload()
        	$location.path('/user/myProfile')
        	v.currentUser();
        	console.log(config)
        	
        	

        });
        res.error(function(data, status, headers, config) {
        	$location.path('/loginerror')
        	console.log("Error")

        });
        return res;
    };
    
    v.logout= function(){
		console.log('logout....')
		return $http.get('http://localhost:8081/joinme/perform_logout')
		.then(
			function(response){
				return response.data;
			},
			null
		);
	}
    
    v.currentUser = function(user) {    	
    	var uploadUrl = "http://localhost:8081/joinme/user/myProfile";   	
    	
    	v.current = $http.get(uploadUrl, {
    	transformRequest : angular.identity,
    	headers : {
    	'Content-Type' : undefined
    	}
    	}).success(function(data) {
    	console.log('success');
    	
    	$location.path('/myProfile')
    	v.user=data;
    	
    	console.log(v.user.userName)
    	$localStorage.userLogged = v.user.userName
    	console.log($localStorage.userLogged)
    	
    	
    	
    	
    	}).error(function() {
    	console.log('error');
    	});
    	}
    
    v.currentUser();
}]);