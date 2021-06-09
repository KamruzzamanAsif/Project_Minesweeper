package game_test.game;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.io.FileInputStream;

public class HelpSubScene {
    private AnchorPane layout;
    private Label label;

    public HelpSubScene(){
        layout = new AnchorPane();
        label = new Label();
        layout.getChildren().add(label);
        layout.setPrefSize(400,450);
        //addFont();
        label.setFont(Font.font(20));
        setLabelLayout();
        addText();
    }

    private void setLabelLayout() {

        label.setLayoutX(-70);
        label.setLayoutY(20);
    }

    private void addText() {
        String text = "" +
                "Each cell is empty, has a number,"
                + " or has a bomb. Mouse-LeftClick reveals " +
                "what's in the cell. A numbered cell means " +
                "how many bombs are in all 8 directions. " +
                "Mouse-Right Click flags a closed cell." +
                "Clicking on a bomb-cell is game over. " +
                "Uncover all cells without the bombs to win.";

        label.setText(text);
        label.setWrapText(true);
        label.setMaxWidth(380);
        label.setTextAlignment(TextAlignment.JUSTIFY);
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