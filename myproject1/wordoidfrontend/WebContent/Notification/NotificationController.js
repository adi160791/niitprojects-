'use strict'
app.controller('NotificationController', ['NotificationService', '$scope', '$location', '$rootScope','$cookieStore',  
function(NotificationService, $scope, $location, $rootScope,$cookieStore)
{
			console.log('NotificationController...');
			var self = this;
			
			self.notification =
			{
			notificationId : '',  
			blogTitle : '', 
			rejectionReason : '',
			approvedOrRejected : '', 
			viewed : '', 
			userId : '',
			errorCode : '',
			errorMessage : ''
			};

			self.notifications = [];


			self.getNotificationsNotViewed = function(){
	
			console.log("-->NController : calling 'getNotificationsNotViewed' method.");
			NotificationService.getNotificationsNotViewed().then
			(function(d)
				{
			 			self.notifications = d;	
						$rootScope.notifications = self.notifications;
						console.log('count '+$rootScope.notificationCount);
						
				},
			 function(errResponse) 
				{
						console.error("Error while getting blog list.")
				}
	          );
			};
			
			if ($rootScope.currentUser.userRole == 'USER' || $rootScope.currentUser.userRole == 'ADMIN')
				{
				self.getNotificationsNotViewed();
				}
			
			

} 
]
);