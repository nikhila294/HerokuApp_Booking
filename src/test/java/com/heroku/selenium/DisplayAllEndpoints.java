package com.heroku.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DisplayAllEndpoints {
	public static void main(String[] args) {
		String baseURL = "https://restful-booker.herokuapp.com/";
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseURL);
		boolean isTextVisisble = driver.findElement(By.xpath("//h1[text()='Welcome to Restful-Booker']")).isDisplayed();
		if (isTextVisisble) {

			driver.findElement(By.xpath("//a[text()='API Docs']")).click();
			String authPost = driver.findElement(By.xpath("(//span[text()='post'])[1]")).getText();
			String authURL = driver.findElement(By.xpath("//code[text()=\"//restful-booker.herokuapp.com/auth\"]"))
					.getText();
			System.out.println("Method Name-->" + authPost + " and URL-->" + authURL);

			String getMethod = driver.findElement(By.xpath("(//span[text()='get'])[1]")).getText();
			String getBooking = driver
					.findElement(By.xpath("(//code[text()=\"//restful-booker.herokuapp.com/booking\"])[1]")).getText();
			System.out.println("Method Name-->" + getMethod + " and  URL-->" + getBooking);

			String getIdMethod = driver.findElement(By.xpath("(//span[text()='get'])[2]")).getText();
			String getBookingId = driver
					.findElement(By.xpath("(//code[text()='//restful-booker.herokuapp.com/booking/:id'])[1]"))
					.getText();
			System.out.println("Method Name-->" + getIdMethod + " and  URL-->" + getBookingId);

			String postMethod = driver
					.findElement(By.xpath("//h1[text()='Booking - CreateBooking']//following::span[text()='post']"))
					.getText();
			String postCreateBooking = driver.findElement(By.xpath(
					"//h1[text()='Booking - CreateBooking']//following::code[text()='//restful-booker.herokuapp.com/booking']"))
					.getText();
			System.out.println("Method Name-->" + postMethod + " and URL-->" + postCreateBooking);

			String putMethod = driver.findElement(By.xpath("//span[text()='put']")).getText();
			String putUpdateBookingId = driver.findElement(By.xpath(
					"//h1[text()='Booking - UpdateBooking']//following::code[text()='//restful-booker.herokuapp.com/booking/:id'][1]"))
					.getText();
			System.out.println("Method Name-->" + putMethod + " and URL-->" + putUpdateBookingId);

			String patchMethod = driver.findElement(By.xpath("//span[text()='patch']")).getText();
			String patchPartialUpdateBooking = driver.findElement(By.xpath(
					"//h1[text()='Booking - PartialUpdateBooking']//following::code[text()='//restful-booker.herokuapp.com/booking/:id']"))
					.getText();
			System.out.println("Method Name-->" + patchMethod + " and  URL-->" + patchPartialUpdateBooking);

			String deleteMethod = driver.findElement(By.xpath("//span[text()='delete']")).getText();
			String deleteBooking = driver
					.findElement(By.xpath("//code[text()='//restful-booker.herokuapp.com/booking/1']")).getText();
			System.out.println("Method Name-->" + deleteMethod + " and  URL-->" + deleteBooking);

			String getHealthCheckMethod = driver
					.findElement(By.xpath("//h1[text()='Ping - HealthCheck']//following::span[text()='get']"))
					.getText();
			String getHealthCheck = driver.findElement(By.xpath("//code[text()='//restful-booker.herokuapp.com/ping']"))
					.getText();
			System.out.println("Method Name-->" + getHealthCheckMethod + " and  URL-->" + getHealthCheck);

			driver.quit ();
		}
	}
}
