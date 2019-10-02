import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


import static com.sun.tools.hat.internal.parser.Reader.readFile;

public class Worker {
    // https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
    public String XMLtoString() {
        String content = "";

        try
        {
            content = new String ( Files.readAllBytes( Paths.get("/Users/Franciscomonteiro/Desktop/UC/Mestrado/4º ano/IS/2ndAttempt/listaDonos.xml") ) );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return content;
    }




}
