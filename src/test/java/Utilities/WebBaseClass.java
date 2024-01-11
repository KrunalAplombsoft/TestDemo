package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WebBaseClass {

    public static WebDriver webDriver;

    @BeforeTest(groups = {"WebSmoke","WebSanity"})
    public void first() throws IOException
    {
        FileInputStream fileInputStream = new FileInputStream("./webconfig.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);
        String browser = properties.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome"))
        {
            webDriver = new ChromeDriver();
        }
        else if (browser.equalsIgnoreCase("firefox"))
        {
            webDriver = new FirefoxDriver();
        }
        else
        {
            System.out.println("You have selected wrong web driver please check again!!");
        }
//        webDriver.navigate().to("https://automationexercise.com/");
        webDriver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest(groups = {"WebSmoke","WebSanity"})
    public void tearDown()
    {
        webDriver.close();
    }
}