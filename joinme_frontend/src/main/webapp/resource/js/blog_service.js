'use strict';
 
app.factory('blog_service', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8081/joinme/blog/';
 
    var factory = {
        fetchAllBlogs: fetchAllBlogs,
        createBlog: createBlog,
        updateBlog:updateBlog,
        deleteBlog:deleteBlog
    };
 
    return factory;
 
    function fetchAllBlogs() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createBlog(blog) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, blog)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
 
    function updateBlog(blog, blog_id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+blog_id, blog)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Blog');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function deleteBlog(blog_id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+blog_id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Blog');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
}]);