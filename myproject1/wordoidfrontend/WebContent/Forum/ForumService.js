app.factory('ForumService', ['$http', '$q', '$rootScope',
		function($http, $q, $rootScope) {
			console.log("ForumService...")

			var BASE_URL = 'http://localhost:9009/wordoid'
				return {
			
									/*      All Forums Details       */			
	fetchAllForums : function() {
console.log("--> ForumService : calling 'fetchAllForums' method.");
				return $http
				.get(BASE_URL + '/forums')
				.then(function(response) {
				return response.data;
					}, 
				function(errResponse) {
				console.error('Error while fetching Uss');
				return $q.reject(errResponse);
					});
				},
				
							   /*        Forums Details by forumid     */	
				
				
		getSelectedForum : function(id) {
					console.log("-->ForumService : calling getSelectedForum() method with id : " + id);
					return $http
						.get(BASE_URL+'/forum/'+ id)
						.then(function(response) {
						$rootScope.selectedUser = response.data;
						return response.data;
							},
						function(errResponse) {
						console.error('Error while Fetching Forum.');
						return $q.reject(errResponse);
									});
						},	
						
						
						   /*        Create new Forums      */	
						
						createForum : function(forum) 
						{
							console.log("-->ForumService : calling 'createForum' method.");
							return $http.post(BASE_URL+'/forum/', forum).then
										(function(response) 
										{
											return response.data;
										},
										function(errResponse)
										{
											console.error('Error while posting new Forum...');
											return $q.reject(errResponse);
										}
										);
						},
							
							
							
							
							  createForumComment : function(forumcomment)
								{
									console.log("-->ForumService : calling 'createForumComment' method.");
									return $http.post(BASE_URL + '/saveForumComment/', forumcomment).then
									(function(response)
											{
													return response.data;
											}, 
											function(errResponse) 
											{
													console.error('Error while creating forumcomment');
													return $q.reject(errResponse);
											}
									);
								},
								
								
								
								
								
								
							     /*       Update Forum Details                   */	
								
								
								
								
								
							
							updateForum: function(forum,id) {
								console.log("--> ForumService : calling 'updateForum' method with id : "+id);
								console.log(forum);
								return $http
									.put(BASE_URL+'/forum/'+id,forum)
									.then(function(response) {
									return response.data;
										},
									function(errResponse) {
									console.error('Error while updating forum');						
									return $q.reject(errResponse);
											});
								},
								
								
								/*      All Approved Forums Details       */		
								
							fetchAllApprovedForums : function() {
							console.log("--> ForumService : calling 'fetchAllApprovedForums' method.");
							return $http
							.get(BASE_URL + '/approvedForum')
							.then(function(response) {
							return response.data;
							}, 
							function(errResponse) {
							console.error('Error while fetching Uss');
							return $q.reject(errResponse);
							});
							},
								
			                 
							
							
							
							
							approveForum : function(forum, id)
							{
								console.log("-->ForumService : calling approveForum() method : getting forum with id : " + id);
								return $http.put(BASE_URL+'/approveForum/'+ id, forum).then
											(function(response) 
											{
												return response.data;
											},
											function(errResponse) 
											{
												console.log("Error while approving Forum");
												return $q.reject(errResponse);
											}
											);
							},
							
							
							
							
							rejectForum : function(forum, id) 
							{
								console.log("-->ForumService : calling rejectForum() method : getting forum with id : " + id);
								return $http.put(BASE_URL+'/rejectForum/'+ id, forum).then
											(function(response)
											{
												return response.data;
											},
											function(errResponse)
											{
												console.log("Error while rejecting Forum");
												return $q.reject(errResponse);
											}
										    );
							},
							
							
							
							
							
							
							
							
							
							
		
			};	
			}]);