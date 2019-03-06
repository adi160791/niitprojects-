app.controller('BlogController', [
		'$scope',
		'BlogService',
		'BlogCommentService',
		'$location',
		'$rootScope',
		'$route',
		'$window',
		function($scope, BlogService , BlogCommentService, $location, $rootScope,$route,$window) {
			console.log("BlogController...")

		var self = this;
		self.blog = {
		    errorCode : '',
		errorMessage : '',
		blogId : '',
		blogContent : '',
		blogTitle : '',
		blogDate : '',
		userId : '',
		blogStatus : '',
		blogCountLike : '',
		blogCommentCount : '',
		
		}
		
		



		/*  To fetch all approved blog        */
		
		
		
		
		
		self.blogs = [];
		self.blogcomment=[];
		self.fetchAllBlogs = function() {
		console.log("--> BlogController : calling fetchAllBlogs method.");
		BlogService.fetchAllBlogs().then(
		function(d) {
		self.blogs = d;
						}, function(errResponse) {
							console.error('Error while fetchingBlogs...');
						});
			};
			self.fetchAllBlogs();

		

								/*  To fetch blog by blogid       */		
			
			self.getSelectedBlog = function(id) {
				console.log("-->BlogController : calling getSelectedBlog method : getting blog with id : " + id);
				BlogService.getSelectedBlog(id).then(
						function(d) {
						self.blog = d;
						console.log('id '+ self.blog.blogId);
						$rootScope.blog= self.blog;
						console.log(' r id '+ $rootScope.blog.blogId);
						$location.path('/viewBlogById');
						self.getSelectedBlogComment(id);
						console.log('comments '+$rootScope.blogcomment);
							}, 
						function(errResponse) {
						console.error('Error while fetching Blog...');
							});
								};
								
								/*  To create new blog      */		
								self.getSelectedBlogComment = function(id) {
									console.log("-->BlogCommentController : calling getSelectedBlogComment method : getting blogComment with id : " + id);
									BlogCommentService.getSelectedBlogComment(id).then(
											function(d) {
											self.blogcomment = d;
											console.log(self.blogcomment);
											console.log('id '+ self.blogcomment.blogCommentId);
											$rootScope.blogcomment= self.blogcomment;
											console.log(' r id '+ $rootScope.blogcomment.blogCommentId);
											//$location.path('/viewBlogCommentById');
												}, 
											function(errResponse) {
											console.error('Error while fetching BlogComment...');
												});
													};	
													
													
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
													
													
													
													
													
													
			self.createBlog = function(blog) {
			console.log("--> BlogController : calling createBlog method.");
			BlogService.createBlog(blog).then(
			function(d) {
			self.blog = d;
			alert('Blog Created Successfully...')
			},
			function(errResponse) {
			console.error('Error while creating blog...');
			});
			};
				
			
			
			
			self.approveBlog = function(blog, id)
			{
				console.log("-->BlogController : calling approveBlog() method : Blog id is : " + id);
				console.log("-->BlogController",self.blog);
				BlogService.approveBlog(blog, id).then
				(
						function(d)
						{
						alert('Accept Blog?'),
						self.blog=d,
						self.listblogs();
						$location.path('/viewBlog');
						},
						function(errResponse) 
						{
							console.error("Error while approving blog...")
						}
				);
			};
			
			
			
			
			
			self.fetchAllApprovedBlogs = function() {
				console.log("--> BlogController : calling fetchAllAprovedBlogs method.");
				BlogService.fetchAllApprovedBlogs().then(
				function(d) {
				self.approvedBlogs = d;
						}, 
				function(errResponse) {
				console.error('Error while fetching Blogs...');
						});
			};
			
			
			
			
			
			
			
			
			
			self.rejectBlog = function(blog, id) 
			{
				console.log("-->BlogController : calling rejectBlog() method : Blog id is : " + id);
				console.log("-->BlogController",self.blog);
				BlogService.rejectBlog(blog, id).then
				(
						function(d)
						{
						alert('Reject Blog?'),
						self.blog=d,
						self.listblogs();
						$location.path('/viewBlog');
						},
						function(errResponse) 
						{
							console.error("Error while rejecting blog...")
						}
				);
			};
			
			
			
			/*  To  update blog details     */	
			                  
			self.updateBlog = function(blog,id) {
				console.log("-->BlogController : calling updateBlog method.");
				BlogService.updateBlog(blog,id).then(
		         function(d) {
						self.blog = d;
						alert('Blog Created Successfully...')
						console.log(self.blog);
						},
				function(errResponse) {
				console.error('Error while updating blog...')
					});
				};
			                     				
							
				   
										self.reset = function() {
											console.log('submit a new blog', self.blog);
											self.blog = {
		                                    errorCode : '',
		                                    errorMessage : '',
											blogId : '',
		                                    blogContent : '',
		                                    blogTitle : '',
		                                    blogDate : '',
		                                    userId : '',
		                                    blogStatus : '',
		                                    blogCountLike : '',
		                                    blogCommentCount : '',
		
		

											};
											$scope.myForm.$setPristine(); // reset form...
										};
				
		
		} ]);