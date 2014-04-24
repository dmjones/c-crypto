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

package org.cryptonode.ccrypto.keys;

import javax.crypto.SecretKey;

/**
 *
 */
public class SecretKeyAndSalt {

    private final SecretKey key;
    private final byte[] salt;

    public SecretKeyAndSalt(SecretKey key, byte[] salt) {
        this.key = key;
        this.salt = salt;
    }

    public SecretKey getKey() {
        return key;
    }

    public byte[] getSalt() {
        return salt;
    }
}
