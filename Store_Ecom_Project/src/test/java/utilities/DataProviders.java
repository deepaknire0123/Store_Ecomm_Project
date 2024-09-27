package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	//Data provider 1
	
		@DataProvider(name = "LoginCredentials")
		public Object[][] getData() throws IOException
		{
			String path = ".\\testData\\TestData.xlsx";
			
			ExcelUtility xlUtils = new ExcelUtility(path);
			
			int totalRows = xlUtils.getRowCount("Credentials");
			int totalCols = xlUtils.getCellCount("Credentials", 1);
			
			Object [][] Data = new Object[totalRows][totalCols];
			
			for(int r=1;r<=totalRows;r++) //Excel r=0 ,c = 1 //java r=0, c=0
			{
				for(int c=0;c<totalCols;c++)
				{
					Data[r-1][c] = xlUtils.getCellData("Credentials", r, c);
				}
			}
			return Data;
		}
		
		//Data Provider 2
		@DataProvider(name = "searchProduct")
		public Object [][] getProduct() throws IOException
		{
			String path = ".\\testData\\TestData.xlsx";
			
			ExcelUtility xlUtils = new ExcelUtility(path);
			
			int totalRows = xlUtils.getRowCount("SearchProduct");
			int totalCols = xlUtils.getCellCount("SearchProduct", 1);
			
			Object [][] searchData = new Object[totalRows][totalCols];
			
			for(int r=1;r<=totalRows;r++) //Excel r=0 ,c = 1 //java r=0, c=0
			{
				for(int c=0;c<totalCols;c++)
				{
					searchData[r-1][c] = xlUtils.getCellData("SearchProduct", r, c);
				}
			}
			return searchData;
			
		}
		
		//Data Provider 3
		@DataProvider(name = "newAcountDetailsData")
		public Object [][] accountCreation() throws IOException
		{
			String path = ".\\testData\\TestData.xlsx";
			
			ExcelUtility xlUtils = new ExcelUtility(path);
			
			int totalRows = xlUtils.getRowCount("AccountCreationData");
			int totalCols = xlUtils.getCellCount("AccountCreationData", 1);
			
			Object [][] Data = new Object[totalRows][totalCols];
			
			for(int r=1;r<=totalRows;r++) //Excel r=0 ,c = 1 //java r=0, c=0
			{
				for(int c=0;c<totalCols;c++)
				{
					Data[r-1][c] = xlUtils.getCellData("AccountCreationData", r, c);
				}
			}
			return Data;
			
		}
		
		//Data Provider 4
		@DataProvider(name = "placeOrderProductDetail")
		public Object [][] orderProduct() throws IOException
		{
			String path = ".\\testData\\TestData.xlsx";
			
			ExcelUtility xlUtils = new ExcelUtility(path);
			
			int totalRows = xlUtils.getRowCount("ProductDetails");
			int totalCols = xlUtils.getCellCount("ProductDetails", 1);
			
			Object [][] Data = new Object[totalRows][totalCols];
			
			for(int r=1;r<=totalRows;r++) //Excel r=0 ,c = 1 //java r=0, c=0
			{
				for(int c=0;c<totalCols;c++)
				{
					Data[r-1][c] = xlUtils.getCellData("ProductDetails", r, c);
				}
			}
			return Data;
			
		}

}
