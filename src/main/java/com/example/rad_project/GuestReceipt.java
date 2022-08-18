package com.example.rad_project;

import com.example.rad_project.entity.GuestBill;
import com.example.rad_project.util.GuestBillUtil;

import com.example.rad_project.util.GuestDetailUtil;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class GuestReceipt implements Initializable {
    @FXML
    private Label arrivaleDate;

    @FXML
    private Label bal;

    @FXML
    private TextField cashGiven;

    @FXML
    private Label departureDate;

    @FXML
    private Label email;

    @FXML
    private Label invoice;

    @FXML
    private Label guestName;

    @FXML
    private Label info;

    @FXML
    private Label numOfRooms;

    @FXML
    private Label output;

    @FXML
    String country, d_roomCategory,d_roomType,d_numDays,d_addinfo,d_noRooms,sRequirement,addInfo;

    @FXML
   int mNumber;

    @FXML
    private Label totalBill;

    private static final SessionFactory SESSION_FACTORY = GuestBillUtil.getSessionFactory();


    DBConnector connectNow = new DBConnector();
    Connection connectDB = connectNow.getConnection();

    PreparedStatement preparedStatement = null;

    public void back(ActionEvent event) {
        FxmlLoader.changeScene(event,"GuestRegistration.fxml");
    }

    public void registration(ActionEvent event) {
        FxmlLoader.changeScene(event,"GuestRegistration.fxml");
    }

    public void availability(ActionEvent event) {    FxmlLoader.changeScene(event,"PriceAvailability.fxml");  }

    @FXML
    String id;



    //Initialize TextFields
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            String connectQuery1 = "SELECT * FROM guestdetail ORDER BY guestID DESC LIMIT 1";
            Statement statement1 = connectDB.createStatement();
            ResultSet rs1 = statement1.executeQuery(connectQuery1);


            if (rs1.next()) {
                addInfo = rs1.getString("additionalInfo");
                String d_lname = rs1.getString("lastName");
                String d_fname = rs1.getString("firstName");
                String d_email = rs1.getString("email");
                String d_arrivalDate = rs1.getString("arrivalDate");
                country = rs1.getString("country");
                String d_departureDate = rs1.getString("departureDate");
                d_noRooms = rs1.getString("noOfRooms");
                mNumber = rs1.getInt("phone");
                d_roomCategory = rs1.getString("roomCategory");
                d_roomType = rs1.getString("roomType");
                d_numDays = rs1.getString("daysOfstay");
                d_addinfo = rs1.getString("additionalInfo");
                id = rs1.getString("guestID");
                sRequirement = rs1.getString("specialRequirements");


                guestName.setText(d_fname+" "+d_lname);
                email.setText(d_email);
                arrivaleDate.setText(d_arrivalDate);
                departureDate.setText(d_departureDate);
                numOfRooms.setText(d_noRooms);
                info.setText(d_roomCategory+"\r\n"+d_roomType+"\r\n"+d_addinfo);

                display(d_roomType,Integer.parseInt(d_numDays), d_addinfo, Integer.parseInt(d_noRooms),d_roomCategory);

                String connectQuery2 = "SELECT currentAvailability FROM hotelrooms WHERE roomType='"+d_roomType+"' AND rooms='"+d_roomCategory+"'";
                Statement statement2 = connectDB.createStatement();
                ResultSet rs2 = statement1.executeQuery(connectQuery2);

                if (rs2.next()) {
                    int numOfRooms = Integer.parseInt(rs2.getString("currentAvailability"));
                    int newNumOfRooms = numOfRooms - Integer.parseInt(d_noRooms);

                    String sql = "UPDATE hotelrooms SET currentAvailability ='"+newNumOfRooms+"' WHERE roomType='"+d_roomType+"' AND rooms='"+d_roomCategory+"'";
                    preparedStatement = connectDB.prepareStatement(sql);
                    preparedStatement.executeUpdate(sql);

                }

                String connectQuery3 = "SELECT count(*) from guestbill";
                Statement statement3 = connectDB.createStatement();
                ResultSet rs3 = statement3.executeQuery(connectQuery3);
                rs3.next();
                int count = rs3.getInt(1);

                invoice.setText(String.valueOf(count+1));
            }


        }catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }

    }


    //Room Price Calculation
    public void display(String roomType,int numDays, String addinfo, int noRooms, String roomCategory) {


        try {

            double roomAmount = 0;

            String connectQuery2 = "SELECT * FROM hotelrooms WHERE roomType='"+roomType+"' AND rooms='"+roomCategory+"'";
            Statement statement2 = connectDB.createStatement();
            ResultSet rs2 = statement2.executeQuery(connectQuery2);

            if (rs2.next()) {
                String pn = rs2.getString("perNight");
                String wb = rs2.getString("withBreakfast");
                String hb = rs2.getString("hBoard");
                String fb = rs2.getString("fBoard");
                String ca = rs2.getString("currentAvailability");

                if (addinfo.equals("perNight")){
                    roomAmount = Double.parseDouble(pn);

                }else if(addinfo.equals("withBreakfast")){
                    roomAmount = Double.parseDouble(wb);

                }else if(addinfo.equals("hBoard")){
                    roomAmount = Double.parseDouble(hb);

                }else if(addinfo.equals("fBoard")){
                    roomAmount = Double.parseDouble(fb);
                }

                totalBill.setText(String.valueOf(roomAmount*noRooms*numDays));

            }

        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }


    }


    //Finding the Balance Amount
    @FXML
    void balButton(ActionEvent event) {


        if(Double.parseDouble(cashGiven.getText())<Double.parseDouble(totalBill.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Cash given is less than the total bill");

            if (alert.showAndWait().get() == ButtonType.OK){
            }
        }else {
            double balance = Double.parseDouble(cashGiven.getText())-Double.parseDouble(totalBill.getText());
            bal.setText(String.valueOf(balance));
        }

    }

    public void pdfCreate(){

        //var doc = new Document();
        try {

            String dest = "C:\\Users\\Lahiru\\Desktop\\GuestInvoice"+id+".pdf";
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdfDoc = new PdfDocument(writer);
            // PdfDocument pdfDoc = new PdfDocument(new PdfWriter("outFileName.pdf"));
            Document doc = new Document(pdfDoc);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            Table table1 = new Table(new float[]{450,150 })
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Guest Invoice Number : "+id)))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph(dtf.format(now))));

            doc.add(table1);

            doc.add(new Paragraph("\n\n"));

            Table table2 = new Table(new float[]{200, 250})
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph("  ")))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph("__THE LUXURY HOTEL__")));


            doc.add(table2);

            doc.add(new Paragraph("\n\n"));

            Table table3 = new Table(new float[]{200, 250})
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph("")))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Guest Invoice")));


            doc.add(table3);

            doc.add(new Paragraph("\n\n"));


            Table table4 = new Table(new float[]{160, 150})
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Customer Name ")))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph(": "+guestName.getText())))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Mobile Number ")))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph(": "+mNumber)))

                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Email ")))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph(": "+email.getText())))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Arrival Date ")))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph(": "+arrivaleDate.getText())))

                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Departure Date ")))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph(": "+departureDate.getText())))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Country ")))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph(": "+country)))

                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Room Type ")))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph(": "+d_roomType)))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Room Category ")))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph(": "+d_roomCategory)))

                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph("No Of Rooms ")))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph(": "+d_noRooms)))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Special Requirements ")))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph(": "+sRequirement)))

                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Additional Info ")))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph(": "+addInfo)));



            doc.add(table4);

            doc.add(new Paragraph("\n\n"));


            Table table5 = new Table(new float[]{100,150})
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Total Bill ")))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph(": "+totalBill.getText())))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Cash Given ")))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph(": "+cashGiven.getText())))

                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Balance ")))
                    .addCell(new com.itextpdf.layout.element.Cell().setBorder(Border.NO_BORDER).add(new Paragraph(": "+bal.getText())));


            doc.add(table5);


            doc.add(new Paragraph("\n\n"));



            Table table6 = new Table(new float[]{200, 150})
                    .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("  ")))
                    .addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph("Thank You Come Again !!!")));


            doc.add(table6);


            doc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }




        //Saving to the database
    public void print(ActionEvent event) {
        if (!totalBill.getText().isBlank() && !cashGiven.getText().isBlank() && !bal.getText().isBlank()) {
            try{

                    Session session = SESSION_FACTORY.openSession();
                    GuestBill guestBill = new GuestBill();
                    guestBill.setGuestID(Integer.parseInt(id));
                    guestBill.setBillAmount(Double.parseDouble(totalBill.getText()));
                    guestBill.setBalance(Double.parseDouble(bal.getText()));
                    guestBill.setCashGiven(Double.parseDouble(cashGiven.getText()));

                    session.save(guestBill);
                    session.close();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Bill generated successfully");


                    if (alert.showAndWait().get() == ButtonType.OK){
                        pdfCreate();


                        try{

                            File file = new File("C:\\Users\\Lahiru\\Desktop\\GuestInvoice"+id+".pdf");
                            if (file.exists()){
                                if (Desktop.isDesktopSupported()){
                                    Desktop.getDesktop().open(file);
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
                                    FxmlLoader.changeScene(event,"GuestRegistration.fxml");
                                }
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }



                        FxmlLoader.changeScene(event,"GuestRegistration.fxml");


                    }


            }catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Fill all the fields");


            if (alert.showAndWait().get() == ButtonType.OK){
                FxmlLoader.changeScene(event,"GuestReceipt.fxml");
            }

        }


    }

}
