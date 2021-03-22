package appiumtests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class AutomationCall {

    static AppiumDriver<MobileElement> driver;

    public static void main (String[] args){

        try {
            openCalculator();
        }catch(Exception exp){
            //System.out.println(exp.getCause());
            System.out.println(exp.getMessage());
            exp.printStackTrace();
        }
    }

    public static void openCalculator() throws Exception{
        
        TecladoCall tecladocall = new TecladoCall();

        DesiredCapabilities cap = new DesiredCapabilities();
        //Capabilitis de configuración del Telefono Movil
        cap.setCapability("deviceName", "ANE LX3");
        cap.setCapability("udid", "6NUDU18A09010885");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "9.0");

        cap.setCapability("appPackage", "com.android.contacts");
        cap.setCapability("appActivity", "com.android.contacts.activities.DialtactsActivity");

        // Conectar con Appium
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AppiumDriver<MobileElement>(url, cap);

        //Marcar la llamada HUAWEI 123
        driver.findElement(By.id("com.android.contacts:id/contacts_dialpad_zero")).click();
        driver.findElement(By.id("com.android.contacts:id/contacts_dialpad_one")).click();
        driver.findElement(By.id("com.android.contacts:id/contacts_dialpad_three")).click();
        driver.findElement(By.id("com.android.contacts:id/contacts_dialpad_five")).click();
        driver.findElement(By.id("com.android.contacts:id/contacts_dialpad_zero")).click();
        driver.findElement(By.id("com.android.contacts:id/contacts_dialpad_three")).click();
        driver.findElement(By.id("com.android.contacts:id/contacts_dialpad_seven")).click();
        driver.findElement(By.id("com.android.contacts:id/contacts_dialpad_three")).click();
        driver.findElement(By.id("com.android.contacts:id/contacts_dialpad_zero")).click();
        driver.findElement(By.id("com.android.contacts:id/dialButton")).click();
        System.out.println("Marcando llamada......");
        //Metodo para grabar el Audio de la llamada
        AudioFormat format = new AudioFormat(16000, 16,1,true,true);
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

                AudioInputStream audioStream = new AudioInputStream(targetDataLine);
                String pathRecordFile = "C:\\Users\\wlozanop\\Documents\\Capa\\AudioGrabado\\AudioGrabado.wav";
                File wavFile = new File(pathRecordFile);

                try {
                    AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, wavFile);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        //Activar altavoz y activar Teclado
        driver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
        driver.findElement(By.id("com.truecaller:id/toggle_audio_route")).click();
        //driver.findElement(By.id("com.truecaller:id/toggle_mute")).click();
        driver.findElement(By.id("com.truecaller:id/button_keypad")).click();

        //Ingresar el Numero de DNI
        Thread.sleep(21000);
        driver.findElement(By.xpath(tecladocall.numero1)).click();
        driver.findElement(By.xpath(tecladocall.numero0)).click();
        driver.findElement(By.xpath(tecladocall.numero3)).click();
        driver.findElement(By.xpath(tecladocall.numero2)).click();
        driver.findElement(By.xpath(tecladocall.numero2)).click();
        driver.findElement(By.xpath(tecladocall.numero8)).click();
        driver.findElement(By.xpath(tecladocall.numero0)).click();
        driver.findElement(By.xpath(tecladocall.numero3)).click();
        //Marcar 1 para saldos y Movimientos
        Thread.sleep(14000);
        driver.findElement(By.xpath(tecladocall.numero1)).click();
        //Ingresar numero completo de Tarjeta
        Thread.sleep(9000);
        driver.findElement(By.xpath(tecladocall.numero4)).click();
        driver.findElement(By.xpath(tecladocall.numero5)).click();
        driver.findElement(By.xpath(tecladocall.numero5)).click();
        driver.findElement(By.xpath(tecladocall.numero7)).click();
        driver.findElement(By.xpath(tecladocall.numero8)).click();
        driver.findElement(By.xpath(tecladocall.numero8)).click();
        driver.findElement(By.xpath(tecladocall.numero5)).click();
        driver.findElement(By.xpath(tecladocall.numero7)).click();
        driver.findElement(By.xpath(tecladocall.numero9)).click();
        driver.findElement(By.xpath(tecladocall.numero0)).click();
        driver.findElement(By.xpath(tecladocall.numero4)).click();
        driver.findElement(By.xpath(tecladocall.numero8)).click();
        driver.findElement(By.xpath(tecladocall.numero2)).click();
        driver.findElement(By.xpath(tecladocall.numero7)).click();
        driver.findElement(By.xpath(tecladocall.numero7)).click();
        driver.findElement(By.xpath(tecladocall.numero5)).click();
        //Ingresar clave Secreta
        Thread.sleep(5000);
        driver.findElement(By.xpath(tecladocall.numero0)).click();
        driver.findElement(By.xpath(tecladocall.numero4)).click();
        driver.findElement(By.xpath(tecladocall.numero2)).click();
        driver.findElement(By.xpath(tecladocall.numero9)).click();
        //Marcar opcion 1
        Thread.sleep(6500);
        driver.findElement(By.xpath(tecladocall.numero1)).click();
        //opcion 1 en Soles y opcion 2 en dolares
        System.out.println("Iniciando Grabación");
        stopper.start();
        Thread.sleep(1000);
        System.out.println("INICIA EL AUDIO BCP:   ");
        //CORTAR LA LLAMADA
        Thread.sleep(1000);
        driver.findElement(By.id("com.truecaller:id/button_end_call")).click();

        targetDataLine.stop();
        System.out.println("Finalizando grabación");
        targetDataLine.close();

        //return System.out.println();

        //Comparar archivos de Audio Tipo WAVE
        //byte[] firstFingerPrint = new FingerprintManager().extractFingerprint(new Wave("C:\\Users\\wlozanop\\Documents\\Capa\\AudioGrabado\\Grabación_audio_original\\AudioGrabado.wav"));
        //byte[] secondFingerPrint = new FingerprintManager().extractFingerprint(new Wave("C:\\Users\\wlozanop\\Documents\\Capa\\AudioGrabado\\Grabación_igual\\AudioGrabado.wav"));
        // Comparar Similitud de Archivos
       /* FingerprintSimilarity fingerprintSimilarity = new FingerprintSimilarityComputer(firstFingerPrint, secondFingerPrint).getFingerprintsSimilarity();

        if (fingerprintSimilarity.getScore() < 3){
            System.out.println("EL AUDIO NO ES SIMILAR");
            System.out.println("Puntuación de Similitud = " + fingerprintSimilarity.getScore());
        }else{
            System.out.println("EL AUDIO ES SIMILAR");
            System.out.println("Puntuación de Similitud = " + fingerprintSimilarity.getScore());
        }*/

       // System.out.println("Puntuación de Similitud = " + fingerprintSimilarity.getScore());

        System.out.println("Programa Terminado.......");

    }

}

