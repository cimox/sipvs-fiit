import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.bouncycastle.tsp.TSPException;
import org.bouncycastle.*;
import org.bouncycastle.tsp.TimeStampResponse;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.util.encoders.Base64;
import org.xml.sax.SAXException;


public abstract class Utils {
    private static String stamped = "";

    public static String addTimestamp(String signedXML) throws TransformerConfigurationException, TransformerException, ParserConfigurationException, SAXException, IOException, TSPException {
        stamped = timeStamp(signedXML);
        System.out.println(stamped);
        return stamped;
    }

    public static String timeStamp(String input) throws TSPException {
        String server = "http://test.ditec.sk/timestampws/TS.asmx";
        String encodedTimestamp = "";

        String signWrapper = input;
        signWrapper = signWrapper.substring(signWrapper.indexOf("<ds:SignatureValue Id=\"signatureIdSignatureValue\">") + 50);
        signWrapper = signWrapper.substring(0, signWrapper.indexOf("</ds:SignatureValue>"));

        try {
            URL tsURL = new URL(server);
            HttpURLConnection tsConnection = (HttpURLConnection) tsURL.openConnection();

            tsConnection.setDoOutput(true);
            tsConnection.setDoInput(true);
            tsConnection.setRequestMethod("POST");
            tsConnection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            tsConnection.setRequestProperty("SOAPAction", "http://www.ditec.sk/GetTimestamp");

            OutputStream out = tsConnection.getOutputStream();
            Writer wout = new OutputStreamWriter(out);
            wout.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "  <soap:Body>\n" +
                    "    <GetTimestamp xmlns=\"http://www.ditec.sk/\">\n" +
                    "      <dataB64>" + Base64.toBase64String(signWrapper.getBytes()) + "</dataB64>\n" +
                    "    </GetTimestamp>\n" +
                    "  </soap:Body>\n" +
                    "</soap:Envelope>");


            wout.flush(); wout.close();

            InputStream in = tsConnection.getInputStream();
            int c; String response = "";
            while ((c = in.read()) != -1) response = response + (char) c;
            in.close();

            int i = response.indexOf("<GetTimestampResult>");
            response = response.substring(i + 20);
            i = response.indexOf("</GetTimestampResult>");
            response = response.substring(0, i);


            try {
//                TimeStampResponse resp = new TimeStampResponse(Base64Coder.decode(response));
                TimeStampResponse resp = new TimeStampResponse(Base64.decode(response));
                TimeStampToken tsToken = resp.getTimeStampToken();

                encodedTimestamp = Base64Coder.encodeString(Base64Coder.encodeLines(tsToken.getEncoded()));
//                encodedTimestamp = Base64.toBase64String(tsToken.getEncoded());
            } catch (TSPException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.err.println(e);
        }
        return encodedTimestamp;
    }

    public static String createStamped(String xml, String stamp) {
        String stamped = "";
        int i = xml.lastIndexOf("</xades:SignedProperties>");
        stamped = xml.substring(0, i + 25);
        stamped += "<xades:UnsignedProperties>";
        stamped += "<xades:UnsignedSignatureProperties>";
        stamped += "<xades:SignatureTimeStamp Id=\"signatureIdSignatureTimeStamp\">";
        stamped += "<xades:EncapsulatedTimeStamp>";
        stamped += stamp;
        stamped += "</xades:EncapsulatedTimeStamp>";
        stamped += "</xades:SignatureTimeStamp>";
        stamped += "</xades:UnsignedSignatureProperties>";
        stamped += "</xades:UnsignedProperties>";
        stamped += xml.substring(i + 25);
        return stamped;
    }
}
