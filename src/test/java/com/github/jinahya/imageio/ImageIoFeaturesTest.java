package com.github.jinahya.imageio;

import org.junit.jupiter.api.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;

public class ImageIoFeaturesTest {

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void xmlSchema() throws JAXBException, IOException {
        final JAXBContext context = JAXBContext.newInstance(ImageIoFeatures.class);
        context.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(final String namespaceUri, final String suggestedFileName) throws IOException {
                final Result result = new StreamResult(System.out);
                result.setSystemId("why do i need to do this?");
                return result;
            }
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void xml() throws JAXBException {
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
    @Test
    public void json() {
        final ImageIoFeatures features = new ImageIoFeatures();
        features.getElements().addAll(ImageIoFileSuffix.availableImageIoFileSuffixes());
        features.getElements().addAll(ImageIoFormatName.availableImageIoFormatNames());
        features.getElements().addAll(ImageIoMimeType.availableImageIoMimeTypes());
        final Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withFormatting(Boolean.TRUE));
        jsonb.toJson(features, System.out);
    }
}
