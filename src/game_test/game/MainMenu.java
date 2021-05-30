package game_test.game;

import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainMenu {
    // main menu will occupy the left side of maindisplay, this is not a subscene.

    // Main menu dimensions // they wont be used anywhere i think
    private static final int WIDTH = 480;
    private static final int HEIGHT = 720;

    private AnchorPane mainMenuLayout = new AnchorPane();
    private Stage mainDisplayStage;

    MenuButtonsArrangement menuButtonsArrangement;
    GameDisplay gameDisplay;
    Board board;

    Subscenes helpDisplay;
    Subscenes settingsDisplay;
    Subscenes highscoresDisplay;
    SubScene gameScene;

    SettingsSubScene settings;
    HelpSubScene help;

    public MainMenu(){
        menuButtonsArrangement = new MenuButtonsArrangement();
        mainMenuLayout.getChildren().add(menuButtonsArrangement.getMenuButtonsArrangementLayout());

        menuButtonsArrangement.setMenuButtonsArrangementLayout(145, (int)(WIDTH / 2.8));

        setListeners();
        initGameDisplay();
        setBoard();
        addSubScenes();
        setGameScene();
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
        highscoresDisplay = new Subscenes();
    }

    private void addSettingsDisplaySubScene() {
        settings = new SettingsSubScene();
        settingsDisplay = new Subscenes();
        settingsDisplay.addLayout(settings.getLayout());
        settings.setGameDisplay(gameDisplay);
    }

    private void addHelpDisplaySubScene() {
        //help = new HelpSubScene();
        helpDisplay = new Subscenes();
        //helpDisplay.addLayout(help.getLayout());
        //help.setGameDisplay(gameDisplay);
    }
    public void setBoard(){

        this.board = gameDisplay.getBoard() ;
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
        mainDisplayStage.close();

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
        return this.helpDisplay;
    }
    public Subscenes getHighscoresDisplaySubScene(){
        return highscoresDisplay;
    }

    public void setMainDisplayStage(Stage mainDisplayStage) {
        this.mainDisplayStage = mainDisplayStage;
    }
}
