package Utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class XLUtility {

    String path;
    XLUtility(String path){
        this.path=path;
    }

    public int getRowCount(String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook workbook= new XSSFWorkbook(fis);
        XSSFSheet sheet= workbook.getSheet(sheetName);
        int rowcount=sheet.getLastRowNum();
        workbook.close();
        fis.close();
        return rowcount;


    }

    public int getCellCount(String sheetName, int rowNum) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook workbook= new XSSFWorkbook(fis);
        XSSFSheet sheet= workbook.getSheet(sheetName);
        int cellCount=sheet.getRow(rowNum).getLastCellNum();
        workbook.close();
        fis.close();
        return cellCount;
    }

    public String readCellData(String sheetName, int rowNum, int CellNumber) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook workbook= new XSSFWorkbook(fis);
        XSSFSheet sheet= workbook.getSheet(sheetName);

        XSSFRow row=sheet.getRow(rowNum);
        XSSFCell cell= row.getCell(CellNumber);

        DataFormatter formator= new DataFormatter();
        String data;

        try {
            data=formator.formatCellValue(cell);
        } catch (Exception e){
            data="";
        }

        String cellValue=cell.toString();
        workbook.close();
        fis.close();
        return data;
    }


  @Test
    public void excelData() throws IOException {
        path= System.getProperty("user.dir")+"/src/test/resources/testData/UserTestData.xlsx";
        System.out.println(path);
        int rowCount= getRowCount("Sheet1");
        System.out.println("rowCount: "+rowCount);
        int cellCount=getCellCount("Sheet1",1);
        System.out.println("cellCount: "+cellCount);

        String [][]userdata= new String[rowCount][cellCount];

        for(int i=1;i<=rowCount;i++){
            for(int k=0;k<=cellCount-1;k++){
                String data= readCellData("Sheet1",i,k);
                System.out.println(data);
                userdata[i-1][k]=data;
            }
        }

        System.out.println("printing the data from an array");

        for(int i=0;i<=userdata.length-1;i++){
            String [] row= userdata[i];
            for(int j=0;j<=row.length-1;j++){
                System.out.print(row[j]+",");
            }
            System.out.println(" ");
        }



  }
}
