
package com.reactlibrary;

import android.content.Context;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class RNAndroidAppListModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNAndroidAppListModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNAndroidAppList";
  }

  
  @ReactMethod
  public void getAllPermissions(Promise promise) {

    PackageManager pm = reactContext.getPackageManager();
    List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
    ArrayList<ApplicationPermission> appPermissions = new ArrayList<ApplicationPermission>();

    for (ApplicationInfo applicationInfo : packages) {

      try {
        PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);

        // Get Permissions
        String[] requestedPermissions = packageInfo.requestedPermissions;

        if (requestedPermissions != null) {
          for (int i = 0; i < requestedPermissions.length; i++) {
            boolean status = pm.checkPermission(requestedPermissions[i], applicationInfo.packageName) == PackageManager.PERMISSION_GRANTED ? true : false;
            ApplicationPermission permission = new ApplicationPermission(applicationInfo.packageName, requestedPermissions[i], status);
            appPermissions.add(permission);
          }
        }
      } catch (PackageManager.NameNotFoundException e) {
        e.printStackTrace();
      }
    }

    JSONArray jsonArray = new JSONArray();
    for (int i = 0; i < appPermissions.size(); i++) {
      jsonArray.put(appPermissions.get(i).getJSONObject());
    }

    promise.resolve(jsonArray.toString());
  }

  class ApplicationPermission {
    private String packageName;
    private String permissionName;
    private boolean granted;

    public ApplicationPermission(String packageName, String permissionName, boolean granted) {
      this.packageName = packageName;
      this.permissionName = permissionName;
      this.granted = granted;
    }

    public void setPackageName(String packageName) {
      this.packageName = packageName;
    }

    public String getPackageName() {
      return this.packageName;
    }

    public void setPermissionName(String permissionName) {
      this.permissionName = permissionName;
    }

    public String getPermissionName() {
      return this.permissionName;
    }

    public void setGranted(boolean granted) {
      this.granted = granted;
    }

    public boolean isGranted() {
      return this.granted;
    }

    public JSONObject getJSONObject() {
      JSONObject obj = new JSONObject();
      try {
        obj.put("packageName", getPackageName());
        obj.put("permissionName", getPermissionName());
        obj.put("granted", isGranted());
      } catch (JSONException e) {
        
      }
      return obj;
    }
  }
}