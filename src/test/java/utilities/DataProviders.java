package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

  // DataProvider 1
  @DataProvider(name = "LoginData")
  public String[][] getData() throws IOException {
    String path = ".\\testData\\Opencart_LoginData.xlsx"; // Taking xl file from testData

    ExcelUtility xlutil = new ExcelUtility(path); // Creating an object for ExcelUtility

    int totalrows = xlutil.getRowCount("Sheet1");
    int totalcols = xlutil.getCellCount("Sheet1", 1);

    String[][] logindata = new String[totalrows][totalcols]; // Created a two-dimensional array to store data

    for (int i = 0; i <= totalrows; i++) { // Start from 0 to match array indexing
      for (int j = 0; j < totalcols; j++) { // 0: i is row and j is column
        logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j); // Corrected row index for getCellData
      }
    }

    return logindata; // Returning the two-dimensional array
  }

  // DataProvider 2

  // DataProvider 3

}
