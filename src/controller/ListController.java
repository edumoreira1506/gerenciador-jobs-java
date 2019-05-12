package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import model.Project;

import java.util.Optional;


public class ListController {

    @FXML
    protected ListView<Project> lvProjects;

    @FXML
    protected void initialize(){
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                if(newScreen.equals("list"))
                    updateList();
            }
        });
    }

    @FXML
    protected void btNewProject(ActionEvent e){
        Main.changeScreen("create");
    }

    @FXML
    protected void btSeeProject(ActionEvent e){
        ObservableList<Project> ol = lvProjects.getSelectionModel().getSelectedItems();

        if(!ol.isEmpty()){
            Project p = ol.get(0);
            Main.changeScreen("project", p);
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Nenhum projeto selecionado");
            alert.setContentText("Selecione algum elemento da lista");
            alert.showAndWait();
        }
    }

    @FXML void btDeleteProject(ActionEvent e){
        ObservableList<Project> ol = lvProjects.getSelectionModel().getSelectedItems();

        if(!ol.isEmpty()){
            Project p = ol.get(0);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Deseja mesmo excluir o projeto?");
            alert.setContentText(p.toString());

            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == ButtonType.OK){
                p.delete();
                updateList();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Nenhum projeto selecionado");
            alert.setContentText("Selecione algum elemento da lista");
            alert.showAndWait();
        }
    }


    @FXML
    protected void btEditProject(ActionEvent e){
        ObservableList<Project> ol = lvProjects.getSelectionModel().getSelectedItems();

        if(!ol.isEmpty()){
            Project p = ol.get(0);
            Main.changeScreen("create", p);
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Nenhum projeto selecionado");
            alert.setContentText("Selecione algum elemento da lista");
            alert.showAndWait();
        }
    }

    @FXML
    protected void btBack(ActionEvent e){
        Main.changeScreen("main");
    }

    private void updateList(){
        lvProjects.getItems().clear();
        for(Project p : Project.all()){
            lvProjects.getItems().add(p);
        }
    }
}
