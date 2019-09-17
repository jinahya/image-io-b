package com.github.jinahya.imageio;

import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.List;

import static java.util.Objects.requireNonNull;

abstract class ImageIoFeatureTest<T extends ImageIoFeature<T>> {

    // -----------------------------------------------------------------------------------------------------------------
    ImageIoFeatureTest(final Class<T> featureClass) {
        super();
        this.featureClass = requireNonNull(featureClass, "featureClass is null");
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
