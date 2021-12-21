package com.opencart.qa.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionManager 
{
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	public OptionManager(Properties prop)
	{
		this.prop= prop;
	}
	
	public ChromeOptions getChromeOption()
	{
		prop = new Properties();
		if (Boolean.parseBoolean((prop.getProperty("headless")))) co.addArguments("--headless");
		if (Boolean.parseBoolean((prop.getProperty("incognito")))) co.addArguments("--incognito");
		System.out.println("asds");
		return co;
	}
	
	public FirefoxOptions getFirefoxOption()
	{
		prop = new Properties();
		if (Boolean.parseBoolean((prop.getProperty("headless")))) fo.addArguments("--headless");
		if (Boolean.parseBoolean((prop.getProperty("incognito")))) fo.addArguments("--incognito");
		return fo;
	}
	public EdgeOptions getEdgeOption()
	{
		prop = new Properties();
		if (Boolean.parseBoolean((prop.getProperty("headless")))) eo.addArguments("--headless");
		if (Boolean.parseBoolean((prop.getProperty("incognito")))) eo.addArguments("--incognito");
		return eo;
	}
	

}
