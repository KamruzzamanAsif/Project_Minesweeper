package game_test.game;

import javax.imageio.IIOException;
import java.io.*;

public class HighScoreHandler {
    // here 0 means easy, 1 means medium and 2 means hard
    private FileReader reader;
    private FileWriter writer;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    private int prevHighScore;
    private boolean isNewHighScore;
    public HighScoreHandler(int difficulty, int currentScore) throws  IOException{
        isNewHighScore = false;
        setReader(difficulty);
        setWriter(difficulty,currentScore);
    }

    private void setWriter(int difficulty, int currentScore) throws IOException {
        if (currentScore < prevHighScore) {
            File file = new File("src/game_test/game/highscores/" + difficulty + ".dat");
            if (!file.exists()) {
                try{
                    file.createNewFile();
                }
                catch (IOException e){

                }
            }
            writer = new FileWriter(file);
            bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(Integer.toString(currentScore));
            bufferedWriter.close();
            isNewHighScore = true;

        }
    }

    private void setReader(int difficulty) {
        try{
            reader = new FileReader("src/game_test/game/highscores/" + difficulty +".dat");
            bufferedReader = new BufferedReader(reader);
            String s = bufferedReader.readLine();
            if (s != null) prevHighScore = Integer.parseInt(s);
            else prevHighScore = 3599;
        }
        catch (IOException e){
            prevHighScore = 3599;
        }
    }

    public boolean isNewHighScore() {
        return isNewHighScore;
    }
}
