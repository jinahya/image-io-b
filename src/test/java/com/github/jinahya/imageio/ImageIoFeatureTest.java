package com.github.jinahya.imageio;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.github.jinahya.imageio.JsonbTests.acceptJsonb;
import static java.util.Objects.requireNonNull;
import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A class for unit testing subclasses of {@link ImageIoFeature} class.
 *
 * @param <T> subclass type parameter
 */
@Slf4j
public abstract class ImageIoFeatureTest<T extends ImageIoFeature> {

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

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void assertAllInstancesAreNotEqualToEachOther() {
        final Set<T> set = new HashSet<>();
        for (final T instance : instances()) {
            assertTrue(set.add(instance));
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void testIsReadable() {
        for (final T instance : instances()) {
            final boolean readable = instance.isReadable();
        }
    }

    @Test
    public void testSetReadable() {
        for (final T instance : instances()) {
            instance.setReadable(current().nextBoolean());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void testIsWritable() {
        for (final T instance : instances()) {
            final boolean writable = instance.isWritable();
        }
    }

    @Test
    public void testSetWritable() {
        for (final T instance : instances()) {
            instance.setWritable(current().nextBoolean());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void testGetValue() {
        for (final T instance : instances()) {
            final String value = instance.getValue();
        }
    }

    @Test
    public void testSetValue() {
        for (final T instance : instances()) {
            instance.setValue(null);
            instance.setValue(instance.toString());
        }
    }

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
