package com.github.jinahya.imageio;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ImageIoFormatNameTest extends ImageIoFeatureTest<ImageIoFormatName> {

    // -----------------------------------------------------------------------------------------------------------------
    public ImageIoFormatNameTest() {
        super(ImageIoFormatName.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    List<ImageIoFormatName> instances() {
        final List<ImageIoFormatName> instances = ImageIoFormatName.availableImageIoFormatNames();
        instances.forEach(instance -> log.debug("instance: {}", instance));
        return instances;
    }
}
