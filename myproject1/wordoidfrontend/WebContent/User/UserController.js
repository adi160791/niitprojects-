app.controller('UserController', [ 
		'$scope',
		'UserService',
		'$location',
		'$rootScope',
		'$route',
		'$window',
		function($scope, UserService, $location, $rootScope,$route,$window) {
			console.log("UserController...")

		var self = this;
		self.user = {
		    errorCode : '',
		errorMessage : '',
		userId : '',
		name : '',
		password : '',
		role : '',
		email : '',
		description : '',
		gender : '',
		dob : '',
		address : '',
		contactNo : '',
		IsOnline : '',
		status : '',
		image : ''
		}
		self.users = [];
		self.fetchAllUsers = function() {
		console.log("--> UserController : calling fetchAllUsers method.");
		UserService.fetchAllUsers().then(
		function(d) {
		self.users = d;
						}, function(errResponse) {
							console.error('Error while fetching Users...');
						});
			};
		self.fetchAllUsers();
		
		self.sendFriendRequest = function sendFriendRequest(friendId) {
			console.log("--> sendFriendRequest : "+friendId);
			UserService.sendFriendRequest(friendId).then(
			function(d) {
			self.friend = d;
			alert('Friend request sent...')
			},
			function(errResponse) {
			console.error('Error while friends...');
			});
			
					
				
		};
		
			
		self.getSelectedUser = function(id) {
			console.log("-->UserController : calling getSelectedUser method : getting User with id : " + id);
			UserService.getSelectedUser(id).then(
					function(d) {
					self.user = d;
					console.log('id '+ self.user.userId);
					$rootScope.user= self.user;
					console.log(' r id '+ $rootScope.user.userId);
					$location.path('/viewUserById');
						}, 
					function(errResponse) {
					console.error('Error while fetching User...');
						});
							};
							
							
							
							
							
		                  
		        			
		        			
		        			
							


		self.createUser = function(user) {
		console.log("--> UserController : calling createUser method.");
		UserService.createUser(user).then(
		function(d) {
			self.user = d;
			alert('User Created Successfully...')
			},
			function(errResponse) {
			console.error('Error while creating user...');
				});
  					};

  					self.updateUser = function(user, id) {
  						console.log("--> UserController : calling updateUser method.");
  						UserService.updateUser(user, id).then(function(d) {
  							self.users = d;
  							$location.path('/viewUser');
  							}, function(errResponse) {
  								console.error('--> UserController : Error while updating User...');
  							});
  					};
  					   
  					
  					
  					
  					
  					
  					
  					
			self.authenticateUser = function(user) {
				console.log("-->UserController : calling authenticateUser method.");
				UserService.authenticateUser(user).then(
					function(d) {
						self.user = d;
			$rootScope.currentUser =self.user;
						console.log (self.user.userId);
						console.log ($rootScope.currentUser.role);
						$location.path('/index');
							}, 
					function(errResponse) {
					console.error('Error while fetching User...');
					});
				};
				self.logout = function(user,id) 
				{
					console.log("--> UserController : calling logout method.");
					alert(user.userId+id);
					UserService.logout(user,id);
					$rootScope.currentUser = {};
					//$localStorage.currentUser.remove('currentUser');
				    $cookieStore.remove('currentUser');
					
					console.log("-->UserController : User Logged out.");
					$window.location.reload();
					$location.path('/index');
				}
               	
               	
		self.register = function() {
				{
				console.log("--> UserController : calling register() method.", self.user);
				self.createUser(self.user);
				console.log('Saving new user...');
			}
				$location.path('/login');
				self.reset();
		                   	};
		                   	
		

		                   	
		                   	
		                   
		        			
		                   	
		                   	
		                   	
		                   	
		self.reset = function() {
				console.log('submit a new User', self.user);
				self.user = {
		    errorCode : '',
		errorMessage : '',
		userId : '',
		name : '',
		password : '',
		role : '',
		email : '',
		description : '',
		gender : '',
		dob : '',
		address : '',
		contactNo : '',
		IsOnline : '',
		status : '',
		image : ''
		};
				$scope.myForm.$setPristine(); // reset form...
			};
		} ]);