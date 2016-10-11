package app;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import java.io.InputStream;


public class ValidatorXML {

    public ValidatorXML() {
    }

    public boolean validateAgainstXSD(InputStream xml, InputStream xsd) {
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));
            return true;
        } catch(Exception ex) {
            return false;
        }
    }
}
