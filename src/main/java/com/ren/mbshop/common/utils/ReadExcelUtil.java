package com.ren.mbshop.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ReadExcelUtil {
	// 总行数
	private int totalRows = 0;
	// 总条数
	private int totalCells = 0;
	// 错误信息接收器
	private String errorMsg;

	// 构造方法
	public ReadExcelUtil() {
	}

	// 获取总行数
	public int getTotalRows() {
		return totalRows;
	}

	// 获取总列数
	public int getTotalCells() {
		return totalCells;
	}

	// 获取错误信息
	public String getErrorInfo() {
		return errorMsg;
	}

	/**
	 * 读EXCEL文件，获取信息集合
	 * 
	 * @param fielName
	 * @return
	 */
	public Map<Integer,List<List<Object>>> getExcelInfo(MultipartFile mFile,Integer rowIndex) {
		String fileName = mFile.getOriginalFilename();// 获取文件名
		Map<Integer,List<List<Object>>> createExcel = new HashMap<>();
		try {
			if (!validateExcel(fileName)) {// 验证文件名是否合格
				return null;
			}
			boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
			if (isExcel2007(fileName)) {
				isExcel2003 = false;
			}
			createExcel = createExcel(mFile.getInputStream(), isExcel2003, rowIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return createExcel;
	}

	/**
	 * 根据excel里面的内容读取客户信息
	 * 
	 * @param is输入流
	 * @param isExcel2003 excel是2003还是2007版本
	 * @return
	 * @throws IOException
	 */
	public Map<Integer,List<List<Object>>> createExcel(InputStream is, boolean isExcel2003, Integer rowIndex) {
		Map<Integer,List<List<Object>>> map = new HashMap<>();
		try {
			Workbook wb = null;
			if (isExcel2003) {// 当excel是2003时,创建excel2003
				wb = new HSSFWorkbook(is);
			} else {// 当excel是2007时,创建excel2007
				wb = new XSSFWorkbook(is);
			}
			map = readExcelValue(wb, rowIndex);// 读取Excel里面客户的信息
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 读取Excel里面客户的信息
	 * 
	 * @param wb
	 * @return
	 */
	private Map<Integer,List<List<Object>>> readExcelValue(Workbook wb, Integer rowIndex) {		
		int numberOfSheets = wb.getNumberOfSheets();
		Map<Integer,List<List<Object>>> shellMap = new HashMap<Integer,List<List<Object>>>();
		for (int i = 0; i < numberOfSheets; i++) {
			// 得到shell
			Sheet sheet = wb.getSheetAt(i);
			// 得到Excel的行数
			this.totalRows = sheet.getPhysicalNumberOfRows();
			// 得到Excel的列数(前提是有行数)
			if (totalRows > 1 && sheet.getRow(0) != null) {
				this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
			}
			// 循环Excel行数
			List<List<Object>> rowList = new ArrayList<List<Object>>();
			for (int r = rowIndex; r < totalRows; r++) {
				Row row = sheet.getRow(r);
				if (row == null) {
					continue;
				}
				List<Object> cellList = new ArrayList<>();
				// 循环Excel的列
				for (int c = 0; c < this.totalCells; c++) {
					Cell cell = row.getCell(c);					
					if (null != cell) {
						Object cellValue = getRightTypeCell(cell);
						cellList.add(cellValue);
					}
				}
				rowList.add(cellList);
			}
			shellMap.put(i, rowList);
		}
		return shellMap;
	}

	/**
	 * 验证EXCEL文件
	 * 
	 * @param filePath
	 * @return
	 */
	public boolean validateExcel(String filePath) {
		if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
			errorMsg = "文件名不是excel格式";
			return false;
		}
		return true;
	}

	// @描述：是否是2003的excel，返回true是2003
	public static boolean isExcel2003(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");
	}

	// @描述：是否是2007的excel，返回true是2007
	public static boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}
	
	/**
	 * @Description:跟据表格列的值类型返回值
	 * @author 任春林
	 * @Date:2018年10月19日 下午3:33:25
	 */
	public static Object getRightTypeCell(Cell cell) {

		Object object = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING: {
			object = cell.getStringCellValue();
			break;
		}
		case Cell.CELL_TYPE_NUMERIC: {
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			object = cell.getNumericCellValue();
			break;
		}

		case Cell.CELL_TYPE_FORMULA: {
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			object = cell.getNumericCellValue();
			break;
		}

		case Cell.CELL_TYPE_BLANK: {
			cell.setCellType(Cell.CELL_TYPE_BLANK);
			object = cell.getStringCellValue();
			break;
		}
		default:
			break;
		}
		return object;
	}

}
