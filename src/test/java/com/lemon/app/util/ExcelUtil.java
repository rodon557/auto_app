package com.lemon.app.util;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	/**读取excel数据
	 * @param path 文件路径
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @param startColumn 开始列
	 * @param endColumn 结束列
	 */
	public static Object[][] read(String path,int sheetNum,int startRow,int endRow,int startColumn,int endColumn){
		Object [][] datas=new Object[endRow-startRow+1][endColumn-startColumn+1];
		try {
			Workbook workbook=WorkbookFactory.create(ExcelUtil.class.getResourceAsStream(path));
			Sheet sheet=workbook.getSheetAt(sheetNum-1);//获取excel里的sheet1
			for(int i=startRow;i<=endRow;i++){
				Row row=sheet.getRow(i-1);
				for(int j=startColumn;j<=endColumn;j++){
					Cell cell=row.getCell(j-1, MissingCellPolicy.CREATE_NULL_AS_BLANK);//添加策略方法将空白行设置为""而不是null
					cell.setCellType(CellType.STRING);//将表里面数据全部设置为String类型
					String value=cell.getStringCellValue();
					datas[i-startRow][j-startColumn]=value;
				}
			}
			
			
		}  catch (Exception e) {//仅catch所有异常的父类
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datas;
   }
	public static void main(String[] args) {
		Object[][]datas=ExcelUtil.read("/wechat_register.xlsx",2, 2, 3, 1, 5);
		for (Object[] objects : datas) {
			for (Object object : objects) {
				System.out.println(object);
			}
		}
	}
}
