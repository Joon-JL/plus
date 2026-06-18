package com.sds.secframework.file.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sds.secframework.common.dto.CommonVO;
import com.sds.secframework.file.dto.AttachFileVO;

/**
 * 파일 업로드를 위한 Interface
 *
 */
public interface AttachFileService {

    /**
     * 파일정보 삭제
     * @param vo 파일정보 VO Object
     * @return 수행결과
     * @throws Exception
     */
    int deleteFile(AttachFileVO vo) throws Exception;

    /**
     * DB에서 넘어온 File 정보를 다시 File Attatch 문자열로 만든다.
     *
     * @param    collist DB에서 넘어온 File List
     * @return   File Attatch 문자열(seq_no*로컬파일명*파일정보*서버파일명(경로+실제저장파일명)*크기|....)
     */
    String getFileInfosToString(List collist) throws Exception;

    /**
     * String 으로 넘어온 File Info를 FileVO 타입으로 변환하여 넘겨준다.
     * @param fileInfos
     * @return
     * @throws Exception
     */
    ArrayList getFileVOFromFileInfos(String fileInfos) throws Exception;

    /**
     * 첨부파일 일련번호 채번<P>
     *
     * @param
     * @return 첨부파일 일련번호
     * @throws Exception
     */
    String getFileRefNo() throws Exception;

    /**
     * 첨부파일 유무. FileInfos정보로 파일이 있는지 없는지 판별<P>
     *
     * @param
     * @return boolean
     * @throws Exception
     */
    boolean isExistsFileInfos(String fileInfos) throws Exception;

    /** 김정곤 2011,03,18
     * 파일을 insert 한다. DataBase 에 정보저장
     * 작업후 file_ref_no 를 리턴해준다.
     * @param
     * @return String
     * @throws Exception
     */
    public String FileInsert(String FileInfos) throws Exception;

    /** 김정곤 2011,03,18
     * 파일을 update 한다. DataBase 에 정보저장
     * 작업후 file_ref_no 를 리턴해준다.
     * @param
     * @return String
     * @throws Exception
     */
    public String FileUpdate(String FileInfos,String File_ref_no) throws Exception;

    /** 김정곤 2011,03,18
     * 파일을 delete 한다. DataBase 에 정보저장
     * 작업후 "" 를 리턴해준다.
     * @param
     * @return String
     * @throws Exception
     */
    public String FileDelete(String FileInfos,String File_ref_no) throws Exception;


    /** 김정곤 2011,03,18
     * 파일을 리스트를 조회 한다.
     * 작업후 fileInfos 를 리턴해준다.
     * 리턴한 값을 다음과 같이 사용할 수 있다.
     * result.put("fileInfos",attachFileService.FileList(vo,vo.getFile_ref_no()));
     * @param
     * @return String
     * @throws Exception
     */
    public String FileList(String File_ref_no) throws Exception;
    /**
     * 파일을 엑션별로 다룬다. DataBase 정보 조회
     * 작업후 fileInfos 를 리턴해준다.
     * 리턴한 값을 다음과 같이 사용할 수 있다.
     * result.put("fileInfos",attachFileService.FileListCom(vo,vo.getFile_ref_no()));
     * @param
     * @return String
     * @throws Exception
     */
    public String FileSave(String FileInfos,String File_ref_no,String div) throws Exception;

}
