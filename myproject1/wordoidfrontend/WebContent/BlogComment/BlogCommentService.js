app.factory('BlogCommentService', ['$http', '$q', '$rootScope',
		function($http, $q, $rootScope) {
			console.log("BlogCommentService...")

			var BASE_URL = 'http://localhost:9009/wordoid'
				return {
			
									/*      All blogcomment Details       */			
	
				
				fetchAllBlogComments : function() {
			    console.log("--> BlogCommentService : calling 'fetchAllblogComments' method.");
							return $http
							.get(BASE_URL + '/blogComments')
							.then(function(response) {
							return response.data;
								}, 
							function(errResponse) {
							console.error('Error while fetching blogcomments');
							return $q.reject(errResponse);
								});
							},
				
							   /*        blog comment  Details by blogcommentid     */	
				
				
							
							getSelectedBlogComment : function(id) {
										console.log("-->BlogCommentService : calling getSelectedBlogComment() method with id : " + id);
										return $http
											.get(BASE_URL+'/blogCommentByBlogId/'+ id)
											.then(function(response) {
											$rootScope.selectedBlogComment = response.data;
											alert(response.data);
											return response.data;
												},
											function(errResponse) {
											console.error('Error while Fetching blogcomment.');
											return $q.reject(errResponse);
														});
											},	
											
						   /*        Create new blogcomment      */	
						
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
											
							     /*       Update blogcomment                    */	
							
							updateBlogComment : function(blogcomment,id) {
								console.log("--> BlogCommentService : calling 'updateBlogComment' method with id : "+id);
								console.log(blogcomment);
								return $http
									.put(BASE_URL+'/updateBlogComment/'+id,blogcomment)
									.then(function(response) {
									return response.data;
										},
									function(errResponse) {
									console.error('Error while updating blogcomment');						
									return $q.reject(errResponse);
											});
								},
								
								
								getSelectedBlogCommentByUserId : function(id) {
									console.log("-->BlogCommentService : calling getSelectedBlogCommentByUserId() method with user id : " + id);
									return $http
										.get(BASE_URL+'/blogCommentsByUserId/'+ id)
										.then(function(response) {
										$rootScope.selectedBlogComment = response.data;
										return response.data;
											},
										function(errResponse) {
										console.error('Error while Fetching Blog Comment.');
										return $q.reject(errResponse);
													});
										}
							
						};
								
		
				
			}]);