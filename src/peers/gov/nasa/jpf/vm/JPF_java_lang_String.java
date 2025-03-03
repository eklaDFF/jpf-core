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
package gov.nasa.jpf.vm;

import gov.nasa.jpf.annotation.MJI;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

/**
 * MJI NativePeer class for java.lang.String library abstraction
 */
public class JPF_java_lang_String extends NativePeer {


  @MJI
  public int init___3CII__Ljava_lang_String_2 (MJIEnv env, int objRef, int valueRef, int offset, int count) {
    char[] value = env.getCharArrayObject(valueRef);
    String result = new String(value, offset, count);
    return env.newString(result);
  }

  @MJI
  public int init___3III__Ljava_lang_String_2 (MJIEnv env, int objRef, int codePointsRef, int offset, int count) {
    int[] codePoints = env.getIntArrayObject(codePointsRef);
    String result = new String(codePoints, offset, count);
    return env.newString(result);
  }

  @SuppressWarnings("deprecation")
  @MJI
  public int init___3BIII__Ljava_lang_String_2 (MJIEnv env, int objRef, int asciiRef, int hibyte, int offset, int count) {
    byte[] ascii = env.getByteArrayObject(asciiRef);
    String result = new String(ascii, hibyte, offset, count);
    return env.newString(result);
  }

  @MJI
  public int init___3BIILjava_lang_String_2__Ljava_lang_String_2 (MJIEnv env, int objRef, int bytesRef, int offset, int length, int charsetNameRef) throws UnsupportedEncodingException {
    byte[] bytes = env.getByteArrayObject(bytesRef);
    String charsetName = env.getStringObject(charsetNameRef);
    String result = new String(bytes, offset, length, charsetName);
    return env.newString(result);
  }

  @MJI
  public int init___3BII__Ljava_lang_String_2 (MJIEnv env, int objRef, int bytesRef, int offset, int length) {
    byte[] bytes = env.getByteArrayObject(bytesRef);
    String result = new String(bytes, offset, length);
    return env.newString(result);
  }

  @MJI
  public int codePointAt__I__I (MJIEnv env, int objRef, int index) {
    String obj = env.getStringObject(objRef);
    return obj.codePointAt(index);
  }

  @MJI
  public int codePointBefore__I__I (MJIEnv env, int objRef, int index) {
    String obj = env.getStringObject(objRef);
    return obj.codePointBefore(index);
  }

  @MJI
  public int codePointCount__II__I (MJIEnv env, int objRef, int beginIndex, int endIndex) {
    String obj = env.getStringObject(objRef);
    return obj.codePointCount(beginIndex, endIndex);
  }

  @MJI
  public int offsetByCodePoints__II__I (MJIEnv env, int objRef, int index, int codePointOffset) {
    String obj = env.getStringObject(objRef);
    return obj.offsetByCodePoints(index, codePointOffset);
  }

  @MJI
  public void getChars___3CI__V(MJIEnv env, int objRef, int dstRef, int dstBegin) {
    String obj = env.getStringObject(objRef);
    char[] dst = env.getCharArrayObject(dstRef);
    obj.getChars(0, obj.length(), dst, dstBegin);
  }

  @SuppressWarnings("deprecation")
  @MJI
  public void getBytes__II_3BI__V (MJIEnv env, int objRef, int srcBegin, int srcEnd, int dstRef, int dstBegin) {
    String obj = env.getStringObject(objRef);
    byte[] dst = env.getByteArrayObject(dstRef);
    obj.getBytes(srcBegin, srcEnd, dst, dstBegin);
  }

  @MJI
  public int getBytes__Ljava_lang_String_2___3B (MJIEnv env, int objRef, int charSetRef) {
    String string = env.getStringObject(objRef);
    String charset = env.getStringObject(charSetRef);

    try {
      byte[] b = string.getBytes(charset);
      return env.newByteArray(b);

    } catch (UnsupportedEncodingException uex) {
      env.throwException(uex.getClass().getName(), uex.getMessage());
      return MJIEnv.NULL;
    }
  }

  @MJI
  public int getBytes_____3B (MJIEnv env, int objRef) {
    String obj = env.getStringObject(objRef);
    byte[] bytes = obj.getBytes();
    return env.newByteArray(bytes);
  }

  @MJI
  public char charAt__I__C (MJIEnv env, int objRef, int index){
    String str = env.getStringObject(objRef);
    return str.charAt(index);
  }

  
  @MJI
  public boolean equals0___3C_3CI__Z (MJIEnv env, int clsObjRef, int charsRef1, int charsRef2, int len) {

    if ((charsRef1 == MJIEnv.NULL) || (charsRef2 == MJIEnv.NULL)) { return false; }

    char[] a = env.getCharArrayObject(charsRef1);
    char[] b = env.getCharArrayObject(charsRef2);

    if (a.length < len || b.length < len) { return false; }

    for (int i = 0; i < len; i++) {
      if (a[i] != b[i]) { return false; }
    }

    return true;
  }

  @MJI
  public boolean equals__Ljava_lang_Object_2__Z (MJIEnv env, int objRef, int argRef) {
    if (argRef == MJIEnv.NULL) { 
      return false; 
    }

    String s1 = env.getStringObject(objRef);
    String s2 = env.getStringObject(argRef);

    return s1.equals(s2);
  }

  @MJI
  public boolean equalsIgnoreCase__Ljava_lang_String_2__Z (MJIEnv env, int objref, int anotherString) {
    String thisString = env.getStringObject(objref);
    if (anotherString != MJIEnv.NULL) {
      return thisString.equalsIgnoreCase(env.getStringObject(anotherString));
    } else {
      return false;
    }
  }

  @MJI
  public int compareTo__Ljava_lang_String_2__I (MJIEnv env, int objRef, int anotherStringRef) {
    String obj = env.getStringObject(objRef);
    String anotherString = env.getStringObject(anotherStringRef);
    return obj.compareTo(anotherString);
  }

  @MJI
  public int MJIcompare__Ljava_lang_String_2Ljava_lang_String_2__I (MJIEnv env, int clsRef, int s1Ref, int s2Ref) {
    // Is there a way to reflect?
    String a = env.getStringObject(s1Ref);
    String s2 = env.getStringObject(s2Ref);
    int n1 = a.length();
    int n2 = s2.length();
    int min = Math.min(n1, n2);
    for (int i = 0; i < min; i++) {
      char x = a.charAt(i);
      char y = s2.charAt(i);
      if (x != y) {
        x = Character.toUpperCase(x);
        y = Character.toUpperCase(y);
        if (x != y) {
          x = Character.toLowerCase(x);
          y = Character.toLowerCase(y);
          if (x != y) { return x - y; }
        }
      }
    }
    return n1 - n2;
  }

  @MJI
  public boolean regionMatches__ILjava_lang_String_2II__Z (MJIEnv env, int objRef, int toffset, int otherRef, int ooffset, int len) {
    String obj = env.getStringObject(objRef);
    String other = env.getStringObject(otherRef);
    return obj.regionMatches(toffset, other, ooffset, len);

  }

  @MJI
  public boolean regionMatches__ZILjava_lang_String_2II__Z (MJIEnv env, int objRef, boolean ignoreCase, int toffset, int otherRef, int ooffset, int len) {
    String obj = env.getStringObject(objRef);
    String other = env.getStringObject(otherRef);
    return obj.regionMatches(ignoreCase, toffset, other, ooffset, len);

  }

  @MJI
  public boolean startsWith__Ljava_lang_String_2I__Z (MJIEnv env, int objRef, int prefixRef, int toffset) {
    String thisStr = env.getStringObject(objRef);
    String prefix = env.getStringObject(prefixRef);
    return thisStr.startsWith(prefix, toffset);
  }

  @MJI
  public boolean startsWith__Ljava_lang_String_2__Z (MJIEnv env, int objRef, int prefixRef) {
    String thisStr = env.getStringObject(objRef);
    String prefix = env.getStringObject(prefixRef);
    return thisStr.startsWith(prefix);
  }

  @MJI
  public int hashCode____I (MJIEnv env, int objref) {
    String str = env.getStringObject(objref);
    return str.hashCode();
  }

  public static int computeStringHashCode(MJIEnv env, int objref) {
    ElementInfo ei = env.getElementInfo(objref);
    int h = ei.getIntField("hash");

    if (h == 0) {
      int vref = env.getReferenceField(objref, "value");

      // now get the char array data, but be aware they are stored as ints
      ElementInfo eiVal = env.getElementInfo(vref);
      byte[] values = eiVal.asByteArray();

      for (int i = 0; i < values.length; i++) {
        h = 31 * h + values[i];
      }

      ei = ei.getModifiableInstance();
      ei.setIntField("hash", h);
    }

    return h;
  }

  @MJI
  public int indexOf__I__I (MJIEnv env, int objref, int c) {
    return indexOf__II__I(env, objref, c, 0);
  }

  @MJI
  public int indexOf__II__I (MJIEnv env, int objref, int c, int fromIndex) {
    String str = env.getStringObject(objref);
    return str.indexOf(c, fromIndex);
  }

  @MJI
  public int lastIndexOf__I__I (MJIEnv env, int objref, int c) {
    return lastIndexOf__II__I(env, objref, c, Integer.MAX_VALUE);
  }

  @MJI
  public int lastIndexOf__II__I (MJIEnv env, int objref, int c, int fromIndex) {
    String str = env.getStringObject(objref);
    return str.lastIndexOf(c, fromIndex);
  }

  @MJI
  public int indexOf__Ljava_lang_String_2__I (MJIEnv env, int objref, int str) {
    String thisStr = env.getStringObject(objref);
    String indexStr = env.getStringObject(str);

    return thisStr.indexOf(indexStr);
  }

  @MJI
  public int indexOf__Ljava_lang_String_2I__I (MJIEnv env, int objref, int str, int fromIndex) {
    String thisStr = env.getStringObject(objref);
    String indexStr = env.getStringObject(str);

    return thisStr.indexOf(indexStr, fromIndex);
  }

  @MJI
  public int lastIndexOf__Ljava_lang_String_2I__I (MJIEnv env, int objref, int str, int fromIndex) {
    String thisStr = env.getStringObject(objref);
    String indexStr = env.getStringObject(str);

    return thisStr.lastIndexOf(indexStr, fromIndex);
  }

  @MJI
  public int substring__I__Ljava_lang_String_2 (MJIEnv env, int objRef, int beginIndex) {
    String obj = env.getStringObject(objRef);
    String result = obj.substring(beginIndex);
    return env.newString(result);

  }

  @MJI
  public int substring__II__Ljava_lang_String_2 (MJIEnv env, int objRef, int beginIndex, int endIndex) {
    String obj = env.getStringObject(objRef);
    String result = obj.substring(beginIndex, endIndex);
    return env.newString(result);

  }

  @MJI
  public int concat__Ljava_lang_String_2__Ljava_lang_String_2 (MJIEnv env, int objRef, int strRef) {
    String thisStr = env.getStringObject(objRef);
    String otherStr = env.getStringObject(strRef);

    String result = thisStr.concat(otherStr);
    return env.newString(result);
  }

  // --- the various replaces

  @MJI
  public int replace__CC__Ljava_lang_String_2 (MJIEnv env, int objRef, char oldChar, char newChar) {
    String thisStr = env.getStringObject(objRef);
    String result = thisStr.replace(oldChar, newChar);
    return env.newString(result);
  }

  @MJI
  public boolean matches__Ljava_lang_String_2__Z (MJIEnv env, int objRef, int regexRef) {
    String s = env.getStringObject(objRef);
    String r = env.getStringObject(regexRef);

    return s.matches(r);
  }

  @MJI
  public int replaceFirst__Ljava_lang_String_2Ljava_lang_String_2__Ljava_lang_String_2 (MJIEnv env, int objRef, int regexRef, int replacementRef) {
    String thisStr = env.getStringObject(objRef);
    String regexStr = env.getStringObject(regexRef);
    String replacementStr = env.getStringObject(replacementRef);

    String result = thisStr.replaceFirst(regexStr, replacementStr);
    return (result != thisStr) ? env.newString(result) : objRef;
  }

  @MJI
  public int replaceAll__Ljava_lang_String_2Ljava_lang_String_2__Ljava_lang_String_2 (MJIEnv env, int objRef, int regexRef, int replacementRef) {
    String thisStr = env.getStringObject(objRef);
    String regexStr = env.getStringObject(regexRef);
    String replacementStr = env.getStringObject(replacementRef);

    String result = thisStr.replaceAll(regexStr, replacementStr);
    return (result != thisStr) ? env.newString(result) : objRef;
  }

  @MJI
  public int split__Ljava_lang_String_2I___3Ljava_lang_String_2 (MJIEnv env, int clsObjRef, int strRef, int limit) {
    String s = env.getStringObject(strRef);
    String obj = env.getStringObject(clsObjRef);

    String[] result = obj.split(s, limit);

    return env.newStringArray(result);
  }

  @MJI
  public int split__Ljava_lang_String_2___3Ljava_lang_String_2 (MJIEnv env, int clsObjRef, int strRef) {
    String s = env.getStringObject(strRef);
    String obj = env.getStringObject(clsObjRef);

    String[] result = obj.split(s);

    return env.newStringArray(result);
  }

  @MJI
  public int toLowerCase__Ljava_util_Locale_2__Ljava_lang_String_2 (MJIEnv env, int objRef, int locRef) {
    String s = env.getStringObject(objRef);
    Locale loc = JPF_java_util_Locale.getLocale(env, locRef);

    String lower = s.toLowerCase(loc);

    return (s == lower) ? objRef : env.newString(lower);
  }

  @MJI
  public int toLowerCase____Ljava_lang_String_2 (MJIEnv env, int objRef) {
    String s = env.getStringObject(objRef);
    String lower = s.toLowerCase();

    return (s == lower) ? objRef : env.newString(lower);
  }

  @MJI
  public int toUpperCase__Ljava_util_Locale_2__Ljava_lang_String_2 (MJIEnv env, int objRef, int locRef) {
    String s = env.getStringObject(objRef);
    Locale loc = JPF_java_util_Locale.getLocale(env, locRef);

    String upper = s.toUpperCase(loc);

    return (s == upper) ? objRef : env.newString(upper);
  }

  @MJI
  public int toUpperCase____Ljava_lang_String_2 (MJIEnv env, int objRef) {
    String s = env.getStringObject(objRef);
    String upper = s.toUpperCase();

    return (s == upper) ? objRef : env.newString(upper);
  }

  @MJI
  public int trim____Ljava_lang_String_2 (MJIEnv env, int objRef) {
    String str = env.getStringObject(objRef);
    String result = str.trim();
    return env.newString(result);
  }

  @MJI
  public int toCharArray_____3C (MJIEnv env, int objref) {
      String str = env.getStringObject(objref);
      return env.newCharArray(str.toCharArray()) ;
  }

  @MJI
  public int format__Ljava_lang_String_2_3Ljava_lang_Object_2__Ljava_lang_String_2 (MJIEnv env, int clsObjRef, int fmtRef, int argRef) {
    return env.newString(env.format(fmtRef, argRef));
  }

  @MJI
  public int format__Ljava_util_Locale_2Ljava_lang_String_2_3Ljava_lang_Object_2__Ljava_lang_String_2 (MJIEnv env, int clsObjRef, int locRef, int fmtRef, int argRef) {
    Locale loc = JPF_java_util_Locale.getLocale(env, locRef);
    return env.newString(env.format(loc, fmtRef, argRef));
  }

  @MJI
  public int intern____Ljava_lang_String_2 (MJIEnv env, int robj) {
    // <2do> Replace this with a JPF space HashSet once we have a String model
    Heap heap = env.getHeap();

    String s = env.getStringObject(robj);
    ElementInfo ei = heap.newInternString(s, env.getThreadInfo());

    return ei.getObjectRef();
  }

  @MJI
  public int valueOf__I__Ljava_lang_String_2 (MJIEnv env, int clsref, int i) {
    String result = String.valueOf(i);
    return env.newString(result);
  }

  @MJI
  public int valueOf__J__Ljava_lang_String_2 (MJIEnv env, int clsref, long l) {
    String result = String.valueOf(l);
    return env.newString(result);
  }

  @MJI
  public int valueOf__F__Ljava_lang_String_2 (MJIEnv env, int clsref, float f) {
    String result = String.valueOf(f);
    return env.newString(result);
  }

  @MJI
  public int valueOf__D__Ljava_lang_String_2 (MJIEnv env, int clsref, double d) {
    String result = String.valueOf(d);
    return env.newString(result);
  }

  @MJI
  public static void checkBoundsOffCount__III__V(MJIEnv env, int objref, int offset, int count, int length) {
    if (offset < 0 || count < 0 || offset > length - count)
        throw new StringIndexOutOfBoundsException("offset " + offset + ", count " + count + ", length " + length);
  }
}
