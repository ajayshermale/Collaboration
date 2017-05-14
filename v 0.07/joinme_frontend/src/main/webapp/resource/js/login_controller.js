app.controller('login_controller', ['$http','$scope','login_service','$rootScope','$cookieStore','$localStorage','$location',function($http,$scope,login_service,$rootScope,$cookieStore,$localStorage,$location) {
var vm = this;
   vm.login = login;
   vm.reset = reset;
   vm.logout =logout;
   vm.currentUser = currentUser;
   
   
    vm.credentials = {};
    $scope.currentUserName=$localStorage.userLogged;
    console.log("user is"+$scope.currentUserName)
    
    
//     function edit(blog_id)
    
    function login(){
       

    	var config = 
    			vm.user = "userName="+vm.credentials.userName+"&password="+vm.credentials.password;
    	        console.log(config)   	        
    	        
    	        $rootScope.currentUser = {
    								username : vm.credentials.userName,
    								password : vm.credentials.password
    								
    							};
    	        
    							$http.defaults.headers.common['Authorization'] = 'Basic'
    								+ $rootScope.currentUser;
    							$cookieStore
    								.put(
    										'currentUser',
    										$rootScope.currentUser);
    	       // vm.reset();
    	        
    	        
              console.log('calling current user...')
        		
        		console.log('called')
    	        login_service.login(config);
        		
    };
    
     function reset(){
    	vm.credentials.userName="";
    	vm.credentials.password="";
    	
    };
    
   function logout(){
    	console.log('logout..');
    	$rootScope.currentUser = {};
    	$scope.currentUserName=undefined;
    	$localStorage.currentUserName=undefined;
    	$localStorage.$reset();
    	console.log($scope.currentUserName)
    	$cookieStore.remove('currentUser');
    	
    	  
    	
    	console.log('calling the method logout of user service');
    	login_service.logout()
    	location.reload();
    	$location.path('/');	
    };
  //fun(user) u pass parameter //  
    function currentUser(user) {    	
    	var uploadUrl = "http://localhost:8081/joinme/user/myProfile";  	
    	
    	vm.current = $http.get(uploadUrl, {
    	transformRequest : angular.identity,
    	headers : {
    	'Content-Type' : undefined
    	}
    	}).success(function(data) {
    	console.log('success');
    	$scope.user=data;
    	console.log($scope.user.user_id)
    	$localStorage.userLogged = $scope.user.userName
    	$localStorage.userLoggedId = $scope.user.user_id
    	$localStorage.userRole = $scope.user.userRole
    	console.log("current userid"+$localStorage.userLoggedId)
    	console.log("current Role"+$localStorage.userRole)
    	console.log($localStorage.userLogged)
    //	vm.updateUserStatus();
    	
    	}).error(function() {
    	console.log('error');
    	});
    	}
    vm.currentUser();
    $scope.currentUserName=$localStorage.userLogged;
    console.log("user is"+$scope.currentUserName);
    
//    vm.updateUserStatus = function() {
//    	var uploadUrl = "http://localhost:8080/UserPortal/updateStatus";
//    	console.log('updating status');
//    	$http.post(uploadUrl);
//    	
//    }
    
    /*$scope.currentUserName=$localStorage.userLogged;
    console.log("user is"+$scope.currentUserName)*/
}]);