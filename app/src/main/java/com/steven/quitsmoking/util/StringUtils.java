package com.steven.quitsmoking.util;

public final class StringUtils {

  private StringUtils() {}

  public static boolean isEmpty(CharSequence cs) {
    return ((cs == null) || (cs.length() == 0));
  }

  public static boolean isNotEmpty(CharSequence cs) {
    return (!(isEmpty(cs)));
  }
}