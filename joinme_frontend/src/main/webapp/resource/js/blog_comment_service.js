'use strict';
 
app.factory('blog_comment_service', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8083/joinme/blogComment/';
 
    var factory = {
        fetchAllBlogComments: fetchAllBlogComments,
        createBlogComment: createBlogComment,
        deleteBlogComment:deleteBlogComment,
      
    };
 
    return factory;
 
    function fetchAllBlogComments() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            }
           
        );
        return deferred.promise;
    }
 
    function createBlogComment(blogComment) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, blogComment)
            .then(
            function (response) {
                deferred.resolve(response.data);
            }
         
        );
        return deferred.promise;
    }
 
    
    
 
    function deleteBlogComment(commentId) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+commentId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting comment');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    
 
}]);