package com.topspeedsnail.game;
import android.app.Activity;
import android.os.Bundle;
public class MainActivity extends Activity
{
  static
  {
    System.loadLibrary( "HelloLib" );
  }
  @Override protected void onCreate( Bundle bundle )
  {
    super.onCreate(bundle);
    printHello();
  }
  public static native void printHello();
};
