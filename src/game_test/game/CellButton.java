package game_test.game;


import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class CellButton extends Button {
    // set the paths to the game_images - closedbutton, opened button, and the flagged button
    private String CLOSED_IMAGE = "-fx-background-image: url('game_test/game/resources/game_images/Board_button_closed.jpg');";
    private String CLOSED_WITH_FLAG_IMAGE = "-fx-background-image: url('game_test/game/resources/game_images/Board_button_closed_with_flag.jpg');";
    private String BOMB_IMAGE = "-fx-background-image: url('game_test/game/resources/game_images/Board_button_opened_with_bomb.jpg');";
    private String BOMB_TRIGGERED_IMAGE = "-fx-background-image: url('game_test/game/resources/game_images/Board_button_opened_with_bomb_triggered.jpg');";
    private String []OPENED_IMAGE = new String [9];

    //set the styling of the buttons
    private String CLOSED_STYLE;
    private String CLOSED_WITH_FLAG_STYLE;
    private String OPENED_STYLE;
    private String BOMB_STYLE;
    private String BOMB_TRIGGERED_STYLE;
    private String TEXT_FILL = "-fx-text-fill: ";
    private String FONT_WEIGHT = "-fx-font-weight: 900; ";
    private String FONT_INFO = "-fx-font: 18px Tahoma ; -fx-stroke-width: 2; ";
    //forced image properties
    private int image_height;
    private int image_width;
    private String background_size;

    // Booleans
    private boolean isClosed;
    private boolean isOpen;
    private boolean isFlagged;

    public CellButton(int height, int width){
        //setText("closed");
        setFocusTraversable(false);
        setPrefSize(height,width);
        isClosed = true;
        isOpen = false;
        isFlagged = false;
        init_listeners();
        image_height = height;
        image_width = width;
        background_size = setBackground_Size();
        CLOSED_STYLE = CLOSED_IMAGE + " " + background_size;
        CLOSED_WITH_FLAG_STYLE = CLOSED_WITH_FLAG_IMAGE + " " + background_size;
        BOMB_STYLE = BOMB_IMAGE + " " + background_size;
        BOMB_TRIGGERED_STYLE = BOMB_TRIGGERED_IMAGE + " " + background_size;
        setPath();
        setStyle(CLOSED_STYLE);
    }
    private String setBackground_Size(){
        return "-fx-background-size: " + image_width +"px " + image_height +"px ;";
    }
    private void setPath(){
        for (int i=0;i<9;i++){
            OPENED_IMAGE[i] = new String("-fx-background-image: url('game_test/game/resources/game_images/" + i + ".jpg');");
        }
    }
    private void init_listeners(){
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("left button clicked");
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    //if closed and not flagged then open the button
                    if (isClosed && !isFlagged) {
                        isClosed = false;
                        isOpen = true;
                        setStyle(OPENED_STYLE);
                    }
                }
                if (mouseEvent.getButton().equals(MouseButton.SECONDARY)){
                    System.out.println("right button clicked");
                    if (isClosed && !isFlagged){
                        isFlagged = true;
                        setStyle(CLOSED_WITH_FLAG_STYLE);
                    }
                    else if (isClosed && isFlagged){
                        isFlagged = false;
                        setStyle(CLOSED_STYLE);
                    }
                }
            }
        });
    }

    public boolean isClosed(){
        return this.isClosed;
    }
    public boolean isOpen(){
        return this.isOpen;
    }
    public boolean isFlagged(){
        return this.isFlagged;
    }
    public void setIsClosed(boolean val){
        this.isClosed = val;
    }
    public void setIsOpened(boolean val){
        this.isOpen = val;
    }
    public void setIsFlagged(boolean val){
        this.isFlagged = val;
    }
    public String getCLOSED_STYLE(){
        return this.CLOSED_STYLE;
    }
    public void setClosedStyle(){
        setStyle(CLOSED_STYLE);
    }
    public String getOPENED_STYLE(int val){
        return OPENED_IMAGE[val] + " " + background_size;
    }
    public void setOpenedStyle(int val){
        setStyle(OPENED_IMAGE[val] + " " + background_size);
    }
    public String getClosedWithFlagStyle(){
        return this.CLOSED_WITH_FLAG_STYLE;
    }
    public void setClosedWithFlagStyle(){
        setStyle(CLOSED_WITH_FLAG_STYLE);
    }
    public String getBombTriggeredStyle() {
        return this.BOMB_TRIGGERED_STYLE;
    }
    public void setBombTriggeredStyle(){
        setStyle(BOMB_TRIGGERED_STYLE);
    }
    public String getBombStyle() {
        return this.BOMB_STYLE;
    }
    public void setBombStyle(){
        setStyle(this.BOMB_STYLE);
    }
    public void reset(){
        isClosed = true;
        isOpen = false;
        isFlagged = false;
        setClosedStyle();
        setText("");
    }
}
