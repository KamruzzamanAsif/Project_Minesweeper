package game_test.game;

import game_test.game.high_score.Write_object;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileInputStream;
import java.io.IOException;



public class Board extends Thread implements AnimationEffect  {
    private CellButton [][] grid_buttons;
    private int [][] grid_info;
    private int row_size;
    private int col_size;

    // button dimensions
    private int cellButtonWidth;
    private int cellButtonHeight;

    private int remaining_cells;
    private int bombs_cnt = 0;
    private int maxBombs;

    // direction arrays for traversal
    private int dx [] = {1,-1,0,0,-1,1,-1,1};
    private int dy [] = {0,0,1,-1,1,1,-1,-1};

    // will need to change this to anchor pane later
    private VBox vbox;
    private AnchorPane boardLayout;
    private HBox hbox;


    //labels for debugging
    private Label remains = new Label();
    private Label bombs = new Label();

    //for the animation effect
    private boolean isShowing;
    private boolean renewedBoard;
    private boolean isGameOver;

    //Button reset_btn;
    private SimpleButton reset_btn;
    private GridPane grid;

    //for Music
    private Music music;

    //for Stopwatch stuff
    private Timeline timeline;
    private Text timeText;
    private int seconds = 0, minutes = 0;
    private boolean isStopwatchRunning;
    private boolean stopwatchShouldStart;
    private boolean isWon;

    private Messages messages;
    //for highscores
    private MenuButtonsArrangement menuButtonsArrangement;

    public Board(int row, int col, int maxBombs, int cellButtonHeight, int cellButtonWidth){
        row_size = row;
        col_size = col;
        this.maxBombs = maxBombs;
        this.cellButtonHeight = cellButtonHeight;
        this.cellButtonWidth = cellButtonWidth;
        grid_buttons = new CellButton[row][col];
        grid_info = new int[row][col];
        isShowing = true;
        renewedBoard = false;
        timeText = new Text();
        timeText.setText("00:00");
        timeText.setFill(Color.DIMGREY);
        seconds = 0;
        minutes = 0;
        isStopwatchRunning = false;
        isGameOver = false;
        stopwatchShouldStart = false;

        messages = new Messages();
        setLayout();
        setLayout2(); // need to rename this to something else

        setFont();
        setTimeline();

    }


    private void setTimeline() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateStopwatchText();
            }
        }));
        timeline.setAutoReverse(false);
        timeline.setCycleCount(Timeline.INDEFINITE);

    }

    private void updateStopwatchText() {
        if (seconds == 60){
            minutes++;
            seconds = 0;
        }
        timeText.setText((((minutes/10) == 0) ? "0" : "") + minutes + ":"
                + (((seconds/10) == 0) ? "0" : "") + seconds++);
    }

    private void setFont() {
        try{
            timeText.setFont(Font.loadFont(new FileInputStream("src/game_test/game/resources/fonts/LEDBDREV.TTF"),
                    40.0));

        }
        catch(Exception e){
            System.out.println("FONT NOT FOUND");
            timeText.setFont(Font.font("Tahoma", 25.0));
        }
    }

    public void newBoard(int row, int col, int maxBombs, int cellButtonHeight, int cellButtonWidth){
        row_size = row;
        col_size = col;
        this.maxBombs = maxBombs;
        this.cellButtonHeight = cellButtonHeight;
        this.cellButtonWidth = cellButtonWidth;
        grid_buttons = new CellButton[row][col];
        grid_info = new int[row][col];
        isShowing = true;

        grid = new GridPane();
        grid.setOpacity(0.75);
        addButtonsToBoard(grid);
        vbox = new VBox();

        hbox = new HBox();
        setHBoxSpacing();
        hbox.getChildren().addAll(timeText, reset_btn);
        vbox.getChildren().addAll(hbox,grid); //remains,bombs);
        isShowing = false;
        renewedBoard = true;
        vbox.setLayoutX(0);
        setLayout2();
        resetTheBoard();

    }
    private void setLayout2(){

        vbox.setLayoutY(100);
    }
    private void setLayout(){
        reset_btn = new SimpleButton("Reset", 100,40,25 );
        grid = new GridPane();

        grid.setOpacity(.75);

        reset_btn.setOnAction(e->resetTheBoard());
        addButtonsToBoard(grid);

        vbox = new VBox();

        hbox = new HBox();
        setHBoxSpacing();
        hbox.getChildren().add(timeText);
        hbox.getChildren().add(reset_btn);

        vbox.getChildren().addAll(hbox,grid); //,remains,bombs);

    }

    private void setHBoxSpacing(){
        if (row_size == 10){
            hbox.setSpacing(102);
            return;
        }
        if (row_size == 12){
            hbox.setSpacing(164);
            return;
        }
        hbox.setSpacing(194);
    }
    public VBox getLayout(){
        return this.vbox;
    }

    private void addButtonsToBoard(GridPane grid){
        //Mouse Click Handler class
        MouseClickHandler Click = new MouseClickHandler();

        //first add all the buttons to the gridpane
        for (int i=0;i<row_size;i++){
            for (int j=0;j<col_size;j++){
                grid_buttons[i][j] = new CellButton(cellButtonHeight,cellButtonWidth);
                grid_buttons[i][j].setOnMouseClicked(Click);
                grid.add(grid_buttons[i][j], j , i, 1, 1);
            }
        }
        //then call setBoard to initialize the texts and grid information
        setBoard();
    }

    private void setBoard(){
        //set the # of remaining cells each time
        remaining_cells = row_size * col_size;

        for (int i=0;i<row_size;i++){
            for (int j=0;j<col_size;j++){
                grid_buttons[i][j].setText("");
                grid_info[i][j] = 0;
            }
        }
        labelUpdate();
        addMines();
        printBoard();
    }

    private void addMines() {
        //set the # of bombs each time
        maxBombs = 1;
        bombs_cnt = 0;
        while (bombs_cnt <= maxBombs){
            for (int i=0;i<row_size && bombs_cnt <= maxBombs;i++){
                for (int j=0;j<col_size && bombs_cnt <= maxBombs;j++){
                    if (grid_info[i][j] == -1) continue;

                    boolean has_bomb = Math.random() < 0.15;

                    if (has_bomb){
                        grid_info[i][j] = -1;
                        bombs_cnt++;
                    }
                }
            }
        }
        setCellNumbers();
    }

    private void setCellNumbers() {
        for (int i=0;i<row_size;i++){
            for (int j=0;j<col_size;j++){
                if (grid_info[i][j] == -1){
                    increaseCellNumber(i,j);
                }
            }
        }
    }

    private void increaseCellNumber(int x, int y) {
        for (int i=0;i<dx.length;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < row_size && 0 <= ny && ny < col_size && grid_info[nx][ny] != -1){
                grid_info[nx][ny]++;
            }
        }
    }

    @Override
    public void animationEffect() {
        if (isShowing) {
            if (isStopwatchRunning && isGameOver) {
                timeline.pause();
                timeText.setFill(Color.RED);
                stopwatchShouldStart = false;
                return;
            }
            else if (!isStopwatchRunning && isGameOver) {
                timeText.setFill(Color.RED);
                stopwatchShouldStart = false;
                return;
            }
            else if (isStopwatchRunning && isWon){
                timeText.setFill(Color.GREEN);
                stopwatchShouldStart = false;
                timeline.pause();
                return;
            }
            if (stopwatchShouldStart){
                timeText.setFill(Color.BLACK);
                timeline.play();
                isStopwatchRunning = true;
            }
        }
        else {
            timeline.pause();
        }
    }
    public boolean getRenewedBoard(){
        return this.renewedBoard;
    }
    public void setRenewedBoard(boolean val){
        this.renewedBoard = val;
    }
    public boolean getIsShowing(){
        return isShowing;
    }
    public void setIsShowing(boolean val){
        this.isShowing = val;
    }
    private class MouseClickHandler implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent mouseEvent) {
            for (int i=0;i<row_size;i++){
                for (int j=0;j<col_size;j++){
                    if (mouseEvent.getSource() == grid_buttons[i][j]){
                        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){

                            if (grid_info[i][j] == -1){
                                if (!grid_buttons[i][j].isFlagged()) {
                                    try {
                                        gameOver(i, j);
                                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                                        e.printStackTrace();
                                    }
                                    isGameOver = true;
                                    animationEffect();
                                }
                            }
                            else {
                                if (!isStopwatchRunning){
                                    stopwatchShouldStart = true;
                                    animationEffect();
                                }
                                try {
                                    leftClick(grid_buttons[i][j], grid_info[i][j]);
                                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                                    e.printStackTrace();
                                }
                                if (grid_info[i][j] == 0){
                                    traverse(i,j);
                                }
                                clickedOnACell(grid_buttons[i][j], grid_info[i][j]);
                                if (isGameWon()){
                                    try {
                                        gameisWon();
                                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        else if (mouseEvent.getButton().equals(MouseButton.SECONDARY)){
                            if (!isStopwatchRunning) {
                                stopwatchShouldStart = true;
                                animationEffect();
                            }
                            try {
                                rightClick(grid_buttons[i][j]);
                            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
            }
            labelUpdate();
        }

        private void leftClick(CellButton cell, int val) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
            if (cell.isClosed() && !cell.isFlagged()){
                cell.setIsClosed(false);
                cell.setIsOpened(true);
                cell.setOpenedStyle(val);
            }

            // music for clicking on a normal cell
            music = new Music();
            music.playMusic("click");
        }
        private void rightClick(CellButton cell) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
            if (cell.isClosed() && !cell.isFlagged()){
                cell.setIsFlagged(true);
                cell.setClosedWithFlagStyle();

                // music for flag
                music = new Music();
                music.playMusic("flag");
            }
            else if (cell.isClosed() && cell.isFlagged()){
                cell.setIsFlagged(false);
                cell.setClosedStyle();

                // music for unflag
                music = new Music();
                music.playMusic("unflag");
            }
        }
    }
    private void gameisWon() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // music for game won
        music = new Music();
        music.playMusic("win");

        isWon = true;
        animationEffect();
        messages.showPopUp();           //show win popup box

        for (int i=0;i<row_size;i++){
            for (int j=0;j<col_size;j++){
                grid_buttons[i][j].setMouseTransparent(true);
            }
        }

    }
    private boolean isGameWon() {
        return remaining_cells <= bombs_cnt;
    }
    private void clickedOnACell(CellButton cell, int val) {
        if (!cell.isMouseTransparent()) remaining_cells--;

        cell.setMouseTransparent(true);
        cell.setOpenedStyle(val);
        cell.setIsClosed(false);
        cell.setIsOpened(true);

    }

    private void traverse(int row, int col) {
        if (!(0 <= row && row < row_size && 0 <= col && col < col_size)) return;
        if (grid_buttons[row][col].isMouseTransparent()) return;
        if (grid_info[row][col] == -1) return;
        if (grid_info[row][col] != 0){
            clickedOnACell(grid_buttons[row][col], grid_info[row][col]);
            return;
        }
        clickedOnACell(grid_buttons[row][col], grid_info[row][col]);
        for (int i=0;i<dx.length;i++){
            int nx = row + dx[i];
            int ny = col + dy[i];

            traverse(nx, ny);
        }
    }

    private void gameOver(int x, int y) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        grid_buttons[x][y].setBombTriggeredStyle();
        grid_buttons[x][y].setMouseTransparent(true);

        // music for clicking on bomb
        music = new Music();
        music.playMusic("bomb");

        for (int i=0;i<row_size;i++){
            for (int j=0;j<col_size;j++){
                if (i == x && j == y) continue;
                if (grid_info[i][j] == -1) {
                    clickedOnABomb(grid_buttons[i][j]);
                }
                grid_buttons[i][j].setMouseTransparent(true);
            }
        }
    }

    private void clickedOnABomb(CellButton cell) {
        cell.setBombStyle();
    }

    private void resetTheBoard() {
        setBoard();
        for(int i=0;i<row_size;i++){
            for(int j=0;j<col_size;j++){
                grid_buttons[i][j].reset();
                grid_buttons[i][j].setMouseTransparent(false);
            }
        }
        isGameOver = false;
        isWon = false;
        isStopwatchRunning = false;
        timeText.setText("00:00");
        seconds = 0;
        minutes = 0;
        stopwatchShouldStart = false;
        timeline.pause();
        timeText.setFill(Color.DIMGREY);
    }

    private void labelUpdate(){
        remains.setText("Remaining cells: " + Integer.toString(remaining_cells));
        bombs.setText("Bombs: " + Integer.toString(bombs_cnt));
    }
    private void printBoard(){
        System.out.println("---------------------------------------");
        for (int i=0;i<row_size;i++){
            for (int j=0;j<col_size;j++){
                if (grid_info[i][j] == -1){
                    System.out.print("X" + "  ");
                }
                else {
                    System.out.print(Integer.toString(grid_info[i][j]) + "  ");
                }
            }
            System.out.print("\n");
        }
    }

    private class Messages extends PopUpBox{
        private Label label;
        // private SubScene subScene;
        int total_time,min,sec;
        String timeStr;

        TextField textField;
        Text text,textName;

        private int height = 40;
        private int width = 100;
        private SimpleButton ok;

        public Messages(){
            super(500,400);
            label = new Label();


            addFont();
            addButtons();
            setupStuff();

            // temp
            ok.setDisable(true);
        }

        private void setupStuff() {
            text = new Text();

            textName = new Text("Your Name: ");
            textField = new TextField();
            // thik korbo
            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if(textField.getText().equals("")){
                        ok.setDisable(true);
                    }
                    else{
                        ok.setDisable(false);
                    }
                }
            });

            getLayout().getChildren().addAll(label,ok,textField,text,textName);
            label.setText("Congratulations!\n   You've Won");
            label.setLayoutX(80);
            label.setLayoutY(60);
            label.setAlignment(Pos.CENTER);
            ok.setLayoutX(210);
            ok.setLayoutY(250);

            textName.setX(50);
            textName.setY(210);
            textName.setFont(Font.font(20));

            textField.setLayoutX(170);
            textField.setLayoutY(190);
            textField.setFont(Font.font(15));
        }

        private void addButtons() {
            ok = new SimpleButton("Ok",width,height,25);
            ok.setOnAction(e->okClicked());
        }

        private void okClicked() {
            Write_object write = new Write_object(getFileName());       //serialization a score add korar jono
            write.writeDetails(textField.getText(),total_time,timeStr);
            textField.clear(); // clear text field after each input
            MainMenu mainMenu = new MainMenu();
            mainMenu.addHighscoresDisplaySubscene();
            hide();
            menuButtonsArrangement.getPlay().setDisable(false);
            menuButtonsArrangement.getSettings().setDisable(false);
            menuButtonsArrangement.getHighscores().setDisable(false);
            menuButtonsArrangement.getExit().setDisable(false);
            menuButtonsArrangement.getHelp().setDisable(false);

            setBoardButtonsDisable(false);
            reset_btn.setDisable(false);

        }

        public void showPopUp(){
            timeConvert();
            show();

            ok.setDisable(true);
            menuButtonsArrangement.getPlay().setDisable(true);
            menuButtonsArrangement.getSettings().setDisable(true);
            menuButtonsArrangement.getHighscores().setDisable(true);
            menuButtonsArrangement.getExit().setDisable(true);
            menuButtonsArrangement.getHelp().setDisable(true);

            //time dhekanor jonno eta ekhane deoa
            String timeName = "Your time : "+ Integer.toString(min) +" minutes "+Integer.toString(sec)+" seconds";
            total_time = (min * 60) + sec;
            text.setText(timeName);
            text.setX(50);
            text.setY(160);
            text.setFont(Font.font(20));

            setBoardButtonsDisable(true);
            reset_btn.setDisable(true);


        }
        private void addFont() {
            try{
                label.setFont(Font.loadFont(new FileInputStream("src/game_test/game/resources/fonts/ghostclan.ttf"),
                        35.0));
            }
            catch(Exception e){
                System.out.println("FONT NOT FOUND");
                label.setFont(Font.font("Tahoma", 25.0));
            }
        }
        private void timeConvert(){
            timeStr = timeText.getText();
            int timeInt[] = new int[5];
//            for(int i : timeInt){
//                timeInt[i] = timeStr.charAt(i);
//            }
            for(int i=0;i<=4;i++){
                timeInt[i] = charConvert(timeStr.charAt(i));
            }

            min = timeInt[0] *10 + timeInt[1];
            sec = timeInt[3] *10 + timeInt[4];

        }
        private int charConvert(char character){
            if(character=='0')
                return 0;
            else if(character == '1')
                return 1;
            else if(character == '2')
                return 2;
            else if(character == '3')
                return 3;
            else if(character == '4')
                return 4;
            else if(character == '5')
                return 5;
            else if(character == '6')
                return 6;
            else if(character == '7')
                return 7;
            else if(character == '8')
                return 8;
            else
                return 9;
        }
        @Override
        public void show(){
            subScene.setLayoutX(230);
            subScene.setLayoutY(160);
        }
    }

    public SubScene getMessagesPopUp(){
        return messages.getSubScene();
    }

    public void setMenuButtonsArrangement(MenuButtonsArrangement menuButtonsArrangement){
        this.menuButtonsArrangement = menuButtonsArrangement;
    }

    private void setBoardButtonsDisable(boolean val){
        for(int i=0;i<row_size;i++){
            for (int j=0;j<col_size;j++){
                grid_buttons[i][j].setDisable(val);
            }
        }
    }

    private String getFileName(){
        if(row_size==10)
            return "src/game_test/game/high_score/high_score_file/Easy.txt";
        else if(row_size == 12)
            return "src/game_test/game/high_score/high_score_file/Medium.txt";
        else
            return "src/game_test/game/high_score/high_score_file/Hard.txt";
    }
}