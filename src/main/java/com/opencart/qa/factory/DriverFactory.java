package com.opencart.qa.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory 
{
	public Properties prop;
	public WebDriver driver;
	public OptionManager optionManager;
	public static ThreadLocal<WebDriver> tlDriver= new ThreadLocal<WebDriver>();
	public WebDriver init_driver(Properties prop)
	{
		String browserName= prop.getProperty("browser");
		optionManager = new OptionManager(prop);
		System.out.println("Launching the browser "+browserName);
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(optionManager.getChromeOption()));
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOption()));
		}
		if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			tlDriver.set(new EdgeDriver(optionManager.getEdgeOption()));
		}
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		driver.manage().window().fullscreen();
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver()
	{
		return tlDriver.get();
		
	}
	
	public void init_properties()
	{
		prop = new Properties();
		FileInputStream fs = null;
		String envName = prop.getProperty("env");
		try {
		if(envName == null)
		{
			System.out.println("We are running on production environment");
			fs = new FileInputStream("./src/test/resources/config/production_config.properties");
		}
		else
			switch (envName.toLowerCase()) {
			case "qa":
				fs = new FileInputStream("./src/test/resources/config/qa_config.properties");
				break;
			case "int":
				fs = new FileInputStream("./src/test/resources/config/int_config.properties");
				break;
			default:
				System.out.println("Please pass the correct environment" + envName);
				break;
			}
		}
		
		catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try {
			prop.load(fs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	public String getScreenshot()
	{
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
	public void getURL(String url)
	{
		try
		{
		if(url == null)
		{
			throw new Exception ("Incorrect URL");
		}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		getDriver().get(url);
	}
	
	public void getURL(URL url)
	{
		try
		{
		if(url == null)
		{
			throw new Exception ("Incorrect URL");
		}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		getDriver().navigate().to(url);
	}
	public void getURL(String url, String path)
	{
		try
		{
		if(url == null)
		{
			throw new Exception ("Incorrect URL");
		}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		getDriver().get(url+"/"+path);
	}
	
	public void getURL(String url, String path, String queryParam)
	{
		try
		{
		if(url == null)
		{
			throw new Exception ("Incorrect URL");
		}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		getDriver().get(url+"/"+path+"?"+queryParam);
	}
	
	}
		
		
		
	



