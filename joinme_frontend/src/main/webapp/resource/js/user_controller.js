'use strict';
 
app.controller('user_controller', ['$scope', 'user_service', function($scope, user_service) {
    var self = this;
    self.user={user_id:null,userName:'',password:'',userFirstname:'',userLastname:'',createdBy:''};
    self.users=[];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
 
 
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
 
    function createUser(user){
    	user_service.createUser(user)
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
            updateUser(self.user, self.user.user_id);
            console.log('User updated with user_id ', self.user.user_id);
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
 
 
    function reset(){
        self.user={user_id:null,userName:'',password:'',userFirstname:'',userLastname:'',createdBy:''};
        $scope.userForm.$setPristine(); //reset Form
    }
 
}]);