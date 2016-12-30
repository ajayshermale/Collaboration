'use strict';
 
app.controller('blog_controller', ['$scope', 'blog_service', function($scope, blog_service) {
    var self = this;
    self.blog={blog_id:null,title:'',content:''};
    self.blogs=[];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
 
 
    fetchAllBlogs();
 
    function fetchAllBlogs(){
        blog_service.fetchAllBlogs()
            .then(
            function(d) {
                self.blogs = d;
            },
            function(errResponse){
                console.error('Error while fetching blogs');
            }
        );
    }
 
    function createBlog(blog){
    	blog_service.createBlog(blog)
            .then(
            fetchAllBlogs,
            function(errResponse){
                console.error('Error while creating blog');
            }
        );
    }
 
    function updateBlog(blog, blog_id){
    	blog_service.updateBlog(blog, blog_id)
            .then(
            fetchAllBlogs,
            function(errResponse){
                console.error('Error while updating blog');
            }
        );
    }
 
    function deleteBlog(blog_id){
    	blog_service.deleteBlog(blog_id)
            .then(
            fetchAllBlogs,
            function(errResponse){
                console.error('Error while deleting Blog');
            }
        );
    }
 
    function submit() {
        if(self.blog.blog_id===null){
            console.log('Saving New Blog', self.blog);
            createBlog(self.blog);
        }else{
            updateBlog(self.blog, self.blog.blog_id);
            console.log('Blog updated with id ', self.blog.blog_id);
        }
        reset();
    }
 
    function edit(blog_id){
        console.log('id to be updated', blog_id);
        for(var i = 0; i < self.blogs.length; i++){
            if(self.blogs[i].blog_id === blog_id) {
                self.blog = angular.copy(self.blogs[i]);
                break;
            }
        }
    }
 
    function remove(blog_id){
        console.log('id to be deleted', blog_id);
        if(self.blog.blog_id === blog_id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteBlog(blog_id);
    }
 
 
    function reset(){
        self.blog={blog_id:null,title:'',content:''};
        $scope.blogForm.$setPristine(); //reset Form
    }
 
}]);