package controller;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.org.apache.xml.internal.utils.URI;
import helpers.Conversions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Project;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Logger;

public class ProjectController {
    @FXML
    private ListView<String> lvProject;

    private Project atualProject;

    @FXML
    protected void initialize(){
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                atualProject = (Project)userData;

                if(newScreen.equals("project"))
                    updateProject();
            }
        });
    }

    @FXML
    protected void btNewProject(ActionEvent e){
        Main.changeScreen("create");
    }

    @FXML
    protected void btBackToList(ActionEvent event) {
        Main.changeScreen("list");
    }

    @FXML
    protected void generatePdf(ActionEvent e){
        Document document = new Document();

        try{
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            PdfWriter.getInstance(document, new FileOutputStream(atualProject.getTitle() + ".pdf"));

            document.open();

            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 35, Font.BOLD, new CMYKColor(96,58,0,81));

            Paragraph cabecalho = new Paragraph(atualProject.getTitle() + ":" + atualProject.getClient(),font);
            cabecalho.setAlignment(Element.ALIGN_CENTER);

            Font fontText = new Font(Font.FontFamily.HELVETICA, 15);
            Font fontBold = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);

            Phrase client = new Phrase("Cliente: ", fontBold);
            Phrase clientName = new Phrase(atualProject.getClient());

            Phrase peaces = new Phrase("Peça(s) do Projeto: ", fontBold);
            Phrase peacesName = new Phrase(Conversions.seeBanner(atualProject.isBanner()) + Conversions.seeSite(atualProject.isSite()) + Conversions.seeSeo(atualProject.isSeo()) + Conversions.seeGrap(atualProject.isGrap()));

            Phrase title = new Phrase("Projeto: ", fontBold);
            Phrase titleName = new Phrase(atualProject.getTitle());

            Phrase order = new Phrase("Data do Pedido: ", fontBold);
            Phrase orderName = new Phrase(formato.format(atualProject.getOrder()));

            Phrase delivery = new Phrase("Prazo de Entrega: ", fontBold);
            Phrase deliveryName = new Phrase(formato.format(atualProject.getDelivery()));

            Phrase boss = new Phrase("Pedido por: ", fontBold);
            Phrase bossName = new Phrase(atualProject.getBoss());

            Phrase responsible = new Phrase("Responsável: ", fontBold);
            Phrase responsibleName = new Phrase(atualProject.getResponsible());

            Phrase descripition = new Phrase("Descrição: ", fontBold);
            Phrase descripitionName = new Phrase(atualProject.getDescription());


            document.add(cabecalho);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(client);
            document.add(clientName);
            document.add(new Paragraph(" "));
            document.add(peaces);
            document.add(peacesName);
            document.add(new Paragraph(" "));
            document.add(title);
            document.add(titleName);
            document.add(new Paragraph(" "));
            document.add(order);
            document.add(orderName);
            document.add(new Paragraph(" "));
            document.add(delivery);
            document.add(deliveryName);
            document.add(new Paragraph(" "));
            document.add(boss);
            document.add(bossName);
            document.add(new Paragraph(" "));
            document.add(responsible);
            document.add(responsibleName);
            document.add(new Paragraph(" "));
            document.add(descripition);
            document.add(descripitionName);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            Image img = Image.getInstance("logo.png");
            img.setAlignment(Element.ALIGN_CENTER);
            document.add(img);

        }catch (DocumentException | IOException ex){
            ex.printStackTrace();
        } finally {
            document.close();
        }

        try{
            Desktop.getDesktop().open(new File(atualProject.getTitle() + ".pdf"));
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void updateProject(){
        lvProject.getItems().clear();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        ArrayList<String> items = new ArrayList<String>();
        items.add("Cliente: " + atualProject.getClient());
        items.add("Peça(s) do projeto: " + Conversions.seeBanner(atualProject.isBanner()) + Conversions.seeSite(atualProject.isSite()) + Conversions.seeSeo(atualProject.isSeo()) + Conversions.seeGrap(atualProject.isGrap()) );
        items.add("Projeto: " + atualProject.getTitle());
        items.add("Data do pedido: " + formato.format(atualProject.getOrder()));
        items.add("Prazo de entrega: " + formato.format(atualProject.getDelivery()));
        items.add("Pedido por: " + atualProject.getBoss());
        items.add("Responsável: " + atualProject.getResponsible());
        items.add("Descrição: " + atualProject.getDescription());

        for(String p : items){
            lvProject.getItems().add(p);
        }
    }
}
