package assertion;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.tms.genericutils.PropertyFileUtilites1;
import com.tms.genericutils.WebDriverUtility1;
import com.tms.objectrepo.HomePage;
import com.tms.objectrepo.UserDashBoardPage;

public class SMOKE_PACKAGE_09_Test {
	@Test
	public void verifyTourPackagePageTest() throws IOException {
		
		WebDriverUtility1 wLib = new WebDriverUtility1();
		PropertyFileUtilites1 pLib = new PropertyFileUtilites1();
		//to launch the browser
		WebDriver driver=new ChromeDriver();
		wLib.maximizeBrowser(driver);
		wLib.implicitWait(driver, 10);

		//to enter the url
		String url = pLib.getPropertyKeyValue("url");
		driver.get(url);
		String un=pLib.getPropertyKeyValue("username");
		String pwd=pLib.getPropertyKeyValue("password");
		HomePage hp = new HomePage(driver);
		//to user login
		hp.getUserSignInLink().click();
		hp.getUserSignInEmailTbx().sendKeys(un);
		hp.getUserSignInPasswordTbx().sendKeys(pwd);
		hp.getUserSignInButton().click();
		//to verify tour package page
		UserDashBoardPage udb = new UserDashBoardPage(driver);
		udb.getTourPackagesLink().click();
		String expectedValue="TMS | Package List";
		String actualValue=driver.getTitle();
		assertEquals(actualValue, expectedValue);
		udb.getUserLogoutLink().click();
		driver.quit();
	}
}
