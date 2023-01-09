package pages;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import OverrideFunctions.HolidayDetails;
import OverrideFunctions.WebElementActions;

public class HolidaysListPage extends FirstandFinal
{
	WebElementActions elements=new WebElementActions(driver);
	int hSize=0;
	int pSize=0;
	
	static String [][] publicHoliday=new String[4][11];
	static String [][] optionalHoliday=new String[4][31];
	public int columnCount;
	public int rowcount;
	public ExtentTest test;
	
	static ArrayList<String> arrCellValues = new ArrayList<String>();
	static String[] Array = new String[164];
	
	HolidayDetails HD=new HolidayDetails(null, null, null, null, hSize);
	HolidayDetails PHD=new HolidayDetails(null, null, null, null, hSize);
	HolidayDetails OHD=new HolidayDetails(null, null, null, null, hSize);
	
	public HolidaysListPage(WebDriver driver) {
		FirstandFinal.driver=driver;
	}
	
	By PageTitle=(By.xpath("//*[@id=\"display-holidaylist-dialog\"]/div/div/div[1]/h4"));
	By Table=(By.xpath("//table[@class='table table-condensed table-hover']"));
	By Rowcount=(By.tagName("tr"));
	By Column=(By.tagName("td"));
	
	
	public boolean titleValidation() {
		String Actual=driver.findElement(PageTitle).getText();
		 if (Actual.contains("Office Holiday list")){
			 System.out.println("Office Holiday list Page is displayed Successfully");		
			 return true;
		 }else {
			 return false;
		 }
			 
	}
	public void holidayvalues(int size) throws InterruptedException {
		//System.out.println("The given public holiday count"+size);
		rowcount=elements.tablerowCount("HolidayList", Rowcount).size();
		//System.out.println("The given public holiday count"+size);
		//System.out.println("calculated row count is  "+rowcount);
		for (int i=0;i<=rowcount-1;i++) {
			List<WebElement> cols=elements.tableValues("HolidayList", Rowcount).get(i).findElements(Column);
			 columnCount=cols.size();
			for (int j=0;j<columnCount;j++) {
				String Cellvalue=cols.get(j).getText();
				
				arrCellValues.add(Cellvalue);
				}
			
			}
		System.out.println("The total arraycount"+arrCellValues.size());
		
			  for(int l=0;l<arrCellValues.size();l++) { 
				  if(arrCellValues.get(l).equalsIgnoreCase("Public Holiday"))
				  { 
					  pSize=pSize+1; 
					  
				  }
			  }
			  System.out.println(pSize);
			  PHD.size=pSize;
		if (pSize>size) {
			System.out.println("The no of Public Holidays is greater than 10 .The Value is " + pSize);
			//return true;
			//Thread.sleep(10000);
			}else if (pSize==size) {
				System.out.println("The no of Public Holidays is equal to 10 .The Value is " + pSize);
				//Thread.sleep(10000);
			}else {
				
				System.out.println("The no of Public Holidays is less than 10 .The Value is " + pSize);
				//Thread.sleep(10000);
			}	
		
			 
			
	}
		
	
			
	public void printPublicHolidayList()
	
	{
		Formatter fmt = new Formatter();  
		fmt.format("%-25s %-20s %-60s %-15s\n", "Holiday Date:", "Day", "Description","Holiday Type");  
		System.out.println("Public Holiday List:");
		System.out.println("<---------------------------------------------------------------------------------------------------------------------------");
		int l=0;
		//System.out.println("<---------------------------------------------------------------------------------------------------------------------");
		System.out.println(fmt);
		
		for(int k=0;k<arrCellValues.size();k=k+4)
		{
			
			HD.HolidayDate=arrCellValues.get(k);
			
			HD.Day=arrCellValues.get(k+1);
			
			HD.Description=arrCellValues.get(k+2);
			
			HD.Type=arrCellValues.get(k+3);
			//publicHoliday[0][k]=
			try {
		         FileOutputStream fileOut =
		         new FileOutputStream("./TestData/Holiday.ser");
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         out.writeObject(HD);
		         out.close();
		         fileOut.close();
		         
		      	} catch (IOException e)
				{
		         e.printStackTrace();
				}
		
			if ((HD.Type).equalsIgnoreCase("Public Holiday")){
			
		      try {
		         FileInputStream fileIn = new FileInputStream("./TestData/Holiday.ser");
		         ObjectInputStream in = new ObjectInputStream(fileIn);
		         PHD = (HolidayDetails) in.readObject();
		         in.close();
		         fileIn.close();
		      	} catch (IOException e)
		      	{
		         e.printStackTrace();
		        // return;
		      	} catch (ClassNotFoundException c) 
		      	{
		         System.out.println("Holiday class not found");
		         c.printStackTrace();
		        // return;
		      	}
		      System.out.println("<---------------------------------------------------------------------------------------------------------------------------");
		      System.out.format("%-25s %-20s %-60s %-15s\n", PHD.HolidayDate,PHD.Day,PHD.Description,PHD.Type);
		   
			}
		}
		  System.out.println();
	      System.out.println();
	      System.out.println();
	    
	}
		public void printOptionalHolidayList()
		{	
			Formatter fmt = new Formatter();  
			fmt.format("%-25s %-20s %-60s %-15s\n", "Holiday Date:", "Day", "Description","Holiday Type");  
		//	int l=0;
			System.out.println("Optional Holiday List:");
			System.out.println("<---------------------------------------------------------------------------------------------------------------------------");
			System.out.println(fmt);
			//System.out.println("<--------------------------------------------------------------------------------------------------------------------------------");
			for(int k=0;k<arrCellValues.size();k=k+4)
			{
				
				HD.HolidayDate=arrCellValues.get(k);
				
				HD.Day=arrCellValues.get(k+1);
				
				HD.Description=arrCellValues.get(k+2);
				
				HD.Type=arrCellValues.get(k+3);
				
				
				try {
			         FileOutputStream fileOut =
			         new FileOutputStream("./TestData/OptionalHoliday.ser");
			         ObjectOutputStream out = new ObjectOutputStream(fileOut);
			         out.writeObject(HD);
			         out.close();
			         fileOut.close();
			        
			      	} catch (IOException e)
					{
			         e.printStackTrace();
					}
			
				if ((HD.Type).equalsIgnoreCase("Optional Holiday")){
				
			      try {
			         FileInputStream fileIn = new FileInputStream("./TestData/OptionalHoliday.ser");
			         ObjectInputStream in = new ObjectInputStream(fileIn);
			         OHD = (HolidayDetails) in.readObject();
			         in.close();
			         fileIn.close();
			      	} catch (IOException e)
			      	{
			         e.printStackTrace();
			       //  return;
			      	} catch (ClassNotFoundException c) 
			      	{
			         System.out.println("Holiday class not found");
			         c.printStackTrace();
			      //   return;
			      	}
			      
			    
			      System.out.println("<---------------------------------------------------------------------------------------------------------------------------");
			      System.out.format("%-25s %-20s %-60s %-15s\n", OHD.HolidayDate,OHD.Day,OHD.Description,OHD.Type);
			    //  optionalHoliday[l][k]=OHD.HolidayDate;
			    //  optionalHoliday[l][k+1]=OHD.Day;
			    //  optionalHoliday[l][k+2]=OHD.Description;
			   //   optionalHoliday[l][k+3]=OHD.Type;
			    //  l=l+1;
			      
				
				}
			}
		//return publicHoliday;
	}
		
	public static String[][] publicholiday(){
		int l=0,m=0;
		//Array=(String[]) arrCellValues.toArray();
		//System.out.println((Array.length));
		for(int k=0;k<arrCellValues.size();k=k+1)
		{
			//System.out.println("After Converting to Array"+(Array[k]));
			if ((arrCellValues.get(k)).toString().equalsIgnoreCase("Public Holiday")){
			publicHoliday[l][m]=(arrCellValues.get(k-3)).toString();
			//System.out.println((arrCellValues.get(k-3)).toString());
			publicHoliday[l][m+1]=(arrCellValues.get(k-2)).toString();
			//System.out.println((arrCellValues.get(k-2)).toString());
			publicHoliday[l][m+2]=(arrCellValues.get(k-1)).toString();
			//System.out.println((arrCellValues.get(k-1)).toString());
			publicHoliday[l][m+3]=(arrCellValues.get(k)).toString();
			//System.out.println((arrCellValues.get(k)).toString());
			l=l+1;
			m=m+4;
			System.out.println("publicHoliday in HolidayListPage"+ publicHoliday[l][m]);
			}
		}
		return publicHoliday;
		
	}
	public static String[][] optionalholiday(){
		int l=0;
		for(int k=0;k<arrCellValues.size();k=k+4)
		{
			if (arrCellValues.get(k+3).equalsIgnoreCase("Optional Holiday")){
			optionalHoliday[l][k]=arrCellValues.get(k);
			
			optionalHoliday[l][k+1]=arrCellValues.get(k+1);
			
			optionalHoliday[l][k+2]=arrCellValues.get(k+2);
			
			optionalHoliday[l][k+3]=arrCellValues.get(k+3);
			l=l+1;
			System.out.println("Optional in HolidayListPage"+ optionalHoliday[l][k]);
			}
		}
		return optionalHoliday;
		
	}
}	
