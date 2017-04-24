'use strict';
 
app.controller('event_controller', ['$scope', 'event_service', function($scope, event_service) {
    var self = this;
    self.event={eventId:null,eventName:'',eventDate:'',eventContent:''};
    self.events=[];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
 
 
    fetchAllEvents();
 
    function fetchAllEvents(){
    	event_service.fetchAllEvents()
            .then(
            function(d) {
                self.events = d;
            },
            function(errResponse){
                console.error('Error while fetchAllEvents ');
            }
        );
    }
 
    function createEvents(event){
    	event_service.createEvents(event)
            .then(
            fetchAllEvents,
            function(errResponse){
                console.error('Error while creating event');
            }
        );
    }
 
    function updateEvent(event, eventId){
    	event_service.updateEvent(event, eventId)
            .then(
            fetchAllEvents,
            function(errResponse){
                console.error('Error while updating blog');
            }
        );
    }
 
    function deleteEvent(eventId){
    	event_service.deleteEvent(eventId)
            .then(
            fetchAllEvents,
            function(errResponse){
                console.error('Error while deleting Blog');
            }
        );
    }
 
    function submit() {
        if(self.event.eventId===null){
            console.log('Saving New event', self.event);
            createEvent(self.event);
        }else{
            updateEvent(self.event, self.event.eventId);
            console.log('event updated with id ', self.event.eventId);
        }
        reset();
    }
 
    function edit(eventId){
        console.log('id to be updated', eventId);
        for(var i = 0; i < self.events.length; i++){
            if(self.events[i].eventId === eventId) {
                self.event = angular.copy(self.events[i]);
                break;
            }
        }
    }
 
    function remove(eventId){
        console.log('id to be deleted', eventId);
        if(self.event.eventId === eventId) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteEvent(eventId);
    }
 
 
    function reset(){
        self.event={eventId:null,eventName:'',eventDate:'',eventContent:''};
        $scope.eventForm.$setPristine(); //reset Form
    }
 
}]);