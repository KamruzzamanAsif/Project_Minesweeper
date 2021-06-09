package game_test.game.high_score;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileIsEmpty extends Exception{

    public FileIsEmpty(String path){
        try {
            Files.delete(Path.of(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
