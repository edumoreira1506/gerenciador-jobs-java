package controller;

import helpers.Conversions;
import helpers.DateUtils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Project;
import org.omg.SendingContext.RunTime;

import java.util.Date;

public class CreateController {
    Date d = new Date();

    private Project atualProject;

    @FXML
    private TextField tfTitle;

    @FXML
    private DatePicker dtDelivery;

    @FXML
    private TextField tfResponsible;

    @FXML
    private TextField tfClient;

    @FXML
    private ChoiceBox<String> chBoss;

    @FXML
    private TextField tfDescription;

    @FXML
    private DatePicker dtPedido;

    @FXML
    private CheckBox cbSEO;

    @FXML
    private CheckBox cbGrap;

    @FXML
    private CheckBox cbBanner;

    @FXML
    private CheckBox cbSite;

    @FXML
    protected void initialize(){
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData) {
            if(chBoss.getItems().size() < 2)
                chBoss.getItems().addAll("Hudson", "Thalia");


                if(newScreen.equals("create")){

                    if(userData != null){
                        atualProject = (Project)userData;
                        tfClient.setText(atualProject.getClient());
                        cbBanner.setSelected(atualProject.isBanner());
                        cbSite.setSelected(atualProject.isSite());
                        cbSEO.setSelected(atualProject.isSeo());
                        cbGrap.setSelected(atualProject.isGrap());
                        tfTitle.setText(atualProject.getTitle());
                        dtPedido.setValue( DateUtils.asLocalDate(atualProject.getOrder()) );
                        dtDelivery.setValue( DateUtils.asLocalDate(atualProject.getOrder()) );
                        chBoss.getSelectionModel().select(Conversions.stringToInt(atualProject.getBoss()));
                        tfResponsible.setText(atualProject.getResponsible());
                        tfDescription.setText(atualProject.getDescription());
                    }else{
                        atualProject = null;

                        tfClient.setText("");
                        cbBanner.setSelected(false);
                        cbSite.setSelected(false);
                        cbSEO.setSelected(false);
                        cbGrap.setSelected(false);
                        tfTitle.setText("");
                        dtPedido.setValue( DateUtils.asLocalDate(new Date()) );
                        dtDelivery.setValue( DateUtils.asLocalDate(new Date()) );
                        chBoss.getSelectionModel().clearSelection();
                        tfResponsible.setText("");
                        tfDescription.setText("");
                    }
                }
            }
        });
    }

    @FXML
    protected void btBackToList(ActionEvent e){
        Main.changeScreen("list");
    }

    @FXML
    protected void btCancel(ActionEvent e){
        Main.changeScreen("list");
    }

    @FXML
    protected void btOKAction(ActionEvent e){
        try {


            if(tfClient.getText().isEmpty())
                throw new RuntimeException("O atributo CLIENTE não pode ser vazio");

            if(tfTitle.getText().isEmpty())
                throw new RuntimeException("O atributo TÍTULO não pode ser vazio");

            if(tfResponsible.getText().isEmpty())
                throw new RuntimeException("O atributo RESPONSÁVEL não pode ser vazio");

            if(tfDescription.getText().isEmpty())
                throw new RuntimeException("O atributo DESCRIÇÃO não pode ser vazio");

            if(chBoss.getValue() == null)
                throw new RuntimeException("O atributo PEDIDO POR não pode ser vazio");

            if(cbBanner.isSelected() && cbSite.isSelected() && cbSEO.isSelected() && cbGrap.isSelected())
                throw new RuntimeException("Selecione pelo menos uma peça");

            if(atualProject != null){
                atualProject.setClient(tfClient.getText());
                atualProject.setBanner(cbBanner.isSelected());
                atualProject.setSite(cbSite.isSelected());
                atualProject.setSeo(cbSEO.isSelected());
                atualProject.setGrap(cbGrap.isSelected());
                atualProject.setTitle(tfTitle.getText());
                atualProject.setOrder(DateUtils.asDate(dtPedido.getValue()));
                atualProject.setDelivery(DateUtils.asDate(dtDelivery.getValue()));
                atualProject.setBoss(chBoss.getSelectionModel().getSelectedItem());
                atualProject.setResponsible(tfResponsible.getText());
                atualProject.setDescription(tfDescription.getText());

                atualProject.save();
            }else {
                Project p = new Project(
                        tfClient.getText(),
                        cbBanner.isSelected(),
                        cbSite.isSelected(),
                        cbSEO.isSelected(),
                        cbGrap.isSelected(),
                        tfTitle.getText(),
                        DateUtils.asDate(dtPedido.getValue()),
                        DateUtils.asDate(dtDelivery.getValue()),
                        chBoss.getSelectionModel().getSelectedItem(),
                        tfResponsible.getText(),
                        tfDescription.getText()
                );

                p.save();
            }
            Main.changeScreen("list");
        }catch (RuntimeException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao lançar Projeto");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

}
