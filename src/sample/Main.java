package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ps.gamedev.Board.GameEngine;
import ps.gamedev.Board.HexBoard;
import ps.gamedev.Board.HexField;


public class Main extends Application {

    GameEngine gameEngine;
    final int WIDTH = 800, HEIGHT = 600;
    final int gridSize = 20;
    final int WIDTH_GRID_NO = 20;
    final int HEIGHT_GRID_NO = 20;
    int rotationAngle = 0;
//    HexBoard hexBoard;
//    Group graphics;


    @Override
    public void start(Stage primaryStage) throws Exception {
        gameEngine=new GameEngine(WIDTH, HEIGHT);
        gameEngine.show();
        //primaryStage.show();


//        root.setFocusTraversable(true);
//        root.requestFocus();
//        root.setOnKeyPressed(this);

        initGraphics();
//        root.getChildren().add(graphics);
        //makeIso();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void initGraphics() {
//        graphics = new Group();
//        graphics.setLayoutX(WIDTH / 2.0);
//        graphics.setLayoutY(HEIGHT / 2.0);

//        hexBoard = new HexBoard(0, 0, 20, 60, 50, true);
//        graphics.getChildren().add(hexBoard);
    }

    private void makeIso() {
//        graphics.setRotate(rotationAngle);
//        graphics.setScaleY(0.5);
    }

    private void update() {
//        graphics.setRotate(rotationAngle);
    }

    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ADD)
            rotationAngle++;
        if (keyEvent.getCode() == KeyCode.SUBTRACT)
            rotationAngle--;
        update();
        System.out.println(rotationAngle);
    }


}
