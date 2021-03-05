package appiumtests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CalculatorTest {

    static AppiumDriver<MobileElement> driver;

    public static void main (String[] args){

        try {
            openCalculator();
        }catch(Exception exp){
            System.out.println(exp.getCause());
            System.out.println(exp.getMessage());
            exp.printStackTrace();
        }

    }

    public static void openCalculator() throws Exception{

        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability("deviceName", "ANE LX3");
        cap.setCapability("udid", "6NUDU18A09010885");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "9.0");

        //cap.setCapability("appPackage", "com.android.calculator2");
        //cap.setCapability("appActivity", "com.android.calculator2.Calculator");
        cap.setCapability("appPackage", "com.android.contacts");
        cap.setCapability("appActivity", "com.android.contacts.activities.DialtactsActivity");

        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AppiumDriver<MobileElement>(url, cap);
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.findElement(By.id("com.android.contacts:id/contacts_dialpad_one")).click();
        driver.findElement(By.id("com.android.contacts:id/contacts_dialpad_two")).click();
        driver.findElement(By.id("com.android.contacts:id/contacts_dialpad_three")).click();

        System.out.println("Marcando llamada......");

        //driver.findElement(By.id("com.android.contacts:id/searchButton")).click();
        //driver.findElement(By.id("com.android.contacts:id/call_type")).click();
        driver.findElement(By.id("com.android.contacts:id/dialButton")).click();

        driver.quit();

        // MobileElement one = driver.findElement(By.id("Teléfono"));
        //MobileElement one = driver.findElement(By.id("com.android.calculator2:id/digit_8"));
        //MobileElement op_add = driver.findElement(By.id("com.android.calculator2:id/op_add"));
        //MobileElement three = driver.findElement(By.id("com.android.calculator2:id/digit_7"));
        //MobileElement eq = driver.findElement(By.id("com.android.calculator2:id/eq"));
        //MobileElement result = driver.findElement(By.className("android.widget.EditText"));

       // MobileElement one = driver.findElement(By.id("com.huawei.android.launcher:id/layout"));
        //MobileElement one = driver.findElement(By.id("android:id/search_src_text"));
       // one.click();
        //op_add.click();
        //three.click();
        //eq.click();
        //result.click();

        //String res = result.getText();
        //System.out.println("\n Resultado es: "+res);
        System.out.println("Completado.......");

    }

}
