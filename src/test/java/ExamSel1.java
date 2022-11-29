import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ExamSel1 {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://shop.pragmatic.bg");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void registerUserTest(){
        WebElement myAccount = driver.findElement(By.className("fa-user"));
        myAccount.click();
        WebElement register = driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a"));
        register.click();
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Diana");
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Ivanova");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("qwer@mrtrta.ru");
        driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys("088874765882");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@name='confirm']")).sendKeys("123456");

        WebElement agreeCheckBox = driver.findElement(By.xpath("//input[@name='agree']"));
        if(!agreeCheckBox.isSelected())
            agreeCheckBox.click();

        driver.findElement(By.className("btn-primary")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Your Account Has Been Created!')]")));
        WebElement accountCreated= driver.findElement(By.xpath("//h1[contains(text(), 'Your Account Has Been Created!')]"));

        Assert.assertEquals(accountCreated.getAttribute("innerHTML"), "Your Account Has Been Created!");


    }

}
