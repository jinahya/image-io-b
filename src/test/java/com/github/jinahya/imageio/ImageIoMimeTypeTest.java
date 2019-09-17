package com.github.jinahya.imageio;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ImageIoMimeTypeTest extends ImageIoFeatureTest<ImageIoMimeType> {

    // -----------------------------------------------------------------------------------------------------------------
    public ImageIoMimeTypeTest() {
        super(ImageIoMimeType.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    List<ImageIoMimeType> instances() {
        final List<ImageIoMimeType> instances = ImageIoMimeType.availableImageIoMimeTypes();
        instances.forEach(instance -> log.debug("instance: {}", instance));
        return instances;
    }
}
