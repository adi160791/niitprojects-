app.factory('ForumCommentService', ['$http', '$q', '$rootScope',
		function($http, $q, $rootScope) {
			console.log("ForumCommentService...")

			var BASE_URL = 'http://localhost:9009/wordoid'
				return {
			
									/*      All forumcomment Details       */			
	fetchAllForumComments : function() {
console.log("--> ForumommentService : calling 'fetchAllForumComments' method.");
				return $http
				.get(BASE_URL + '/forumComments')
				.then(function(response) {
				return response.data;
					}, 
				function(errResponse) {
				console.error('Error while fetching forumcomment');
				return $q.reject(errResponse);
					});
				},
				
							   /*        forum  Details by forumcommentid     */	
				
				
				getSelectedForumComment : function(id) {
					console.log("-->ForumCommentService : calling getSelectedForumComment() method with id : " + id);
					return $http
						.get(BASE_URL+'/forumCommentByforumId/'+ id)
						.then(function(response) {
						$rootScope.selectedForumComment = response.data;
						alert(response.data);
						return response.data;
							},
						function(errResponse) {
						console.error('Error while Fetching Forumcomment.');
						return $q.reject(errResponse);
									});
						},	
						
						   /*        Create new forumcomment      */	
						
						createForumComment: function(forumcomment) {
							console.log("--> ForumCommentService : calling 'createForumComment' method.");
							return $http
							.post(BASE_URL + '/saveForumComment/', forumcomment)
							.then(function(response) {
								$rootScope.selectedForum = response.data;
							return response.data;
									}, 
							function(errResponse) {
								console.error('Error while creating forumcomment');
								return $q.reject(errResponse);
									});
							},
							
							     /*       Update forumcomment                   */	
							
							updateForumComment: function(forumcomment,id) {
								console.log("--> ForumCommentService : calling 'updateForumComment' method with id : "+id);
								console.log(forumcomment);
								return $http
									.put(BASE_URL+'/updateForumComment/'+id,forum)
									.then(function(response) {
									return response.data;
										},
									function(errResponse) {
									console.error('Error while updating forumcomment');						
									return $q.reject(errResponse);
											});
								},
								
		
			};	
			}]);