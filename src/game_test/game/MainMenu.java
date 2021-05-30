package game_test.game;

import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;


public class MainMenu {
    // main menu will occupy the left side of maindisplay, this is not a subscene.

    // Main menu dimensions // they wont be used anywhere i think
    private static final int WIDTH = 480;
    private static final int HEIGHT = 720;

    private AnchorPane mainMenuLayout = new AnchorPane();
    private Stage mainDisplayStage;

    private MenuButtonsArrangement menuButtonsArrangement;
    private GameDisplay gameDisplay;
    private Board board;

    private Subscenes helpDisplay;
    private Subscenes settingsDisplay;
    private Subscenes highscoresDisplay;
    private SubScene gameScene;

    private SettingsSubScene settings;
    private HelpSubScene help;
    private HighScoresSubScene highscores;
    //PopUps
    private ExitPopUp exitPopUp;

    public MainMenu(){
        menuButtonsArrangement = new MenuButtonsArrangement();
        mainMenuLayout.getChildren().add(menuButtonsArrangement.getMenuButtonsArrangementLayout());

        menuButtonsArrangement.setMenuButtonsArrangementLayout(145, (int)(WIDTH / 2.8));

        setListeners();
        initGameDisplay();
        setBoard();
        addSubScenes();
        addExitPopUp();
        setGameScene();
        setButtonArrangementInBoard();

    }

    private void setButtonArrangementInBoard() {
        board.setMenuButtonsArrangement(menuButtonsArrangement);
    }

    private void addExitPopUp() {
        exitPopUp = new ExitPopUp();
    }

    private void setGameScene(){
        gameScene = gameDisplay.getGameSubScene();
    }
    private void addSubScenes() {
        addSettingsDisplaySubScene();
        addHelpDisplaySubScene();
        addHighscoresDisplaySubscene();
    }

    private void addHighscoresDisplaySubscene() {
        highscores = new HighScoresSubScene();
        highscoresDisplay = new Subscenes();
        highscoresDisplay.addLayout(highscores.getLayout());
    }

    private void addSettingsDisplaySubScene() {
        settings = new SettingsSubScene();
        settingsDisplay = new Subscenes();
        settingsDisplay.addLayout(settings.getLayout());
        settings.setGameDisplay(gameDisplay);
    }

    private void addHelpDisplaySubScene() {
        help = new HelpSubScene();
        helpDisplay = new Subscenes();
        helpDisplay.addLayout(help.getLayout());
    }
    public void setBoard(){

        this.board = gameDisplay.getBoard() ;
    }
    public Board getBoard(){
        return board;
    }
    public AnchorPane getMainMenuLayout(){
        return mainMenuLayout;
    }

    private void setListeners() {
        menuButtonsArrangement.getPlay().setOnAction(e-> playButtonClicked());
        menuButtonsArrangement.getSettings().setOnAction(e->settingsButtonClicked());
        menuButtonsArrangement.getHelp().setOnAction(e-> helpButtonClicked());
        menuButtonsArrangement.getHighscores().setOnAction(e->highScoresButtonClicked());
        menuButtonsArrangement.getExit().setOnAction(e->exitButtonClicked());
    }
    private void playButtonClicked(){
        menuButtonsArrangement.getSettings().setDefaultStyle();
        menuButtonsArrangement.getHelp().setDefaultStyle();
        menuButtonsArrangement.getExit().setDefaultStyle();
        menuButtonsArrangement.getHighscores().setDefaultStyle();

        if (!gameDisplay.getIsShowing()){
            gameDisplay.animationEffect();
        }
        if (helpDisplay.getIsShowing()){
            helpDisplay.animationEffect();
        }
        if (highscoresDisplay.getIsShowing()){
            highscoresDisplay.animationEffect();
        }
        if(settingsDisplay.getIsShowing()){
            settingsDisplay.animationEffect();
            settings.setHiddenStyle();
        }
    }

    private void settingsButtonClicked() {
        menuButtonsArrangement.getPlay().setDefaultStyle();
        menuButtonsArrangement.getHelp().setDefaultStyle();
        menuButtonsArrangement.getHighscores().setDefaultStyle();
        menuButtonsArrangement.getExit().setDefaultStyle();
        if (gameDisplay.getIsShowing()){
            gameDisplay.animationEffect();
        }
        if (helpDisplay.getIsShowing()){
            helpDisplay.animationEffect();
        }
        if (highscoresDisplay.getIsShowing()){
            highscoresDisplay.animationEffect();
        }
        if(!settingsDisplay.getIsShowing()){
            settingsDisplay.animationEffect();
        }
    }
    private void helpButtonClicked() {
        menuButtonsArrangement.getPlay().setDefaultStyle();
        menuButtonsArrangement.getSettings().setDefaultStyle();
        menuButtonsArrangement.getHighscores().setDefaultStyle();
        menuButtonsArrangement.getExit().setDefaultStyle();

        if (gameDisplay.getIsShowing()){
            gameDisplay.animationEffect();
        }
        if (!helpDisplay.getIsShowing()){
            helpDisplay.animationEffect();
        }
        if (highscoresDisplay.getIsShowing()){
            highscoresDisplay.animationEffect();
        }
        if(settingsDisplay.getIsShowing()){
            settingsDisplay.animationEffect();
            settings.setHiddenStyle();
        }
    }
    private void highScoresButtonClicked() {
        menuButtonsArrangement.getPlay().setDefaultStyle();
        menuButtonsArrangement.getSettings().setDefaultStyle();
        menuButtonsArrangement.getHelp().setDefaultStyle();
        menuButtonsArrangement.getExit().setDefaultStyle();

        if (gameDisplay.getIsShowing()){
            gameDisplay.animationEffect();
        }
        if (helpDisplay.getIsShowing()){
            helpDisplay.animationEffect();
        }
        if (!highscoresDisplay.getIsShowing()){
            highscores.updateTimes();
            highscoresDisplay.animationEffect();
        }
        if(settingsDisplay.getIsShowing()){
            settingsDisplay.animationEffect();
            settings.setHiddenStyle();
        }
    }
    private void exitButtonClicked() {
        menuButtonsArrangement.getPlay().setDefaultStyle();
        menuButtonsArrangement.getSettings().setDefaultStyle();
        menuButtonsArrangement.getHelp().setDefaultStyle();
        menuButtonsArrangement.getHighscores().setDefaultStyle();
        if (gameDisplay.getIsShowing()){
            gameDisplay.animationEffect();
        }
        if (helpDisplay.getIsShowing()){
            helpDisplay.animationEffect();
        }
        if (highscoresDisplay.getIsShowing()){
            highscoresDisplay.animationEffect();
        }
        if(settingsDisplay.getIsShowing()){
            settingsDisplay.animationEffect();
        }
        //mainDisplayStage.close();
        exitPopUp.showPopUp();
    }
    private void initGameDisplay() {
        int difficulty = 0;
        gameDisplay = new GameDisplay(difficulty);
        SubScene gameDisplaySubScene = gameDisplay.getGameSubScene();
        gameDisplaySubScene.setLayoutX(WIDTH);
       // gameDisplaySubScene.setOpacity(0.75);
    }

    public SubScene getGameDisplaySubScene() {
        return this.gameDisplay.getGameSubScene();
    }
    public Subscenes getSettingsDisplaySubScene(){
        return this.settingsDisplay;
    }
    public Subscenes getHelpDisplaySubScene(){
        return helpDisplay;
    }
    public Subscenes getHighscoresDisplaySubScene(){
        return highscoresDisplay;
    }
    public SubScene getExitPopUp(){
        return exitPopUp.getSubScene();
    }
    public void setMainDisplayStage(Stage mainDisplayStage) {
        this.mainDisplayStage = mainDisplayStage;
    }

    private class ExitPopUp extends PopUpBox{
        private Label label;
        private VBox vbox;
        private HBox hbox;

        private int height = 40;
        private int width = 100;
        SimpleButton yes, no;

        public  ExitPopUp(){
            label = new Label("Do you wish to exit?");
            vbox = new VBox(40);

            addFont();
            addButtons();
            setupStuff();
        }

        private void setupStuff() {
            getLayout().getChildren().addAll(label,hbox);
            label.setLayoutX(50);
            label.setLayoutY(60);

            hbox.setLayoutX(90);
            hbox.setLayoutY(110);
        }

        private void addFont() {
            try{
                label.setFont(Font.loadFont(new FileInputStream("src/game_test/game/resources/fonts/ghostclan.ttf"),
                        25.0));

            }
            catch(Exception e){
                System.out.println("FONT NOT FOUND");
                label.setFont(Font.font("Tahoma", 25.0));
            }
        }



        private void addButtons() {
            yes = new SimpleButton("Yes", width, height, 25);
            yes.setOnAction(e->yesClicked());
            no = new SimpleButton("No",width,height,25 );
            no.setOnAction(e->noClicked());
            hbox = new HBox(20);
            hbox.setLayoutX(90);
            hbox.getChildren().addAll(yes,no);

        }

        private void noClicked() {
            hide();
            menuButtonsArrangement.getPlay().setDisable(false);
            menuButtonsArrangement.getSettings().setDisable(false);
            menuButtonsArrangement.getHighscores().setDisable(false);
            menuButtonsArrangement.getExit().setDisable(false);
            menuButtonsArrangement.getHelp().setDisable(false);
            menuButtonsArrangement.getExit().setDefaultStyle();
        }

        private void yesClicked() {
            mainDisplayStage.close();
        }

        public void showPopUp(){
            show();
            menuButtonsArrangement.getPlay().setDisable(true);
            menuButtonsArrangement.getSettings().setDisable(true);
            menuButtonsArrangement.getHighscores().setDisable(true);
            menuButtonsArrangement.getExit().setDisable(true);
            menuButtonsArrangement.getHelp().setDisable(true);


        }

    }
}
