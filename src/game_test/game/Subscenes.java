package game_test.game;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class Subscenes extends SubScene implements AnimationEffect{

    private final String FONT_PATH = "src/game_test/game/resources/fonts/ghostclan.ttf";
    private final String BACKGROUND_IMAGE_PATH = "/game_test/game/resources/subscene_images/glassPanel.png";

    private static final int WIDTH = 450;
    private static final int HEIGHT = 500;

    private boolean isShowing;
    AnchorPane root;
    public Subscenes(){
       super (new AnchorPane(),WIDTH,HEIGHT);
        prefWidth(WIDTH);
        prefHeight(HEIGHT);
        setIsShowing(false);

        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE_PATH, WIDTH, HEIGHT,false, true),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,null);

        root = (AnchorPane) this.getRoot();
        root.setBackground(new Background(backgroundImage));

        setLayoutX(960);
        setLayoutY(100);
    }

    public void addLayout(AnchorPane layout){
        root.getChildren().add(layout);
        layout.setLayoutY(100);
        layout.setLayoutX(100);
    }
    @Override
    public void animationEffect() {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(this);
        if (isShowing){
            translateTransition.setDuration(Duration.seconds(0.2));
            translateTransition.setToX(0);
            setIsShowing(false);
            //System.out.println("Now Hidden");
        }
        else if (!isShowing) {
            translateTransition.setDuration(Duration.seconds(0.5));
            translateTransition.setToX(-480);
            setIsShowing(true);
           // System.out.println("Now showing");
        }
        translateTransition.play();
    }

    public boolean getIsShowing() {
        return isShowing;
    }
    public void setIsShowing(boolean val){
        isShowing = val;
    }
}
