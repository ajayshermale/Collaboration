'use strict';
 
app.controller('forum_controller', ['$scope', 'forum_service', function($scope, forum_service) {
    var self = this;
    self.forum={forum_id:null,name:'',content:''};
    self.forums=[];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
 
 
    fetchAllForums();
 
    function fetchAllForums(){
    	forum_service.fetchAllForums()
            .then(
            function(d) {
                self.forums = d;
            },
            function(errResponse){
                console.error('Error while fetching forum');
            }
        );
    }
 
    function createForum(forum){
    	forum_service.createForum(forum)
            .then(
            fetchAllForums,
            function(errResponse){
                console.error('Error while creating forum');
            }
        );
    }
 
    function updateForum(forum, forum_id){
    	forum_service.updateForum(forum, forum_id)
            .then(
            fetchAllForums,
            function(errResponse){
                console.error('Error while updating forum');
            }
        );
    }
 
    function deleteForum(forum_id){
    	forum_service.deleteForum(forum_id)
            .then(
            fetchAllForums,
            function(errResponse){
                console.error('Error while deleting forum');
            }
        );
    }
 
    function submit() {
        if(self.forum.forum_id===null){
            console.log('Saving New forum', self.forum);
            createForum(self.forum);
        }else{
            updateForum(self.forum, self.forum.forum_id);
            console.log('forum updated with id ', self.forum.forum_id);
        }
        reset();
    }
 
    function edit(forum_id){
        console.log('id to be updated', forum_id);
        for(var i = 0; i < self.forums.length; i++){
            if(self.forums[i].forum_id === forum_id) {
                self.forum = angular.copy(self.forums[i]);
                break;
            }
        }
    }
 
    function remove(forum_id){
        console.log('id to be deleted', forum_id);
        if(self.forum.forum_id === forum_id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteForum(forum_id);
    }
 
 
    function reset(){
        self.forum={forum_id:null,title:'',content:''};
        $scope.forumForm.$setPristine(); //reset Form
    }
 
}]);