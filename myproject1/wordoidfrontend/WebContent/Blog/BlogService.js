app.factory('BlogService', ['$http', '$q', '$rootScope',
		function($http, $q, $rootScope) {
			console.log("BlogService...")

			var BASE_URL = 'http://localhost:9009/wordoid'
				return {
			
									/*      All blog Details       */			
				
				fetchAllBlogs : function() {
			console.log("--> BlogService : calling 'fetchAllblogs' method.");
							return $http
							.get(BASE_URL + '/blogs')
							.then(function(response) {
							return response.data;
								}, 
							function(errResponse) {
							console.error('Error while fetching blogs');
							return $q.reject(errResponse);
								});
							},
				
							   /*        blog  Details by blogid     */	
				
				
		getSelectedBlog : function(id) {
					console.log("-->BlogService : calling getSelectedBlog() method with id : " + id);
					return $http
						.get(BASE_URL+'/blog/'+ id)
						.then(function(response) {
						$rootScope.selectedBlog = response.data;
						return response.data;
							},
						function(errResponse) {
						console.error('Error while Fetching blog.');
						return $q.reject(errResponse);
									});
						},	
						
						
						   /*        Create new blog      */	
						
						createBlog : function(blog) {
							console.log("--> BlogService : calling 'createBlog' method.");
							console.log(blog);
							return $http
							.post(BASE_URL + '/blog/', blog)
							.then(function(response) {
							return response.data;
									}, 
							function(errResponse) {
								console.error('Error while creating blog');
								
								return $q.reject(errResponse);
									});
							},
							
							createBlogComment : function(blogComment)
							{
								console.log("-->BlogService : calling 'createBlogComment' method.");
								return $http.post(BASE_URL + '/blogComment/', blogComment).then
								(function(response)
										{
												return response.data;
										}, 
										function(errResponse) 
										{
												console.error('Error while creating blogComment');
												return $q.reject(errResponse);
										}
								);
							},
							
							
							

							fetchAllApprovedBlogs : function() {
											console.log("--> BlogService : calling 'fetchAllApprovedBlogs' method.");
											return $http
											.get(BASE_URL + '/approvedBlog')
											.then(function(response) 
											{
											return response.data;
											}, 
											function(errResponse) 
											{
											console.error('Error while fetching Uss');
											return $q.reject(errResponse);
											});
											},
							
											
											
											
											
											
											
											approveBlog : function(blog, id)
											{
												console.log("-->BlogService : calling approveBlog() method : getting blog with id : " + id);
												return $http.put(BASE_URL+'/approveBlog/'+ id, blog).then
															(function(response) 
															{
																return response.data;
															},
															function(errResponse) 
															{
																console.log("Error while approving Blog");
																return $q.reject(errResponse);
															}
															);
											},
											
											
											
											
											
											rejectBlog : function(blog, id) 
											{
												console.log("-->BlogService : calling rejectBlog() method : getting blog with id : " + id);
												return $http.put(BASE_URL+'/rejectBlog/'+ id, blog).then
															(function(response)
															{
																return response.data;
															},
															function(errResponse)
															{
																console.log("Error while rejecting Blog");
																return $q.reject(errResponse);
															}
														    );
											},				
							
							
							
							
							
							
							
							
							
							
							
							
							
							     /*       Update blog                   */	
							
							updateBlog: function(blog,id) {
								console.log("--> BlogService : calling 'updateBlog' method with id : "+id);
								console.log(blog);
								return $http
									.put(BASE_URL+'/blog/'+id,blog)
									.then(function(response) {
									return response.data;
										},
									function(errResponse) {
									console.error('Error while updating blog');						
									return $q.reject(errResponse);
											});
								},
								
		
			};	
			}]);