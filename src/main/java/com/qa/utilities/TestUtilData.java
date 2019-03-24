package com.qa.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TestUtilData {

	static Workbook book;
	static Sheet sheet;
	
	public static Object[][] getTestData() throws EncryptedDocumentException, FileNotFoundException, IOException
	{
		
		book = WorkbookFactory.create(new FileInputStream("C:\\Users\\SAHIL\\eclipse-workspace\\February2019_Project\\src\\main\\java\\com\\qa\\testdata\\test.xlsx"));
		sheet = book.getSheet("Sheet1");
		int size1=sheet.getLastRowNum();
		int size2=sheet.getRow(1).getLastCellNum();
		Object[][] data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()] ;
		for(int i=1;i<size1;i++)
		{
			for(int j=0;j<size2;j++)
			{
				data[i][j]=sheet.getRow(i).getCell(j).toString();
			}
		}
		return data;
	}
}
