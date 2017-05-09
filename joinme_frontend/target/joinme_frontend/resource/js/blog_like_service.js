'use strict';
 
app.factory('blog_like_service', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8081/joinme/blogLike/';
 
    var factory = {
        fetchAllBlogLikes: fetchAllBlogLikes,
        createBlogLike: createBlogLike,
        deleteBlogLike:deleteBlogLike,
      
    };
 
    return factory;
 
    function fetchAllBlogLikes() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            }
           
        );
        return deferred.promise;
    }
 
    function createBlogLike(blogLike) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, blogLike)
            .then(
            function (response) {
                deferred.resolve(response.data);
            }
           
        );
        return deferred.promise;
    }

    
 
    function deleteBlogLike(blogLikeId) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+blogLikeId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Like');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    
 
}]);