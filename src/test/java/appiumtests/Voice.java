package appiumtests;

import com.sun.speech.freetts.FreeTTS;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;

import javax.sound.sampled.AudioFileFormat;
import java.io.FileInputStream;

class VoiceClass {

    static void call(String msg){
        System.out.println("U r here");
        //com.sun.speech.freetts.Voice voice = new Voice();
        FreeTTS freetts;
        System.out.println("---------************");
        VoiceManager vm=VoiceManager.getInstance();
        System.out.println("--------------////");
        Voice voice ; //= new Voice();
        voice = vm.getVoice("kevin16");
        System.setProperty("com.sun.speech.freetts.voice.defaultAudioPlayer", "com.sun.speech.freetts.audio.SingleFileAudioPlayer");

        FileInputStream fis ;
        SingleFileAudioPlayer sfap = null;
        //sfap = new SingleFileAudioPlayer(getBasename("AudioGrabado"), AudioFileFormat.Type.WAVE);
        sfap = new SingleFileAudioPlayer("AudioGrabado", AudioFileFormat.Type.WAVE);
        //System.out.println("sfap..."+sfap.getAudioFormat().getEncoding());

        voice.setAudioPlayer(sfap);
        if(voice==null)
            System.out.println("voice is null");
        System.out.println("---0---");
        if(voice!=null)
        {
            voice.allocate();
        }
        System.out.println("---1---");
        freetts=new FreeTTS(voice);
        String text = new String(msg);
        byte b[] = text.getBytes();
        if(b==null)
        {
            System.out.println("no byte array");
            System.exit(1);
        }
        try
        {
            sfap = (SingleFileAudioPlayer)voice.getDefaultAudioPlayer();
            sfap.write(b);
        }
        catch(Exception e)
        {

        }
        voice.speak(msg);
        voice.deallocate();

        System.out.println("---------?????-----------");
        try {
            System.out.println("inside copy try block");
            String command="copy \"C:\\Program Files\\Apache Software Foundation\\Apache Tomcat 6.0.18\\bin\\freetts.wav\" \"C:\\Program Files\\Apache Software Foundation\\Apache Tomcat 6.0.18\\webapps\"";
            System.out.println("copying file from "+command);
            Runtime.getRuntime().exec("cmd.exe /c"+command);
            System.out.println("file copied");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("---2---");
        System.out.println("calling wav file");
        //AePlayWave aw=new AePlayWave("C:/Program Files/Apache Software Foundation/Apache Tomcat 6.0.18/bin/freetts.wav");
        System.out.println("---last---");

    }

    private static String getBasename(String path) {
        int index = path.lastIndexOf(".");
        if (index == -1) {
            return path;
        }else{
            return path.substring(0, index);
        }
    }
    public static void main(String[] args)throws Exception {
        call("hola");
    }

}
