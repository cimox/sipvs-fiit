package models;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXParseException;

public class Form {
	private List personBooks;
	private Person person;
    private Address personAddress;

    public Form(){

    }

	public Form(Person person) {
		this.person = person;
        this.personAddress = person.getAddress();
        this.personBooks = person.getBooks();
	}

	protected Document generateDocument() {
		Document documentXML = null;
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			documentXML = docBuilder.newDocument();
			Element rootElement = documentXML.createElement("rental");
			rootElement.setAttribute("xmlns", "http://some.uri.org");
			documentXML.appendChild(rootElement);

			// staff elements
			Element person = documentXML.createElement("person");
			rootElement.appendChild(person);

			// firstname elements
			Element firstname = documentXML.createElement("firstName");
			firstname.appendChild(documentXML.createTextNode(this.person.getFirstName()));
			person.appendChild(firstname);

			// lastname elements
			Element lastname = documentXML.createElement("lastName");
			lastname.appendChild(documentXML.createTextNode(this.person.getLastName()));
			person.appendChild(lastname);

			// email elements
			Element email = documentXML.createElement("email");
			email.appendChild(documentXML.createTextNode(this.person.getEmail()));
			person.appendChild(email);

			// notification elements
			Element notification = documentXML.createElement("notification");
			notification.appendChild(documentXML.createTextNode(this.person.getNotification()));
			person.appendChild(notification);

			Element address = documentXML.createElement("address");
			person.appendChild(address);
			Element street = documentXML.createElement("street");
			street.appendChild(documentXML.createTextNode(this.personAddress.getStreet()));
			address.appendChild(street);
            Element streetNumber= documentXML.createElement("streetNumber");
            streetNumber.appendChild(documentXML.createTextNode(this.personAddress.getStreetNumber()));
            address.appendChild(streetNumber);
			Element postalCode = documentXML.createElement("postalCode");
			postalCode.appendChild(documentXML.createTextNode(this.personAddress.getPostalCode()));
			address.appendChild(postalCode);
			Element city = documentXML.createElement("city");
			city.appendChild(documentXML.createTextNode(this.personAddress.getCity()));
			address.appendChild(city);
			Element country = documentXML.createElement("country");
			country.appendChild(documentXML.createTextNode(this.personAddress.getCountry()));
			address.appendChild(country);

			for (Book book : (LinkedList<Book>) this.personBooks) {
				Element bookElement = documentXML.createElement("book");
				bookElement.setAttribute("isbn", book.getBookISBN());
				rootElement.appendChild(bookElement);
				Element title = documentXML.createElement("title");
				title.appendChild(documentXML.createTextNode(book.getBookTitle()));
				bookElement.appendChild(title);
				Element dateFrom = documentXML.createElement("dateFrom");
				dateFrom.appendChild(documentXML.createTextNode(book.getDateFrom()));
				bookElement.appendChild(dateFrom);
				Element dateTo = documentXML.createElement("dateTo");
				dateTo.appendChild(documentXML.createTextNode(book.getDateTo()));
				bookElement.appendChild(dateTo);
			}

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}
		return documentXML;
	}

	public boolean createXML() {
        // write the content into xml file
        Document document = this.generateDocument();

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult("api/src/main/resources/public/data/form.xml");
            transformer.transform(source, result);

            return true;
        } catch (TransformerConfigurationException e1) {
            e1.printStackTrace();
        } catch (TransformerException e1) {
            e1.printStackTrace();
        }
        return false;
    }

	public void transformToHTML() {
		TransformerFactory factory = TransformerFactory.newInstance();
		Source xslt = new StreamSource(new File("api/src/main/resources/public/data/transform.xsl"));
		try {
			Transformer transformer = factory.newTransformer(xslt);
			Source text = new StreamSource(new File("api/src/main/resources/public/data/form.xml"));
			transformer.transform(text, new StreamResult(new File("api/src/main/resources/public/data/form.html")));
            System.out.println("HTML created");
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

    public String validateXML() {
        ValidatorXML validatorXML = new ValidatorXML();
        String validationResult = "Error";
        try {
            InputStream xml = new FileInputStream("api/src/main/resources/public/data/form.xml");
            InputStream xsd = new FileInputStream("api/src/main/resources/public/data/schema.xsd");
            validationResult = validatorXML.validateAgainstXSD(xml, xsd);
        } catch (FileNotFoundException err) {
            System.err.println("Error: " + err.getMessage());
            err.printStackTrace();
        }

        return validationResult;
    }

	public String readFile(String path, Charset encoding)  throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

    public class ValidatorXML {

        public ValidatorXML() {
        }

        public String validateAgainstXSD(InputStream xml, InputStream xsd) {
            try {
                SchemaFactory factory =
                        SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                Schema schema = factory.newSchema(new StreamSource(xsd));
                Validator validator = schema.newValidator();
                validator.validate(new StreamSource(xml));
                return "Valid!";
            } catch (SAXParseException ex) {
                return "Invalid format! Cause: " + ex.getMessage() + ". At line " + ex.getLineNumber() + " column " + ex.getColumnNumber();
            } catch (Exception e) {
                return e.getMessage();
            }
        }
    }
}
