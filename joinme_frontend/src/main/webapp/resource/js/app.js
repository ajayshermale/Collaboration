'use strict';
var app=angular.module('app',['ngRoute','ngSanitize','textAngular','ngStorage','ngCookies']);




app.config(function($routeProvider){
	$routeProvider
     .when('/', {
    	//controller: 'login_controller',
    	 templateUrl: 'home.html',
        
     })
     
   
     .when('/blog', {
    	  controller: 'blog_controller',
    	  controller: 'blog_comment_controller',
    	  controller: 'blog_like_controller',
        templateUrl: 'resource/views/BlogManagement.html',
        
     })
     
//     .when('/login', {
//    	controller: 'login_controller',
//        templateUrl: 'resource/views/myProfile.html',
//        
//     })
	  .when('/myProfile', {
	    	 controller: 'login_controller',
	    	 controller: 'blog_controller',
	    	  controller: 'user_controller',
	    	 templateUrl: 'resource/views/myProfile.html',
	        
	     })
     
    .when('/loginerror', {
	    	
	    	 templateUrl: 'resource/views/loginerror.html',
	        
	     })

     .when('/user', {
         
         templateUrl: 'resource/views/UserManagement.html',
         controller: 'user_controller'
     }) 
    
      .when('/forum', {
         
         templateUrl: 'resource/views/ForumManagement.html',
         controller: 'forum_controller'
     })
});


app.directive('fileModel', ['$parse', function ($parse) {
 return {
 restrict: 'A',
 link: function(scope, element, attrs) {
 var model = $parse(attrs.fileModel);
 var modelSetter = model.assign;

element.bind('change', function(){
 scope.$apply(function(){
 modelSetter(scope, element[0].files[0]);
 });
 });
 }
 };
 }]);
