package testscripts;

import org.bson.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.demo.base.DriverScript;

public class TestScripts extends DriverScript{
	@Test
	public void webScrappingTest () throws NumberFormatException, IOException {
		String tcId = "TC001";
		if (isRunnable(tcId, 2)) {
			String url = driver.getCurrentUrl();
			String title = driver.getTitle();
			int linksCount = driver.findElements(By.tagName("a")).size();
			int imageCount = driver.findElements(By.tagName("img")).size();
			
			List<WebElement> listOfLinks = driver.findElements(By.tagName("a"));
			List<String> linksAttribute = new ArrayList<String>();
			
			Document d = new Document();
			d.append("url", url);
			d.append("title", title);
			d.append("total_links", linksCount);
			d.append("image_count", imageCount);
			
			for (WebElement ele : listOfLinks) {
				String href = ele.getAttribute("href");
				linksAttribute.add(href);
			}
			d.append("links_attribute", linksAttribute);
			
			ArrayList<Document> docsList = new ArrayList<Document>();
			docsList.add(d);
			
			webCollection.insertMany(docsList);
		}
	}
}
