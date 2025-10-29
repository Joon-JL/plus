package com.sec.common.jobs.service;

import com.sec.common.jobs.service.impl.LasJobServiceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LasJob extends Thread {

    public void run() {
    	
		try {
    	 
	    	while(true) {
	    		//결재상태 조회
   				setOwnerDeptBatch();
				sleep(60000); //1분동안 Holding
	    	}
    	} catch (Exception ex) {
    		ex.printStackTrace();	    		
    	} finally {
    		try {
				sleep(60000); //1분동안 Holding
    		} catch (InterruptedException ex) {}
    	}

	}
      
    //Daemon 그룹장 이관상태조회 및 Ownership 부서 설정
    public void setOwnerDeptBatch() throws Exception {
    	LasJobServiceImpl lasOwnerDeptJob = new LasJobServiceImpl();
    	//lasOwnerDeptJob.ownerDeptBatch();
	}
    
    //Daemon 계약관리시스템메일발송처리
    public void setClmMailBatch() throws Exception {
    	LasJobServiceImpl lasOwnerDeptJob = new LasJobServiceImpl();
    	lasOwnerDeptJob.clmMailBatch();
	}

	/**
	 * Log 
	 *
	 * @param  
	 * @return Log
	 * @throws 
	*/
	private Log getLogger() {
		return LogFactory.getLog(this.getClass());
	}
	
	public static void main(String[] args) {
		LasJob approvalDaemon = new LasJob();
		approvalDaemon.start();
	}
}
