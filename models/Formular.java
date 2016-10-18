package models;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Formular {
	private ArrayList<Book> books;
	private Person person;

	public Formular(ArrayList<Book> books, Person person) {
		this.books = books;
		this.person = person;
	}

	public Document generateDocument() {
		Document documentXML = null;
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			documentXML = docBuilder.newDocument();
			Element rootElement = documentXML.createElement("rental");
			documentXML.appendChild(rootElement);

			// staff elements
			Element person = documentXML.createElement("person");
			rootElement.appendChild(person);

			// set attribute to staff element -- atribut pre knihy TODO: ISBN
			// atribut
			// Attr attr = doc.createAttribute("id");
			// attr.setValue("1");
			// staff.setAttributeNode(attr);

			// shorten way
			// staff.setAttribute("id", "1");

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
			notification.appendChild(documentXML.createTextNode(this.person.getNotification().toString()));
			person.appendChild(notification);

			Element address = documentXML.createElement("address");
			person.appendChild(address);
			Element street = documentXML.createElement("street");
			street.appendChild(documentXML.createTextNode(this.person.getStreet()));
			address.appendChild(street);
			Element postalCode = documentXML.createElement("postalCode");
			postalCode.appendChild(documentXML.createTextNode(this.person.getPostalCode()));
			address.appendChild(postalCode);
			Element city = documentXML.createElement("city");
			city.appendChild(documentXML.createTextNode(this.person.getCity()));
			address.appendChild(city);
			Element country = documentXML.createElement("country");
			country.appendChild(documentXML.createTextNode(this.person.getState()));
			address.appendChild(country);

			for (Book book : this.books) {
				Element bookElement = documentXML.createElement("book");
				bookElement.setAttribute("isbn", book.getBookISBN());
				rootElement.appendChild(bookElement);
				Element title = documentXML.createElement("title");
				title.appendChild(documentXML.createTextNode(book.getBookTitle()));
				bookElement.appendChild(title);

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Element dateFrom = documentXML.createElement("dateFrom");
				dateFrom.appendChild(documentXML.createTextNode(dateFormat.format(book.getDateFrom())));
				bookElement.appendChild(dateFrom);
				Element dateTo = documentXML.createElement("dateTo");
				dateTo.appendChild(documentXML.createTextNode(dateFormat.format(book.getDateTo())));
				bookElement.appendChild(dateTo);
			}

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}
		return documentXML;
	}

	public void transformXML() {
		// TODO: enable this method
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
