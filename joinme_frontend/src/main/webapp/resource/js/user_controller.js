'use strict';
 
app.controller('user_controller', ['$scope', 'user_service','$http','$location', function($scope, user_service,$http,$location) {
    var self = this;
    self.user={user_id:null,userName:'',password:'',userFirstname:'',userLastname:'',createdBy:''};
    self.users=[];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.submitUser = submitUser;
 
    fetchAllUsers();
 
    function fetchAllUsers(){
        user_service.fetchAllUsers()
            .then(
            function(d) {
                self.users = d;
            },
            function(errResponse){
                console.error('Error while fetching users');
            }
        );
    }
 
    function createUser(users){
    	user_service.createUser(users)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while creating user');
            }
        );
    }
 
    function updateUsers(user, user_id){
    	user_service.updateUser(user, user_id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while updating user');
            }
        );
    }
 
    function deleteUser(user_id){
    	user_service.deleteUser(user_id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while deleting User');
            }
        );
    }
 
    function submit() {
        if(self.user.user_id===null){
            console.log('Saving New User', self.user);
            createUser(self.user);
        }else{
            updateUsers(self.user, self.user.user_id);
            console.log('User updated with id ', self.user.user_id);
        }
        reset();
    }
 
    function edit(user_id){
        console.log('id to be updated', user_id);
        for(var i = 0; i < self.users.length; i++){
            if(self.users[i].user_id === user_id) {
                self.user = angular.copy(self.users[i]);
                break;
            }
        }
    }
 
    function remove(user_id){
        console.log('id to be deleted', user_id);
        if(self.user.user_id === user_id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteUser(user_id);
    }
 
    

    function submitUser(){
 	   var file = $scope.profileImage;
 	 
 	   var uploadUrl =  'http://localhost:8081/joinme/user/profileUpload';
 	   console.log('file is:',file);
 	   var fd = new FormData();
 	   fd.append('file', file);
 	   $http.post(uploadUrl, fd, {
 	   transformRequest : angular.identity,
 	   headers : {
 	   'Content-Type' : undefined
 	   }
 	   }).success(function() {
 	   console.log('success');
 	   }).error(function() {
 	   console.log('error');
 	   });
    }

    
    
 
    function reset(){
        self.user={user_id:null,userName:'',password:'',userFirstname:'',userLastname:'',createdBy:''};
        $scope.userForm.$setPristine(); //reset Form
    }
    
    
 
}]);