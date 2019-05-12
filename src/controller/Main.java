package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Project;
import model.sqlite.ProjectSQLite;

import java.util.ArrayList;
import java.util.Date;

public class Main extends Application {

    private static Stage stage;

    private static Scene listScene;
    private static Scene createScene;
    private static Scene mainScene;
    private static Scene projectScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;

        primaryStage.setTitle("Gerenciador de projetos");

        Parent fxmlList = FXMLLoader.load(getClass().getResource("/view/projects_list.fxml"));
        listScene = new Scene(fxmlList, 750, 600);

        Parent fxmlCreate = FXMLLoader.load(getClass().getResource("/view/create_screen.fxml"));
        createScene = new Scene(fxmlCreate, 750, 600);

        Parent fxmlProject = FXMLLoader.load(getClass().getResource("/view/project_screen.fxml"));
        projectScene = new Scene(fxmlProject, 750, 600);

        Parent fxmlMain = FXMLLoader.load(getClass().getResource("/view/main_screen.fxml"));
        mainScene = new Scene(fxmlMain, 750, 600);

        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void changeScreen(String scr, Object userData){
        switch (scr){
            case "main":
                stage.setScene(mainScene);
                notifyAllListeners("main", userData);
                break;
            case "list":
                stage.setScene(listScene);
                notifyAllListeners("list", userData);
                break;
            case "create":
                stage.setScene(createScene);
                notifyAllListeners("create", userData);
                break;
            case "project":
                stage.setScene(projectScene);
                notifyAllListeners("project", userData);
                break;
        }
    }

    public static void changeScreen(String scr){
        changeScreen(scr, null);
    }


    public static void main(String[] args) {
        launch(args);
    }

    //------------------
    private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    public static interface OnChangeScreen{
        void onScreenChanged(String newScreen, Object userData);
    }

    public static void addOnChangeScreenListener(OnChangeScreen newListener){
        listeners.add(newListener);
    }

    private static void notifyAllListeners(String newScreen, Object userData){
        for(OnChangeScreen l : listeners)
            l.onScreenChanged(newScreen, userData);
    }
}
