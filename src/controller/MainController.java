package controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class MainController {
    @FXML
    protected void btNewProject(ActionEvent e){
        Main.changeScreen("create");


    }

    @FXML
    protected void btSeeProjects(ActionEvent e){
        Main.changeScreen("list");
    }
}
