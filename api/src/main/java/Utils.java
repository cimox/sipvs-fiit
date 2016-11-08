import com.sun.deploy.net.HttpResponse;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.tsp.TimeStampResp;
import org.bouncycastle.tsp.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.Element;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

/**
 * Created by cimo on 11/8/2016.
 */

public abstract class Utils {

    public static void addTimestamp() {
        String TSAUrl = "http://test.ditec.sk/timestampws/TS.aspx";
        byte[] digest = "A73Lu1KNvEn05tzitoeGSN8G66OYSIHlRXRaRA3B1iQ=".getBytes();
        OutputStream out = null;

        try {
            TimeStampRequestGenerator reqgen = new TimeStampRequestGenerator();
            TimeStampRequest req = reqgen.generate(TSPAlgorithms.SHA1, digest);
            byte request[] = req.getEncoded();

            URL url = new URL(TSAUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type", "application/timestamp-query");

            con.setRequestProperty("Content-length", String.valueOf(request.length));
            out = con.getOutputStream();
            out.write(request);
            out.flush();

            if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException("Received HTTP error: " + con.getResponseCode() + " - " + con.getResponseMessage());
            }

            InputStream in = con.getInputStream();
            System.out.println(in.read());
            TimeStampResp resp = TimeStampResp.getInstance(new ASN1InputStream(in).readObject());
            TimeStampResponse response = new TimeStampResponse(resp);
            response.validate(req);

            System.out.println(response.getTimeStampToken().getTimeStampInfo().getGenTime());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}