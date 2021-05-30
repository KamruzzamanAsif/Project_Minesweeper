package game_test.game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    File file;
    AudioInputStream audioStream;
    Clip clip;

    public void playMusic(String str) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        switch (str) {
            case "click":
                file = new File("src/game_test/game/resources/music/sounds_click.wav");
                break;
            case "flag":
                file = new File("src/game_test/game/resources/music/sounds_flag.wav");
                break;
            case "unflag":
                file = new File("src/game_test/game/resources/music/sounds_unflag.wav");
                break;
            case "bomb":
                file = new File("src/game_test/game/resources/music/sounds_wrong.wav");
                break;
            case "win":
                file = new File("src/game_test/game/resources/music/sounds_gameWin_2.wav");
                break;
        }

        audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
}
