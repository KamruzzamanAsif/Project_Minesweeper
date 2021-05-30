package game_test.game;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class GameDisplay implements AnimationEffect  {

    // easy difficulty = 10 x 10
    // medium difficulty = 12 x 12
    // hard difficulty = 15 x 15

    // so the width of the gamedisplay has to be a multiple of lcm (10,12,15) = 60;
    // Need to make the board cells a perfect square, so the width property is important

    private static final int WIDTH_EASY = 360;
    private static final int WIDTH_MEDIUM = 420;
    private static final int WIDTH_HARD = 450;

    private static final int ROW_EASY = 10;
    private static final int ROW_MEDIUM = 12;
    private static final int ROW_HARD = 15;

    private static final int HEIGHT = 720;

    private AnchorPane gameLayout;
    private SubScene gameScene;

    private Stage gameStage;
    Board board;
    boolean isShowing;

    public GameDisplay(int difficulty){
        gameLayout = new AnchorPane();
        gameScene = new SubScene (gameLayout,450, HEIGHT);
        //gameStage = new Stage();
        //gameStage.setScene(gameScene);

        gameLayout.setStyle("-fx-background-color: transparent");
        gameScene.setFill(Color.TRANSPARENT);

        //need to change the name later
        addBoard(difficulty);
        isShowing = true;
    }


    public SubScene getGameSubScene() {
        return this.gameScene;
    }

    private void addBoard(int difficulty){
        int row_num = getRowSizes(difficulty);
        int col_num = row_num;
        int width = getWidthSizes(difficulty);
        int cellwidth = width / row_num;

        // total number of bombs = total cell x 15%
        int maxBombs = (int)((double) row_num * col_num * 0.15);
        board = new Board(row_num, col_num,maxBombs,cellwidth,cellwidth);

        gameLayout.getChildren().add(board.getLayout());

    }

    public void newDifficulty(int difficulty){
        gameLayout.getChildren().remove(board.getLayout());
        int row_num = getRowSizes(difficulty);
        int col_num = row_num;
        int width = getWidthSizes(difficulty);
        int cellwidth = width / row_num;
        int maxBombs = (int)((double) row_num * col_num * 0.15);
        board.newBoard(row_num,col_num,maxBombs,cellwidth,cellwidth);
        gameLayout.getChildren().add(board.getLayout());
    }
    public Board getBoard(){
        return this.board;
    }
    private int getRowSizes(int difficulty){
        switch (difficulty){
            case 0:
                return ROW_EASY;
            case 1:
                return ROW_MEDIUM;
            default:
                return ROW_HARD;
        }
    }
    private int getWidthSizes(int difficulty){
        switch (difficulty){
            case 0:
                return WIDTH_EASY;
            case 1:
                return WIDTH_MEDIUM;
            default:
                return WIDTH_HARD;
        }
    }

    @Override
    public void animationEffect() {
        TranslateTransition transition = new TranslateTransition();
        //transition.setDuration(Duration.seconds(1.0));
        transition.setNode(gameScene);
        //transition.setNode(gameLayout);

        if (!board.getRenewedBoard()) {
            if (isShowing) {
                transition.setDuration(Duration.seconds(0.3));
                transition.setToX(960);
                isShowing = false;
                board.setIsShowing(false);
                board.animationEffect();
            } else if (!isShowing) {
                transition.setDuration(Duration.seconds(0.5));
                transition.setToX(0);
                isShowing = true;
                board.setIsShowing(true);
                board.animationEffect();
            }
        }
        else {
            if (isShowing) {
                transition.setDuration(Duration.seconds(0.3));
                transition.setToX(480);
                isShowing = false;
                board.setIsShowing(false);
                board.animationEffect();
            }
            else if (!isShowing) {
                transition.setDuration(Duration.seconds(0.5));
                transition.setToX(0);
                isShowing = true;
                board.setIsShowing(true);
                board.animationEffect();
            }

        }
        transition.play();
    }
    public void setIsShowing(boolean val){
        isShowing = val;
    }
    public boolean getIsShowing() {
        return isShowing;
    }

}
