package game_test.game;

import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SimpleButton extends MenuButton{
    private static String NORMAL = "game_test/game/resources/menu_images/grey_button_normal.png";
    private static String ENTERED = "game_test/game/resources/menu_images/grey_button_entered.png";
    private static String PRESSED = "game_test/game/resources/menu_images/grey_button_mouse_entered.png";

    public SimpleButton(String text,int width, int height, int fontSize){
        super(NORMAL, PRESSED);
        setPrefSize(width,height);
        setButtonBackgroundSize(width,height);
        setText(text);
        setButtonFont(fontSize);
        setDefaultStyle();
        setListeners();
    }

    @Override
    protected void setListeners() {
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(null);
            }
        });
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    setMouseButtonPressedStyle();
                }
            }
        });
        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setDefaultStyle();
                setLayoutY(getLayoutY() - 4);
            }
        });
    }

    @Override
    public void setDefaultStyle() {
        setStyle(getDefaultStyle());
        setTextFill(Color.BLACK);

    }

    @Override
    public void setMouseButtonPressedStyle() {
        setStyle(getMouseButtonPressedStyle());
        setTextFill(Color.DIMGREY);
        setLayoutY(getLayoutY() + 4);
    }
}
