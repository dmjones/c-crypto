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

import java.util.Arrays;

/**
 *
 */
public class ByteArrayPlaintext implements Plaintext {

    private final byte[] plaintext;

    public ByteArrayPlaintext(byte[] plaintext) {
        if (plaintext == null) {
            throw new IllegalThreadStateException("Plaintext cannot be null.");
        }

        this.plaintext = plaintext;
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

        ByteArrayPlaintext that = (ByteArrayPlaintext) o;

        return Arrays.equals(plaintext, that.plaintext);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(plaintext);
    }
}
