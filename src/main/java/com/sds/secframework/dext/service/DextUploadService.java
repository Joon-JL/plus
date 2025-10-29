package com.sds.secframework.dext.service;

import devpia.dextupload.FileUpload;

/**
 * @author Administrator
 *
 */
public interface DextUploadService {
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String dextFileUpload(FileUpload fileUpload) throws Exception;
}
