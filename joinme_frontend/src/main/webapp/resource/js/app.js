'use strict';
var app=angular.module('app',['ngRoute']);

app.config(function($routeProvider){
	$routeProvider
     .when('/', {
    	 templateUrl: 'home.html',
        
     })
     
     .when('/blog', {
      
         templateUrl: 'resource/views/BlogManagement.html',
         controller: 'blog_controller'
     }) 
     
     .when('/user', {
         
         templateUrl: 'resource/views/UserManagement.html',
         controller: 'user_controller'
     }) 
    
      .when('/forum', {
         
         templateUrl: 'resource/views/ForumManagement.html',
         controller: 'forum_controller'
     })
     
      .when('/event', {
         
         templateUrl: 'resource/views/EventManagement.html',
         controller: 'event_controller'
     })
     
});
