package com.examples;

import com.facebook.react.ReactActivity;
import com.like.permission.PermissionUtil;

public class MainActivity extends ReactActivity {

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
    return "examples";
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    PermissionUtil.getInstance().onRequestPermissionsResult(requestCode, permissions, grantResults);
  }
}
