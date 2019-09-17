package com.github.jinahya.imageio;

import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.util.List;

import static java.util.Objects.requireNonNull;

public abstract class ImageIoFeatureTest<T extends ImageIoFeature<T>> {

    // -----------------------------------------------------------------------------------------------------------------
    ImageIoFeatureTest(final Class<T> featureClass) {
        super();
        this.featureClass = requireNonNull(featureClass, "featureClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void printXmlSchema() throws JAXBException, IOException {
        final JAXBContext context = JAXBContext.newInstance(featureClass);
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
    abstract List<T> instances();

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void marshal() throws JAXBException {
        for (final T instance : instances()) {
            final JAXBContext context = JAXBContext.newInstance(featureClass);
            final Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(instance, System.out);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    final Class<T> featureClass;
}
