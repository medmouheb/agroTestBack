package com.agrotech.api.controller;

import com.agrotech.api.Repository.FileRepository;
import jakarta.mail.internet.MimeMessage;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import java.nio.charset.StandardCharsets;
import javax.mail.MessagingException;
import org.springframework.web.bind.annotation.RequestBody;
import org.json.JSONObject;


//@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RestController
@RequestMapping("/emailing")
@RequiredArgsConstructor
public class EmailController {


    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private FileRepository fileRepository;

    @PostMapping("")
    public void sendActivateMAil( @RequestBody String reviewDto ) throws JSONException {
        JSONObject DataJSON = new JSONObject(reviewDto);

        String email=DataJSON.getString("email");
        String title=DataJSON.getString("title");
        String paragraph=DataJSON.getString("paragraph");


        JSONArray array = DataJSON.getJSONArray("files");


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
            for (int i = 0; i < array.length(); i++) {
                htmlBody+="<div style=' margin: 15px;'>\n" +
                        "                <h3 >" + fileRepository.findById(array.getString(i)).get().getFileName()+"</h3>\n" +
                        "              <div style='display : flex '>  <div style='width:30%'></div><a style='width:70%' href='http://localhost:8080/files/download/"+array.getString(i)+"' target='_blank'>\n" +
                        "                    <img src=\"https://cdn-icons-png.flaticon.com/512/9502/9502265.png\" style=\"width: 30px; height: 30px;\">\n" +
                        "                </a></div>\n" +
                        "            </div>";
                System.out.println(fileRepository.findById(array.getString(i)).get().getFileName());

            }
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
            helper.setSubject("Activez votre compte !\n");
            helper.setText(htmlBody, true);

            javaMailSender.send(message);
        } catch (jakarta.mail.MessagingException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }



}
