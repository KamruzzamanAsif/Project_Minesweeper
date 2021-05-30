package game_test.game;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.io.FileInputStream;

public class MenuButton extends Button {
    private String BACKGROUND_SIZE;
    private String BUTTON_DEFAULT_STYLE;
    private String BUTTON_MOUSE_ENTERED_STYLE;
    private String BUTTON_MOUSE_PRESSED_STYLE;
    private String BUTTON_RELEASED_STYLE;

    private String BUTTON_BACKGROUND_SIZE;
    private boolean isPressed;

    public MenuButton(String text){
        setText(text);

        BACKGROUND_SIZE = "-fx-background-size: 190px 45px;";
        BUTTON_DEFAULT_STYLE ="-fx-background-image: url('game_test/game/resources/menu_images/grey_button_normal.png');";
        BUTTON_MOUSE_ENTERED_STYLE = "-fx-background-image: url('game_test/game/resources/menu_images/grey_button_mouse_entered.png');" ;
        BUTTON_MOUSE_PRESSED_STYLE = "-fx-background-image: url('game_test/game/resources/menu_images/grey_button_mouse_pressed.png');" ;
        BUTTON_RELEASED_STYLE = "-fx-background-image: url('game_test/game/resources/menu_images/grey_button_released.png');";

        setDefaultStyle();
        setListeners();
        setFocusTraversable(false);
        setButtonFont();
        setTextAlignment(TextAlignment.CENTER);
    }
    // I don't even know anything at this point

    public MenuButton(String normal_url, String pressed_url){
        BUTTON_DEFAULT_STYLE = "-fx-background-image: url('" + normal_url + "'); ";
        BUTTON_MOUSE_PRESSED_STYLE = "-fx-background-image: url('" + pressed_url + "'); ";
        setListeners();
    }

    public void setButtonBackgroundSize(int width, int height){
        BUTTON_BACKGROUND_SIZE = "-fx-background-color: transparent; -fx-background-size: " + width +"px " + height + "px; ";
        BUTTON_DEFAULT_STYLE += BUTTON_BACKGROUND_SIZE;
        BUTTON_MOUSE_PRESSED_STYLE += BUTTON_BACKGROUND_SIZE;
    }
    private void setButtonFont(){
        try{
            setFont(Font.loadFont(new FileInputStream("game_test/game/resources/fonts/ThaleahFat.ttf"),
                     28.0));

        }
        catch(Exception e){
            System.out.println("FONT NOT FOUND");
            setFont(Font.font("Tahoma", 25.0));
        }

    }
    protected void setListeners() {
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!getIsPressed()) {
                    setMouseEnteredStyle();
                    setEffect(new DropShadow());
                }
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!getIsPressed()) {
                    setDefaultStyle();
                    setLayoutY(getLayoutY() + 4);
                    setEffect(null);
                }
                if (getIsPressed()){
                    setEffect(null);
                }

            }
        });
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if (!getIsPressed()){
                        setMouseButtonPressedStyle();
                        setEffect(new DropShadow());
                    }
                }
            }
        });
        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if (!getIsPressed()){
                        setMouseButtonReleasedStyle();
                    }
                }
            }
        });
    }

    public void setDefaultStyle(){
        setStyle(BUTTON_DEFAULT_STYLE);
        setPrefSize(190.0,45.0);
        setIsPressed(false);
        setTextFill(Color.BLACK);
    }

    public String getDefaultStyle() {
        return BUTTON_DEFAULT_STYLE;
    }
    public void setMouseEnteredStyle(){
        setStyle(BUTTON_MOUSE_ENTERED_STYLE);
        setLayoutY(getLayoutY() - 4);
        setPrefSize(190.0, 49.0);
        setTextFill(Color.FIREBRICK);
    }
    public String getMouseEnteredStyle(){
        return BUTTON_MOUSE_ENTERED_STYLE;
    }
    public void setMouseButtonPressedStyle(){
        setStyle(BUTTON_MOUSE_PRESSED_STYLE);
        setLayoutY(getLayoutY() + 4);
        setPrefSize(190.0, 49.0);
        setTextFill(Color.DARKSALMON);
    }
    public String getMouseButtonPressedStyle(){
        return BUTTON_MOUSE_PRESSED_STYLE;
    }
    public void setMouseButtonReleasedStyle(){
        setIsPressed(true);
        setStyle(BUTTON_RELEASED_STYLE);
        setLayoutY(getLayoutY() - 4);
        setPrefSize(190.0, 49.0);
        setTextFill(Color.DIMGRAY);
    }

    public void setIsPressed(boolean val){
        isPressed = val;
    }
    public boolean getIsPressed() {
        return this.isPressed;
    }
}
