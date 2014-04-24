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

import java.security.SecureRandom;

/**
 *
 */
public class SecureRandomGenerator implements RandomGenerator {

    private final SecureRandom random = new SecureRandom();

    @Override
    public byte[] nextBytes(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("Length cannot be negative");
        }

        byte[] result = new byte[length];
        random.nextBytes(result);
        return result;
    }
}
