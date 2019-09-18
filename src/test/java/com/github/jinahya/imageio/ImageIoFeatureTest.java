package com.github.jinahya.imageio;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import static com.github.jinahya.imageio.JsonbTests.acceptJsonb;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A class for unit testing subclasses of {@link ImageIoFeature} class.
 *
 * @param <T> subclass type parameter
 */
@Slf4j
public abstract class ImageIoFeatureTest<T extends ImageIoFeature<T>> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     *
     * @param featureClass the subclass of {@link ImageIoFeature} class to test.
     */
    ImageIoFeatureTest(final Class<T> featureClass) {
        super();
        this.featureClass = requireNonNull(featureClass, "featureClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    abstract List<T> instances();

    // ------------------------------------------------------------------------------------------------------------- XML
    @Test
    public void printXml() throws JAXBException {
        for (final T instance : instances()) {
            final JAXBContext context = JAXBContext.newInstance(featureClass);
            final Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(instance, System.out);
        }
    }

    @Test
    public void assertEqualsHashCodeXml() throws JAXBException {
        for (final T instance : instances()) {
            final JAXBContext context = JAXBContext.newInstance(featureClass);
            final Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            marshaller.marshal(instance, baos);
            final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            final T unmarshalled = featureClass.cast(unmarshaller.unmarshal(bais));
            assertEquals(instance, unmarshalled);
            assertEquals(instance.hashCode(), unmarshalled.hashCode());
        }
    }

    // ------------------------------------------------------------------------------------------------------------ JSON
    @Test
    public void printJson() {
        acceptJsonb(jsonb -> {
            for (final T instance : instances()) {
                log.debug("{}: {}", instance, jsonb.toJson(instance));
            }
        });
    }

    @Test
    public void assertEqualsHashCodeJson() throws Exception {
        acceptJsonb(jsonb -> {
            for (final T instance : instances()) {
                final String printed = jsonb.toJson(instance);
                final T parsed = jsonb.fromJson(printed, featureClass);
                assertEquals(instance, parsed);
                assertEquals(instance.hashCode(), parsed.hashCode());
            }
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    final Class<T> featureClass;
}
