package com.star.stat.utils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

public class ExprotHSSFCellStyle {

	/**
	 * 创建基础样式  
	 * 水平和垂直居中
	 */
	public static HSSFCellStyle  createBaseStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		//设置水平居中
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		//设置垂直居中
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		return style;
	}
	
	/**
	 * 创建数据表格的头的样式 
	 */
	public static HSSFCellStyle createTableTitleStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = createBaseStyle(workbook);
		
		//设置字体
		HSSFFont font=workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //字体加粗
		font.setFontHeightInPoints((short)15); //设置字体大小
		font.setColor(HSSFColor.RED.index); //字体颜色
		font.setFontName("黑体");//设置字体
		style.setFont(font);
		
		return style;
	}
	
	
	/**
	 * 创建小标题样式
	 */
	public static HSSFCellStyle createSubTitleStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = createBaseStyle(workbook);
		//设置字体
		HSSFFont font=workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //字体加粗
		font.setFontHeightInPoints((short)20); //设置字体大小
		font.setColor(HSSFColor.RED.index); //字体颜色
		font.setFontName("黑体");//设置字体
		style.setFont(font);
		return style;
	}
	
	
	
	/**
	 * 创建标题样式
	 */
	public static HSSFCellStyle createTitleStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = createBaseStyle(workbook);
		//设置字体
		HSSFFont font=workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //字体加粗
		font.setFontHeightInPoints((short)25); //设置字体大小
		font.setColor(HSSFColor.RED.index); //字体颜色
		font.setFontName("华文行楷");//设置字体
		style.setFont(font);
		return style;
	}
	

}
