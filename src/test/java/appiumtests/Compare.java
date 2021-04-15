package appiumtests;

import com.musicg.fingerprint.FingerprintManager;
import com.musicg.fingerprint.FingerprintSimilarity;
import com.musicg.fingerprint.FingerprintSimilarityComputer;
import com.musicg.wave.Wave;
//import javazoom.jl.converter.Converter;
//import javazoom.jl.decoder.JavaLayerException;

public class Compare {

    public static void main(String[] args) throws Exception{
        // MP3 to WAV
       // new Converter().convert("White Wedding.mp3", "White Wedding.wav");
       // new Converter().convert("Poison.mp3", "Poison.wav");
        // Fingerprint from WAV
        byte[] firstFingerPrint = new FingerprintManager().extractFingerprint(new Wave("C:\\Users\\wlozanop\\Documents\\Capa\\AudioGrabado\\Musica copia Original\\Bohemian-Rhapsody.wav"));
        byte[] secondFingerPrint = new FingerprintManager().extractFingerprint(new Wave("C:\\Users\\wlozanop\\Documents\\Capa\\AudioGrabado\\Musica Original\\Bohemian-Rhapsody.wav"));
        // Compare fingerprints
        FingerprintSimilarity fingerprintSimilarity = new FingerprintSimilarityComputer(firstFingerPrint, secondFingerPrint).getFingerprintsSimilarity();
        System.out.println("Puntuación de Similitud = " + fingerprintSimilarity.getScore());
    }
}

