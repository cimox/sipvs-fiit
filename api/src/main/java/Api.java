/**
 * Created by cimo on 10/21/2016.
 */

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Form;
import models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Api {
    static boolean localhost = true;
    static Logger log = LoggerFactory.getLogger(Api.class);

    public static void main(final String[] args) {
        System.gc();
        if (localhost) { // absolute (kinda) path to static files. DO NOT CHANGE THIS for development
            String projectDir = System.getProperty("user.dir");
            String staticDir = "/api/src/main/resources/public";
            staticFiles.externalLocation(projectDir + staticDir);
        } else {
            staticFiles.location("/public");
        }

        // index
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index");
        }, new ThymeleafTemplateEngine());

        // create XML
        post("/create", "application/json", (request, response) -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                Person person = mapper.readValue(request.body(), Person.class);

                // create XML file
                Form form = new Form(person);
                boolean result = form.createXML();
                if (result == true) {
                    // silently create HTML file
                    form.transformToHTML();
                }

                return true;
            } catch (JsonParseException e) {
                System.out.println("Error parsing json: " + e.getMessage());
            }
            return false;
        });

        // get form.xml
        get("/xml", (request, response) -> {
            response.type("application/octet-stream");
            response.header("Content-Disposition","attachment; filename=\"data/form.xml\"");
            String xmlContent = new Form().readFile("api/src/main/resources/public/data/form.xml", StandardCharsets.UTF_8);

            return xmlContent;
        });

        // get signed.xml
        get("/xml-signed", (request, response) -> {
            response.type("application/octet-stream");
            response.header("Content-Disposition","attachment; filename=\"data/signed.xml\"");
            String xmlContent = new Form().readFile("api/src/main/resources/public/data/signed.xml", StandardCharsets.UTF_8);

            return xmlContent;
        });

        // get form.html
        get("/html", (request, response) -> {
            response.type("application/octet-stream");
            response.header("Content-Disposition","attachment; filename=\"data/form.html\"");
            String htmlContent = new Form().readFile("api/src/main/resources/public/data/form.html", StandardCharsets.UTF_8);

            return htmlContent;
        });

        // validate XML
        get("/validate", (request, response) -> {
            String validationResult = new Form().validateXML();
            return validationResult;
        });

        get("/xml-content", (request, response) -> {
            String xmlContent = new Form().readFile("api/src/main/resources/public/data/form.xml", StandardCharsets.UTF_8);

            return xmlContent;
        });

        get("/xsd-content", (request, response) -> {
            String xmlContent = new Form().readFile("api/src/main/resources/public/data/schema.xsd", StandardCharsets.UTF_8);

            return xmlContent;
        });

        get("/xsl-content", (request, response) -> {
            String xmlContent = new Form().readFile("api/src/main/resources/public/data/transform.xsl", StandardCharsets.UTF_8);

            return xmlContent;
        });

        post("/save-signed", (request, response) -> {
            // read request content and same XML
            PrintWriter signedXML = new PrintWriter("api/src/main/resources/public/data/signed.xml");
            signedXML.println(request.body());
            signedXML.close();
            System.out.println("Signed XML saved");

            return "success";
        });
    }
}
