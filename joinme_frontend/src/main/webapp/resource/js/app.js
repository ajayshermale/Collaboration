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
     
});
