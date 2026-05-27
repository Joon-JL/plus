package com.sds.secframework.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

//import zion.service.PropertyService;

/**
 * 파일 관련된 Utility
 * 
 * @version 1.0, 2007. 02. 14
 * @author handbell
 * @since 1.0
 */
public class FileUtil {

    /**
     * <pre>
     * 해당 파일을 읽어 byte[]로 리턴한다.
     * 
     * @param StrFileName java.lang.String 파일명
     * @return byte[] 파일의 내용 <br> b == null 일때 예외오류 <br> b.length = 0 일때 파일 없음.
     * 
     */
    public static byte[] readFileByte(String StrFileName) {

        byte[] b = null;
        BufferedInputStream in = null;
        File f = new File(StrFileName);

        try {

            if (!f.exists()) {

                b = new byte[0];

            } else {

                in = new BufferedInputStream(new FileInputStream(f));
                b = new byte[(int) f.length()];

                int i;
                while ((i = in.read(b)) != -1) {
                    /*do nothing*/
                }

            }
        } catch (Exception e) {
            b = null;

        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (Exception ee) {
            }
            
        }
        return b;
    }

	public static String readFileString(String filePath) {
		StringBuilder sb = new StringBuilder();

		// 괄호 안에 선언하면 Java가 자동으로 스트림을 닫아줍니다 (Resource Leak 완벽 해결)
		try (FileReader fr = new FileReader(filePath);
			 BufferedReader br = new BufferedReader(fr)) {

			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\r\n");
			}

			return sb.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
    /**
     * NFS로 파일을 저장한다.
     * 
     * @return int == 1 일때 정상<br> 0 일때 동일파일명존재<br> < 0 일때 예외오류.
     * @param strFileName java.lang.String 파일명
     * @param b byte[] 파일내용
     *
     */
    public static int saveFile(String strFileName, byte[] b) {

        BufferedOutputStream out = null;
        File f = new File(strFileName);

        try {

            if (f.exists()) {
                return 0;
            }

            out = new BufferedOutputStream(new FileOutputStream(f));
            out.write(b);
            return 1;

        } catch (Exception e) {
            return -1;

        } finally {

            try {
                if (out != null)
                    out.close();
            } catch (Exception ee) {

            }
        }
    }

    /**
     * 파일의 권한을 수정
     * 
     * @param strFileName 파일명
     * @param mode 변경할 권한 모드 (ex : 777)
     */
    /*public static void chmod(String strFileName, String mode) {

        try {

            Runtime rt = Runtime.getRuntime();
            rt.exec("chmod " + mode + " " + strFileName);

        } catch (Exception e) {
        }
    }*/


    /**
     * Repository에 파일을 저장한다. 
     * 
     * @param fileName 저장할 File명(path를 제외한 단순 파일명)
     * @param sysId 시스템 분류코드
     * @param b 파일 내용에 대한 byte[]
     * @return 상대경로를 포함한 저장된 파일명<br>오류인 경우는 null을 return함.
     */
//    public static String saveFileToRepository(String fileName, String sysId, byte[] b) {
//
//        String rootPath = getRootPath(sysId);
//                
//        String saveFileName = getSaveFileName(fileName, sysId);;
//
//        try {
//            if ( saveFile(rootPath + "/" + saveFileName ,b) == 1 ) {
//                return saveFileName;
//            }
//            else {
//                return null;
//            }
//        } catch (Exception e) {
//            return null;
//        }
//    }
    
        
    /**
     * 파일 이동
     * 
     * @param orgFile Source 파일
     * @param toFile Target 파일
     * @return 수행결과
     */
    public static boolean moveFile(File orgFile, File toFile) {
        boolean isSuccess = false;
        try {
            isSuccess = orgFile.renameTo(toFile);
        } catch (Exception e) {
        }
        
        return isSuccess;
  
    }
  
    /**
     * 파일 이동
     * 
     * @param orgFilePath Source 파일명
     * @param toFilePath Target 파일명
     * @return 수행결과
     */
    public static boolean moveFile(String orgFilePath, String toFilePath) {
    
        return moveFile(new File(orgFilePath), new File(toFilePath));
    
    }
  
    /**
     * 파일 삭제
     * 
     * @param file 삭제할 파일 객체
     * @return 수행결과 
     */
    public static boolean deleteFile(File file) {
        boolean isSuccess = false;
        
        try {
            isSuccess =  file.delete();
        } catch (Exception e) {
        }
        
        return isSuccess;
        
    }
  
    /**
     * 파일 삭제
     * 
     * @param filePath 삭제할 파일명(full Path 포함)
     * @return 수행결과 
     */
    public static boolean deleteFile(String filePath) {
    
        return deleteFile(new File(filePath));
    
    }

    /**
     * 파일 삭제
     * 
     * @param fileName 삭제할 파일명(년도/월(/일시)/파일명 의 형태)
     * @param sysId 시스템 분류코드 
     * @return 수행결과 
     */
//    public static boolean deleteFile(String fileName, String sysId) {
//    
//        return deleteFile(new File(getRootPath(sysId) + "/" + fileName));
//    
//    }
    /**
     * 디렉토리 생성
     * 
     * @param path 생성될 디렉토리 path
     * @return 수행결과
     */
    public static boolean mkdir(String path) {
    	
    	boolean result = false;
    	File f = new File(path);
		
    	try {
	    	if(!f.exists() || !f.isDirectory()) {
				result = f.mkdir();
			} else {
				result = true ;
			}
	    	//result = true;
    	} catch(Exception e) {
    		e.printStackTrace() ;
    		result = false;
    	}

    	return result;
    
    }   
    
    /**
     * 경로를 제거한 파일명을 return한다.
     * 
     * @param fullFileName 경로를 포함한 파일 명
     * @return 파일 명
     */
    public static String getOnlyFileName(String fullFileName)
    {
    	String seperator = File.separator;
        // 파일 명 분석하여 파일경로와 파일명을 분리함
        int pos = fullFileName.lastIndexOf(seperator);
        if (pos != -1) {
            return fullFileName.substring(pos+1);
        }
        
        pos = fullFileName.lastIndexOf("\\");
        if (pos != -1) {
            return fullFileName.substring(pos+1);
        }
        else {
            return fullFileName;
        }
        
    }

    /**
     * 경로를 포함한 파일명을 return한다.
     * 
     * @param fileName 파일 명(년도/월(/일시)/파일명 의 형태)
     * @param sysId 시스템 분류코드
     * @return 경로를 포함한 전체 파일 명
     */
//    public static String getFullFileName(String fileName, String sysId)
//    {
//        return getRootPath(sysId) + "/" + fileName;
//        
//    }

    /**
     * 저장될 File명 생성
     * 
     * @param fileName 저장될 File명(경로를 제외한 파일명)
     * @param sysId 시스템 분류코드
     * @return 저장될 File명(년도/월(/일시)/파일명), 오류발생시 null을 return
     */
//    public static String getSaveFileName(String fileName, String sysId) {
//
//        String rootPath = getRootPath(sysId);
//        
//        // 년도 및 월로 디렉토리를 생성하고 상대경로를 지정
//        SimpleDateFormat oFormatter = new SimpleDateFormat ("yyyyMMddHHmmssSSS", java.util.Locale.KOREA);
//        String curTime = oFormatter.format(new Date());
//        String rPath = curTime.substring(0,4) + "/" + curTime.substring(4,6);
//        String tPath = rPath + "/" + curTime.substring(6);
//        mkdir(rootPath + "/" + rPath);
//
//        // 저장할 파일명(상대경로)
//        String saveFileName = rPath + "/" + fileName;        
//
//        try {
//            if (isFileExist(rootPath + "/" + saveFileName)) {
//                mkdir(rootPath + "/" + tPath);
//                saveFileName = tPath + "/" + fileName;
//            }
//        } catch (Exception e) {
//            return null;
//        }
//        
//        return saveFileName;
//    }


    /**
     * File이 존재하는 지 확인
     * 
     * @param fullFileName 확인할 File명
     * @return true-존재하는 경우<br>false-존재하지 않는 경우
     */
    public static boolean isFileExist(String fullFileName){
        File f = new File(fullFileName);
        return f.exists();
    }

    
    /**
     * File이 존재하는 지 확인
     * 
     * @param path 파일의 경로
     * @param fileName 파일명
     * @return  true-존재하는 경우<br>false-존재하지 않는 경우
     */
    public static boolean isFileExist(String path, String fileName) {
        return isFileExist(path + "/" + fileName);
    }
    
    /**
     * 파일 저장소의 위치 Path를 가져옴
     * 
     * @return 저장소의 위치, 오류발생이나 property가 없는 경우 null을 return
     */
//    private static String getRepositoryPath() {
//        String repositoryPath = new PropertyService().getProperty("common.attach.repository.path");
//        
//        if (repositoryPath == null || "".equals(repositoryPath)) {
//            return null;
//        }
//
//        // '/'로 Path가 끝난 경우 '/'를 제거함
//        if ( repositoryPath.endsWith("/") ) {
//            repositoryPath = repositoryPath.substring(0, repositoryPath.length()-1);
//        }
//
//        return repositoryPath;
//        
//    }

    /**
     * 저장할 파일의 root 경로를 가져옴
     * Repository/시스템분류코드/년도/월 의 경로가 됨
     * 
     * @param sysId 시스템 분류코드
     * @return 현재의 파일저장 root path 정보
     */
//    private static String getRootPath(String sysId) {
//        String path = getRepositoryPath();
//        
//        if (path == null) {
//            return null;
//        }
//        
//        path = path + "/" + sysId ;
//        
//        // 해당 디렉토리 생성
//        mkdir(path);
//
//        return path;
//        
//    }
    
    /**
	  * DB에서 넘어온 File 정보를 다시 File Attatch 문자열로 만든다.
	  *
	  * @param    collist 	DB에서 넘어온 File List(DOC_ID, DOC_GBN, FILE_NM, FILE_INFO, FILE_PATH, FILE_SZ, ATTC_IDX, ORG_ID)
	  * @return   File Attatch 문자열(DocID*로컬파일명*파일정보*서버파일명*크기|....)
	  *                               DocID*로컬파일명*파일정보*서버파일명*크기 
	  */
	public static String getFileInfosToString(ArrayList collist) {
		// 1. Null 발생 예방 및 빈 리스트인 경우 즉시 빈 문자열 반환 (Fast-Fail 최적화)
		if (collist == null || collist.isEmpty()) {
			return "";
		}

		// 2. 루프 외부에서 단 하나의 StringBuilder를 생성하여 메모리 낭비 원천 차단
		StringBuilder fileInfos = new StringBuilder();

		for (int i = 0; i < collist.size(); i++) {
			HashMap hList = (HashMap) collist.get(i);

			// 데이터 누락을 방지하기 위해 각 맵 객체의 Null 검증을 추가해주는 것이 안전합니다.
			if (hList == null) {
				continue;
			}

			// 3. 임시 객체 생성 없는 연속 체이닝 버퍼 처리 수행
			fileInfos.append(StringUtil.nvl(FormatUtil.formatNumToString(hList.get("SEQ_NO")), ""))
					.append("*").append(StringUtil.nvl((String) hList.get("FILE_NM"), ""))
					.append("*").append(StringUtil.nvl((String) hList.get("FILE_INFO"), ""))
					.append("*").append(StringUtil.nvl((String) hList.get("FILE_PTH"), ""))
					.append("*").append(StringUtil.nvl(FormatUtil.formatNumToString(hList.get("FILE_SIZE")), ""))
					.append("|");
		}

		return fileInfos.toString();
	}

		/**
		 * Returns the Mime Type of the file, depending on the extension of the filename
		 */
		static String getMimeType(String fName) {
			fName = fName.toLowerCase();
			if (fName.endsWith(".jpg") || fName.endsWith(".jpeg") || fName.endsWith(".jpe")) return "image/jpeg";
			else if (fName.endsWith(".gif")) return "image/gif";
			else if (fName.endsWith(".pdf")) return "application/pdf";
			else if (fName.endsWith(".htm") || fName.endsWith(".html") || fName.endsWith(".shtml")) return "text/html";
			else if (fName.endsWith(".avi")) return "video/x-msvideo";
			else if (fName.endsWith(".mov") || fName.endsWith(".qt")) return "video/quicktime";
			else if (fName.endsWith(".mpg") || fName.endsWith(".mpeg") || fName.endsWith(".mpe")) return "video/mpeg";
			else if (fName.endsWith(".zip")) return "application/zip";
			else if (fName.endsWith(".tiff") || fName.endsWith(".tif")) return "image/tiff";
			else if (fName.endsWith(".rtf")) return "application/rtf";
			else if (fName.endsWith(".mid") || fName.endsWith(".midi")) return "audio/x-midi";
			else if (fName.endsWith(".xl") || fName.endsWith(".xls") || fName.endsWith(".xlv")
					|| fName.endsWith(".xla") || fName.endsWith(".xlb") || fName.endsWith(".xlt")
					|| fName.endsWith(".xlm") || fName.endsWith(".xlk")) return "application/excel";
			else if (fName.endsWith(".doc") || fName.endsWith(".dot")) return "application/msword";
			else if (fName.endsWith(".png")) return "image/png";
			else if (fName.endsWith(".xml")) return "text/xml";
			else if (fName.endsWith(".svg")) return "image/svg+xml";
			else if (fName.endsWith(".mp3")) return "audio/mp3";
			else if (fName.endsWith(".ogg")) return "audio/ogg";
			else return "text/plain";
		}
		
	 // 트리의 하위디렉토리가없는 파일 디렉토리인지 판별
	  public static boolean isLeaf(Object node) {
	    return ((File) node).isFile();
	  }

	/**
	 * 클래스 파일인지 여부
	 */
	public static boolean isClass(File f) {
		String fName = FileUtil.getOnlyFileName(f.getAbsolutePath());
		fName = fName.toLowerCase();
		if (fName.endsWith(".class")) 
			return true;
		else 
			return false;
	}

	  // 하위노드 갯수를 반환
	  public static int getChildCount(Object parent) {
	    String[] children = ((File) parent).list();
	    if (children == null)
	      return 0;
	    return children.length;
	  }

	  // 트리에 각노드를 붙여넣음
	  // 트리의 모든 파일 노드를 반환
	  // File.toString()가 불릴때마다 하위 노드를 표시.
	  public static Object getChild(Object parent, int index) {
	    String[] children = ((File) parent).list();
	    if ((children == null) || (index >= children.length))
	      return null;
	    return new File((File) parent, children[index]);
	  }

}
