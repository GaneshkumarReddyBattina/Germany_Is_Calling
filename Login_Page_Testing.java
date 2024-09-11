package com.Test.SeleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login_Page_Testing {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void successfulLoginTest() throws InterruptedException {
        // Test 1: Successful Login Test
        driver.get("https://app.germanyiscalling.com/common/login/?next=https%3A%2F%2Fapp.germanyiscalling.com%2Fcv%2Fhome%2F");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("ganeshkumarreddy411@gmail.com");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Maari@.12345");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()='Log In']")).click();
        Thread.sleep(2000);

        // Assert if 'CV analysis' is displayed after login
        boolean isCVAnalysisDisplayed = driver.findElement(By.xpath("//span[text()='CV analysis']")).isDisplayed();
        Assert.assertTrue(isCVAnalysisDisplayed, "CV Analysis section is not displayed!");
    }

    @Test
    public void unsuccessfulLoginTest() throws InterruptedException {
        // Test 2: Unsuccessful Login Test
        driver.get("https://app.germanyiscalling.com/common/login/?next=https%3A%2F%2Fapp.germanyiscalling.com%2Fcv%2Fhome%2F");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("ganeshkumarreddy411@gmail.com");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin@1234");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()='Log In']")).click();
        Thread.sleep(2000);

        // Assert if error message is displayed for incorrect login
        boolean isErrorDisplayed = driver.findElement(By.xpath("//li[text()='Please enter a correct username and password. Note that both fields may be case-sensitive.']")).isDisplayed();
        Assert.assertTrue(isErrorDisplayed, "Error message is not displayed for incorrect login!");
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
