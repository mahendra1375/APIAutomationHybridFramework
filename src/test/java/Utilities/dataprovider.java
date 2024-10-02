package Utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class dataprovider {

    @DataProvider(name= "userData")
    public String[][] getData() throws IOException {
        String path = System.getProperty("user.dir") + "/src/test/resources/testData/UserTestData.xlsx";

        XLUtility xl = new XLUtility(path);
        int rowCount = xl.getRowCount("Sheet1");
        System.out.println("rowCount: " + rowCount);
        int cellCount = xl.getCellCount("Sheet1", 1);
        System.out.println("cellCount: " + cellCount);

        String[][] userdata = new String[rowCount][cellCount];

        for (int i = 1; i <= rowCount; i++) {
            for (int k = 0; k <= cellCount - 1; k++) {
                String data = xl.readCellData("Sheet1", i, k);
                System.out.println(data);
                userdata[i - 1][k] = data;
            }
        }
        return userdata;
    }

}
