/*
 * Copyright (C) 2014, United States Government, as represented by the
 * Administrator of the National Aeronautics and Space Administration.
 * All rights reserved.
 *
 * The Java Pathfinder core (jpf-core) platform is licensed under the
 * Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gov.nasa.jpf.test.java.nio;

import gov.nasa.jpf.util.test.TestJPF;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class BufferTest extends TestJPF {

  /**
   * This test case checks to see if the missing
   * class java.nio.Buffer.<init>(IIII)V issue
   * is resolved and fails otherwise
   */
  @Test
  public void testByteBufferConstructor() {
    if (verifyNoPropertyViolation()) {
      byte[] bytes1 = "Buffer".getBytes(StandardCharsets.UTF_8);
      byte[] bytes2 = "testBuffer".getBytes(StandardCharsets.UTF_8);

      ByteBuffer buffer1 = ByteBuffer.wrap(bytes1);
      ByteBuffer buffer2 = ByteBuffer.wrap(bytes2);
      buffer2.position(4);

      assertTrue(buffer1.equals(buffer2));
    }
  }

  @Test
  public void testCharBufferConstructorJapanese(){
    if(verifyNoPropertyViolation()){
    String utf8String = "Hello, 世界";
    byte[] utf8Bytes = utf8String.getBytes(StandardCharsets.UTF_8);

    String decodedUtf8FromUtf8 = decodeWithBufferedReader(utf8Bytes, StandardCharsets.UTF_8);
    String decodedLatin1FromUtf8 = decodeWithBufferedReader(utf8Bytes, StandardCharsets.ISO_8859_1);

    assertEquals(("Assertion failed. Original: " + utf8String + ", Decoded: " + decodedUtf8FromUtf8), decodedUtf8FromUtf8, utf8String);
    assertNotEquals(("Assertion failed. Original: " + utf8String + ", Decoded: " + decodedLatin1FromUtf8), decodedLatin1FromUtf8, utf8String);
    }
  }

  @Test
  public void testCharBufferConstructorSwedishEncodingWithLATIN1() {
    if (verifyNoPropertyViolation()) {
      String latin1String = "Hello, Åland";

      byte[] latin1BytesFromLatin1String = latin1String.getBytes(StandardCharsets.ISO_8859_1);

      String decodedUtf8FromLatin1 = decodeWithBufferedReader(latin1BytesFromLatin1String, StandardCharsets.UTF_8);
      String decodedLatin1FromLatin1 = decodeWithBufferedReader(latin1BytesFromLatin1String, StandardCharsets.ISO_8859_1);

      assertNotEquals(("Assertion failed. Original: " + latin1String + ", Decoded: " + decodedUtf8FromLatin1), decodedUtf8FromLatin1, latin1String);
      assertEquals(("Assertion failed. Original: " + latin1String + ", Decoded: " + decodedLatin1FromLatin1), decodedLatin1FromLatin1, latin1String);
    }
  }

  @Test
  public void testCharBufferConstructorSwedishEncodingWithUTF(){
    if(verifyNoPropertyViolation()){
      String latin1String = "Hello, Åland";

      byte[] utf8BytesFromLatin1String = latin1String.getBytes(StandardCharsets.UTF_8);

      String decodedUtf8FromUtf8 = decodeWithBufferedReader(utf8BytesFromLatin1String, StandardCharsets.UTF_8);
      String decodedLatin1FromUtf8 = decodeWithBufferedReader(utf8BytesFromLatin1String, StandardCharsets.ISO_8859_1);

      assertEquals(("Assertion failed. Original: " + latin1String + ", Decoded: " + decodedUtf8FromUtf8), decodedUtf8FromUtf8, latin1String);
      assertNotEquals(("Assertion failed. Original: " + latin1String + ", Decoded: " + decodedLatin1FromUtf8), decodedLatin1FromUtf8, latin1String);
    }
  }


  private static String decodeWithBufferedReader(byte[] bytes, Charset charset) {
    String s = new String(bytes, charset);
    BufferedReader reader = new BufferedReader(new StringReader(s));
    StringBuilder result = new StringBuilder();
    String line;

    try {
      while ((line = reader.readLine()) != null) {
        result.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        reader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return result.toString();
  }

}
