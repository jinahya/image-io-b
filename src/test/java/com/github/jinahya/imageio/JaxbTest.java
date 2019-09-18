package com.github.jinahya.imageio;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.validation.Schema;
import java.io.IOException;

import static com.github.jinahya.imageio.JaxbTests.schema;

/**
 * A class for unit testing subclasses of {@link ImageIoFeature} class.
 */
@Slf4j
public class JaxbTest {

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void printSchema() throws JAXBException, IOException, SAXException {
        final JAXBContext context = JAXBContext.newInstance(ImageIoFeature.class);
        final Schema schema = schema(context);
    }
}
