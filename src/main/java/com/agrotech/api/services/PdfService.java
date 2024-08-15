package com.agrotech.api.services;
import com.agrotech.api.model.MvtStk;
import com.itextpdf.text.DocumentException;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;

@Service
public class PdfService {

    private final TemplateEngine templateEngine;

    public PdfService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public byte[] generatePdf(String templateName, Context context) throws DocumentException, IOException {
        String htmlContent = templateEngine.process(templateName, context);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();
        renderer.createPDF(outputStream);
        return outputStream.toByteArray();
    }

    public byte[] generatePdf2(int totalUsers, int totalSales, Context context) throws DocumentException {
        // Prepare the context with your data
        context.setVariable("totalUsers", totalUsers);
        context.setVariable("totalSales", totalSales);
        context.setVariable("chartBase64", generateChartBase64());

        // Render the HTML using Thymeleaf
        String htmlContent = templateEngine.process("report", context);

        // Convert HTML to PDF using Flying Saucer
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();

        // Create the PDF output stream
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            renderer.createPDF(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new DocumentException(e);
        }
    }

    public byte[] generatePdf3(int totalUsers, int totalSales, Context context , List<MvtStk> l,List<List<Integer>> listOfLists,List<String> products ) throws DocumentException {
        // Prepare the context with your data
        context.setVariable("totalUsers", totalUsers);
        context.setVariable("totalSales", totalSales);
        context.setVariable("chartBase64", generateChartBase64A(l,listOfLists,products));

        // Render the HTML using Thymeleaf
        String htmlContent = templateEngine.process("report", context);

        // Convert HTML to PDF using Flying Saucer
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();

        // Create the PDF output stream
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            renderer.createPDF(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new DocumentException(e);
        }
    }

    public String generateChartBase64() {
        // Create a dataset for the chart
        CategoryDataset dataset = createDataset();

        // Create a chart using the dataset
        JFreeChart barChart = ChartFactory.createBarChart(
                "Enter vs Exit vs Return",
                "Category",
                "Value",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Convert the chart to a BufferedImage
        BufferedImage chartImage = barChart.createBufferedImage(600, 400);

        // Convert the BufferedImage to a Base64 String
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ChartUtils.writeBufferedImageAsPNG(outputStream, chartImage);
            byte[] chartBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(chartBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String generateChartBase64A(List<MvtStk> l,List<List<Integer>> listOfLists,List<String> products ) {
        // Create a dataset for the chart
        CategoryDataset dataset = createDatasetA(l,listOfLists,products);

        // Create a chart using the dataset
        JFreeChart barChart = ChartFactory.createBarChart(
                "Enter vs Exit vs Return",
                "Category",
                "Value",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Convert the chart to a BufferedImage
        BufferedImage chartImage = barChart.createBufferedImage(600, 400);

        // Convert the BufferedImage to a Base64 String
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ChartUtils.writeBufferedImageAsPNG(outputStream, chartImage);
            byte[] chartBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(chartBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private CategoryDataset createDataset() {
        final String usersCategory = "Enter";
        final String salesCategory = "Exit";
        final String salesCategory1 = "Return";

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(1000, usersCategory, "Q1");
        dataset.addValue(1500, usersCategory, "Q2");
        dataset.addValue(2000, usersCategory, "Q3");

        dataset.addValue(50000, salesCategory, "Q1");
        dataset.addValue(75000, salesCategory, "Q2");
        dataset.addValue(100000, salesCategory, "Q3");

        dataset.addValue(60000, salesCategory1, "Q1");
        dataset.addValue(85000, salesCategory1, "Q2");
        dataset.addValue(120000, salesCategory1, "Q3");

        return dataset;
    }
    private CategoryDataset createDatasetA(List<MvtStk> l,List<List<Integer>> listOfLists,List<String> products) {
        final String usersCategory = "Enter";
        final String salesCategory = "Exit";
        final String salesCategory1 = "Return";
        String[] months = {
                "January", "February", "March", "April",
                "May", "June", "July", "August",
                "September", "October", "November", "December"
        };
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i=0;i<12;i++) {
            dataset.addValue(listOfLists.get(i).get(0), usersCategory, months[i]);
            dataset.addValue(listOfLists.get(i).get(1), salesCategory, months[i]);
            dataset.addValue(listOfLists.get(i).get(2), salesCategory1, months[i]);


        }

//        dataset.addValue(1000, usersCategory, "Q1");
//        dataset.addValue(1500, usersCategory, "Q2");
//        dataset.addValue(2000, usersCategory, "Q3");
//
//        dataset.addValue(50000, salesCategory, "Q1");
//        dataset.addValue(75000, salesCategory, "Q2");
//        dataset.addValue(100000, salesCategory, "Q3");
//
//        dataset.addValue(60000, salesCategory1, "Q1");
//        dataset.addValue(85000, salesCategory1, "Q2");
//        dataset.addValue(120000, salesCategory1, "Q3");

        return dataset;
    }
}
