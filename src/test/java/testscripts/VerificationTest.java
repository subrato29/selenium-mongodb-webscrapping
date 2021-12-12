package testscripts;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import com.demo.base.DriverScript;

@Listeners(MongoDBListeners.class)
public class VerificationTest extends DriverScript{	
	@Test
	public void verifyUrl () throws NumberFormatException, IOException {
		String tcId = "TC001";
		if (isRunnable(tcId, 2)) {
			Assert.assertEquals(driver.getCurrentUrl().trim(), "https://www.lyft.com/");
		}
	}
	
	@Test
	public void verifyTitle () throws NumberFormatException, IOException {
		String tcId = "TC001";
		if (isRunnable(tcId, 2)) {
			Assert.assertEquals(driver.getTitle().trim(), "A ride whenever you need one");
		}
	}
	
	@Test
	public void verifyCountOfLinks () throws NumberFormatException, IOException {
		String tcId = "TC001";
		if (isRunnable(tcId, 2)) {
			Assert.assertEquals(driver.findElements(By.tagName("a")).size(), 76);
		}
	}
}
