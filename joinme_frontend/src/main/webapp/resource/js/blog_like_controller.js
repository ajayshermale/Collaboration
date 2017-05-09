'use strict';
 
app.controller('blog_like_controller', ['$scope', 'blog_like_service','$location',function($scope, blog_like_service,$location) {
  
	var self = this;
    self.blogLike={blogLikeId:null};
    self.blogLikes=[];
   
    self.submit = submit;
  
    self.remove = remove;
    self.reset = reset;
    self.refresh = refresh;
   
 
    
   
 
     self.fetchAllBlogLikes = function(){
    	 blog_like_service.fetchAllBlogLikes()
            .then(
            function(d) {
                self.blogLikes = d;
                var count = Object.keys(self.blogLikes).length;
                console.log(count);

            },
            function(errResponse){
                console.error('Error while fetching Likes');
            }
        );
    }
    
 
    
    function createBlogLike(blogLike){
    	blog_like_service.createBlogLike(blogLike)
            .then(
            self.fetchAllBlogLikes,
            function(errResponse){
                console.error('Error while posting Likes');
            }
        );
    }
 
    function deleteBlogLike(blogLikeId){
    	blog_like_service.deleteBlogLike(blogLikeId)
            .then(
            self.fetchAllBlogLikes,
            function(errResponse){
                console.error('Error while deleting like');
            }
        );
    }
 
    function submit() {
        if(self.blogLike.blogLikeId===null){
            console.log('Posting New like', self.blogLike);
            createBlogLike(self.blogLike);
        }
        reset();
    }
 
    function remove(blogLikeId){
        console.log('blogLikeId id to be deleted', blogLikeId);
        if(self.blogLike.blogLikeId === blogLikeId) {
            reset();
        }
        deleteBlogLike(blogLikeId);
    }
 
 
    function reset(){
        self.blogLike={blogLikeId:null};
        $scope.blogLikeForm.$setPristine(); 
    }
    
    function refresh(){
    	
    	location.reload();
    } 
    
 
}]);