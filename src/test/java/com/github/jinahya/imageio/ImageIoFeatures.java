package com.github.jinahya.imageio;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlSeeAlso({ImageIoFileSuffix.class, ImageIoFormatName.class, ImageIoMimeType.class})
@XmlRootElement
public class ImageIoFeatures {

    // -----------------------------------------------------------------------------------------------------------------
    public List<ImageIoFeature<?>> getElements() {
        if (elements == null) {
            elements = new ArrayList<>();
        }
        return elements;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @XmlElement
    private List<ImageIoFeature<?>> elements;
}
