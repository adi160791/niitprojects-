app.controller('BlogCommentController', [
		'$scope',
		'BlogCommentService',
		'$location',
		'$rootScope',
		'$route',
		'$window',
		function($scope, BlogCommentService, $location, $rootScope,$route,$window) {
			console.log("BlogCommentController...")

		var self = this;
		self.blogcomment = {
		    errorCode : '',
		errorMessage : '',
		blogCommentId : '',
		blogId : '',
		userId : '',
		blogComment : '',
		userName : '',
		blogCommentDate : '',
		}
		
								/*  To fetch all blog comments       */
		
		self.blogcomments = [];
		self.fetchAllBlogComments = function() {
		console.log("--> BlogCommentController : calling fetchAllBlogComments method.");
		BlogCommentService.fetchAllBlogComments().then(
		function(d) {
		self.blogcomments = d;
						}, function(errResponse) {
							console.error('Error while fetchingBlogcomments...');
						});
			};
			self.fetchAllBlogComments();
								/*  To fetch blogcomments by blogcommentid       */		
			
			self.getSelectedBlogComment = function(id) {
				console.log("-->BlogCommentController : calling getSelectedBlogComment method : getting blogComment with id : " + id);
				BlogCommentService.getSelectedBlogComment(id).then(
						function(d) {
						self.blogcomment = d;
						console.log('id '+ self.blogcomment.blogCommentId);
						$rootScope.blogcomment= self.blogcomment;
						console.log(' r id '+ $rootScope.blogcomment.blogCommentId);
						$location.path('/viewBlogCommentById');
							}, 
						function(errResponse) {
						console.error('Error while fetching BlogComment...');
							});
								};
								
								/*  To create new blogcomment      */		
								
								self.createBlogComment = function(blogComment) {
									blogComment.blogId= $rootScope.blog.blogId ;
									console.log("-->BlogController : calling 'createBlogComment' method.", blogComment);
									console.log("-->BlogController BlogId :" +blogComment.blogId);
									BlogService.createBlogComment(blogComment).then
												(function(d) 
												{
													console.log('Current User :',$rootScope.currentUser.userId)
													self.blogComment = d;
													console.log(self.blogComment)
													
												},
												function(errResponse) {
													console.error('Error while creating blogComment...');
												}
												);
								};
												
							     /*  To update blogcomment details     */	
			                  
			self.updateBlogComment = function(blogcomment,id) {
				console.log("-->UserController : calling updateUser method.");
				BlogCommentService.updateBlogComment(blogcomment,id).then(
		         function(d) {
						self.blogcomment = d;
						alert('blogcomment Created Successfully...')
						console.log(self.blogcomment);
						},
				function(errResponse) {
				console.error('Error while updating blogcomment...')
					});
				};
			                    	
									
				                   
				                   	
				                      /*  To clear form     */			
				   
										self.reset = function() {
											console.log('submit a new blogcomment', self.blogcomment);
											self.blogcomment = {
		                                    errorCode : '',
		                                    errorMessage : '',
		                                    blogCommentId : '',
		                                    blogId : '',
		                                    userId : '',
		                                    blogComment : '',
		                                    userName : '',
		                                    blogCommentDate : '',
		                                  

											};
											$scope.myForm.$setPristine(); // reset form...
										};
				
		
		} ]);