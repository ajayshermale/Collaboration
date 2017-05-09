'use strict';

 
app.controller('blog_controller', ['$scope', 'blog_service','$http','$location', function($scope, blog_service,$http,$location) {
    var self = this;
    self.blog={blog_id:null,title:'',content:''};
    self.blogs=[];
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.viewBlog = viewBlog;
    self.reset = reset;
   self.submitBlogImage = submitBlogImage;
 
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
        
//        var file = $scope.myFile;
//    	/* console.log('file is ' );
//    	console.dir(file);*/
//    	var uploadUrl = 'http://localhost:8081/joinme/blog/blogImage';
//    	var fd = new FormData();
//    	fd.append('file', file);
//    	$http.post(uploadUrl, fd, {
//    	transformRequest : angular.identity,
//    	headers : {
//    	'Content-Type' : undefined
//    	}
//    	}).success(function() {
//    	console.log('success');
//    	}).error(function() {
//    	console.log('error');
//    	});
        
        reset();
    }
 
    function submitBlogImage(){
      var file = $scope.blogImage;
    	 console.log('file is ' );
    	/*console.dir(file);*/
    	var uploadUrl = 'http://localhost:8081/joinme/blog/blogImage';
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
    
//		$scope.orightml = '<h2>Try me!</h2><p>textAngular is a super cool WYSIWYG Text Editor directive for AngularJS</p><p><b>Features:</b></p><ol><li>Automatic Seamless Two-Way-Binding</li><li>Super Easy <b>Theming</b> Options</li><li style="color: green;">Simple Editor Instance Creation</li><li>Safely Parses Html for Custom Toolbar Icons</li><li class="text-danger">Doesn&apos;t Use an iFrame</li><li>Works with Firefox, Chrome, and IE8+</li></ol><p><b>Code at GitHub:</b> <a href="https://github.com/fraywing/textAngular">Here</a> </p>';
//		$scope.htmlcontent = $scope.orightml;
//		$scope.disabled = false;
	


    
//    function submitBlog(blog_id) {
//    	 console.log('id to be updated', blog_id);
//    	var file = $scope.myFile;
//    	/* console.log('file is ' );
//    	console.dir(file);*/
//    	var uploadUrl = 'http://localhost:8083/joinme/blog/blogImage';
//    	var fd = new FormData();
//    	fd.append('file', file);
//    	//fd.append('user',angular.toJson($scope.user,true));
//    	//console.log('Socpe of user'+$scope.user);
//    	$http.post(uploadUrl, fd, {
//    	transformRequest : angular.identity,
//    	headers : {
//    	'Content-Type' : undefined
//    	}
//    	}).success(function() {
//    	console.log('success');
//    	}).error(function() {
//    	console.log('error');
//    	});
//    	}
       
    function edit(blog_id){
        console.log('id to be updated', blog_id);
        for(var i = 0; i < self.blogs.length; i++){
            if(self.blogs[i].blog_id === blog_id) {
                self.blog = angular.copy(self.blogs[i]);
                break;
            }
        }
    }
 
  
    


    function viewBlog(blog_id){
    	$http.get('http://localhost:8081/joinme/blog/'+blog_id).then
    	console.log('blog to view ', blog_id);
         for(var i = 0; i < self.blogs.length; i++){
             if(self.blogs[i].blog_id === blog_id) {
                 self.blogview = angular.copy(self.blogs[i]);
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