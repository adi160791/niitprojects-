app.controller('ForumController', [
		'$scope',
		'ForumService',
		'ForumCommentService',
		'$location',
		'$rootScope',
		'$route',
		'$window',
		function($scope, ForumService, ForumCommentService , $location, $rootScope,$route,$window) {
			console.log("ForumController...")

		var self = this;
		self.forum = {
		    errorCode : '',
		errorMessage : '',
		forumId : '',
		forumName : '',
		forumDescription : '',
		userName : '',
		userId : '',
		forumCreationDate : '',
		forumStatus : '',
		forumCountComment : '',
		forumUserCount : ''
		}

		self.forums = [];
		self.approvedForums = [];
												
		self.fetchAllForums = function() {
	    console.log("--> ForumController : calling fetchAllForums method.");
		ForumService.fetchAllForums().then(
		function(d) {
		self.forums = d;
						}, function(errResponse) {
							console.error('Error while fetching Forums...');
						});
			};
			self.fetchAllForums();

								/*  To fetch forum by id       */		
			
			self.getSelectedForum = function(id) {
				console.log("-->ForumController : calling getSelectedForum method : getting forum with id : " + id);
				ForumService.getSelectedForum(id).then(
						function(d) {
						self.forum = d;
						console.log('id '+ self.forum.forumId);
						$rootScope.forum= self.forum;
						console.log(' r id '+ $rootScope.forum.forumId);
						$location.path('/viewForumById');
						self.getSelectedForumComment(id);
						console.log('comments '+$rootScope.forumcomment);
							}, 
						function(errResponse) {
						console.error('Error while fetching forum...');
							});
								};
								
								
								self.getSelectedForumComment = function(id) {
									console.log("-->ForumCommentController : calling getSelectedForumComment method : getting ForumComment with id : " + id);
										ForumCommentService.getSelectedForumComment(id).then(
										function(d) {
										self.forumcomment = d;
										console.log(self.forumcomment);
										console.log('id '+ self.forumcomment.forumCommentId);
										$rootScope.forumcomment= self.forumcomment;
										console.log(' r id '+ $rootScope.forumcomment.forumCommentId);
										//$location.path('/viewForumCommentById');
											}, 
										function(errResponse) {
										console.error('Error while fetching forumComment...');
										});
										};	
										
										
										
										
										
										  self.createForumComment = function(forumComment) {
												forumComment.forumId= $rootScope.forum.forumId ;
												console.log("-->ForumController : calling 'createForumComment' method.", forumComment);
												console.log("-->ForumController ForumId :" +forumComment.forumId);
												ForumService.createForumComment(forumComment).then
															(function(d) 
															{
																console.log('Current User :',$rootScope.currentUser.userId)
																self.forumComment = d;
																console.log(self.forumComment)
																
															},
															function(errResponse) {
																console.error('Error while creating forumComment...');
															}
															);
											};	
											
																									
								
								
				
								
								/*  To create new forum     */		
											self.createForum = function(forum) {
												console.log("--> ForumController : calling createForum method.");
												ForumService.createForum(forum).then(
												function(d) {
												self.forum = d;
												alert('Forum Created Successfully...')
												},
												function(errResponse) {
												console.error('Error while creating Forum...');
												});
												};
													
							     /*  To update forumdetails     */	
			                  
			self.updateForum = function(forum,id) {
				console.log("-->ForumController : calling updateForum method.");
				ForumService.updateForum(forum,id).then(
		         function(d) {
						self.forum = d;
						alert('Forum Created Successfully...')
						console.log(self.forum);
						},
				function(errResponse) {
				console.error('Error while updating forum...')
					});
				};       
					
				
				
				self.rejectForum = function(forum, id) 
				{
					console.log("-->ForumController : calling rejectForum() method : Forum id is : " + id);
					console.log("-->ForumController",self.forum);
					ForumService.rejectForum(forum, id).then
					(
							function(d)
							{
							alert('Reject Forum?'),
							self.forum=d,
							self.listforums,
							$location.path('/list_rejectedforums');
							},
							function(errResponse) 
							{
								console.error("Error while rejecting forum...")
							}
					);
				};
				
				
				

				self.approveForum = function(forum, id)
				{
					console.log("-->ForumController : calling approveForum() method : Forum id is : " + id);
					console.log("-->ForumController",self.forum);
					ForumService.approveForum(forum, id).then
					(
							function(d)
							{
							alert('Accept Forum?'),
							self.forum=d,
							self.listforums,
							$location.path('/list_forums');
							},
							function(errResponse) 
							{
								console.error("Error while approving forum...")
							}
					);
				};
				
				
				
				
				
				
				
                                   /*  To Register new Forum     */		
					
					self.register = function(forum) {
						{
						console.log("--> ForumController : calling register() method.", forum);
						self.createForum(forum);
						console.log('Saving new forum...');
					}
						$location.path('/login');
						self.reset();
						
				                   	};
									
				        		
				                   	
				                    
				                   	
				                      /*  To clear form     */			
				    self.reset = function() {
				console.log('submit a new Forum', self.forum);
				self.forum= {
				    errorCode : '',
					errorMessage : '',
					forumId : '',
					forumName : '',
					forumDescription : '',
					userName : '',
					userId : '',
					forumCreationDate : '',
					forumStatus : '',
					forumCountComment : '',
					forumUserCount : ''

				};
				$scope.myForm.$setPristine(); // reset form...
			};
									/*  To fetch all forums which are approved     */	
			
			
									
		
		} ]);