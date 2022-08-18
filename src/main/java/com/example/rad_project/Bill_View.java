package com.example.rad_project;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static com.example.rad_project.DBConnector.dbLink;


public class Bill_View implements Initializable {



    @FXML
    private Button back;

    @FXML
    private Label cName2;

    @FXML
    private Label fullBill2;

    @FXML
    private Label gMoney2;

    @FXML
    private TableColumn<Record, Double> iName2;

    @FXML
    private Label mBalance2;

    @FXML
    private TableColumn<Record, Double> noOfQuanti2;

    @FXML
    private TableColumn<Record, Double> uPrice2;

    @FXML
    private TableView<Record> table2;

    @FXML
    private Label telNo2;

    @FXML
    private double blnce,sum;

    @FXML
    private int invoiceNo,upDateNo;

//    long invoice;
//
//    @FXML
//    private String foodName,unitPrice;
//
    @FXML
    private double total;
    @FXML
    private TableColumn<Record, Double> total2;

    DBConnector connectNow = new DBConnector();
    Connection connectDB = connectNow.getConnection();
    String connectQuery = "SELECT * FROM bill";
    String connectQuery1 = "SELECT * FROM customerdetail";
    String connectQuery2 = "SELECT invoice FROM billdetail ORDER BY invoice DESC LIMIT 1";

    ObservableList<Record> bill = FXCollections.observableArrayList();
    Statement statement,statement2,statement3,statement4;
    ResultSet rs;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        {
            try {
                statement = connectDB.createStatement();
                 rs = statement.executeQuery(connectQuery);
                while(rs.next()){

                    //add values to bill list

                    bill.add(new Record(rs.getString("foodName"), rs.getDouble("price"),rs.getDouble("quantity"), rs.getDouble("total") ));
                    fullBill2.setText(String.valueOf(rs.getDouble("total")));
                    total = rs.getDouble("total");
                }

                iName2.setCellValueFactory(new PropertyValueFactory<>("foodName"));    //add values to table view column

                uPrice2.setCellValueFactory(new PropertyValueFactory<>("price"));
                noOfQuanti2.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                total2.setCellValueFactory(new PropertyValueFactory<>("total"));


                table2.setItems(bill);


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



        {
            try {

                statement2 = connectDB.createStatement();
                ResultSet rs2 = statement2.executeQuery(connectQuery1);


               while (rs2.next()) {

                   cName2.setText(rs2.getString("customerName"));       //set values
                   telNo2.setText("0"+rs2.getInt("mobileNumber"));
                   gMoney2.setText("$"+rs2.getDouble("givenMoney"));
                   fullBill2.setText("$"+rs2.getDouble("totalMoney"));
                   blnce =rs2.getDouble("givenMoney")-rs2.getDouble("totalMoney");


                   mBalance2.setText("$"+String.format("%.2f",blnce));

               }



            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }



    public void pdfCreate(){

        try {

            statement3 = connectDB.createStatement();
            ResultSet rs3 = statement3.executeQuery(connectQuery2);
            while(rs3.next()){

                invoiceNo=rs3.getInt("invoice"); //get invoice number from billdetail table
            }

            String dest = "C:\\Users\\Lahiru\\Desktop\\invoice"+invoiceNo+".pdf";    //set location
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdfDoc = new PdfDocument(writer);

            Document doc = new Document(pdfDoc);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            Table table1 = new Table(new float[]{450,150 })
                    .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Invoice Number : "+invoiceNo)))
                    .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(dtf.format(now))));

            doc.add(table1);

            doc.add(new Paragraph("\n\n"));

            Table table2 = new Table(new float[]{200, 250})
                    .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("  ")))
                    .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("__THE LUXURY HOTEL__")));

            doc.add(table2);

            doc.add(new Paragraph("\n"));

            Table table3 = new Table(new float[]{200, 250})
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph("")))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Customer Invoice")));


            doc.add(table3);
            doc.add(new Paragraph("\n"));

            Table table4 = new Table(new float[]{100, 150})
                    .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Customer Name ")))
                    .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(": "+cName2.getText())))
                    .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Mobile Number ")))
                    .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(": "+telNo2.getText())));

            doc.add(table4);

            doc.add(new Paragraph("\n\n"));

            statement4 = connectDB.createStatement();
            statement4 = connectDB.createStatement();
            ResultSet rs4 = statement4.executeQuery(connectQuery);
            Table table5 = new Table(new float[]{150, 70,50,150})
                    .addCell(new Cell().add(new Paragraph("Food Name")))
                    .addCell(new Cell().add(new Paragraph("Food Price")))
                    .addCell(new Cell().add(new Paragraph("Quantity")))
                    .addCell(new Cell().add(new Paragraph("Total Price")));


            doc.add(table5);

       while(rs4.next()){

                Table table = new Table(new float[]{150, 70,50,150})
                        .addCell(new Cell().add(new Paragraph(rs4.getString("foodName"))))
                        .addCell(new Cell().add(new Paragraph("$"+rs4.getDouble("price"))))
                        .addCell(new Cell().add(new Paragraph(String.valueOf(rs4.getDouble("quantity")))))
                        .addCell(new Cell().add(new Paragraph("$"+rs4.getDouble("total"))));


           doc.add(table);


            }

       doc.add(new Paragraph("\n\n"));


            Table table6 = new Table(new float[]{100, 150})
                    .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Total Food Price ")))
                    .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(": "+fullBill2.getText())))
                    .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Given Money ")))
                    .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(": "+gMoney2.getText())))
                    .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Balance ")))
                    .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(": "+mBalance2.getText())));


            doc.add(table6);


            doc.add(new Paragraph("\n\n"));



            Table table7 = new Table(new float[]{200, 150})
                    .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("  ")))
                    .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Thank You Come Again !!!")));


            doc.add(table7);



            doc.close();


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }


    }

    public void print(ActionEvent event) {


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Successful");
        alert.setHeaderText("Your Bill was Created Successfully");



        if (alert.showAndWait().get() == ButtonType.OK){

            pdfCreate();
            PreparedStatement preparedStatement;



            try {

                String sql ="DELETE FROM customerdetail;";
                String sql1 = "DELETE FROM bill";


                preparedStatement = dbLink.prepareStatement(sql);
                preparedStatement.executeUpdate(sql);
                preparedStatement.executeUpdate(sql1);

            } catch (SQLException e) {
                e.printStackTrace();
            }


            try{

                File file = new File("C:\\Users\\Lahiru\\Desktop\\invoice"+invoiceNo+".pdf");  //get pdf location
                if (file.exists()){
                    if (Desktop.isDesktopSupported()){
                        Desktop.getDesktop().open(file);  //show pdf
                    }else {
                        Alert alert2 = new Alert(Alert.AlertType.WARNING);
                        alert2.setTitle("Warning");
                        alert2.setHeaderText("Not Supported");



                        if (alert2.showAndWait().get() == ButtonType.OK){}
                    }
                }else {
                    Alert alert2 = new Alert(Alert.AlertType.WARNING);
                    alert2.setTitle("Warning");
                    alert2.setHeaderText("File Not Exist");



                    if (alert2.showAndWait().get() == ButtonType.OK){
                        FxmlLoader.changeScene(event,"Bill_View.fxml");
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            FxmlLoader.changeScene(event,"Billing.fxml");


        }

    }


    public void back(ActionEvent event) {
        FxmlLoader.changeScene(event,"Billing.fxml");
        PreparedStatement preparedStatement;

        try {
            String sql ="DELETE FROM customerdetail;";
            String sql1 = "DELETE FROM bill";
            preparedStatement = dbLink.prepareStatement(sql);

            preparedStatement.executeUpdate(sql);
            preparedStatement.executeUpdate(sql1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
