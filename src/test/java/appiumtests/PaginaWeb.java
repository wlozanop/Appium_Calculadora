package appiumtests;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class PaginaWeb {
    static RemoteWebDriver driver;

    public static void main (String[] args){

        try {
            automationWeb();
        }catch(Exception exp){
            //System.out.println(exp.getCause());
            System.out.println(exp.getMessage());
            exp.printStackTrace();
        }
    }

    public static <webDriver> void automationWeb() throws MalformedURLException {

        //DesiredCapabilities cap = new DesiredCapabilities();
        DesiredCapabilities cap = DesiredCapabilities.android();
        //Capabilitis de configuración del Telefono Movil
        //cap.setCapability("deviceName", "ANE LX3");
        cap.setCapability("udid", "6NUDU18A09010885");
        //cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "9.0");

        //cap.setCapability("appPackage", "com.android.chrome");
        //cap.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        cap.setCapability(MobileCapabilityType.VERSION, "5.1");
        //cap.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "ANE LX3");

        // Conectar con Appium
        //URL url = new URL("http://127.0.0.1:4723/wd/hub");
        System.out.println("LLEGO HASTA AQUI 1......");
        // driver = new AppiumDriver<MobileElement>(url, cap);
        //WebDriver driver = new AndroidDriver(url, cap);
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);

        driver.get("https://www.amazon.com/");
        //System.out.println("url "+url);
        System.out.println("cap "+cap);

        driver.findElement(By.id("nav-search-keywords")).sendKeys("ABCD");

       // @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View/android.widget.Button") WebElement AddContact;

        System.out.println("Title "+driver.getTitle());

        /*driver.findElement(By.id("com.android.chrome:id/terms_accept")).click();
        driver.findElement(By.id("com.android.chrome:id/next_button")).click();
        driver.findElement(By.id("com.android.chrome:id/negative_button")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("com.android.chrome:id/url_bar")).click();*/

    }

}
