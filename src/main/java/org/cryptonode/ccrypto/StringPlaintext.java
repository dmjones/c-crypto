/*
 * Copyright 2014 Duncan Jones
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cryptonode.ccrypto;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 *
 */
public class StringPlaintext implements Plaintext {

    private final byte[] plaintext;


    public StringPlaintext(String plaintext) {
        this(plaintext, Charset.forName("UTF-8"));
    }

    public StringPlaintext(String plaintext, Charset charset) {
        if (plaintext == null) {
            throw new IllegalArgumentException("Plaintext cannot be null.");
        }
        if (charset == null) {
            throw new IllegalArgumentException("Charset cannot be null.");
        }

        this.plaintext = plaintext.getBytes(charset);
    }

    public byte[] getPlaintext() {
        return plaintext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StringPlaintext that = (StringPlaintext) o;

        return Arrays.equals(plaintext, that.plaintext);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(plaintext);
    }
}
