package org.example;

import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.xml.sax.SAXException;
import org.apache.camel.*;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.util.Date;


public class App {
    public static void main(String[] args) throws IOException, SAXException, TransformerException {


        var xmlPath = "src/main/fop/in/student.xml";
        var xsltPath = "src/main/fop/in/student.xslt";
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File(xsltPath));
        Transformer transformer = factory.newTransformer(xslt);

        Source text = new StreamSource(new File(xmlPath));
        transformer.transform(text, new StreamResult(new File("src/main/fop/out/output.fo")));


        var confPath = "src/main/fop/conf/fop.xconf";
        var fac = FopFactory.newInstance(new File(confPath));
        var file = new File("src\\main\\fop\\out\\result.pdf");
        try (var output = new BufferedOutputStream(
                new FileOutputStream(
                        file))) {
            var fop = fac.newFop(MimeConstants.MIME_PDF, output);
            // Шаг 4: Установка JAXP для использования "identity transformer"
            factory = TransformerFactory.newInstance();
            transformer = factory.newTransformer(); // identity transformer

            // Шаг 5: Установка input и output для XSLT-трансформации
            // Установка input stream
            Source src = new StreamSource(new File("src/main/fop/out/output.fo"));

            // Результирующие SAX-события (созданные FO) должны быть запайпены (piped) до FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            // Шаг 6: Запуск XSLT-трансформации и FOP-процессинга
            transformer.transform(src, res);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
