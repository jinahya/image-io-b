package com.github.jinahya.imageio;

import lombok.extern.slf4j.Slf4j;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
final class JaxbTests {

    // -----------------------------------------------------------------------------------------------------------------
    static Schema schema(final JAXBContext context) throws IOException, SAXException {
        final List<ByteArrayOutputStream> outputs = new ArrayList<>();
        context.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(final String namespaceUri, final String suggestedFileName) throws IOException {
                log.debug("createOutput({}, {})", namespaceUri, suggestedFileName);
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                outputs.add(baos);
                final Result result = new StreamResult(baos);
                result.setSystemId("why do i need to do this?");
                return result;
            }
        });
        return SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(
                outputs.stream()
                        .map(ByteArrayOutputStream::toByteArray)
                        .peek(b -> System.out.println(new String(b)))
                        .map(ByteArrayInputStream::new)
                        .map(input -> new StreamSource(input, ""))
                        .toArray(StreamSource[]::new)
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private JaxbTests() {
        super();
    }
}
