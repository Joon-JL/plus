package com.sds.secframework.singleIF.job;

import com.sds.secframework.singleIF.service.impl.EsbApprovalServiceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EsbApprovalJob extends Thread {

    public void run() {
    	
		try {
    	 
	    	while(true) {
	    		//결재상태 조회
    			getStatusBatch();
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
      
    //결재상태조회
    public void getStatusBatch() throws Exception {
    	EsbApprovalJobImpl esbApproval = new EsbApprovalJobImpl();
		esbApproval.getStatusBatch();
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
		EsbApprovalJob approvalDaemon = new EsbApprovalJob();
		approvalDaemon.start();
	}
}
