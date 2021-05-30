package game_test.game;

import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class PopUpBox {
    private SubScene subScene;
    private AnchorPane layout;
    private int startingX = 1000;
    private int startingY = 1000;
    private int width = 400;
    private int height = 200;

    private boolean isShowing;

    public PopUpBox(){
        layout = new AnchorPane();
        subScene = new SubScene(layout,960,720);
        subScene.setLayoutY(startingY);
        subScene.setLayoutX(startingX);
        isShowing = false;
        addBackground();

    }

    protected void addBackground(){
        BackgroundImage backgroundImage = new BackgroundImage(new Image("/game_test/game/resources/popup_images/glassPanel_corners.png", width,height, false, true),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);

        layout.setBackground(new Background(backgroundImage));
    }

    public SubScene getSubScene() {
        return subScene;
    }
    public void show(){
        subScene.setLayoutX(320);
        subScene.setLayoutY(240);
    }
    public void hide(){
        subScene.setLayoutY(startingY);
        subScene.setLayoutX(startingX);
    }

    public boolean getIsShowing() {
        return isShowing;
    }
    public void setIsShowing(boolean val){
        isShowing = val;
    }
    public AnchorPane getLayout(){
        return layout;
    }
}
