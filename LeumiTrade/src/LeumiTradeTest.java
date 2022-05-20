import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeumiTradeTest {
	
	public static final String DRIVER= "webdriver.chrome.driver";
	public static final String DRIVER_LOCATION = "C:\\Program Files\\Java\\drivers\\chromedriver.exe";	
	
	public static final String SITE_URL = "https://www.leumi.co.il/";	
	
	
	public static final String POINT_ELEMENT= "/html/body/div[4]/ul/li[6]/a";	
	public static final String LINK_ELEMENT= "/html/body/div[5]/div[6]/section/div/div[1]/div/div/div/div/div[1]/div/div/a";	
	public static final String ACCOUNT_ELEMENT = "/html/body/app-root/lti-home/div[3]/app-topbox/div/div[2]/div[1]/div[1]/a";	
	
	private WebDriver driver;
	String parentlWindow;

	public void setUp() {

		try {
			System.setProperty( DRIVER, DRIVER_LOCATION);
			
			System.out.println("Property Driver: " + DRIVER);
			System.out.println("Driver Location: " + DRIVER_LOCATION);
			
			driver = new ChromeDriver();

			driver.manage().window().maximize();

			System.out.println("Open Website");
			driver.get(SITE_URL);

			parentlWindow = driver.getWindowHandle();
			System.out.println("Original Window: " + parentlWindow);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cleanUp() {
		System.out.println("Close Browser");
		if (driver != null)
			driver.quit();
	}
	
	public void clicOnElement (String xPath) {
		
		WebElement webElement = null;
		
		try {
			webElement = driver.findElement(By.xpath(xPath));
			System.out.println("Element" + webElement.getText() + " " + webElement.getTagName());
			
			if (webElement != null) {
				webElement.click();
				System.out.println("Click on Element:" + xPath);
			} else {
				System.out.println("Web Element doesn't find");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void swichWindow() {
		Set<String> s = driver.getWindowHandles();

		// Now iterate using Iterator
		Iterator<String> I1 = s.iterator();

		while (I1.hasNext()) {

			String child_window = I1.next();
			if (!parentlWindow.equals(child_window))
				driver.switchTo().window(child_window);
		}
		driver.manage().window().maximize();
	}

	public void validationFlow () {
		
		clicOnElement(POINT_ELEMENT);
		clicOnElement(LINK_ELEMENT);
		
		swichWindow();
		
		clicOnElement(ACCOUNT_ELEMENT);

	}

	public static void main(String[] args) throws InterruptedException {

		LeumiTradeTest obj;

		obj = new LeumiTradeTest();

		obj.setUp();

		obj.validationFlow();

		//obj.cleanUp();
	}

}
