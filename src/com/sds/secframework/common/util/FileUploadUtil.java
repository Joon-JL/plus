/**
 * Title     : CommonUploadUtil.java
 * Copyright : Copyright 2007 Zion
 * Auther    : handbell
 * Date      : 2007. 02. 14
 * Description : FileUpload 관련된 Utility
 */

/*package zion.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import zion.util.StringUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.RequestUtils;
*/
/**
 *  
 * @version 1.0, 2007. 02. 15
 * @author handbell
 * @since 1.0
 */
//public class FileUploadUtil {
	
	/**
	 * Log 
	 *
	 * @param  
	 * @return Log
	 * @throws 
	*/	
//	protected Log getLogger() {
//		return LogFactory.getLog(this.getClass());
//	}
//	
//	/**
//	 * ActionForm에서 받은 FormFile 형식의 데이터를 업로드하는 메소드
//	 * 
//	 * @param file 클라이언트에서 올리는 파일.
//	 * @param sysId 시스템 분류코드
//     * 
//     * @return 저장된 파일이름(상대경로 포함)
//	 *
//	 * @exception FileNotFoundException If some sort of file representation
//	 *                                  cannot be found for the FormFile
//	 * @exception IOException If there is some sort of IOException
//	 * 
//	 */
//	public String doFileUpload(FormFile file, String sysId) throws FileNotFoundException, IOException {
//	
//        String targetFileName = null;
//        
//		if (file != null) {
//			String fileName = file.getFileName();
//                
//			if ((fileName != null) && (!fileName.equals(""))) {
//			    targetFileName = FileUtil.getSaveFileName(fileName, sysId);
//			    fileStreamUpload(file, FileUtil.getFullFileName(targetFileName, sysId));
//			}
//		}	
//        
//        return targetFileName;
//	}
//
//    /**
//     * ActionForm에서 받은 FormFile 형식의 데이터를 업로드하는 메소드
//     * 
//     * @param file 클라이언트에서 올리는 파일.
//     * @param sysId 시스템 분류코드
//     * @param newName 저장될 파일명을 변경하고 싶은 경우 지정함
//     * 
//     * @return 저장된 파일이름(상대경로 포함)
//     *
//     * @exception FileNotFoundException If some sort of file representation
//     *                                  cannot be found for the FormFile
//     * @exception IOException If there is some sort of IOException
//     * 
//     */
//    public String doFileUpload(FormFile file, String sysId, String newName) throws FileNotFoundException, IOException {
//    
//        String targetFileName = null;
//        
//        if (file != null) {
//            String fileName = file.getFileName();
//                
//            if ((fileName != null) && (!fileName.equals(""))) {
//                fileName = (StringUtil.null2space(newName).equals("") ? fileName : newName);
//                targetFileName = FileUtil.getSaveFileName(fileName, sysId);
//                fileStreamUpload(file, FileUtil.getFullFileName(targetFileName, sysId));
//            }
//        }   
//        
//        return targetFileName;
//    }
//
//    /**
//     * ActionForm에서 받은 FormFile 형식의 데이터를 업로드하는 메소드
//     * 
//     * @param file 클라이언트에서 올리는 파일.
//     * @param sysId 시스템 분류코드
//     * @param newName 저장될 파일명을 변경하고 싶은 경우 지정함
//     * @param prefix 저장될 파일명 앞에 prefix를 붙이고 싶은 경우 지정함
//     * 
//     * @return 저장된 파일이름(상대경로 포함)
//     *
//     * @exception FileNotFoundException If some sort of file representation
//     *                                  cannot be found for the FormFile
//     * @exception IOException If there is some sort of IOException
//     * 
//     */
//    public String doFileUpload(FormFile file, String sysId, String newName, String prefix) throws FileNotFoundException, IOException {
//    
//        String targetFileName = null;
//        
//        if (file != null) {
//            String fileName = file.getFileName();
//            
//            if ((fileName != null) && (!fileName.equals(""))) {
//                fileName = StringUtil.null2space(prefix) + (StringUtil.null2space(newName).equals("") ? fileName : newName);
//                targetFileName = FileUtil.getSaveFileName(fileName, sysId);
//                fileStreamUpload(file, FileUtil.getFullFileName(targetFileName, sysId));
//            }
//        }   
//        
//        return targetFileName;
//    }
//
//    /**
//     * 이 fileStreamUpload 메소드는 Stream 형태로 데이터를 업로드 한다.
//     * 
//     *
//     * @param file 클라이언트에서 올리는 파일.
//     * @param fullFileName 저장되는실제파일명(전체경로포함)
//     * 
//     * @exception FileNotFoundException If some sort of file representation
//     *                                  cannot be found for the FormFile
//     * @exception IOException If there is some sort of IOException
//     */
//    public void fileStreamUpload(FormFile file, String fullFileName)
//        throws FileNotFoundException, IOException {
//
//        getLogger().debug("fullFileName ==> " + fullFileName);
//
//        FileOutputStream os = null;
//        
//        try{
//            os = new FileOutputStream(fullFileName);
//            InputStream is = file.getInputStream();
//        
//            int bytesRead = 0;
//            byte[] buffer = new byte[8192];
//            while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
//                os.write(buffer, 0, bytesRead);                             
//            }               
//            os.flush();
//        }catch(FileNotFoundException fe){
//            fe.printStackTrace();
//        }catch(IOException ie){
//            ie.printStackTrace();           
//        }finally{
//            os.close();
//        }               
//        return;
//    }
//	
//	/**
//	 * file의 타입을 리턴한다.
//	 *
//	 * @return A String representing content type.
//	 */
//	public String getContentType(FormFile file){
//		return file.getContentType();		
//	}
//	
//	/**
//	 * 이 파일의 사이즈 리턴한다.
//	 *
//	 * @return 파일 사이즈 byte로 리턴한다.
//	 */
//	protected int getFileSize(FormFile file){
//		return file.getFileSize();
//	}
//	
//	/**
//	 * 이 파일의 이름을 리턴한다.
//	 *
//	 * @return 파일의 이름 리턴한다.
//	 */
//	protected String getFileName(FormFile file){
//		return file.getFileName();
//	}
//	
//	/**
//	 * 현재 업로드 할 수 있는 최대의 파일 사이즈를 리턴한다.
//	 * 이 리턴 값은 환경 설정에 있는 값이다.
//	 *
//	 * @param request The servlet request we are processing
//	 *
//	 * @return 최대 파일 사이즈 , byte로 표시
//	 */
//	protected String getSizeMax(HttpServletRequest request) {
//		
//		ModuleConfig mc = RequestUtils.getRequestModuleConfig(request);		
//		return mc.getControllerConfig().getMaxFileSize();
//	}
//
//	/**
//	 * 메모리안의 캐쉬나 디스크에 한 번 올릴 수 있는 파일
//	 *
//	 * @param request The servlet request we are processing
//	 *
//	 * @return The size threshold, in bytes.
//	 */
//	protected String getSizeThreshold(HttpServletRequest request) {
//		ModuleConfig mc = RequestUtils.getRequestModuleConfig(request);
//		return mc.getControllerConfig().getMemFileSize();
//	}
//}

