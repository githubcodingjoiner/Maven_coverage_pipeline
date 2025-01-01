package com.example.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AditiTest {

    private WebDriver driver;

    // Setup WebDriver before each test
    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Update this path
        driver = new ChromeDriver();
    }

    @Test
    public void testLogin() {
        // Navigate to the login page
        driver.get("https://example.com/login");

        // Locate the username and password fields
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        // Perform login
        usernameField.sendKeys("testUser");
        passwordField.sendKeys("testPassword");
        loginButton.click();

        // Validate successful login
        String expectedTitle = "Dashboard";
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle);
    }

    // Tear down WebDriver after each test
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
