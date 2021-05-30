package game_test.game;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.FileInputStream;

public class HelpSubScene {
    private AnchorPane layout;
    private Label label;

    public HelpSubScene(){
        layout = new AnchorPane();
        label = new Label();
        layout.getChildren().add(label);
        layout.setPrefSize(400,450);
        addFont();
        setLabelLayout();
        addText();
    }

    private void setLabelLayout() {
        label.setLayoutX(-70);
    }

    private void addText() {
        label.setText("" +
                " Each cell is empty, has \n a number,"
                + " or has a bomb.\n Mouse-LeftClick reveals\n " +
                "what's in the cell. A\n numbered cell means " +
                "how \n many bombs are in all 8 \n directions. " +
                "Mouse-Right \n Click flags a closed cell.\n " +
                "Clicking on a bomb-cell is\n game over. " +
                "Uncover all \n cells without the bombs\n to win.\n"
        );
        label.setAlignment(Pos.BASELINE_CENTER);
    }

    private void addFont() {
        try{
            label.setFont(Font.loadFont(new FileInputStream("src/game_test/game/resources/fonts/ghostclan.ttf"),
                    25));

        }
        catch(Exception e){
            System.out.println("FONT NOT FOUND");
            label.setFont(Font.font("Tahoma", 18));
        }
    }

    public AnchorPane getLayout() {
        return layout;
    }
}