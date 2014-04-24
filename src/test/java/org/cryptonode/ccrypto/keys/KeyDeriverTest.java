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
import org.cryptonode.ccrypto.SymmetricAlgorithm;
import org.junit.Test;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.util.Arrays;
import java.util.Random;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 *
 */
public class KeyDeriverTest {

    private static final Random RANDOM = new Random();

    @Test
    public void testNoSalt() throws Exception {
        byte[] salt = new byte[KeyDeriver.SALT_BYTE_LENGTH];
        RANDOM.nextBytes(salt);

        RandomGenerator randomGenerator = mock(RandomGenerator.class);
        when(randomGenerator.nextBytes(KeyDeriver.SALT_BYTE_LENGTH)).thenReturn(salt);

        KeyDeriver deriver =
                new KeyDeriver(SymmetricAlgorithm.AES_128, KeyDeriver.DEFAULT_ITERATIONS, randomGenerator);
        SecretKeyAndSalt keyAndSalt = deriver.keyFromPassword("foo".toCharArray());

        assertArrayEquals(salt, keyAndSalt.getSalt());
    }

    @Test
    public void testDeriveWithSalt() throws Exception {
        // Vector from http://www.ietf.org/rfc/rfc6070.txt
        char[] password = "password".toCharArray();
        byte[] salt = new byte[] {'s', 'a', 'l', 't'};
        int iterations = 1;

        KeyDeriver deriver = new KeyDeriver(SymmetricAlgorithm.AES_256, iterations);
        SecretKey key = deriver.keyFromPassword(password, salt);
        byte[] expectedResult = DatatypeConverter.parseHexBinary("0c60c80f961f0e71f3a9b524af6012062fe037a6");
        byte[] result = Arrays.copyOf(key.getEncoded(), expectedResult.length);
        assertArrayEquals(expectedResult, result);
    }
}
