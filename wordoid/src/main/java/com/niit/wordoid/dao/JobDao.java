package com.niit.wordoid.dao;

import java.util.List;

import com.niit.wordoid.model.Job;
import com.niit.wordoid.model.JobApplication;




public interface JobDao {
	   public boolean saveJob(Job job);
		
		public boolean update(Job job);
		
		/*public boolean saveOrUpdate(Job job);*/
		
		public boolean delete(Job job);
		
		public Job getJobByJobId(int id);
		
		public List<Job> list();
		
		public List<Job> getMyAppliedJobs(String userid);
		
		public JobApplication get(String userid, int jobid);
		
		public boolean updateJobApplication(JobApplication jobApplication);
		
		public boolean applyForJob(JobApplication jobApplication);
		
		public List<JobApplication> listJobApplications();
		
		public List<Job> listVacantJobs();
		
	}
