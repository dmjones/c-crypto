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

import org.cryptonode.ccrypto.RandomGenerator;
import org.cryptonode.ccrypto.SecureRandomGenerator;
import org.cryptonode.ccrypto.SymmetricAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 *
 */
public class KeyDeriver {

    static final int SALT_BYTE_LENGTH = 16;
    static final int DEFAULT_ITERATIONS = 10_000;

    private SymmetricAlgorithm algorithm;
    private int iterations;
    private RandomGenerator randomGenerator;

    public KeyDeriver(SymmetricAlgorithm algorithm) {
        this(algorithm, DEFAULT_ITERATIONS);
    }

    public KeyDeriver(SymmetricAlgorithm algorithm, int iterations) {
        this(algorithm, iterations, new SecureRandomGenerator());
    }

    KeyDeriver(SymmetricAlgorithm algorithm, int iterations, RandomGenerator randomGenerator) {
        this.algorithm = algorithm;
        this.iterations = iterations;
        this.randomGenerator = randomGenerator;
    }

    public SecretKeyAndSalt keyFromPassword(char[] password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] salt = randomGenerator.nextBytes(SALT_BYTE_LENGTH);

        SecretKey key = keyFromPassword(password, salt);
        return new SecretKeyAndSalt(key, salt);
    }

    public SecretKey keyFromPassword(char[] password, byte[] salt) throws
            InvalidKeySpecException, NoSuchAlgorithmException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec spec = new PBEKeySpec(password, salt, iterations, algorithm.getKeyBitLength());
        SecretKey tmp = factory.generateSecret(spec);
        return new SecretKeySpec(tmp.getEncoded(), algorithm.getJceAlgorithmName());
    }
}
