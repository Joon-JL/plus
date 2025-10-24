package com.sec.clm.search.common.provider;

import com.autonomy.aci.AciConnectionDetails;

public class AciConnectionDetailsProvider extends Provider {
	
	AciConnectionDetails details = null;
	
//	public AciConnectionDetailsProvider(){
//	}
	
    public static AciConnectionDetailsProvider getInstance() throws InstantiationException, IllegalAccessException {
    	return (AciConnectionDetailsProvider) getInstance(AciConnectionDetailsProvider.class);
    }
	
	public AciConnectionDetails provide(){
		return this.provide("165.213.248.183",29010); //운영 : 165.213.248.183, 개발 : 165.213.248.185
	}
	
//	public synchronized AciConnectionDetails provide(String host, int port) {
//		if(details == null){
//			details = new AciConnectionDetails();	
//		}
//		
//		details.setHost(host);
//		details.setPort(port);
//		return details;
//	}
	
	public AciConnectionDetails provide(String host, int port) {
		synchronized(this) {
			if(details == null){
				details = new AciConnectionDetails();	
			}
			
			details.setHost(host);
			details.setPort(port);
			return details;
		}		
	}
}