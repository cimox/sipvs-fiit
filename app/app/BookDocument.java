package app;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class BookDocument {
    String firstName;
    String lastName;
    String street;
    Integer streetNumber;
    String city;
    String postalCode;
    String state;
    String email;
    Integer notification;
    String bookTitle;
    Date dateFrom;
    Date dateTo;

    public BookDocument(String firstName, String lastName, String street, Integer streetNumber, String city, String postalCode, String state, String email, Integer notification, String bookTitle, Date dateFrom, Date dateTo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.postalCode = postalCode;
        this.state = state;
        this.email = email;
        this.notification = notification;
        this.bookTitle = bookTitle;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public BookDocument(String firstName, String lastName, String email, Integer notification) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.notification = notification;
    }

    //    <?xml version="1.0" encoding="UTF-8" standalone="no" ?>
//    <company>
//        <staff id="1">
//            <firstname>yong</firstname>
//            <lastname>mook kim</lastname>
//            <nickname>mkyong</nickname>
//            <salary>100000</salary>
//        </staff>
//    </company>

    public Document generateXML() {
    	Document doc = null;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("rental");
            doc.appendChild(rootElement);

            // staff elements
            Element person = doc.createElement("person");
            rootElement.appendChild(person);

            // set attribute to staff element -- atribut pre knihy
//            Attr attr = doc.createAttribute("id");
//            attr.setValue("1");
//            staff.setAttributeNode(attr);

            // shorten way
            // staff.setAttribute("id", "1");

            // firstname elements
            Element firstname = doc.createElement("firstName");
            firstname.appendChild(doc.createTextNode(this.firstName));
            person.appendChild(firstname);

            // lastname elements
            Element lastname = doc.createElement("lastName");
            lastname.appendChild(doc.createTextNode(this.lastName));
            person.appendChild(lastname);

            // email elements
            Element email = doc.createElement("email");
            email.appendChild(doc.createTextNode(this.email));
            person.appendChild(email);

            // notification elements
            Element notification = doc.createElement("notification");
            notification.appendChild(doc.createTextNode(this.notification.toString()));
            person.appendChild(notification);

            Element address = doc.createElement("address");
            person.appendChild(address);
            Element street = doc.createElement("street");
            street.appendChild(doc.createTextNode(this.street));
            address.appendChild(street);
            Element postalCode = doc.createElement("postalCode");
            postalCode.appendChild(doc.createTextNode(this.postalCode.toString()));
            address.appendChild(postalCode);
            Element city = doc.createElement("city");
            city.appendChild(doc.createTextNode(this.city));
            address.appendChild(city);
            Element country = doc.createElement("country");
            country.appendChild(doc.createTextNode(this.state));
            address.appendChild(country);

            Element book = doc.createElement("book");
            rootElement.appendChild(book);
//            Attr isbn = doc.createAttribute("isbn");
//            isbn.setValue(new Random().ints().toString());
//            book.setAttribute(isbn);
            Element title = doc.createElement("title");
            title.appendChild(doc.createTextNode(this.bookTitle));
            book.appendChild(title);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Element dateFrom = doc.createElement("dateFrom");
            dateFrom.appendChild(doc.createTextNode(dateFormat.format(this.dateFrom)));
            book.appendChild(dateFrom);
            Element dateTo = doc.createElement("dateTo");
            dateTo.appendChild(doc.createTextNode(dateFormat.format(this.dateTo)));
            book.appendChild(dateTo);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
        return doc;
    }
   // }

    public void transformXML() {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File("../sipvs-fiit/data/transform.xslt"));
        Transformer transformer = null;
        try {
            transformer = factory.newTransformer(xslt);
            Source text = new StreamSource(new File("../sipvs-fiit/data/file.xml"));
            transformer.transform(text, new StreamResult(new File("output.html")));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}
