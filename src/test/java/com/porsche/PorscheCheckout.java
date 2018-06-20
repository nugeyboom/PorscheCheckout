package com.porsche;
/*Let’s buy a Porsche today.  
 * Create a new maven project ‘PorscheCheckout’. 
 * Automate the following test case in that project. 
 * Create all necessary packages and classes. 
 * Add all the necessary libraries. 
 * Push the code to GitHub.
 * 1.Open browser
 * 2.Go to url “https://www.porsche.com/usa/modelstart/”
 * 3.Select model 718
 * 4.Remember the price of 718Cayman
 * 5.Click on Build & Price under 718Cayman
 * 6.Verify that Base price displayed on the page is same as the price from step 4
 * 7.Verify that Price for Equipment is 0
 * 8.Verify that total price is the sum of base price + Delivery, Processing and Handling Fee
 * 9.Select color “Miami Blue”
 * 10.Verify that Price for Equipment is Equal to Miami Blue price
 * 11.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
 * 12.Select 20" Carrera Sport Wheels
 * 13.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport Wheels
 * 14.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
 * 15.Select seats ‘Power Sport Seats (14-way) with Memory Package’
 * 16.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package
 * 17.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
 * 18.Click on Interior Carbon Fiber
 * 19.Select Interior Trim in Carbon Fiber i.c.w. Standard Interior
 * 20.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package + Interior Trim in Carbon Fiber i.c.w. Standard Interior
 * 21.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
 * 22.Click on Performance
 * 23.Select 7-speed Porsche Doppelkupplung (PDK)
 * 24.Select Porsche Ceramic Composite Brakes (PCCB)
 * 25.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package + Interior Trim in Carbon Fiber i.c.w. Standard Interior + 7-speed Porsche Doppelkupplung (PDK) + Porsche Ceramic Composite Brakes (PCCB)
 * 26.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
 * 
 */

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PorscheCheckout {

	public static void main(String[] args) {
		// 1.Open browser
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.manage().window().fullscreen();

		// Go to url “https://www.porsche.com/usa/modelstart/”
		String url = "https://porsche.com/usa/modelstart/";
		driver.get(url);

		// 3.Select model 718
		driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/div[2]/a[1]/div/div[2]/div")).click();

		// 4.Remember the price of 718Cayman
		String AAA = driver.findElement(By.xpath("//*[@id=\"m982120\"]/div[1]/div[2]/div[2]")).getText();

		
		int priceStep4 = Integer.parseInt(AAA.replace("From $ ", "").replace(",", "").replace(".00*", ""));
		

		// 5.Click on Build & Price under 718Cayman
		driver.findElement(By.xpath("//a[@class='m-01-link m-14-build'][1]")).click();

		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}

		driver.switchTo().window(subWindowHandler);

		/// 6.Verify that Base price displayed on the page is same as the price from
		/// step 4
		String basePrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[1]/div[2]")).getText();
		
		int Cayman718Price = Integer.parseInt(basePrice.replace("$", "").replace(",", ""));
		

		if (priceStep4 == Cayman718Price) {
			System.out.println("Base price: " + basePrice + " verified.");
		} else {
			System.out.println("Base price not verified");
		}
		/// 7.Verify that Price for Equipment is 0
		String EQ = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		
		int EQPrice = Integer.parseInt(EQ.replace("$", ""));
		

		if (EQPrice == 0) {
			System.out.println("Equipment Price: " + EQ + " verified");
		}else{
			System.out.println("Equipment price not verified");
		}
			
		/// 8.Verify that total price is the sum of base price + Delivery, Processing
		/// and Handling Fee
		String handling = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[3]/div[2]")).getText();
		System.out.println("Processing and Handling fee: "+ handling);

		int handlingPrice = Integer.parseInt(handling.replace("$", "").replace(",", ""));

		String total = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		
		int totalPrice = Integer.parseInt(total.replace("$", "").replace(",", ""));

		if (totalPrice == (handlingPrice + Cayman718Price)) {
			System.out.println("Total price: " + total + " verified");
		} else {
			System.out.println("Total price not verified");
		}
		// 9.Select color “Miami Blue”

		driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_FJ5\"]/span")).click();
		String miamiBlue = driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_IAF\"]/div[2]/div[1]/div/div[2]"))
				.getText();
		System.out.println("The Price for Miami Blue Exterior Color is : " + miamiBlue);
		int miamiBluePrice = Integer.parseInt(miamiBlue.replace("$", "").replace(",", ""));
		

		// 10.Verify that Price for Equipment is Equal to Miami Blue price

		String EQ1 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		int EQ1Price = Integer.parseInt(EQ1.replace("$", "").replace(",", ""));

		if (miamiBluePrice == EQ1Price) {
			System.out.println("Equipment: " + EQ1 + " is equal to Miami Blue's price");
		} else {
			System.out.println("Equipment is not equal to Miami Blue's price");
		}
		// 11.Verify that total price is the sum of base price + Price for Equipment +
		// Delivery, Processing and Handling Fee
		String newTotal = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		int newTotalPrice = Integer.parseInt(newTotal.replace("$", "").replace(",", ""));

		if (newTotalPrice == (Cayman718Price + handlingPrice + EQ1Price)) {
			System.out.println("New total price: " + newTotal
					+ " is equal to base price + Price for Equipment + Delivery, Processing and Handling Fee");
		} else {
			System.out.println("total price is not the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee");
		}
		// 12.Select 20" Carrera Sport Wheels

		driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_MXRD\"]/span/span")).click();
		
		String sportWheels = driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_IRA\"]/div[2]/div[1]/div/div[2]"))
				.getText();
		int sportWheelsPrice = Integer.parseInt(sportWheels.replace("$", "").replace(",", ""));
		
		// 13.Verify that Price for Equipment is the sum of Miami Blue price + 20"
		// Carrera Sport Wheels

		String EQ2 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		int EQ2Price = Integer.parseInt(EQ2.replace("$", "").replace(",", ""));

		if (EQ2Price == (sportWheelsPrice + miamiBluePrice)) {
			System.out.println(
					"Equipment price: " + EQ2 + " is verified as the price of Miami Blue + 20\" Sports Wheel");

		} else {
			System.out.println("Equipment is not equal to Miami Blue's price + 20\" Carrera Sport Wheels");
		}
		// 14.Verify that total price is the sum of base price + Price for Equipment +
		// Delivery, Processing and Handling Fee
		String newTotal1 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		int newTotalPrice1 = Integer.parseInt(newTotal1.replace("$", "").replace(",", ""));

		if (newTotalPrice1 == (Cayman718Price + handlingPrice + EQ2Price)) {
			System.out.println("New total price: " + newTotal1
					+ " is equal to base price + Price for Equipment + Delivery, Processing and Handling Fee");

		} else {
			System.out.println("total price is not the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee");
		}
		// 15.Select seats ‘Power Sport Seats (14-way) with Memory Package

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1000)", "");

		WebElement radio1 = driver.findElement(By.xpath("//*[@id=\"s_interieur_x_PP06\"]"));

		radio1.click();

		String powerSeat = driver.findElement(By.xpath("//*[@id=\"seats_73\"]/div[2]/div[1]/div[3]/div")).getText();
		int powerSeatPrice = Integer.parseInt(powerSeat.replace("$", "").replace(",", ""));
		System.out.println("Price of Sport power seats: " + powerSeat);

		// 16.Verify that Price for Equipment is the sum of Miami Blue price + 20"
		// Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package
		String EQ3 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		int EQ3Price = Integer.parseInt(EQ3.replace("$", "").replace(",", ""));

		if (EQ3Price == (sportWheelsPrice + miamiBluePrice + powerSeatPrice)) {
			System.out.println(
					"Equipment price: " + EQ3 + " is verified as the price of Miami Blue + 20\" Sports Wheel + power sport seats");
		}else {
			System.out.println("Equipment is not equal to Miami Blue's price + 20\" Carrera Sport Wheels + power sport seats");
		}
		// 17.Verify that total price is the sum of base price + Price for Equipment +
		// Delivery, Processing and Handling Fee

		String newTotal2 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		int newTotalPrice2 = Integer.parseInt(newTotal2.replace("$", "").replace(",", ""));

		if (newTotalPrice2 == (Cayman718Price + handlingPrice + EQ3Price)) {
			System.out.println("New total price: " + newTotal2
					+ " is equal to base price + Price for Equipment + Delivery, Processing and Handling Fee");

		}else {
			System.out.println("total price is not the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee");
		}
		// 18.Click on Interior Carbon Fiber
		jse.executeScript("window.scrollBy(0,1000)", "");

		driver.findElement(By.xpath("//*[@id=\"IIC_subHdl\"]")).click();
		// 19.Select Interior Trim in Carbon Fiber i.c.w. Standard Interior
		driver.findElement(By.xpath("//*[@id=\"vs_table_IIC_x_PEKH_x_c01_PEKH\"]")).click();

		String carbonFiber = driver.findElement(By.xpath("//*[@id=\"vs_table_IIC_x_PEKH\"]/div[1]/div[2]/div"))
				.getText();
		System.out.println("Price of Carbon Fiber i.c.w. Standard Interior: " + carbonFiber);
		int carbonFiberPrice = Integer.parseInt(carbonFiber.replace("$", "").replace(",", ""));
		

		// 20.Verify that Price for Equipment is the sum of Miami Blue price + 20"
		// Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package +
		// Interior Trim in Carbon Fiber i.c.w. Standard Interior
		String EQ4 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		int EQ4Price = Integer.parseInt(EQ4.replace("$", "").replace(",", ""));

		if (EQ4Price == (sportWheelsPrice + miamiBluePrice + powerSeatPrice + carbonFiberPrice)) {
			System.out.println("Equipment price: " + EQ4
					+ " is verified as the price of Miami Blue + 20\" Sports Wheel + power Sport seats + Carbon Fiber Interior");
		} else {
			System.out.println("Equipment is not equal to Miami Blue's price + 20\" Carrera Sport Wheels + power sport seats + Carbon Fiber Interior");
		}

		// 21.Verify that total price is the sum of base price + Price for Equipment +
		// Delivery, Processing and Handling Fee
		String newTotal3 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		int newTotalPrice3 = Integer.parseInt(newTotal3.replace("$", "").replace(",", ""));

		if (newTotalPrice3 == (Cayman718Price + handlingPrice + EQ4Price)) {
			System.out.println("New total price: " + newTotal3
					+ " is equal to base price + Price for Equipment + Delivery, Processing and Handling Fee");

		} else {
			System.out.println("total price is not the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee");
		}
		// 22.Click on Performance

		driver.findElement(By.xpath("//*[@id=\"IMG_subHdl\"]")).click();

		// 23.Select 7-speed Porsche Doppelkupplung (PDK)

		driver.findElement(By.xpath("//*[@id=\"vs_table_IMG_x_M250_x_c11_M250\"]")).click();

		// *[@id="vs_table_IMG_x_M250"]/div[1]/div[2]/div
		String speed7 = driver.findElement(By.xpath("//*[@id=\"vs_table_IMG_x_M250\"]/div[1]/div[2]/div")).getText();
		System.out.println("Price of 7-speed Porsche Doppelkupplung: " + speed7);
		int speed7Price = Integer.parseInt(speed7.replace("$", "").replace(",", ""));
		

		// 24.Select Porsche Ceramic Composite Brakes (PCCB)
		jse.executeScript("window.scrollBy(0,500)", "");

		driver.findElement(By.xpath("//*[@id=\"vs_table_IMG_x_M450_x_c91_M450\"]")).click();

		String ceramicBrakes = driver.findElement(By.xpath("//*[@id=\"vs_table_IMG_x_M450\"]/div[1]/div[2]/div"))
				.getText();
		System.out.println("Price of Porsche Ceramic Composite Brakes: " + ceramicBrakes);
		int ceramicBrakesPrice = Integer.parseInt(ceramicBrakes.replace("$", "").replace(",", ""));
		

		// 25.Verify that Price for Equipment is the sum of Miami Blue price + 20"
		// Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package +
		// Interior Trim in Carbon Fiber i.c.w. Standard Interior + 7-speed Porsche
		// Doppelkupplung (PDK) + Porsche Ceramic Composite Brakes (PCCB)
		String EQ5 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		int EQ5Price = Integer.parseInt(EQ5.replace("$", "").replace(",", ""));
		

		if (EQ5Price == (sportWheelsPrice + miamiBluePrice + powerSeatPrice + carbonFiberPrice + speed7Price
				+ ceramicBrakesPrice)) {
			System.out.println("Equipment price: " + EQ5
					+ " is verified as the price of Miami Blue + 20\" Sports Wheel + Sport Wheels + Carbon Fiber Interior");
		} else {
			System.out.println("Equipment is not equal to Miami Blue's price + 20\\\" Carrera Sport Wheels + power sport seats + 7 speed DoppelKupplung + Carbon Fiber Interior + Ceramic Brakes");
			
		}

		// * 26.Verify that total price is the sum of base price + Price for Equipment +
		// Delivery, Processing and Handling Fee
		String newTotal4 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		int newTotalPrice4 = Integer.parseInt(newTotal4.replace("$", "").replace(",", ""));

		if (newTotalPrice4 == (Cayman718Price + handlingPrice + EQ5Price)) {
			System.out.println("New total price: " + newTotal4
					+ " is equal to base price + Price for Equipment + Delivery, Processing and Handling Fee");

		} else {
			System.out.println("total price is not the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee");
		}

		

	}
}
