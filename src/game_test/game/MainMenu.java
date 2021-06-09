package game_test.game;

import game_test.game.high_score.*;
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
    private Subscenes easyScoreDisplay;
    private Subscenes mediumScoreDisplay;
    private Subscenes hardScoreDisplay;
    private SubScene gameScene;

    private SettingsSubScene settings;
    private HelpSubScene help;
    private HighScoreControl highscores;
    private HighScoreHandler easyScore;
    private HighScoreHandler mediumScore;
    private HighScoreHandler hardScore;
    private ExitPopUp exitPopUp;

    //back button is for the highscore subscene
    private SimpleButton back;

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
        addEasyScoreDisplaySubscene();
        addMediumScoreDisplaySubscene();
        addHardScoreDisplaySubscene();
    }

    public void addHighscoresDisplaySubscene() {
        highscores = new HighScoreControl();
        highscoresDisplay = new Subscenes();
        highscoresDisplay.addLayout(highscores.getLayoutHighscore());
        back = new SimpleButton("Back",80,40,18);
        back.setOnAction(e->backButtonClicked());
    }

    private void backButtonClicked() {
        highScoresButtonClicked();
    }

    private void addEasyScoreDisplaySubscene(){
        easyScore = new HighScoreHandler("Easy");
        easyScoreDisplay = new Subscenes();
        easyScoreDisplay.addLayout(easyScore.getLayout());
        easyScore.setBackButton(back);
    }
    private void addMediumScoreDisplaySubscene(){
        mediumScore = new HighScoreHandler("Medium");
        mediumScoreDisplay = new Subscenes();
        mediumScoreDisplay.addLayout(mediumScore.getLayout());
        mediumScore.setBackButton(back);
    }
    private void addHardScoreDisplaySubscene(){
        hardScore = new HighScoreHandler("Hard");
        hardScoreDisplay = new Subscenes();
        hardScoreDisplay.addLayout(hardScore.getLayout());
        hardScore.setBackButton(back);
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
        if(easyScoreDisplay.getIsShowing()){
            easyScoreDisplay.animationEffect();
        }
        if(mediumScoreDisplay.getIsShowing()){
            mediumScoreDisplay.animationEffect();
        }
        if(hardScoreDisplay.getIsShowing()){
            hardScoreDisplay.animationEffect();
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
        if(easyScoreDisplay.getIsShowing()){
            easyScoreDisplay.animationEffect();
        }
        if(mediumScoreDisplay.getIsShowing()){
            mediumScoreDisplay.animationEffect();
        }
        if(hardScoreDisplay.getIsShowing()){
            hardScoreDisplay.animationEffect();
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
        if(easyScoreDisplay.getIsShowing()){
            easyScoreDisplay.animationEffect();
        }
        if(mediumScoreDisplay.getIsShowing()){
            mediumScoreDisplay.animationEffect();
        }
        if(hardScoreDisplay.getIsShowing()){
            hardScoreDisplay.animationEffect();
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
            highscoresDisplay.animationEffect();
        }
        if(settingsDisplay.getIsShowing()){
            settingsDisplay.animationEffect();
            settings.setHiddenStyle();
        }
        if(easyScoreDisplay.getIsShowing()){
            easyScoreDisplay.animationEffect();
        }
        if(mediumScoreDisplay.getIsShowing()){
            mediumScoreDisplay.animationEffect();
        }
        if(hardScoreDisplay.getIsShowing()){
            hardScoreDisplay.animationEffect();
        }
        highscores.getHighScoreButtonArragement().setDefault();
    }
    private void easyScoresButtonClicked() {
        menuButtonsArrangement.getPlay().setDefaultStyle();
        menuButtonsArrangement.getSettings().setDefaultStyle();
        menuButtonsArrangement.getHelp().setDefaultStyle();
        menuButtonsArrangement.getExit().setDefaultStyle();
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
            settings.setHiddenStyle();
        }
        if(!easyScoreDisplay.getIsShowing()){
            easyScoreDisplay.animationEffect();
        }
        if(mediumScoreDisplay.getIsShowing()){
            mediumScoreDisplay.animationEffect();
        }
        if(hardScoreDisplay.getIsShowing()){
            hardScoreDisplay.animationEffect();
        }
        highscores.highScoreButtonArrangement.getMedium().setDefaultStyle();
        highscores.highScoreButtonArrangement.getHard().setDefaultStyle();
    }
    private void mediumScoreButtonClick(){
        menuButtonsArrangement.getPlay().setDefaultStyle();
        menuButtonsArrangement.getSettings().setDefaultStyle();
        menuButtonsArrangement.getHelp().setDefaultStyle();
        menuButtonsArrangement.getExit().setDefaultStyle();
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
            settings.setHiddenStyle();
        }
        if(easyScoreDisplay.getIsShowing()){
            easyScoreDisplay.animationEffect();
        }
        if(!mediumScoreDisplay.getIsShowing()){
            mediumScoreDisplay.animationEffect();
        }
        if(hardScoreDisplay.getIsShowing()){
            hardScoreDisplay.animationEffect();
        }
        highscores.highScoreButtonArrangement.getEasy().setDefaultStyle();
        highscores.highScoreButtonArrangement.getHard().setDefaultStyle();

    }
    private void hardScoreButtonClick(){
        menuButtonsArrangement.getPlay().setDefaultStyle();
        menuButtonsArrangement.getSettings().setDefaultStyle();
        menuButtonsArrangement.getHelp().setDefaultStyle();
        menuButtonsArrangement.getExit().setDefaultStyle();
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
            settings.setHiddenStyle();
        }
        if(easyScoreDisplay.getIsShowing()){
            easyScoreDisplay.animationEffect();
        }
        if(mediumScoreDisplay.getIsShowing()){
            mediumScoreDisplay.animationEffect();
        }
        if(!hardScoreDisplay.getIsShowing()){
            hardScoreDisplay.animationEffect();
        }
        highscores.highScoreButtonArrangement.getEasy().setDefaultStyle();
        highscores.highScoreButtonArrangement.getMedium().setDefaultStyle();

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
        if(easyScoreDisplay.getIsShowing()){
            easyScoreDisplay.animationEffect();
        }
        if(mediumScoreDisplay.getIsShowing()){
            mediumScoreDisplay.animationEffect();
        }
        if(hardScoreDisplay.getIsShowing()){
            hardScoreDisplay.animationEffect();
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
    public Subscenes getEasyScoreDisplay(){
        return easyScoreDisplay;
    }
    public Subscenes getMediumScoreDisplay(){
        return mediumScoreDisplay;
    }
    public Subscenes getHardScoreDisplay(){
        return hardScoreDisplay;
    }
    public SubScene getExitPopUp(){
        return exitPopUp.getSubScene();
    }
    public void setMainDisplayStage(Stage mainDisplayStage) {
        this.mainDisplayStage = mainDisplayStage;
    }


    public class HighScoreControl{
        private HighScoreButtonArrangement highScoreButtonArrangement;
        private AnchorPane layoutHighscore;


        public HighScoreControl(){
            layoutHighscore = new AnchorPane();
            highScoreButtonArrangement = new HighScoreButtonArrangement();
            layoutHighscore.getChildren().add(highScoreButtonArrangement.getHighScoreButtonsArrangementLayout()); //vbox button add

            highScoreButtonArrangement.setHighScoreButtonsArrangementLayout(35, 40);
            setListenersHighscore();
        }

        private void setListenersHighscore(){
            highScoreButtonArrangement.getEasy().setOnAction(e-> easyButtonClick());
            highScoreButtonArrangement.getMedium().setOnAction(e->mediumButtonClick());
            highScoreButtonArrangement.getHard().setOnAction(e-> hardButtonClick());
        }

        private void easyButtonClick(){
            easyScoresButtonClicked();
            highScoreButtonArrangement.setDefault();
            menuButtonsArrangement.getHighscores().setMouseButtonReleasedStyle();
            easyScore.setMenuButtonsArrangement(menuButtonsArrangement);
            easyScore.updateScore();
        }
        private void mediumButtonClick(){
            mediumScoreButtonClick();
            highScoreButtonArrangement.setDefault();
            menuButtonsArrangement.getHighscores().setMouseButtonReleasedStyle();
            mediumScore.setMenuButtonsArrangement(menuButtonsArrangement);
            mediumScore.updateScore();
        }
        private void hardButtonClick(){
            hardScoreButtonClick();
            highScoreButtonArrangement.setDefault();
            menuButtonsArrangement.getHighscores().setMouseButtonReleasedStyle();
            hardScore.setMenuButtonsArrangement(menuButtonsArrangement);
            hardScore.updateScore();
        }
        public HighScoreButtonArrangement getHighScoreButtonArragement(){
            return highScoreButtonArrangement;
        }
        public AnchorPane getLayoutHighscore(){
            return layoutHighscore;
        }
    }


    private class ExitPopUp extends PopUpBox{
        private Label label;
        private VBox vbox;
        private HBox hbox;

        private int height = 40;
        private int width = 100;
        private SimpleButton yes, no;

        public  ExitPopUp(){
            label = new Label("Do you want to exit?");
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

    public HighScoreHandler getEasyScore(){
        return easyScore;
    }
    public HighScoreHandler getMediumScore(){
        return mediumScore;
    }
    public HighScoreHandler getHardScore(){
        return hardScore;
    }
}