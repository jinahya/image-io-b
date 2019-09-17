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

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An abstract for image features.
 *
 * @param <T> feature type parameter
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public abstract class ImageIoFeature<T extends ImageIoFeature<T>> {

    // -----------------------------------------------------------------------------------------------------------------
    static <T extends ImageIoFeature<T>> List<T> list(final Class<T> featureClass, final String[] readerValues,
                                                      final String[] writerValues) {
        final Map<String, T> m = new HashMap<>();
        for (final String value : readerValues) {
            try {
                m.put(value, featureClass.getConstructor().newInstance().readable(true).value(value));
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }
        for (final String value : writerValues) {
            m.computeIfAbsent(value, k -> {
                try {
                    return featureClass.getConstructor().newInstance().value(k);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            }).setWritable(true);
        }
        return new ArrayList<>(m.values());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString()
               + "{"
               + "readable=" + readable
               + ",writable=" + writable
               + ",value=" + value
               + "}";
    }

    // -----------------------------------------------------------------------------------------------------------------

    public Boolean getReadable() {
        return readable;
    }

    public void setReadable(final Boolean readable) {
        this.readable = readable;
    }

    @SuppressWarnings({"unchecked"})
    public T readable(final Boolean readable) {
        setReadable(readable);
        return (T) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public Boolean getWritable() {
        return writable;
    }

    public void setWritable(final Boolean writable) {
        this.writable = writable;
    }

    @SuppressWarnings({"unchecked"})
    public T writable(final Boolean writable) {
        setWritable(writable);
        return (T) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    @SuppressWarnings({"unchecked"})
    public T value(final String value) {
        setValue(value);
        return (T) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @XmlAttribute
    private Boolean readable;

    @XmlAttribute
    private Boolean writable;

    @XmlValue
    @NotNull
    private String value;
}
