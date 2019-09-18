package com.github.jinahya.imageio;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import java.io.IOException;

import static com.github.jinahya.imageio.JsonbTests.acceptJsonb;

public class ImageIoFeaturesTest {

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void schema() throws JAXBException, IOException, SAXException {
        final JAXBContext context = JAXBContext.newInstance(ImageIoFeatures.class);
        final Schema schema = JaxbTests.schema(context);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void instance() throws JAXBException {
        final ImageIoFeatures features = new ImageIoFeatures();
        features.getElements().addAll(ImageIoFileSuffix.availableImageIoFileSuffixes());
        features.getElements().addAll(ImageIoFormatName.availableImageIoFormatNames());
        features.getElements().addAll(ImageIoMimeType.availableImageIoMimeTypes());
        final JAXBContext context = JAXBContext.newInstance(ImageIoFeatures.class);
        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(features, System.out);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Disabled // not intended
    @Test
    public void json() {
        final ImageIoFeatures features = new ImageIoFeatures();
        features.getElements().addAll(ImageIoFileSuffix.availableImageIoFileSuffixes());
        features.getElements().addAll(ImageIoFormatName.availableImageIoFormatNames());
        features.getElements().addAll(ImageIoMimeType.availableImageIoMimeTypes());
        acceptJsonb(jsonb -> {
            System.out.println(jsonb.toJson(features));
        });
    }
}
