
package com.like.permission;

import android.Manifest;
import android.content.pm.PackageManager;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class RNPermissionModule extends ReactContextBaseJavaModule implements OnRequestPermissionsResult {

    private final ReactApplicationContext reactContext;

    private static final int REQUEST_CODE = 0x123;

    private Callback mCallback;

    public RNPermissionModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        PermissionUtil.getInstance().setRequestPermissionsResult(this);
    }

    @Override
    public String getName() {
        return "RNPermission";
    }

    /**
     * 常量
     *
     * @return
     */
    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        Map<String, Object> map = new HashMap<>();
        map.put("RECORD_AUDIO", Manifest.permission.RECORD_AUDIO);
        map.put("GET_ACCOUNTS", Manifest.permission.GET_ACCOUNTS);
        map.put("READ_PHONE_STATE", Manifest.permission.READ_PHONE_STATE);
        map.put("CALL_PHONE", Manifest.permission.CALL_PHONE);
        map.put("CAMERA", Manifest.permission.CAMERA);
        map.put("ACCESS_FINE_LOCATION", Manifest.permission.ACCESS_FINE_LOCATION);
        map.put("ACCESS_COARSE_LOCATION", Manifest.permission.ACCESS_COARSE_LOCATION);
        map.put("READ_EXTERNAL_STORAGE", Manifest.permission.READ_EXTERNAL_STORAGE);
        map.put("WRITE_EXTERNAL_STORAGE", Manifest.permission.WRITE_EXTERNAL_STORAGE);
        map.put("READ_CALENDAR", Manifest.permission.READ_CALENDAR);
        map.put("WRITE_CALENDAR", Manifest.permission.WRITE_CALENDAR);
        map.put("READ_CONTACTS", Manifest.permission.READ_CONTACTS);
        map.put("WRITE_CONTACTS", Manifest.permission.WRITE_CONTACTS);
        map.put("READ_CALL_LOG", Manifest.permission.READ_CALL_LOG);
        map.put("WRITE_CALL_LOG", Manifest.permission.WRITE_CALL_LOG);
        map.put("ADD_VOICEMAIL", Manifest.permission.ADD_VOICEMAIL);
        map.put("USE_SIP", Manifest.permission.USE_SIP);
        map.put("PROCESS_OUTGOING_CALLS", Manifest.permission.PROCESS_OUTGOING_CALLS);
        map.put("BODY_SENSORS", Manifest.permission.BODY_SENSORS);
        map.put("SEND_SMS", Manifest.permission.SEND_SMS);
        map.put("RECEIVE_SMS", Manifest.permission.RECEIVE_SMS);
        map.put("READ_SMS", Manifest.permission.READ_SMS);
        map.put("RECEIVE_WAP_PUSH", Manifest.permission.RECEIVE_WAP_PUSH);
        map.put("RECEIVE_MMS", Manifest.permission.RECEIVE_MMS);
        return map;
    }

    /**
     * 检测单个权限
     *
     * @param permission
     * @param callback
     */
    @ReactMethod
    public void checkSelfPermission(String permission, Callback callback) {
        boolean hasPer = PermissionUtil.checkSelfPermission(reactContext, permission);
        WritableMap writableMap = new WritableNativeMap();
        writableMap.putBoolean(permission, hasPer);
        callback.invoke(writableMap);
    }

    /**
     * 检测多个权限
     *
     * @param permissions
     * @param callback
     */
    @ReactMethod
    public void checkSelfPermissions(ReadableArray permissions, final Callback callback) {
        String[] pers = arrayToString(permissions);
        WritableMap writableMap = new WritableNativeMap();
        for (String per : pers) {
            boolean hasPer = PermissionUtil.checkSelfPermission(reactContext, per);
            writableMap.putBoolean(per, hasPer);
        }
        callback.invoke(writableMap);
    }

    /**
     * 用户请求过权限，被拒绝后的状态
     *
     * @param permission
     */
    @ReactMethod
    public void shouldShowRequestPermissionRationale(String permission, final Callback callback) {
        boolean status = PermissionUtil.shouldShowRequestPermissionRationale(getCurrentActivity(), permission);
        callback.invoke(status);
    }

    /**
     * 请求单个权限
     *
     * @param permission
     */
    @ReactMethod
    public void requestPermission(String permission, Callback callback) {
        this.mCallback = callback;
        String[] strings = new String[]{permission};
        PermissionUtil.requestPermissions(getCurrentActivity(), strings, REQUEST_CODE);
    }

    /**
     * 请求权限组
     *
     * @param permissions
     */
    @ReactMethod
    public void requestPermissions(ReadableArray permissions, Callback callback) {
        this.mCallback = callback;
        PermissionUtil.requestPermissions(getCurrentActivity(), arrayToString(permissions), REQUEST_CODE);
    }

    /**
     * 打开系统设置界面
     */
    @ReactMethod
    public void openSetting() {
        SettingUtil settingUtil = new SettingUtil();
        settingUtil.jumpPermissionPage(reactContext);
    }

    @Override
    public void result(int requestCode, String[] permissions, int[] grantResults) {
        if (mCallback != null && requestCode == REQUEST_CODE) {
            WritableMap writableMap = new WritableNativeMap();
            for (int i = 0; i < permissions.length; i++) {
                writableMap.putBoolean(permissions[i], grantResults[i] == PackageManager.PERMISSION_GRANTED ? true : false);
            }
            mCallback.invoke(writableMap);
        }
    }

    /**
     * 检测悬浮窗权限
     *
     * @param callback
     */
    @ReactMethod
    public void checkFloatWindowPermissions(Callback callback) {
        try {
            callback.invoke(FloatWindowManager.getInstance().checkPermission(reactContext));
        } catch (Exception e) {
            callback.invoke(false);
        }
    }

    /**
     * 打开悬浮窗设置
     */
    @ReactMethod
    public void openFloatWindowSetting(){
        FloatWindowManager.getInstance().applyPermission(reactContext);
    }

    /**
     * 把RN数组转化为字符串数组
     *
     * @param array
     * @return
     */
    private String[] arrayToString(ReadableArray array) {
        if (array == null) {
            return new String[0];
        }
        int size = array.size();
        String[] strings = new String[size];
        for (int i = 0; i < size; i++) {
            strings[i] = array.getString(i);
        }
        return strings;
    }

}