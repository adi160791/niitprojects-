app.controller('ForumCommentController', [
		'$scope',
		'ForumCommentService',
		'$location',
		'$rootScope',
		'$route',
		'$window',
		function($scope, ForumCommentService, $location, $rootScope,$route,$window) {
			console.log("ForumCommentController...")

		var self = this;
		self.forumcomment = {
		    errorCode : '',
		errorMessage : '',
		forumCommentId : '',
		forumId : '',
		forumComment : '',
		forumCommentDate : '',
		userId : '',
		userName : '',
		
		
		}
		
								/*  To fetch all forum       */
		
		self.forumComments = [];
		self.fetchAllForumComments = function() {
				console.log("--> ForumCommentController : calling fetchAllForumComments method.");
		ForumCommentService.fetchAllForumComments().then(
		function(d) {
		self.forumcomments = d;
						}, function(errResponse) {
							console.error('Error while fetching Forumcomments...');
						});
			};
			self.fetchAllForumComments();

								/*  To fetch forum by forumCommentid       */		
			
			self.getSelectedForumComment = function(id) {
				console.log("-->ForumCommnetController : calling getSelectedForumComment method : getting forumcomment with id : " + id);
				ForumCommentService.getSelectedForumComment(id).then(
						function(d) {
						self.forumcomment = d;
						console.log('id '+ self.forumcomment.forumCommentId);
						$rootScope.forumcomment= self.forumcomment;
						console.log(' r id '+ $rootScope.forumcomment.forumCommentId);
						$location.path('/viewForumcommentById');
							}, 
						function(errResponse) {
						console.error('Error while fetching Forumcomment...');
							});
								};
								
								/*  To create new Forumcomment     */		
								
								self.createForumComment = function(forumComment, id) {
									console.log("-->ForumController : calling 'createForumComment' method.", self.forum);
									forumComment.forumId = id;
									console.log("-->ForumController ForumId :" +forumComment.forumId);
									ForumService.createForumComment(forumComment).then
												(function(d) 
												{
													console.log('Current User :',$rootScope.currentUser.userId)
													self.forumComment = d;
													console.log('-->ForumController :', self.forumComment)
													self.fetchAllForumComments(id);
													self.resetComment();
												},
												function(errResponse) {
													console.error('Error while creating forumComment...');
												}
												);
								};
							     /*  To update forumcomment      */	
			                  
								self.updateForumComment= function(forumComment,id) {
									console.log("-->ForumController : calling updateForum method.");
									ForumService.updateForumComment(forumComment,id).then(
							         function(d) {
											self.forumComment = d;
											alert('ForumComment Created Successfully...')
											console.log(self.forumComment);
											},
									function(errResponse) {
									console.error('Error while updating forumComment...')
										});
									};
			                     				
							
				   
										self.reset = function() {
											console.log('submit a new forum', self.forum);
											self.forumcomment = {
		                                    errorCode : '',
		                                    errorMessage : '',
		                                    forumCommentId : '',
		                                    forumId : '',
		                                    forumComment : '',
		                                    forumCommentDate : '',
	                                    	userId : '',
		                                    userName : '',
		
		
		                                   
		

											};
											$scope.myForm.$setPristine(); // reset form...
										};
				
		
		} ]);