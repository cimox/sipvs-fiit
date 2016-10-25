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

                // silently create HTML file
                form.transformToHTML();

                System.out.println("XML created");
                return true;
            } catch (JsonParseException e) {
                System.out.println("Error parsing json: " + e.getMessage());
            }
            return false;
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
            String xmlContent = new Form().readFile("api/src/main/resources/public/data/transform.xslt", StandardCharsets.UTF_8);

            return xmlContent;
        });
    }
}
