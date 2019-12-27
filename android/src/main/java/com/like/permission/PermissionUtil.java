package com.like.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * 作者：like on 2019-05-06 11:25
 * <p>
 * 邮箱：like@tydic.com
 * <p>
 * 描述：权限申请
 */
public class PermissionUtil {

    private static PermissionUtil mPermissionUtil = null;

    private static OnRequestPermissionsResult onRequestPermissionsResult = null;

    private PermissionUtil() {

    }

    public static PermissionUtil getInstance() {
        if (mPermissionUtil == null) {
            mPermissionUtil = new PermissionUtil();
        }
        return mPermissionUtil;
    }

    public void setRequestPermissionsResult(OnRequestPermissionsResult onRequestPermissionsResult) {
        this.onRequestPermissionsResult = onRequestPermissionsResult;
    }

    /**
     * 权限申请回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (onRequestPermissionsResult != null) {
            onRequestPermissionsResult.result(requestCode, permissions, grantResults);
        }
    }

    /**
     * 检查应用是否具有某个危险权限。如果应用具有此权限，方法将返回 PackageManager.PERMISSION_GRANTED，并且应用可以继续操作。
     * 如果应用不具有此权限，方法将返回 PackageManager.PERMISSION_DENIED，且应用必须明确向用户要求权限。
     *
     * @param permission
     */
    public static boolean checkSelfPermission(Context context, String permission) {
        int hasPermission = ContextCompat.checkSelfPermission(context, permission);
        return hasPermission == PackageManager.PERMISSION_GRANTED;
    }


    /**
     * 如果应用之前请求过此权限但用户拒绝了请求，此方法将返回 true。
     * 如果用户在过去拒绝了权限请求，并在权限请求系统对话框中选择了 Don't ask again 选项，此方法将返回 false。
     * 如果设备规范禁止应用具有该权限，此方法也会返回 false。
     *
     * @param activity
     * @param permission
     */
    public static boolean shouldShowRequestPermissionRationale(Activity activity, String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }

    /**
     * 应用可以通过这个方法动态申请权限，调用后会弹出一个对话框提示用户授权所申请的权限。
     *
     * @param activity
     * @param permission
     * @param requestCode
     */
    public static void requestPermissions(Activity activity, String[] permission, int requestCode) {
        ActivityCompat.requestPermissions(activity, permission, requestCode);
    }

}
