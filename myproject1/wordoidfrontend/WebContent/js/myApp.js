var app = angular.module("myApp", ['ngRoute']);
app.config(['$routeProvider',function($routeProvider) {
	$routeProvider
	
	
	
	// user for login and admin + authentication
	
	
	
	
		.when('/viewUser', {
			templateUrl: 'User/viewUser.html',
			controller: 'UserController as m'
		})
		.when('/viewUserById', {
			templateUrl: 'User/viewUserById.html',
			controller: 'UserController as m'
		})
		.when('/registerUser', {
			templateUrl: 'User/registerUser.html',
			controller: 'UserController as m'
		})
		.when('/updateUser', {
			templateUrl: 'User/updateUser.html',
			controller: 'UserController as m'
		})
		
		
		
		
		
		
		
		
		// blog 
		
		.when('/addBlog', {
			templateUrl: 'Blog/addBlog.html',
			controller: 'BlogController as m'
		})
		.when('/viewBlog', {
			templateUrl: 'Blog/viewBlog.html',
			controller: 'BlogController as m'
		})
		.when('/viewBlogById', {
			templateUrl: 'Blog/viewBlogById.html',
			controller: 'BlogController as m'
		})
		.when('/updateBlog', {
			templateUrl: 'Blog/updateBlog.html',
			controller: 'BlogController as m'
		})
		.when('/viewApprovedBlog', {
			templateUrl: 'Blog/viewApprovedBlog.html',
			controller: 'BlogController as m'
		})
		
		
		
		// login for user and admin ... 
		
		.when('/login', {
			templateUrl: 'User/login.html',
			controller: 'UserController as m'
		})
		
		.when('/logout', {
			templateUrl: 'User/logout.html',
			controller: 'UserController as m'
		})
		
		
		
		// blog comment linked with blog  
		
		
		.when('/viewBlogComment', {
			templateUrl: 'BlogComment/viewBlogComment.html',
			controller: 'BlogCommentController as m'
		})
		.when('/addBlogComment', {
			templateUrl: 'BlogComment/addBlogComment.html',
			controller: 'BlogCommentController as m'
		})
		.when('/updateBlogComment', {
			templateUrl: 'BlogComment/updateBlogComment.html',
			controller: 'BlogCommentController as m'
		})
		.when('/viewBlogCommentById', {
			templateUrl: 'BlogComment/viewBlogCommentById.html',
			controller: 'BlogCommentController as m'
		})
		
		
		
		// forum 
		
		
		.when('/addForum', {
			templateUrl: 'Forum/addForum.html',
			controller: 'ForumController as m'
		})
		.when('/updateForum', {
			templateUrl: 'Forum/updateForum.html',
			controller: 'ForumController as m'
		})
		.when('/viewForum', {
			templateUrl: 'Forum/viewForum.html',
			controller: 'ForumController as m'
		})
		.when('/viewForumById', {
			templateUrl: 'Forum/viewForumById.html',
			controller: 'ForumController as m'
		})
		
		
		
		// forum comment linked with forum
		
		
		.when('/viewForumCommentById', {
			templateUrl: 'ForumComment/viewForumCommentById.html',
			controller: 'ForumCommentController as m'
		})
		.when('/viewForumComment', {
			templateUrl: 'ForumComment/viewForumComment.html',
			controller: 'ForumCommentController as m'
		})
		.when('/updateForumComment', {
			templateUrl: 'ForumComment/updateForumComment.html',
			controller: 'ForumCommentController as m'
		})
		.when('/addForumComment', {
			templateUrl: 'ForumComment/addForumComment.html',
			controller: 'ForumCommentController as m'
		})
		
		 
		
		// friend 
		
		
		
		.when('/viewFriend', {
			templateUrl: 'Friend/viewFriend.html',
			controller: 'FriendController as m'
		})
		.when('/viewFriendProfile', {
			templateUrl: 'Friend/viewFriendProfile.html',
			controller: 'FriendController as m'
		})
		.when('/friend', {
			templateUrl: 'Friend/friend.html',
			controller: 'FriendController as m'
		})
		.when('/FriendRequest', {
			templateUrl: 'Friend/FriendRequest.html',
			controller: 'FriendController as m'
		})
		
		
		
		// job 
	
		
		.when('/addJob', {
			templateUrl: 'Job/addJob.html',
			controller: 'JobController as m'
		})
		.when('/updateJob', {
			templateUrl: 'Job/updateJob.html',
			controller: 'JobController as m'
		})
		.when('/viewJob', {
			templateUrl: 'Job/viewJob.html',
			controller: 'JobController as m'
		})
		.when('/viewJobById', {
			templateUrl: 'Job/viewJobById.html',
			controller: 'JobController as m'
		})
		.when('/jobApplications', {
			templateUrl: 'Job/jobApplications.html',
			controller: 'JobController as m'
		})
		.when('/jobApplied', {
			templateUrl: 'Job/jobApplied.html',
			controller: 'JobController as m'
		})
		.when('/listalljobapplications', {
			templateUrl: 'Job/listalljobapplications.html',
			controller: 'JobController as m'
		})
		
		
		
		// chat for user once both users are logged in 
		
		.when('/chat', {
			templateUrl : 'Chat/chat.html',
			controller : 'ChatController as ctrl'
		})
			
		.when('/ImageUpload', {
			templateUrl : 'uploadImage/ImageUpload.html',
			controller : 'ImageController as ctrl'
		})
		
		
		
		
		// notification for users
		
		.when('/notificationinDetail', {
			templateUrl: 'Notification/notificationinDetail.html',
			controller: 'NotificationController as m'
		})
		
		
		
		
		var myApp = angular.module('myApp', []);
         myApp.directive('fileModel', ['$parse', function ($parse) {
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
		
		
		
		
		
}]);