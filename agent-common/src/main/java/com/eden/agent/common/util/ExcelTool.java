package com.eden.agent.common.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * Create by zhaoxianghui on 2017/11/21.
 */
public class ExcelTool {
    private static Sheet sheet;    //表格对象
    private static SXSSFWorkbook wb;    //工作簿
    private static LinkedHashMap<String, String> headerData;   //表头

    private static final String DEFAULT_SHEET_NAME = "defaultSheet";

    public static void writeToExcel(String excelFile, List<?> data, String sheetName,
                                    LinkedHashMap<String, String> header) {
        wb = new SXSSFWorkbook();

        if (StringUtils.isBlank(sheetName)) {
            sheetName = DEFAULT_SHEET_NAME;
        }

        sheet = wb.createSheet(sheetName);
        headerData = header;

        if (headerData != null) {
            createHeadRow();
        }

        if (CollectionUtils.isNotEmpty(data)) {
            createRows(data);
        }

        try {
            FileOutputStream out = new FileOutputStream(excelFile);
            flashFile(out, wb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建各列表头
     */
    public static void createHeadRow() {
        Row head = sheet.createRow(0);   //创建表格第一行对象，为表头行
        Iterator<Map.Entry<String, String>> headTitle = headerData.entrySet().iterator();  //循环输出表头
        for (int i = 0; headTitle.hasNext(); i++) {
            Cell cell = head.createCell(i);
            cell.setCellValue(headTitle.next().getValue());
        }
    }

    /**
     * 创建数据行
     *
     * @param data
     */
    public static void createRows(List<?> data) {
        int rowCount = data.size();
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.createRow(i + 1);

            Map<String, FieldsEntity> map = DataConvertUtil.convertObjectToMap(data.get(i));
            Iterator<Map.Entry<String, String>> head = headerData.entrySet().iterator();

            for (int col = 0; col < headerData.size() && head.hasNext(); col++) {
                Cell cell = row.createCell(col);

                Map.Entry<String, String> enty = (Map.Entry<String, String>) head.next();
                String name = enty.getKey();
                cell.setCellValue(map.get(name).getValue().toString());
            }
        }
    }

    private static void flashFile(OutputStream out, SXSSFWorkbook book) {
        try {
            book.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        LinkedHashMap<String, String> header = new LinkedHashMap<String, String>();
        header.put("recordTime", "时间");
        header.put("businessLine", "业务线");
        header.put("resourceName", "名称");

        Date date = new Date();
        DataObject data1 = new DataObject();
        data1.setRecordTime(date);
        data1.setBusinessLine("业务1");
        data1.setResourceName("资源1");

        DataObject data2 = new DataObject();
        data2.setRecordTime(date);
        data2.setBusinessLine("业务2");
        data2.setResourceName("资源2");

        List<DataObject> data = new ArrayList<DataObject>();
        data.add(data1);
        data.add(data2);

        writeToExcel("/Users/baidu/测试2.xlsx", data, "测试表格", header);
    }
}
