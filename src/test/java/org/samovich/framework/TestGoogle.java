package org.samovich.framework;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

/**
/**
 * @author Valery Samovich
 * @see
 */
public class TestGoogle {

    private static final String CHROME_DRIVER_PATH = "../chromedriver";
    private static final String BASE_URL = "https://google.com/";
    private WebDriver driver;
    private WebDriverWait wait;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testExample() throws Exception {
        driver.get(BASE_URL);
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputEmail")));
        driver.findElement(By.id("inputEmail")).sendKeys("...");
        driver.findElement(By.id("inputPassword")).sendKeys("...!");
        driver.findElement(By.id("submitButton")).click();
        driver.findElement(By.xpath("//*[@id=\"submenu\"]/div/ul/li[3]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"submenu\"]/div/ul/li[3]/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody/tr[23]/td[7]/a")).click();
        String revenueAsStars = driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody/tr[10]/td[2]")).getText();
        Assert.assertTrue("Revenue have only stars!", revenueAsStars.contains("***"));
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
