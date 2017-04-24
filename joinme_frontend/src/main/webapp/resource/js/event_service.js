'use strict';
 
app.factory('event_service', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8081/joinme/event/';
 
    var factory = {
        fetchAllEvents: fetchAllEvents,
        createEvent: createEvent,
        updateEvent:updateEvent,
        deleteEvent:deleteEvent
    };
 
    return factory;
 
    function fetchAllEvents() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Events');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createEvent(event) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, event)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating event');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
 
    function updateEvent(event, eventId) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+eventId, event)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating event');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function deleteEvent(eventId) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+eventId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting event');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
}]);