package com.github.jinahya.imageio;

/*-
 * #%L
 * image-io-b
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

import static javax.imageio.ImageIO.getReaderFormatNames;
import static javax.imageio.ImageIO.getWriterFormatNames;

/**
 * An image feature for informal format names.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 * @see ImageIO#getReaderFormatNames()
 * @see ImageIO#getWriterFormatNames()
 */
@XmlRootElement
public class ImageIoFormatName extends ImageIoFeature<ImageIoFormatName> {

    // -----------------------------------------------------------------------------------------------------------------
    public static List<ImageIoFormatName> availableImageIoFormatNames() {
        return list(ImageIoFormatName.class, getReaderFormatNames(), getWriterFormatNames());
    }
}
