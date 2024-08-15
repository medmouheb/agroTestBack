package com.agrotech.api.controller;

import com.agrotech.api.Repository.FileRepository;
import com.agrotech.api.Repository.MvtStkRepository;
import com.agrotech.api.Repository.UserRepository;
import com.agrotech.api.dto.DevisDto;
import com.agrotech.api.model.DevisProduct;
import com.agrotech.api.model.FileDocument;
import com.agrotech.api.model.MvtStk;
import com.agrotech.api.model.User;
import com.agrotech.api.services.PdfService;
import com.agrotech.api.services.impl.UserService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RestController
@RequestMapping("/pdfgenerate")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  MvtStkRepository mvtStkRepository;


    @PostMapping("/download-pdf")
    public  ResponseEntity<?>  downloadPdf(@RequestBody DevisDto dd) throws IOException, DocumentException {
        Context context = new Context();
        User u=userRepository.findByUsername(dd.getFarmer()).get();
        context.setVariable("logo","http://localhost:8080/images/"+u.getAvatar());
        context.setVariable("signature","http://localhost:8080/images/"+u.getSignature());
        context.setVariable("name",dd.getFarmer());
        context.setVariable("telephone",u.getNumeroTelephone());
        context.setVariable("mail",u.getEmail());
        context.setVariable("region",u.getRegion());
        context.setVariable("customerName",dd.getBuyer().getName());
        context.setVariable("customerPhone",dd.getBuyer().getPhone());
        context.setVariable("customerEmail",dd.getBuyer().getEmail());
        context.setVariable("customerAddress",dd.getBuyer().getAddress());
        context.setVariable("products",dd.getProduits());
        context.setVariable("availableTo",dd.getAvailableTo());
        float totalProductPrice =0;
        float totalTvaPrice =0;
        float totalDiscountPrice =0;
        float totalPrice =0;
        for (DevisProduct p : dd.getProduits() ) {
            totalProductPrice+=  p.getProduit().getPrixUnitaireHt().floatValue() * p.getQte();
            totalTvaPrice+=  p.getProduit().getPrixUnitaireHt().floatValue() * p.getQte() *( (float) p.getTax().getPercentage() /100) ;
            totalDiscountPrice+=  p.getProduit().getPrixUnitaireHt().floatValue() * p.getQte() *(  p.getRemise() /100) ;
            totalPrice+=totalProductPrice+totalTvaPrice-totalDiscountPrice;
        }
        context.setVariable("totalProductPrice",totalProductPrice);
        context.setVariable("totalTvaPrice",totalTvaPrice);
        context.setVariable("totalDiscountPrice",totalDiscountPrice);
        context.setVariable("totalPrice",totalPrice);

        byte[] pdfBytes = pdfService.generatePdf("template", context);
        FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName("devis "+dd.getCode()+ " "  +dd.getFarmer()+".pdf");
        fileDocument.setContentType("application/pdf");
        fileDocument.setDocumentType("pdf");
        fileDocument.setData(pdfBytes);
        fileRepository.save(fileDocument);
        return new ResponseEntity<>(fileDocument.getId(), HttpStatus.OK);

    }

    class FinancialData1{
        private String mType;
        private String mDate;
        private int initialQuantity;

        private int newQuantity;
        private String product;
        private String warehouse;

        public FinancialData1(String mType, String mDate, int initialQuantity, int newQuantity, String product, String warehouse) {
            this.mType = mType;
            this.mDate = mDate;
            this.initialQuantity = initialQuantity;
            this.newQuantity = newQuantity;
            this.product = product;
            this.warehouse = warehouse;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getWarehouse() {
            return warehouse;
        }

        public void setWarehouse(String warehouse) {
            this.warehouse = warehouse;
        }

        public String getmType() {
            return mType;
        }

        public void setmType(String mType) {
            this.mType = mType;
        }

        public String getmDate() {
            return mDate;
        }

        public void setmDate(String mDate) {
            this.mDate = mDate;
        }

        public int getInitialQuantity() {
            return initialQuantity;
        }

        public void setInitialQuantity(int initialQuantity) {
            this.initialQuantity = initialQuantity;
        }

        public int getNewQuantity() {
            return newQuantity;
        }

        public void setNewQuantity(int newQuantity) {
            this.newQuantity = newQuantity;
        }

    }

    class FinancialData {
        private String quarter;
        private int revenue;
        private int expenses;
        private int profit;

        public FinancialData(String quarter, int revenue, int expenses, int profit) {
            this.quarter = quarter;
            this.revenue = revenue;
            this.expenses = expenses;
            this.profit = profit;
        }

        public String getQuarter() {
            return quarter;
        }

        public void setQuarter(String quarter) {
            this.quarter = quarter;
        }

        public int getRevenue() {
            return revenue;
        }

        public void setRevenue(int revenue) {
            this.revenue = revenue;
        }

        public int getExpenses() {
            return expenses;
        }

        public void setExpenses(int expenses) {
            this.expenses = expenses;
        }

        public int getProfit() {
            return profit;
        }

        public void setProfit(int profit) {
            this.profit = profit;
        }

        // Constructors, getters and setters
    }
    @PostMapping("/generatereport")
    public  ResponseEntity<?>  generateRapport(@RequestBody DevisDto dd) throws IOException, DocumentException {
        Context context = new Context();

        List<FinancialData> financialData = List.of(
                new FinancialData("Q1", 50000, 30000, 20000),
                new FinancialData("Q2", 60000, 35000, 25000),
                new FinancialData("Q3", 70000, 40000, 30000),
                new FinancialData("Q4", 80000, 45000, 35000)
        );

        int totalRevenue = financialData.stream().mapToInt(FinancialData::getRevenue).sum();
        int totalProfit = financialData.stream().mapToInt(FinancialData::getProfit).sum();
        double yearOverYearGrowth = ((double) (financialData.get(3).getRevenue() - financialData.get(0).getRevenue()) / financialData.get(0).getRevenue()) * 100;

        context.setVariable("financialData",financialData);
        context.setVariable("totalRevenue",totalRevenue);
        context.setVariable("netProfit",totalProfit);
        context.setVariable("growth",String.format("%.2f%%", yearOverYearGrowth));
//        byte[] pdfBytes = pdfService.generatePdf("report", context);
        byte[] pdfBytes = pdfService.generatePdf2(1000, 50000,context); // Example statistics

        FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName("report "+ dd.getFarmer()+".pdf");
        fileDocument.setContentType("application/pdf");
        fileDocument.setDocumentType("pdf");
        fileDocument.setData(pdfBytes);
        fileRepository.save(fileDocument);
        return new ResponseEntity<>(fileDocument.getId(), HttpStatus.OK);

    }
    private String getusername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();

    }
    @PreAuthorize("hasRole('FARMER')")
    @GetMapping("/monthly-report")
    public ResponseEntity<?> generateMonthelyReport() throws DocumentException {
        Context context = new Context();

        List<FinancialData> financialData = List.of(
                new FinancialData("Q1", 50000, 30000, 20000),
                new FinancialData("Q2", 60000, 35000, 25000),
                new FinancialData("Q3", 70000, 40000, 30000),
                new FinancialData("Q4", 80000, 45000, 35000)
        );

        int totalRevenue = financialData.stream().mapToInt(FinancialData::getRevenue).sum();
        int totalProfit = financialData.stream().mapToInt(FinancialData::getProfit).sum();
        double yearOverYearGrowth = ((double) (financialData.get(3).getRevenue() - financialData.get(0).getRevenue()) / financialData.get(0).getRevenue()) * 100;

        context.setVariable("financialData",financialData);
        context.setVariable("totalRevenue",totalRevenue);
        context.setVariable("netProfit",totalProfit);
        context.setVariable("growth",String.format("%.2f%%", yearOverYearGrowth));
        byte[] pdfBytes = pdfService.generatePdf2(1000, 50000,context); // Example statistics

        FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName("report "+ getusername()+".pdf");
        fileDocument.setContentType("application/pdf");
        fileDocument.setDocumentType("pdf");
        fileDocument.setData(pdfBytes);
        fileRepository.save(fileDocument);
        return new ResponseEntity<>(fileDocument.getId(), HttpStatus.OK);
    }

    @GetMapping("/mvtstk")
    public ResponseEntity<?> getMvtStkByFarmerAndMonthAndYear(
            @RequestParam String farmer,
            @RequestParam int month,
            @RequestParam int year) throws DocumentException {
        List<String> products = new ArrayList<>();
        List<MvtStk> result=new ArrayList<>();
        List<List<Integer>> listOfLists = new ArrayList<>();


        for (int i = 0; i <= 12; i++) {
            int countEntre=0;
            int countSort=0;
            int countReturn=0;
            Calendar calendar = Calendar.getInstance();

            // Set the start date to the first day of the specified month and year
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, i);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Date startDate = calendar.getTime();

            // Set the end date to the first day of the next month
            calendar.add(Calendar.MONTH, i+1);
            Date endDate = calendar.getTime();



            List<MvtStk> l= mvtStkRepository.findByFarmerAndMonthAndYear(farmer, startDate, endDate);
            for(MvtStk l1:l){
                if(l1.getTypeMvt().equals("enter")){
                    countEntre+=l1.getQuantite().intValue();
                }
                if(l1.getTypeMvt().equals("exit")){
                    countSort+=l1.getQuantite().intValue();
                }
                if(l1.getTypeMvt().equals("return")){
                    countReturn+=l1.getQuantite().intValue();
                }


            }
            List<Integer> listTest = new ArrayList<>();
            listTest.add(countEntre);
            listTest.add(countSort);
            listTest.add(countReturn);
            listOfLists.add(listTest);
            result.addAll(l);
        }
        Context context = new Context();
        System.out.println(listOfLists);

        List<FinancialData1> financialData = new ArrayList<>();

        for (MvtStk mvtStk : result) {
            products.add(mvtStk.getStock().getProduct());
            financialData.add(new FinancialData1(mvtStk.getTypeMvt(),mvtStk.getDateMvt().toString(),(int)mvtStk.getStock().getQuantity(),mvtStk.getQuantite().intValue(),mvtStk.getStock().getProduct(),mvtStk.getStock().getWarehouse()));
        }


        context.setVariable("financialData",financialData);

//        byte[] pdfBytes = pdfService.generatePdf("report", context);
        Set<String> uniqueNumbersSet = new HashSet<>(products);

        byte[] pdfBytes = pdfService.generatePdf3(1000, 50000,context,result,listOfLists,new ArrayList<>(uniqueNumbersSet));

        FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName("report "+farmer+".pdf");
        fileDocument.setContentType("application/pdf");
        fileDocument.setDocumentType("pdf");
        fileDocument.setData(pdfBytes);
        fileRepository.save(fileDocument);
        return new ResponseEntity<>(fileDocument.getId(), HttpStatus.OK);
    }
}
