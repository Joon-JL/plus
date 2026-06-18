package com.sds.secframework.dataIF.service.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.FileUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.dataIF.service.DataLoadService;

public class DataLoadServiceImpl extends CommonServiceImpl implements DataLoadService {

    private String genQuery(String tempTableName, String realTableName, String[] tempColumnNames, String[] realColumnNames, String gbn) throws Exception {

        String sql = "";

        // 1. 방어 코드: 루프 처리를 위한 상위 조건 검증 및 상수를 앞에 두는 Null-Safe 비교 처리
        if (gbn != null && "TEMP_INSERT".equals(gbn)) {
            // 컬럼 개수를 기반으로 대략적인 버퍼 초기 용량을 산정하여 할당 (배열 확장 오버헤드 최소화)
            StringBuilder sb = new StringBuilder(128 + (tempColumnNames.length * 15));

            sb.append("INSERT INTO ").append(tempTableName).append(" ( ");

            for (int i = 0; i < tempColumnNames.length; i++) {
                sb.append(tempColumnNames[i]);
                if (i + 1 < tempColumnNames.length) {
                    sb.append(",");
                }
            }
            sb.append(") VALUES (");

            for (int i = 0; i < tempColumnNames.length; i++) {
                sb.append("?");
                if (i + 1 < tempColumnNames.length) {
                    sb.append(",");
                }
            }
            sb.append(")");
            sql = sb.toString();
        }
        // 2. 오타 수정: 단순 독립 'if'문을 'else if' 구조로 정렬하여 불필요한 연산 조건 차단
        else if (gbn != null && "REAL_INSERT".equals(gbn)) {
            StringBuilder sb = new StringBuilder(256 + (realColumnNames.length * 15) + (tempColumnNames.length * 15));

            sb.append("INSERT INTO ").append(realTableName).append(" ( ");

            for (int i = 0; i < realColumnNames.length; i++) {
                sb.append(realColumnNames[i]);
                if (i + 1 < realColumnNames.length) {
                    sb.append(",");
                }
            }
            sb.append(") SELECT ");

            for (int i = 0; i < tempColumnNames.length; i++) {
                sb.append(tempColumnNames[i]);
                if (i + 1 < tempColumnNames.length) {
                    sb.append(",");
                }
            }
            sb.append(" FROM ").append(tempTableName);
            sql = sb.toString();
        }

        return sql;

    }

    private int deleteData(String tableName, String sysGbn) throws Exception {
        String sql = "";
        String[] type = null;
        Object[] value = null;

        if(sysGbn != null && !"".equals(sysGbn)) {
            sql = "DELETE " + tableName + " WHERE SYS_GBN=?" ;
            type = new String[] {"VARCHAR"};
            value = new Object[] {sysGbn};
        } else {
            sql = "DELETE " + tableName;
            type = new String[0];
            value = new Object[0];
        }

        return commonDAO.deleteBySQL(sql, type, value);
    }

    private String[] getColumnInfo(String key, String separator) throws Exception {
        return StringUtil.split(propertyService.getProperty(key), separator);
    }

    private ArrayList getTempData(String srcDir, String srcFileName, int valueCnt, String dataSeparator, String encType) throws Exception {
        ArrayList result = new ArrayList();

        // 1. Path Traversal 방어 로직: 경로를 정규화(normalize)하고 실제 하위 경로인지 검증
        Path baseDirPath = Paths.get(srcDir).normalize();
        Path resolvedPath = baseDirPath.resolve(srcFileName).normalize();

        if (!resolvedPath.startsWith(baseDirPath)) {
            throw new SecurityException("Path traversal attack detected!");
        }

        // 2. Try-With-Resources: 파일 스트림 누수(Resource Leak) 방지
        try (FileInputStream fis = new FileInputStream(resolvedPath.toFile());
             BufferedReader br = new BufferedReader(new InputStreamReader(fis, encType))) {

            String lineStr;
            while ((lineStr = br.readLine()) != null) {
                Object[] data = new Object[valueCnt];
                String[] strArr = StringUtil.split(lineStr, dataSeparator, false);

                for(int i = 0; i < strArr.length; i++) {
                    data[i] = strArr[i];
                }

                result.add(data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public void loadOrgData() throws Exception {

        try {
            int result = -1;
            String fileSeparator = System.getProperty("file.separator");

            String separator        = propertyService.getProperty("secfw.dataLoad.file.org.separator");
            String dir              = propertyService.getProperty("secfw.dataLoad.file.org.dir");
            String backUpDir        = propertyService.getProperty("secfw.dataLoad.file.org.backUpDir");
            String fileName         = propertyService.getProperty("secfw.dataLoad.file.org.fileName");
            String fileEncType      = propertyService.getProperty("secfw.dataLoad.file.org.fileEncType");

            String tempTableName    = propertyService.getProperty("secfw.dataLoad.table.org.temp");
            String[] tempColumnName = getColumnInfo("secfw.dataLoad.columnName.org.temp", separator);
            String[] tempColumnType = getColumnInfo("secfw.dataLoad.columnType.org.temp", separator);

            String realTableName    = propertyService.getProperty("secfw.dataLoad.table.org.real");
            String[] realColumnName = getColumnInfo("secfw.dataLoad.columnName.org.real", separator);
            String[] realColumnType = getColumnInfo("secfw.dataLoad.columnType.org.real", separator);

            boolean flag = FileUtil.isFileExist(dir, fileName);

            if(flag) { // 파일이 존재하면

                String sql = "";
                String[] types = null;
                ArrayList values = null;

                //1. 템프테이블 데이타 입력
                sql   = genQuery(tempTableName, realTableName, tempColumnName, realColumnName, "TEMP_INSERT");
                types  = tempColumnType;
                values = getTempData(dir, fileName, tempColumnName.length, separator, fileEncType);

                commonDAO.batchUpdateBySQL(sql, types, values);

                //2. Real 테이블 데이타 삭제
                deleteData(realTableName, null);

                //3. Real 테이블 데이타 입력
                sql   = genQuery(tempTableName, realTableName, tempColumnName, realColumnName, "REAL_INSERT");

                commonDAO.insertBySQL(sql, new String[0], new Object[0]);

                //4. Real 테이블 데이타 삭제
                deleteData(tempTableName, null);

                //5. File MOVE
                FileUtil.moveFile(dir + fileSeparator + fileName, backUpDir + fileSeparator + fileName);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
