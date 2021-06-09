package game_test.game;

import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;


public class SettingsSubsceneButtons extends MenuButton{
    private static final String NORMAL_PATH = "/game_test/game/resources/settings_subscene_images/grey_circle.png";
    private static final String PRESSED_PATH = "/game_test/game/resources/settings_subscene_images/blue_circle.png";
    private static final int BUTTON_WIDTH = 25;
    private static final int BUTTON_HEIGHT = 25;

    public SettingsSubsceneButtons(){
        super(NORMAL_PATH, PRESSED_PATH);
        setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        setButtonBackgroundSize(BUTTON_WIDTH,BUTTON_HEIGHT);
        setDefaultStyle();
        setListeners();
    }

    // polymorphism (method overloading)
    public SettingsSubsceneButtons(String normal, String pressed, int width, int height){
        super(normal,pressed);
        setPrefSize(width,height);
        setButtonBackgroundSize(width, height);
        setDefaultStyle();
        setButtonFont();
        setListeners();
    }

    private void setButtonFont(){
        try{
            setFont(Font.loadFont(new FileInputStream("src/game_test/game/resources/fonts/ThaleahFat.ttf"),
                    20));

        }
        catch(Exception e){
            System.out.println("FONT NOT FOUND");
            setFont(Font.font("Tahoma", 20.0));
        }
    }
    @Override
    protected void setListeners() {
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    //System.out.println("CLICKED!");
                    if (!getIsPressed()){
                        setMouseButtonPressedStyle();
                    }
                }
            }
        });
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!getIsPressed()){
                    setEffect(new DropShadow());
                }
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (getIsPressed()){
                    setEffect(null);
                }
                else{
                    setDefaultStyle();
                    setEffect(null);
                }
            }
        });
        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(null);
            }
        });
    }

    @Override
    public void setDefaultStyle() {
        setStyle(getDefaultStyle());
        setIsPressed(false);
        setFocusTraversable(true);
    }

    @Override
    public void setMouseButtonPressedStyle() {
        setStyle(getMouseButtonPressedStyle());
        setIsPressed(true);
        setFocusTraversable(false);
    }
}
