package com.github.jinahya.imageio;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ImageIoFileSuffixTest extends ImageIoFeatureTest<ImageIoFileSuffix> {

    // -----------------------------------------------------------------------------------------------------------------
    public ImageIoFileSuffixTest() {
        super(ImageIoFileSuffix.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    List<ImageIoFileSuffix> instances() {
        final List<ImageIoFileSuffix> instances = ImageIoFileSuffix.availableImageIoFileSuffixes();
        instances.forEach(instance -> log.debug("instance: {}", instance));
        return instances;
    }
}
