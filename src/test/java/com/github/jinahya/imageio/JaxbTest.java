package com.github.jinahya.imageio;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;

/**
 * A class for unit testing subclasses of {@link ImageIoFeature} class.
 */
@Slf4j
public class JaxbTest {

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void printXmlSchema() throws JAXBException, IOException {
        final JAXBContext context = JAXBContext.newInstance(ImageIoFeature.class);
        context.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(final String namespaceUri, final String suggestedFileName) throws IOException {
                final Result result = new StreamResult(System.out);
                result.setSystemId("why do i need to do this?");
                return result;
            }
        });
    }
}
