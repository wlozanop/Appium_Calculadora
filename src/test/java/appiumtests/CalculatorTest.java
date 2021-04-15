package appiumtests;

import com.musicg.fingerprint.FingerprintManager;
import com.musicg.fingerprint.FingerprintSimilarity;
import com.musicg.fingerprint.FingerprintSimilarityComputer;
import com.musicg.wave.Wave;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
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
        //cap.setCapability("deviceName", "MOTO E__4__PLUS");
        //cap.setCapability("udid", "ZY322GZDNW");
        cap.setCapability("platformName", "Android");
        //VERSION ANDROID HUAWEI
        cap.setCapability("platformVersion", "9.0");
        //VERSION ANDROID MOTOROLA
        //cap.setCapability("platformVersion", "7.1.1");

        //cap.setCapability("appPackage", "com.android.calculator2");
        //cap.setCapability("appActivity", "com.android.calculator2.Calculator");
        //MOVIL HUAWEI
        cap.setCapability("appPackage", "com.android.contacts");
        cap.setCapability("appActivity", "com.android.contacts.activities.DialtactsActivity");
        //MOVIL MOTOROLA
        //cap.setCapability("appPackage", "com.android.dialer");
        //cap.setCapability("appActivity", "com.android.dialer.DialtactsActivity");

        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AppiumDriver<MobileElement>(url, cap);
        //driver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
        //Marcar la llamada MATOROLA 104
        /*driver.findElement(By.id("com.android.dialer:id/floating_action_button_container")).click();
        driver.findElement(By.id("com.android.dialer:id/zero")).click();
        driver.findElement(By.id("com.android.dialer:id/eight")).click();
        driver.findElement(By.id("com.android.dialer:id/zero")).click();
        driver.findElement(By.id("com.android.dialer:id/zero")).click();
        driver.findElement(By.id("com.android.dialer:id/one")).click();
        driver.findElement(By.id("com.android.dialer:id/one")).click();
        driver.findElement(By.id("com.android.dialer:id/eight")).click();
        driver.findElement(By.id("com.android.dialer:id/zero")).click();
        driver.findElement(By.id("com.android.dialer:id/zero")).click();
        driver.findElement(By.id("com.android.dialer:id/dialpad_floating_action_button_container")).click();
        */

        //Marcar la llamada HUAWEI 123
        driver.findElement(By.id("com.android.contacts:id/contacts_dialpad_one")).click();
        driver.findElement(By.id("com.android.contacts:id/contacts_dialpad_two")).click();
        driver.findElement(By.id("com.android.contacts:id/contacts_dialpad_three")).click();
        driver.findElement(By.id("com.android.contacts:id/dialButton")).click();
        System.out.println("Marcando llamada......");

        AudioFormat format = new AudioFormat(16000, 8,2,true,true);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        if(!AudioSystem.isLineSupported(info)){
            System.out.println("Linea no soporta");
        }

        final TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
        targetDataLine.open();

        targetDataLine.start();

        Thread stopper = new Thread( new Runnable() {
            // @Override
            public void run(){
                // TODO Auto-generated method stub

                AudioInputStream audioStream = new AudioInputStream(targetDataLine);
                File wavFile = new File("C:\\Users\\wlozanop\\Documents\\Capa\\AudioGrabado\\AudioGrabado.wav");

                try {
                    AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, wavFile);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        System.out.println("Iniciando Grabación");
        stopper.start();

        //Activar altavoz y activar Teclado de opciones MOTOROLA
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.findElement(By.id("com.truecaller:id/toggle_audio_route")).click();
        driver.findElement(By.id("com.truecaller:id/toggle_mute")).click();
        driver.findElement(By.id("com.truecaller:id/button_keypad")).click();

        //Marcar la opición 1
        Thread.sleep(13000);
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.View[1]")).click();
        //Marcar la opición 3
        Thread.sleep(10000);
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.View[3]")).click();
        //Cortar la llamada
        Thread.sleep(11000);
        driver.findElement(By.id("com.truecaller:id/button_end_call")).click();

        //Thread.sleep(5000);

        targetDataLine.stop();
        System.out.println("Finalizando grabación");
        targetDataLine.close();

       // public static void main (String[] args) throws Exception{
            // MP3 to WAV
            // new Converter().convert("White Wedding.mp3", "White Wedding.wav");
            // new Converter().convert("Poison.mp3", "Poison.wav");
            // Fingerprint from WAV
            byte[] firstFingerPrint = new FingerprintManager().extractFingerprint(new Wave("C:\\Users\\wlozanop\\Documents\\Capa\\AudioGrabado\\AudioGrabado.wav"));
            byte[] secondFingerPrint = new FingerprintManager().extractFingerprint(new Wave("C:\\Users\\wlozanop\\Documents\\Capa\\AudioGrabado\\Musica Original\\Bohemian-Rhapsody.wav"));
            // Compare fingerprints
            FingerprintSimilarity fingerprintSimilarity = new FingerprintSimilarityComputer(firstFingerPrint, secondFingerPrint).getFingerprintsSimilarity();
            System.out.println("Puntuación de Similitud = " + fingerprintSimilarity.getScore());
       // }

        //driver.findElement(By.id("com.android.contacts:id/searchButton")).click();
        //driver.findElement(By.id("com.android.contacts:id/call_type")).click();
        //driver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
        //Activar Altavoz y el teclado de opciones HUAWEI
        //Thread.sleep(2000);
        //driver.findElement(By.id("com.truecaller:id/toggle_audio_route")).click();
        //driver.findElement(By.id("com.truecaller:id/button_keypad")).click();
        //Marcar la opición 1
        //Thread.sleep(19000);
        //driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.View[1]")).click();
        //driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.View[2]")).click();
        //driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.View[3]")).click();
        //Cortarla llamada
        //Thread.sleep(10000);
        //driver.findElement(By.id("com.truecaller:id/button_end_call")).click();
        //driver.quit();

        // MobileElement one = driver.findElement(By.id("Teléfono"));
        //MobileElement one = driver.findElement(By.id("com.android.calculator2:id/digit_8"));
        //MobileElement op_add = driver.findElement(By.id("com.android.calculator2:id/op_add"));
        //MobileElement three = driver.findElement(By.id("com.android.calculator2:id/digit_7"));
        //MobileElement eq = driver.findElement(By.id("com.android.calculator2:id/eq"));
        //MobileElement result = driver.findElement(By.className("android.widget.EditText"));

        //MobileElement one = driver.findElement(By.id("com.huawei.android.launcher:id/layout"));
        //MobileElement one = driver.findElement(By.id("android:id/search_src_text"));
        //one.click();
        //op_add.click();
        //three.click();
        //eq.click();
        //result.click();

        //String res = result.getText();
        //System.out.println("\n Resultado es: "+res);
        System.out.println("Programa Terminado.......");

    }

}
