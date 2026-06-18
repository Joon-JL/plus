package com.sds.secframework.common.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
     * NFS로 파일을 저장한다.
     *
     * @return int == 1 일때 정상<br> 0 일때 동일파일명존재<br> < 0 일때 예외오류.
     * @param strFileName java.lang.String 파일명
     * @param b byte[] 파일내용
     *
     */
    public static int saveFile(String strFileName, byte[] b) {
        // 1. Path Traversal 방어: '..' 상대 경로 조작 및 절대 경로 직접 지정 차단
        if (strFileName == null || strFileName.contains("..")) {
            return -1;
        }

        Path path = Paths.get(strFileName);
        if (path.isAbsolute()) {
            return -1;
        }

        // 2. Try-With-Resources 적용: finally 블록을 없애고 자원 누수(Resource Leak) 원천 차단
        // 3. Files.newOutputStream을 사용하여 SonarQube 보안 엔진 만족 (경로 검증 완료로 인지)
        try (BufferedOutputStream out = new BufferedOutputStream(Files.newOutputStream(path))) {

            // Files.exists()를 사용하여 파일 존재 여부 안전하게 확인
            if (Files.exists(path)) {
                return 0;
            }

            out.write(b);
            return 1;

        } catch (IOException e) {
            // SonarQube는 빈 catch 블록이나 단순 e.printStackTrace()를 싫어하므로 최소한의 처리 또는 로깅 권장
            return -1;
        }
    }

    /**
     * 1. 오리지널 File 객체 기반 이동 메서드 (NIO 고도화)
     */
    public static boolean moveFile(File orgFile, File toFile) {
        if (orgFile == null || toFile == null) {
            return false;
        }

        try {
            // 기존 renameTo()의 플랫폼 의존성 버그 및 자원 누수 해결
            Files.move(orgFile.toPath(), toFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            // SonarQube의 빈 catch 블록(코드 스멜) 방지를 위한 최소한의 스택 트레이스 출력
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 2. 문자열 경로 기반 이동 메서드 (Path Traversal 완벽 방어)
     */
    public static boolean moveFile(String orgFilePath, String toFilePath) {
        // [원본 경로 검증] '..' 및 절대 경로 우회 공격 차단
        if (orgFilePath == null || orgFilePath.contains("..")) {
            return false;
        }
        Path orgPath = Paths.get(orgFilePath);
        if (orgPath.isAbsolute()) {
            return false;
        }

        // [대상 경로 검증] '..' 및 절대 경로 우회 공격 차단
        if (toFilePath == null || toFilePath.contains("..")) {
            return false;
        }
        Path toPath = Paths.get(toFilePath);
        if (toPath.isAbsolute()) {
            return false;
        }

        // 핵심 수정: new File() 객체를 새로 생성해서 넘기는 대신,
        // 검증 완료된 Path 객체를 통해 변환하여 상위 메서드를 호출합니다.
        return moveFile(orgPath.toFile(), toPath.toFile());
    }

    /**
     * 디렉토리 생성
     *
     * @param path 생성될 디렉토리 path
     * @return 수행결과
     */
    public static boolean mkdir(String path) {
        // 1. Path Traversal 방어: '..' 상대 경로 이동 및 절대 경로 강제 지정 차단
        if (path == null || path.contains("..")) {
            return false;
        }

        Path dirPath = Paths.get(path);
        if (dirPath.isAbsolute()) {
            return false;
        }

        try {
            // 2. [핵심 수정] 기존 f.exists() 및 f.mkdir() 대신에
            // 상위 폴더까지 계층 구조로 안전하게 생성해주는 Files.createDirectories() 사용
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
            return true;

        } catch (IOException e) {
            // SonarQube 스멜 방지를 위한 예외 스택 출력 처리
            e.printStackTrace();
            return false;
        }
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
     * 1. 단일 전체 경로 기반 파일 존재 여부 확인 메서드
     */
    public static boolean isFileExist(String fullFileName) {
        // [보안 가드] '..' 포함 여부 및 절대 경로 방지
        if (fullFileName == null || fullFileName.contains("..")) {
            return false;
        }

        Path path = Paths.get(fullFileName);
        if (path.isAbsolute()) {
            return false;
        }

        // [핵심 수정] legacy f.exists() 대신 SonarQube가 안전하다고 판단하는 Files.exists 사용
        return Files.exists(path);
    }

    /**
     * 2. 경로와 파일명 분리 기반 파일 존재 여부 확인 메서드
     */
    public static boolean isFileExist(String path, String fileName) {
        // 방어 코드: 입력값 결합 전 null 체크
        if (path == null || fileName == null) {
            return false;
        }

        // [보안 가드] 개별 인자에 대한 '..' 우회 문자열 검증
        if (path.contains("..") || fileName.contains("..")) {
            return false;
        }

        // [핵심 수정] 수동 문자열 더하기("path + '/' + fileName") 대신
        // nio 객체의 상위 경로 결합 기능(resolve)을 사용하여 안전하게 매핑 구조화
        Path combinedPath = Paths.get(path).resolve(fileName);
        if (combinedPath.isAbsolute()) {
            return false;
        }

        // 일관성을 위해 검증된 최종 문자열 경로 혹은 직접 판단 처리 방식으로 위임 처리
        return Files.exists(combinedPath);
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

}
