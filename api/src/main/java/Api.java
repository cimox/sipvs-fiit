/**
 * Created by cimo on 10/21/2016.
 */

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Form;
import models.Person;
import org.bouncycastle.tsp.TSPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class Api {
    static boolean localhost = true;
    static Logger log = LoggerFactory.getLogger(Api.class);

    public static void main(final String[] args) throws IOException, ParserConfigurationException, TSPException, SAXException, TransformerException {
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
            String xsdContent = new Form().readFile("api/src/main/resources/public/data/schema.xsd", StandardCharsets.UTF_8);

            return xsdContent;
        });

        get("/xsl-content", (request, response) -> {
            String xslContent = new Form().readFile("api/src/main/resources/public/data/transform.xsl", StandardCharsets.UTF_8);

            return xslContent;
        });

        post("/save-signed", (request, response) -> {
            // read request content and same XML
            PrintWriter signedXML = new PrintWriter("api/src/main/resources/public/data/signed.xml");
            signedXML.println(request.body());
            signedXML.close();
            System.out.println("Signed XML saved");

            return "success";
        });

        get("/timestamp", (request, response) -> {
            System.out.println("Adding timestamp");
            String signedXML = new Form().readFile("api/src/main/resources/public/data/signed.xml", StandardCharsets.UTF_8);
            String timestamp = Utils.addTimestamp(signedXML);

            BufferedWriter out;
            try {
                out = new BufferedWriter(new FileWriter("api/src/main/resources/public/data/stamp.raw"));
                out.write(Base64Coder.decodeString(timestamp));
                out.close();

                out = new BufferedWriter(new FileWriter("api/src/main/resources/public/data/stamped.xml"));
                out.write(Utils.createStamped(signedXML, timestamp));
                out.close();

                out = new BufferedWriter(new FileWriter("api/src/main/resources/public/data/signed.xml"));
                out.write(Utils.createStamped(signedXML, timestamp));
                out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            return "OK";
        });
    }
}
