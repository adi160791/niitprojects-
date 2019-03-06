'use strict';
app.factory
('NotificationService', 
['$http', '$q', '$rootScope',
function($http, $q, $rootScope) 
{
console.log("NotificationService...");
var BASE_URL='http://localhost:9009/wordoid'
return {
	
						
	
           getNotificationsNotViewed : function() 
           {
				console.log("-->getNotificationsNotViewed: calling 'getNotificationsNotViewed' method.");
				return $http.get(BASE_URL+'/getnotificationsnotviewed').then
				(function(response) 
						{
								return response.data;
						},
						function(errResponse)
						{
								console.error('Error while getting Blog list...');
								return $q.reject(errResponse);
						}
				);
			},
	};
}]);