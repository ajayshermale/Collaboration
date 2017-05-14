'use strict';
 
app.controller('friend_controller', ['$scope', 'friend_service','$location',function($scope, friend_service,$location) {
	
	var self = this;
    self.friend={friendTableId:null};
    self.friendRequests=[];
    self.friends = [];
    self.allFriends = [];
    getAllFriendRequest();
    getAllFriends();
    listFriends();
    
    $scope.status=false;
    function getAllFriendRequest(){
    	friend_service.getAllFriendRequest()
    	.then(
    		function(d){
    			self.friendRequests = d;
    		},
    		function(errResponse){
    			console.error('Error while fetching friendRequests');
    		}
    	);
    }
    
    function getAllFriends(){
    	friend_service.getAllFriends()
    	.then(
    		function(d){
    			self.friends = d;
    		},
    		function(errResponse){
    			console.error('Error while fetching friends');
    		}
    	);
    }
    
    function listFriends(){
    	friend_service.listFriends()
    	.then(
    		function(d){
    			self.allFriends = d;
    		},
    		function(errResponse){
    			console.error('Error while fetching friend table');
    		}
    	);
    }
    
     self.sendFriendRequest= function(friend, friendId){
    	 friend_service.sendFriendRequest(self.friend, friendId)
         .then(
        		 $scope.status = true,
         function(response){
             console.error('posting friend request successfull');
         }
     );
    }
    
     self.acceptFriendRequest= function(user_id){
    	 friend_service.acceptFriendRequest(user_id)
         .then(
         function(response){
             console.error('accepting friend request successfull');
             location.reload();
         }
     );
    }
     
     self.rejectFriendRequest= function(user_id){
    	 friend_service.rejectFriendRequest(user_id)
         .then(
         function(response){
             console.error('rejecting friend request successfull');
             
         }
         
     );
    	 location.reload();
    } 
     
}]);
