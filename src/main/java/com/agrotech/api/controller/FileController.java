package com.agrotech.api.controller;

import com.agrotech.api.Repository.*;
import com.agrotech.api.dto.DevisDto;
import com.agrotech.api.dto.FactureDto;
import com.agrotech.api.model.*;
import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.mail.internet.MimeMessage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import java.io.ByteArrayOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;


@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private TaxRepository taxRepository;

    @Autowired
    private BuyersRepository buyersRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    private String email;
    private String emailSubject;
    private String emailBody;



    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            FileDocument fileDocument = new FileDocument();
            fileDocument.setFileName(file.getOriginalFilename());
            fileDocument.setContentType(file.getContentType());


            fileDocument.setData(file.getBytes());
            fileRepository.save(fileDocument);
            return ResponseEntity.status(HttpStatus.CREATED).body(fileDocument.getId());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String id) {
        Optional<FileDocument> fileDocumentOptional = fileRepository.findById(id);
        if (fileDocumentOptional.isPresent()) {
            FileDocument fileDocument = fileDocumentOptional.get();
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + fileDocument.getFileName() + "\"")
                    .contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
                    .body(fileDocument.getData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PreAuthorize("hasRole('FARMER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    @GetMapping("/getall")
    public List<Map<String, String>>getAll() {
        AtomicReference<String> t= new AtomicReference<>("admin");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        userDetails.getAuthorities().forEach(authority -> {
            if(authority.getAuthority().equals("ROLE_FARMER")){
                t.set("farmer");
            }else if(authority.getAuthority().equals("ROLE_ADMIN")){
                t.set("admin");
            }else if(authority.getAuthority().equals("ROLE_EMPLOYEE")){
                t.set("employee");
            }
        });
        if(t.get().equals("admin")){
            List<FileDocument> entities = fileRepository.findAll();
            return entities.stream()
                    .map(entity -> Map.of("id", entity.getId(), "fileName", entity.getFileName()))
                    .collect(Collectors.toList());
        }else if(t.get().equals("farmer")){
            List<FileDocument> entities = fileRepository.findAllByFarmerContainingIgnoreCase(userDetails.getUsername());
            return entities.stream()
                    .map(entity -> Map.of("id", entity.getId(), "fileName", entity.getFileName()))
                    .collect(Collectors.toList());
        }else{
            User employee= userRepository.findByUsername(userDetails.getUsername()).get();
            List<FileDocument> entities = fileRepository.findAllByFarmerContainingIgnoreCase(employee.getFarmer());
            return entities.stream()
                    .map(entity -> Map.of("id", entity.getId(), "fileName", entity.getFileName()))
                    .collect(Collectors.toList());
        }

    }

    private byte[] generateItextPdf(JSONObject DataJSON) throws DocumentException, IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {


            Buyers buyer = buyersRepository.findById(DataJSON.getString("buyerId")).get();
            this.email=buyer.getEmail();
            this.emailBody=DataJSON.getString("emailBody");
            this.emailSubject=DataJSON.getString("emailSubject");
            System.out.println("DataJSON1");

            JSONArray products = DataJSON.getJSONArray("products");
            Document document = new Document();
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

            // Left side: Logo and text
            PdfPTable table = new PdfPTable(2);
            PdfPCell cellOne = new PdfPCell();
            // Add image at the top left with size 300x300 pixels
            Path imagePath = Paths.get(ClassLoader.getSystemResource("images/SBI.png").toURI());
            Image img = Image.getInstance(imagePath.toAbsolutePath().toString());
            img.scaleAbsolute(100, 100);


            cellOne.addElement(img);
            Font activityFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 10);
            Paragraph textActivity= new Paragraph("service informatique \n \n",activityFont);

            Font textFont = FontFactory.getFont(FontFactory.COURIER, 8);
            Paragraph textParagraph = new Paragraph("Tunis 12,Carthage salambo \n(+216) 28070378 \ncontact@sierrabravointelligence.com \nsierrabravointelligence.com", textFont);
            cellOne.addElement(textActivity);

            cellOne.addElement(textParagraph);

            PdfPCell cellTwo = new PdfPCell();

            cellOne.setBorder(Rectangle.NO_BORDER);

            cellTwo.setBorder(Rectangle.NO_BORDER);


            PdfPTable table1 = new PdfPTable(1);
            table1.setTotalWidth(280);
            table1.setLockedWidth(true);

            PdfPCell cellDevisContainer = new PdfPCell();
            cellDevisContainer.setBackgroundColor(new BaseColor(255,220,0));

            PdfPTable UpperDivTable =new PdfPTable(2);



            PdfPCell divCell1=new PdfPCell();

            Font DevisContainerFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 26);

            Paragraph textDevis= new Paragraph(DataJSON.getString("documentType").toUpperCase(),DevisContainerFont);
            divCell1.setBorder(Rectangle.NO_BORDER);
            divCell1.addElement(textDevis);

            PdfPCell divCell2=new PdfPCell();
            divCell2.setBorder(Rectangle.NO_BORDER);

            Font DevisNumberContainerFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 16);

            Paragraph textNumberDevis= new Paragraph("n°: "+DataJSON.getString("Code"),DevisNumberContainerFont);
            divCell2.addElement(textNumberDevis);

            UpperDivTable.addCell(divCell1);

            UpperDivTable.addCell(divCell2);

            cellDevisContainer.addElement(UpperDivTable);
            PdfPTable bottomDivTable =new PdfPTable(1);

            PdfPCell divCell3=new PdfPCell();

            Font DevisBottomContainerFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 16);

            Paragraph textDevisBottom= new Paragraph("Date : "+getCurrentDate(),DevisBottomContainerFont);
            divCell3.setBorder(Rectangle.NO_BORDER);
            divCell3.addElement(textDevisBottom);

            bottomDivTable.addCell(divCell3);

            cellDevisContainer.addElement(bottomDivTable);


            table1.addCell(cellDevisContainer);




            // Set border properties for the box


            // Add the text to cellTwo
            cellTwo.addElement(table1);


            PdfPTable clientTable= new PdfPTable(1);
            clientTable.setTotalWidth(280);
            clientTable.setLockedWidth(true);
            PdfPCell clientCell= new PdfPCell();
            clientCell.setBorder(Rectangle.NO_BORDER);
            Font bigTextClientFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 14);
            Paragraph bigTextClienBottom= new Paragraph("\n     "+buyer.getName()+"\n",bigTextClientFont);
            clientCell.addElement(bigTextClienBottom);

            Font smallTextClientFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 10);
            Paragraph smallTextClienBottom= new Paragraph("         "+ buyer.getAddress() +"\n ",smallTextClientFont);
            clientCell.addElement(smallTextClienBottom);

            clientTable.addCell(clientCell);
            cellTwo.addElement(clientTable);

            table.addCell(cellOne);
            table.addCell(cellTwo);
            document.add(table);

            Paragraph beforetableText= new Paragraph("\n\nintitulé : "+ DataJSON.getString("description") +"\n\n",DevisBottomContainerFont);


            document.add(beforetableText);


            PdfPTable tableBig = new PdfPTable(5);
            tableBig.setWidthPercentage(100);
            float[] columnWidths = {10, 50, 6 ,17,17};
            tableBig.setWidths(columnWidths);
            // Add table headers
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            addTableCell(tableBig, "Qté", headerFont);
            addTableCell(tableBig, "désignation", headerFont);
            addTableCell(tableBig, "tva", headerFont);
            addTableCell(tableBig, "Prix Unit.", headerFont);
            addTableCell(tableBig, "Total HT", headerFont);

            // Add dummy data (3 rows)
            for (int i = 0; i <products.length(); i++) {
                JSONObject productLigne = new JSONObject(products.getString(i));
                Produit p= produitRepository.findById(productLigne.getString("id")).get();

                addTableCell(tableBig, productLigne.getString("qte"));
                addTableCell(tableBig, p.getName());
                addTableCell(tableBig, ""+(i+1));
                addTableCell(tableBig, ""+p.getPrixUnitaireHt());
                addTableCell(tableBig, ""+(Integer.parseInt(productLigne.getString("qte"))   * p.getPrixUnitaireHt().floatValue()    ));

            }

            // Add the table to the document
            document.add(tableBig);
            Paragraph BreakerText= new Paragraph("\n\n\n\n",DevisBottomContainerFont);
            document.add(BreakerText);

            PdfPTable tableDevisTotal = new PdfPTable(3);
            tableDevisTotal.setWidthPercentage(100);
            float[] columnWidths1 = {50,35,15};
            tableDevisTotal.setWidths(columnWidths1);

            addTableCell1(tableDevisTotal, DataJSON.getString("documentType")+"en euros");

            float total=0;


            for (int i = 0; i <products.length(); i++) {
                JSONObject productLigne = new JSONObject(products.getString(i));
                Produit p= produitRepository.findById(productLigne.getString("id")).get();
                float productPrice=0;
                Tax t= taxRepository.findById(productLigne.getString("tva")).get();
                float taxPrice=0;
                productPrice=+(Integer.parseInt(productLigne.getString("qte"))   * p.getPrixUnitaireHt().floatValue()    );
                taxPrice=(100+t.getPercentage())*productPrice;
                total= total+productPrice+taxPrice;
                if(i==0){
                    addTableCell1(tableDevisTotal, "Total HT");
                    addTableCell1(tableDevisTotal, ""+productPrice);
                }else{
                    addTableCell1(tableDevisTotal, "");
                    addTableCell1(tableDevisTotal, "Total HT");
                    addTableCell1(tableDevisTotal, ""+productPrice);
                }
                addTableCell1(tableDevisTotal, "");
                addTableCell1(tableDevisTotal, "Tva("+(i+1)+") "+t.getPercentage()+"%");
                addTableCell1(tableDevisTotal, ""+taxPrice);
            }

            try {
                boolean hasShipping =Boolean.parseBoolean(DataJSON.getString("hasShipping"));
                if(hasShipping){
                    addTableCell1(tableDevisTotal, "");
                    addTableCell1(tableDevisTotal, "Shipping "+DataJSON.getString("shippingMethod"));
                    addTableCell1(tableDevisTotal, DataJSON.getString("shippingPrice"));
                }
            }catch(Exception e){
                System.out.println(e.toString());
            }




            addTableCell1(tableDevisTotal, "");
            addTableCell1(tableDevisTotal, "Total TTC");
            addTableCell1(tableDevisTotal, ""+total);

            document.add(tableDevisTotal);

            try {
                boolean hasShipping =Boolean.parseBoolean(DataJSON.getString("hasPaymentMethod"));
                if(hasShipping){
                    Font RolesTextFont2 = new Font(Font.FontFamily.HELVETICA, 10);
                    Paragraph RolesText2= new Paragraph("Payment via "+ DataJSON.getString("PayingMethod")   +" in "+  DataJSON.getString("InstallmentsNumber") +" installments",RolesTextFont2);
                    document.add(RolesText2);
                }
            }catch(Exception e){
                System.out.println(e.toString());
            }
            Font RolesTextFont = new Font(Font.FontFamily.HELVETICA, 10);
            Paragraph RolesText= new Paragraph("Validité du "+DataJSON.getString("documentType")+": 3 mois\nConditions de règlement: 40% à la commande, le solde à la livraison\nNous restons à votre disposition pour toute information complémentaire.\nCordialement,\nSi ce devis vous convient, veuillez nous le retourner signé précédé de la mention :\n\"BON POUR ACCORD ET EXECUTION DES TRAVAUX\"\n\n\n",RolesTextFont);
            document.add(RolesText);

            PdfPTable tablesignatureTotal = new PdfPTable(3);
            tableDevisTotal.setWidthPercentage(100);
            float[] columnWidths2 = {35,30,35};
            tablesignatureTotal.setWidths(columnWidths2);

            addTableCell1(tablesignatureTotal, "");
            Path image1Path = Paths.get(ClassLoader.getSystemResource("images/signature.jpg").toURI());
            Image img1 = Image.getInstance(image1Path.toAbsolutePath().toString());
            img1.scaleAbsolute(100, 100);
            PdfPCell cell = new PdfPCell();
            Font RolesTextFont1 = new Font(Font.FontFamily.HELVETICA, 10);
            cell.setBorder(Rectangle.NO_BORDER);

            Paragraph signatureText= new Paragraph("        signateur",RolesTextFont1);
            cell.addElement(signatureText);

            cell.addElement(img1);

            tablesignatureTotal.addCell(cell);

            addTableCell1(tablesignatureTotal, "");

            document.add(tablesignatureTotal);

            Paragraph FooterText= new Paragraph("\n\n\n\n\nSARL au capital de 7000 - N° Siret 210.896.764 00015 RCS Nantes\nCode APE 947A - N° TVA Intracom. FR 7785896764000\nBanque Postale RIB: 20042 00001 5740054W020 44",RolesTextFont1);
            FooterText.setAlignment(Element.ALIGN_CENTER);


            document.add(FooterText);
            document.close();


            return byteArrayOutputStream.toByteArray();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }


    private byte[] generateItextPdf1(DevisDto d) throws DocumentException, IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            System.out.println("aaa");

            Buyers buyer = d.getBuyer();
            this.email=buyer.getEmail();
            this.emailBody=d.getDescription();
            this.emailSubject="devis to  "+buyer.getName();
            System.out.println("DataJSON1");

            List<DevisProduct> products = d.getProduits();
            Document document = new Document();
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

            // Left side: Logo and text
            PdfPTable table = new PdfPTable(2);
            PdfPCell cellOne = new PdfPCell();
            // Add image at the top left with size 300x300 pixels
            Path imagePath = Paths.get(ClassLoader.getSystemResource("images/SBI.png").toURI());
            Image img = Image.getInstance(imagePath.toAbsolutePath().toString());
            img.scaleAbsolute(100, 100);

            System.out.println("bbb");

            cellOne.addElement(img);
            Font activityFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 10);
            Paragraph textActivity= new Paragraph("service informatique \n \n",activityFont);

            Font textFont = FontFactory.getFont(FontFactory.COURIER, 8);
            Paragraph textParagraph = new Paragraph("Tunis 12,Carthage salambo \n(+216) 28070378 \ncontact@sierrabravointelligence.com \nsierrabravointelligence.com", textFont);
            cellOne.addElement(textActivity);

            cellOne.addElement(textParagraph);

            PdfPCell cellTwo = new PdfPCell();

            cellOne.setBorder(Rectangle.NO_BORDER);

            cellTwo.setBorder(Rectangle.NO_BORDER);


            PdfPTable table1 = new PdfPTable(1);
            table1.setTotalWidth(280);
            table1.setLockedWidth(true);

            PdfPCell cellDevisContainer = new PdfPCell();
            cellDevisContainer.setBackgroundColor(new BaseColor(255,220,0));

            PdfPTable UpperDivTable =new PdfPTable(2);



            PdfPCell divCell1=new PdfPCell();

            Font DevisContainerFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 26);

            Paragraph textDevis= new Paragraph("Devis",DevisContainerFont);
            divCell1.setBorder(Rectangle.NO_BORDER);
            divCell1.addElement(textDevis);

            PdfPCell divCell2=new PdfPCell();
            divCell2.setBorder(Rectangle.NO_BORDER);
            System.out.println("cc");

            Font DevisNumberContainerFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 16);

            Paragraph textNumberDevis= new Paragraph("n°: "+d.getCode(),DevisNumberContainerFont);
            divCell2.addElement(textNumberDevis);

            UpperDivTable.addCell(divCell1);

            UpperDivTable.addCell(divCell2);

            cellDevisContainer.addElement(UpperDivTable);
            PdfPTable bottomDivTable =new PdfPTable(1);

            PdfPCell divCell3=new PdfPCell();

            Font DevisBottomContainerFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 16);
            System.out.println("gg");

            Paragraph textDevisBottom= new Paragraph("Date : "+getCurrentDate(),DevisBottomContainerFont);
            divCell3.setBorder(Rectangle.NO_BORDER);
            divCell3.addElement(textDevisBottom);

            bottomDivTable.addCell(divCell3);

            cellDevisContainer.addElement(bottomDivTable);


            table1.addCell(cellDevisContainer);




            // Set border properties for the box


            // Add the text to cellTwo
            cellTwo.addElement(table1);


            PdfPTable clientTable= new PdfPTable(1);
            clientTable.setTotalWidth(280);
            clientTable.setLockedWidth(true);
            PdfPCell clientCell= new PdfPCell();
            clientCell.setBorder(Rectangle.NO_BORDER);
            Font bigTextClientFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 14);
            Paragraph bigTextClienBottom= new Paragraph("\n     "+buyer.getName()+"\n",bigTextClientFont);
            clientCell.addElement(bigTextClienBottom);

            Font smallTextClientFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 10);
            Paragraph smallTextClienBottom= new Paragraph("         "+ buyer.getAddress() +"\n ",smallTextClientFont);
            clientCell.addElement(smallTextClienBottom);

            clientTable.addCell(clientCell);
            cellTwo.addElement(clientTable);

            table.addCell(cellOne);
            table.addCell(cellTwo);
            document.add(table);

            Paragraph beforetableText= new Paragraph("\n\nintitulé : "+ d.getDescription() +"\n\n",DevisBottomContainerFont);

            System.out.println("ppp");

            document.add(beforetableText);


            PdfPTable tableBig = new PdfPTable(5);
            tableBig.setWidthPercentage(100);
            float[] columnWidths = {10, 50, 6 ,17,17};
            tableBig.setWidths(columnWidths);
            // Add table headers
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            addTableCell(tableBig, "Qté", headerFont);
            addTableCell(tableBig, "désignation", headerFont);
            addTableCell(tableBig, "tva", headerFont);
            addTableCell(tableBig, "Prix Unit.", headerFont);
            addTableCell(tableBig, "Total HT", headerFont);

            // Add dummy data (3 rows)
            for (int i = 0; i <products.size(); i++) {
//                JSONObject productLigne = new JSONObject(products.getString(i));
                Produit p= products.get(i).getProduit();
                System.out.println(products.get(i).toString());

                addTableCell(tableBig, ""+products.get(i).getQte());
                System.out.println("xxx");

                addTableCell(tableBig, p.getName());
                addTableCell(tableBig, ""+(i+1));
                addTableCell(tableBig, ""+p.getPrixUnitaireHt());
                addTableCell(tableBig, ""+(products.get(i).getQte()   * p.getPrixUnitaireHt().floatValue()    ));

            }

            // Add the table to the document
            document.add(tableBig);
            Paragraph BreakerText= new Paragraph("\n\n\n\n",DevisBottomContainerFont);
            document.add(BreakerText);
            System.out.println("mmmm");

            PdfPTable tableDevisTotal = new PdfPTable(3);
            tableDevisTotal.setWidthPercentage(100);
            float[] columnWidths1 = {50,35,15};
            tableDevisTotal.setWidths(columnWidths1);

            addTableCell1(tableDevisTotal, "Devis "+"en euros");

            float total=0;


            for (int i = 0; i <products.size(); i++) {
//                JSONObject productLigne = new JSONObject(products.getString(i));
                Produit p= products.get(i).getProduit();
                float productPrice=0;
                Tax t= products.get(i).getTax();
                float taxPrice=0;
                productPrice=+(products.get(i).getQte()  * p.getPrixUnitaireHt().floatValue()    );
                taxPrice=(100+t.getPercentage())*productPrice;
                total= total+productPrice+taxPrice;
                if(i==0){
                    addTableCell1(tableDevisTotal, "Total HT");
                    addTableCell1(tableDevisTotal, ""+productPrice);
                }else{
                    addTableCell1(tableDevisTotal, "");
                    addTableCell1(tableDevisTotal, "Total HT");
                    addTableCell1(tableDevisTotal, ""+productPrice);
                }
                addTableCell1(tableDevisTotal, "");
                addTableCell1(tableDevisTotal, "Tva("+(i+1)+") "+t.getPercentage()+"%");
                addTableCell1(tableDevisTotal, ""+taxPrice);
            }

            try {

                addTableCell1(tableDevisTotal, "");
                addTableCell1(tableDevisTotal, "Shipping "+d.getShipMethods().getName());
                addTableCell1(tableDevisTotal, ""+d.getShippingPrice());

            }catch(Exception e){
                System.out.println(e.toString());
            }




            addTableCell1(tableDevisTotal, "");
            addTableCell1(tableDevisTotal, "Total TTC");
            addTableCell1(tableDevisTotal, ""+total);

            document.add(tableDevisTotal);

            try {

                Font RolesTextFont2 = new Font(Font.FontFamily.HELVETICA, 10);
                Paragraph RolesText2= new Paragraph("Payment via "+ d.getPaymentMethod().getName()  +" in "+  d.getInstallmentsNumber() +" installments",RolesTextFont2);
                document.add(RolesText2);

            }catch(Exception e){
                System.out.println(e.toString());
            }
            Font RolesTextFont = new Font(Font.FontFamily.HELVETICA, 10);
            Paragraph RolesText= new Paragraph("Validité du "+"devis"+": 3 mois\nConditions de règlement: 40% à la commande, le solde à la livraison\nNous restons à votre disposition pour toute information complémentaire.\nCordialement,\nSi ce devis vous convient, veuillez nous le retourner signé précédé de la mention :\n\"BON POUR ACCORD ET EXECUTION DES TRAVAUX\"\n\n\n",RolesTextFont);
            document.add(RolesText);

            PdfPTable tablesignatureTotal = new PdfPTable(3);
            tableDevisTotal.setWidthPercentage(100);
            float[] columnWidths2 = {35,30,35};
            tablesignatureTotal.setWidths(columnWidths2);

            addTableCell1(tablesignatureTotal, "");
            Path image1Path = Paths.get(ClassLoader.getSystemResource("images/signature.jpg").toURI());
            Image img1 = Image.getInstance(image1Path.toAbsolutePath().toString());
            img1.scaleAbsolute(100, 100);
            PdfPCell cell = new PdfPCell();
            Font RolesTextFont1 = new Font(Font.FontFamily.HELVETICA, 10);
            cell.setBorder(Rectangle.NO_BORDER);

            Paragraph signatureText= new Paragraph("        signateur",RolesTextFont1);
            cell.addElement(signatureText);

            cell.addElement(img1);

            tablesignatureTotal.addCell(cell);

            addTableCell1(tablesignatureTotal, "");

            document.add(tablesignatureTotal);

            Paragraph FooterText= new Paragraph("\n\n\n\n\nSARL au capital de 7000 - N° Siret 210.896.764 00015 RCS Nantes\nCode APE 947A - N° TVA Intracom. FR 7785896764000\nBanque Postale RIB: 20042 00001 5740054W020 44",RolesTextFont1);
            FooterText.setAlignment(Element.ALIGN_CENTER);


            document.add(FooterText);
            document.close();


            return byteArrayOutputStream.toByteArray();
        } catch (URISyntaxException e) {
            System.out.println("22");

            throw new RuntimeException(e);
        }


    }
    private byte[] generateItextPdf2(FactureDto d) throws DocumentException, IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            System.out.println("aaa");

            Buyers buyer = d.getBuyer();
            this.email=buyer.getEmail();
            this.emailBody=d.getDescription();
            this.emailSubject="facture to  "+buyer.getName();
            System.out.println("DataJSON1");

            List<DevisProduct> products = d.getProduits();
            Document document = new Document();
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

            // Left side: Logo and text
            PdfPTable table = new PdfPTable(2);
            PdfPCell cellOne = new PdfPCell();
            // Add image at the top left with size 300x300 pixels
            Path imagePath = Paths.get(ClassLoader.getSystemResource("images/SBI.png").toURI());
            Image img = Image.getInstance(imagePath.toAbsolutePath().toString());
            img.scaleAbsolute(100, 100);

            System.out.println("bbb");

            cellOne.addElement(img);
            Font activityFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 10);
            Paragraph textActivity= new Paragraph("service informatique \n \n",activityFont);

            Font textFont = FontFactory.getFont(FontFactory.COURIER, 8);
            Paragraph textParagraph = new Paragraph("Tunis 12,Carthage salambo \n(+216) 28070378 \ncontact@sierrabravointelligence.com \nsierrabravointelligence.com", textFont);
            cellOne.addElement(textActivity);

            cellOne.addElement(textParagraph);

            PdfPCell cellTwo = new PdfPCell();

            cellOne.setBorder(Rectangle.NO_BORDER);

            cellTwo.setBorder(Rectangle.NO_BORDER);


            PdfPTable table1 = new PdfPTable(1);
            table1.setTotalWidth(280);
            table1.setLockedWidth(true);

            PdfPCell cellDevisContainer = new PdfPCell();
            cellDevisContainer.setBackgroundColor(new BaseColor(255,220,0));

            PdfPTable UpperDivTable =new PdfPTable(2);



            PdfPCell divCell1=new PdfPCell();

            Font DevisContainerFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 26);

            Paragraph textDevis= new Paragraph("Facture",DevisContainerFont);
            divCell1.setBorder(Rectangle.NO_BORDER);
            divCell1.addElement(textDevis);

            PdfPCell divCell2=new PdfPCell();
            divCell2.setBorder(Rectangle.NO_BORDER);
            System.out.println("cc");

            Font DevisNumberContainerFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 16);

            Paragraph textNumberDevis= new Paragraph("n°: "+d.getCode(),DevisNumberContainerFont);
            divCell2.addElement(textNumberDevis);

            UpperDivTable.addCell(divCell1);

            UpperDivTable.addCell(divCell2);

            cellDevisContainer.addElement(UpperDivTable);
            PdfPTable bottomDivTable =new PdfPTable(1);

            PdfPCell divCell3=new PdfPCell();

            Font DevisBottomContainerFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 16);
            System.out.println("gg");

            Paragraph textDevisBottom= new Paragraph("Date : "+getCurrentDate(),DevisBottomContainerFont);
            divCell3.setBorder(Rectangle.NO_BORDER);
            divCell3.addElement(textDevisBottom);

            bottomDivTable.addCell(divCell3);

            cellDevisContainer.addElement(bottomDivTable);


            table1.addCell(cellDevisContainer);




            // Set border properties for the box


            // Add the text to cellTwo
            cellTwo.addElement(table1);


            PdfPTable clientTable= new PdfPTable(1);
            clientTable.setTotalWidth(280);
            clientTable.setLockedWidth(true);
            PdfPCell clientCell= new PdfPCell();
            clientCell.setBorder(Rectangle.NO_BORDER);
            Font bigTextClientFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 14);
            Paragraph bigTextClienBottom= new Paragraph("\n     "+buyer.getName()+"\n",bigTextClientFont);
            clientCell.addElement(bigTextClienBottom);

            Font smallTextClientFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 10);
            Paragraph smallTextClienBottom= new Paragraph("         "+ buyer.getAddress() +"\n ",smallTextClientFont);
            clientCell.addElement(smallTextClienBottom);

            clientTable.addCell(clientCell);
            cellTwo.addElement(clientTable);

            table.addCell(cellOne);
            table.addCell(cellTwo);
            document.add(table);

            Paragraph beforetableText= new Paragraph("\n\nintitulé : "+ d.getDescription() +"\n\n",DevisBottomContainerFont);

            System.out.println("ppp");

            document.add(beforetableText);


            PdfPTable tableBig = new PdfPTable(5);
            tableBig.setWidthPercentage(100);
            float[] columnWidths = {10, 50, 6 ,17,17};
            tableBig.setWidths(columnWidths);
            // Add table headers
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            addTableCell(tableBig, "Qté", headerFont);
            addTableCell(tableBig, "désignation", headerFont);
            addTableCell(tableBig, "tva", headerFont);
            addTableCell(tableBig, "Prix Unit.", headerFont);
            addTableCell(tableBig, "Total HT", headerFont);

            // Add dummy data (3 rows)
            for (int i = 0; i <products.size(); i++) {
//                JSONObject productLigne = new JSONObject(products.getString(i));
                Produit p= products.get(i).getProduit();
                System.out.println(products.get(i).toString());

                addTableCell(tableBig, ""+products.get(i).getQte());
                System.out.println("xxx");

                addTableCell(tableBig, p.getName());
                addTableCell(tableBig, ""+(i+1));
                addTableCell(tableBig, ""+p.getPrixUnitaireHt());
                addTableCell(tableBig, ""+(products.get(i).getQte()   * p.getPrixUnitaireHt().floatValue()    ));

            }

            // Add the table to the document
            document.add(tableBig);
            Paragraph BreakerText= new Paragraph("\n\n\n\n",DevisBottomContainerFont);
            document.add(BreakerText);
            System.out.println("mmmm");

            PdfPTable tableDevisTotal = new PdfPTable(3);
            tableDevisTotal.setWidthPercentage(100);
            float[] columnWidths1 = {50,35,15};
            tableDevisTotal.setWidths(columnWidths1);

            addTableCell1(tableDevisTotal, "Devis "+"en euros");

            float total=0;


            for (int i = 0; i <products.size(); i++) {
//                JSONObject productLigne = new JSONObject(products.getString(i));
                Produit p= products.get(i).getProduit();
                float productPrice=0;
                Tax t= products.get(i).getTax();
                float taxPrice=0;
                productPrice=+(products.get(i).getQte()  * p.getPrixUnitaireHt().floatValue()    );
                taxPrice=(100+t.getPercentage())*productPrice;
                total= total+productPrice+taxPrice;
                if(i==0){
                    addTableCell1(tableDevisTotal, "Total HT");
                    addTableCell1(tableDevisTotal, ""+productPrice);
                }else{
                    addTableCell1(tableDevisTotal, "");
                    addTableCell1(tableDevisTotal, "Total HT");
                    addTableCell1(tableDevisTotal, ""+productPrice);
                }
                addTableCell1(tableDevisTotal, "");
                addTableCell1(tableDevisTotal, "Tva("+(i+1)+") "+t.getPercentage()+"%");
                addTableCell1(tableDevisTotal, ""+taxPrice);
            }

            try {

                addTableCell1(tableDevisTotal, "");
                addTableCell1(tableDevisTotal, "Shipping "+d.getShipMethods().getName());
                addTableCell1(tableDevisTotal, ""+d.getShippingPrice());

            }catch(Exception e){
                System.out.println(e.toString());
            }




            addTableCell1(tableDevisTotal, "");
            addTableCell1(tableDevisTotal, "Total TTC");
            addTableCell1(tableDevisTotal, ""+total);

            document.add(tableDevisTotal);

            try {

                Font RolesTextFont2 = new Font(Font.FontFamily.HELVETICA, 10);
                Paragraph RolesText2= new Paragraph("Payment via "+ d.getPaymentMethod().getName()  +" in "+  d.getInstallmentsNumber() +" installments",RolesTextFont2);
                document.add(RolesText2);

            }catch(Exception e){
                System.out.println(e.toString());
            }
            Font RolesTextFont = new Font(Font.FontFamily.HELVETICA, 10);
            Paragraph RolesText= new Paragraph("Validité du "+"facture"+": 3 mois\nConditions de règlement: 40% à la commande, le solde à la livraison\nNous restons à votre disposition pour toute information complémentaire.\nCordialement,\nSi ce devis vous convient, veuillez nous le retourner signé précédé de la mention :\n\"BON POUR ACCORD ET EXECUTION DES TRAVAUX\"\n\n\n",RolesTextFont);
            document.add(RolesText);

            PdfPTable tablesignatureTotal = new PdfPTable(3);
            tableDevisTotal.setWidthPercentage(100);
            float[] columnWidths2 = {35,30,35};
            tablesignatureTotal.setWidths(columnWidths2);

            addTableCell1(tablesignatureTotal, "");
            Path image1Path = Paths.get(ClassLoader.getSystemResource("images/signature.jpg").toURI());
            Image img1 = Image.getInstance(image1Path.toAbsolutePath().toString());
            img1.scaleAbsolute(100, 100);
            PdfPCell cell = new PdfPCell();
            Font RolesTextFont1 = new Font(Font.FontFamily.HELVETICA, 10);
            cell.setBorder(Rectangle.NO_BORDER);

            Paragraph signatureText= new Paragraph("        signateur",RolesTextFont1);
            cell.addElement(signatureText);

            cell.addElement(img1);

            tablesignatureTotal.addCell(cell);

            addTableCell1(tablesignatureTotal, "");

            document.add(tablesignatureTotal);

            Paragraph FooterText= new Paragraph("\n\n\n\n\nSARL au capital de 7000 - N° Siret 210.896.764 00015 RCS Nantes\nCode APE 947A - N° TVA Intracom. FR 7785896764000\nBanque Postale RIB: 20042 00001 5740054W020 44",RolesTextFont1);
            FooterText.setAlignment(Element.ALIGN_CENTER);


            document.add(FooterText);
            document.close();


            return byteArrayOutputStream.toByteArray();
        } catch (URISyntaxException e) {
            System.out.println("22");

            throw new RuntimeException(e);
        }


    }


    private static void addTableCell(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }

    private static void addTableCell1(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
    }

    private static void addTableCell(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBackgroundColor(new BaseColor(205,120,0));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }
    private static String getCurrentDate() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Format the date using a specific pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
    }


    @PostMapping("/senddevis")
    public  ResponseEntity<?>  gettest(@RequestBody String reviewDto ) throws FileNotFoundException, DocumentException {
        try {
            JSONObject DataJSON = new JSONObject(reviewDto);
            byte[] pdfContent = generateItextPdf(DataJSON);
            FileDocument fileDocument = new FileDocument();
            fileDocument.setFileName(DataJSON.getString("documentType")+DataJSON.getString("Code")+".pdf");
            fileDocument.setContentType("application/pdf");
            fileDocument.setDocumentType(DataJSON.getString("documentType"));
            fileDocument.setData(pdfContent);
            fileRepository.save(fileDocument);
            sendActivateMAil(this.email, DataJSON.getString("documentType"), this.emailSubject,this.emailBody, fileDocument.getId());
            return new ResponseEntity<>(fileDocument.getId(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(500).body("Failed to generate and store PDF.");
        }
    }

    public String getatest(@RequestBody DevisDto d ) throws FileNotFoundException, DocumentException {
        try {
            byte[] pdfContent = generateItextPdf1(d);
            FileDocument fileDocument = new FileDocument();
            fileDocument.setFileName("devis"+d.getCode()+".pdf");
            fileDocument.setContentType("application/pdf");
            fileDocument.setDocumentType("devis");
            fileDocument.setData(pdfContent);
            fileRepository.save(fileDocument);
            sendActivateMAil(this.email, "devis", this.emailSubject,this.emailBody, fileDocument.getId());
            return fileDocument.getId();
        } catch (Exception e) {
            System.out.println("11");
            System.out.println(e.toString());
            return "Failed to generate and store PDF.";
        }
    }

    public String getatest1(@RequestBody FactureDto d ) throws FileNotFoundException, DocumentException {
        try {
            byte[] pdfContent = generateItextPdf2(d);
            FileDocument fileDocument = new FileDocument();
            fileDocument.setFileName("facture"+d.getCode()+".pdf");
            fileDocument.setContentType("application/pdf");
            fileDocument.setDocumentType("facture");
            fileDocument.setData(pdfContent);
            fileRepository.save(fileDocument);
            sendActivateMAil(this.email, "facture", this.emailSubject,this.emailBody, fileDocument.getId());
            return fileDocument.getId();
        } catch (Exception e) {
            System.out.println("11");
            System.out.println(e.toString());
            return "Failed to generate and store PDF.";
        }
    }


    public void sendActivateMAil(  String email ,String title,String subject, String paragraph , String fileId ) throws JSONException {





        String htmlBody="<body>\n" +
                "    <div style='background-color: antiquewhite; width: 95%;padding: 20px;'>\n" +
                "        <div style='display: flex; '>\n" +
                "            <div style='width:30%'    ><img style='width: 200px; height: 200px;' src='https://sierrabravointelligence.com/assets/images/SBI_w.png'></div>\n" +
                "            <div style='width:60%' ; padding-top: 2%;   ><h1 style='text-align: center;'> "+title+" </h1></div>\n" +
                "            <div style='width:30%'    ><img style='width: 200px; height: 200px;' src='https://cdn-icons-png.flaticon.com/512/3921/3921617.png'></div>\n" +
                "        </div>\n" +
                "\n" +
                "        <p style='text-align: justify;'>\n" + paragraph +
                "        </p>\n" +
                "\n" +
                "        <div style='background-color: antiquewhite; width: 100%; display: flex; '>";


        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {

                htmlBody+="<div style=' margin: 15px;'>\n" +
                        "                <h3 >" + fileRepository.findById(fileId).get().getFileName()+"</h3>\n" +
                        "              <div style='display : flex '>  <div style='width:30%'></div><a style='width:70%' href='http://localhost:8080/files/download/"+fileId+"' target='_blank'>\n" +
                        "                    <img src=\"https://cdn-icons-png.flaticon.com/512/9502/9502265.png\" style=\"width: 30px; height: 30px;\">\n" +
                        "                </a></div>\n" +
                        "            </div>";
                System.out.println(fileRepository.findById(fileId).get().getFileName());


            htmlBody+="</div>\n" +
                    "\n" +
                    " <div style='display:flex' > <div style='width:60%'></div>      <div style='width:40%'>\n" +
                    "            <ul >\n" +
                    "                <li>Tunis 12,Carthage salambo</li>\n" +
                    "                <li>Cité Layoune-Casablanca-Moroco</li>\n" +
                    "                <li>1, rue Julius et Ethel Rosenberg, immeuble SCENEO - Paris-France</li>\n" +
                    "                <li> (+33) 184252992</li>\n" +
                    "                <li> (+216) 28070378</li>\n" +
                    "                <li> (+212) 664156495</li>\n" +
                    "                <li> contact@sierrabravointelligence.com</li>\n" +
                    "              </ul>\n" +
                    "        </div>\n </div>\n" +
                    "\n" +
                    "\n" +
                    "    </div>\n" +
                    "\n" +
                    "</body>";
            helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());





            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);

            javaMailSender.send(message);
        } catch (jakarta.mail.MessagingException e) {
            e.printStackTrace();
        }
    }
}
