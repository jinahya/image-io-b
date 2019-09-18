package com.github.jinahya.imageio;

/*-
 * #%L
 * imageio-features
 * %%
 * Copyright (C) 2019 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import javax.imageio.ImageIO;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import static javax.imageio.ImageIO.getReaderFileSuffixes;
import static javax.imageio.ImageIO.getWriterFileSuffixes;

/**
 * An image feature for file suffixes.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @see ImageIO#getReaderFileSuffixes()
 * @see ImageIO#getWriterFileSuffixes()
 */
@XmlRootElement
public class ImageIoFileSuffix extends ImageIoFeature<ImageIoFileSuffix> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns a list of all available features for file suffixes.
     *
     * @return a list of image io file suffixes.
     */
    public static List<ImageIoFileSuffix> availableImageIoFileSuffixes() {
        return list(ImageIoFileSuffix.class, getReaderFileSuffixes(), getWriterFileSuffixes());
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImageIoFileSuffix)) {
            return false;
        }
        return fieldsEqual(obj);
    }
}
