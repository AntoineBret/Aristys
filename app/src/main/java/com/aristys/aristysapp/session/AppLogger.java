package com.aristys.aristysapp.session;

import android.util.Log;

public final class AppLogger {
  private static final String TAG = "Volcanofit";
  private static final String EMPTY = "";
  private static boolean disableLogging;

  public static void disableLogging(boolean value) {
    AppLogger.disableLogging = value;
  }

  public static void verbose(String format, Object... args) {
    if (disableLogging) {
      return;
    }

    Log.v(TAG, formatMessage(format, args));
  }


  public static void verbose(String msg, Throwable e) {
    if (disableLogging) {
      return;
    }

    Log.v(TAG, msg, e);
  }

  public static void verbose(String format, Throwable e, Object... args) {
    if (disableLogging) {
      return;
    }

    Log.v(TAG, formatMessage(format, args), e);
  }

  public static void debug(String format, Object... args) {
    if (disableLogging) {
      return;
    }

    Log.d(TAG, formatMessage(format, args));
  }

  public static void debug(String msg, Throwable e) {
    if (disableLogging) {
      return;
    }

    Log.d(TAG, msg, e);
  }


  public static void debug(String format, Throwable e, Object... args) {
    if (disableLogging) {
      return;
    }

    Log.d(TAG, formatMessage(format, args), e);
  }

  public static void warn(String format, Object... args) {
    if (disableLogging) {
      return;
    }

    Log.w(TAG, formatMessage(format, args));
  }

  public static void warn(String msg, Throwable e) {
    if (disableLogging) {
      return;
    }

    Log.w(TAG, msg, e);
  }


  public static void warn(String format, Throwable e, Object... args) {
    if (disableLogging) {
      return;
    }

    Log.w(TAG, formatMessage(format, args), e);
  }

  public static void info(String format, Object... args) {
    if (disableLogging) {
      return;
    }

    Log.i(TAG, formatMessage(format, args));
  }

  public static void info(String msg, Throwable e) {
    if (disableLogging) {
      return;
    }

    Log.i(TAG, msg, e);
  }

  public static void info(String format, Throwable e, Object... args) {
    if (disableLogging) {
      return;
    }

    Log.i(TAG, formatMessage(format, args), e);
  }

  public static void error(String format, Object... args) {
    Log.e(TAG, formatMessage(format, args));
  }

  public static void error(String msg, Throwable e) {
    Log.e(TAG, msg, e);
  }

  public static void error(String format, Throwable e, Object... args) {
    Log.e(TAG, formatMessage(format, args), e);
  }

  public void wtf(String message, Object... args) {
    Log.wtf(TAG, formatMessage(message, args));
  }

  @Deprecated
  public void wtf(Throwable e, String message, Object... args) {
    Log.wtf(TAG, formatMessage(message, args), e);
  }

  private static String formatMessage(String format, Object... args) {
    try {
      return String.format(format == null ? EMPTY : format, args);
    } catch (RuntimeException e) {
      AppLogger.warn(TAG, "Formating Error. Reason=%s, Format=%s", e.getMessage(), format);
      return String.format(EMPTY, format);
    }
  }

}
