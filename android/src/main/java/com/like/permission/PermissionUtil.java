package com.like.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

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

    private  PermissionUtil(){

    }

    public static PermissionUtil getInstance(){
        if (mPermissionUtil == null){
            mPermissionUtil = new PermissionUtil();
        }
        return mPermissionUtil;
    }

    public void setRequestPermissionsResult(OnRequestPermissionsResult onRequestPermissionsResult){
        this.onRequestPermissionsResult = onRequestPermissionsResult;
    }

    /**
     * 权限申请回调
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void onRequestPermissionsResult(int requestCode,String permissions[],int[] grantResults){
        if (onRequestPermissionsResult != null){
            onRequestPermissionsResult.result(requestCode,permissions,grantResults);
        }
    }

    /**
     * 检查应用是否具有某个危险权限。如果应用具有此权限，方法将返回 PackageManager.PERMISSION_GRANTED，并且应用可以继续操作。
     * 如果应用不具有此权限，方法将返回 PackageManager.PERMISSION_DENIED，且应用必须明确向用户要求权限。
     * @param permission
     * @param listener
     */
    public static void checkSelfPermission(Context context , String permission , OnPermissionListener listener){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int type = ContextCompat.checkSelfPermission(context, permission);
            if (listener != null) {
                if (type == PackageManager.PERMISSION_GRANTED) {
                    listener.status(true);
                } else {
                    listener.status(false);
                }
            }
        }else {
            if (listener != null) {
                listener.status(true);
            }
        }
    }

    /**
     * 检测权限组
     * @param context
     * @param permissions
     * @param listener
     */
    public static void checkSelfPermissions(Context context , String[] permissions , OnPermissionListener listener){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean status = true;
            for (String perm : permissions) {
                if (ContextCompat.checkSelfPermission(context, perm) != PackageManager.PERMISSION_GRANTED) {
                    status = false;
                }
            }
            if (listener != null) {
                listener.status(status);
            }
        }else {
            if (listener != null) {
                listener.status(true);
            }
        }
    }

    /**
     * 如果应用之前请求过此权限但用户拒绝了请求，此方法将返回 true。
     * 如果用户在过去拒绝了权限请求，并在权限请求系统对话框中选择了 Don't ask again 选项，此方法将返回 false。
     * 如果设备规范禁止应用具有该权限，此方法也会返回 false。
     * @param activity
     * @param permission
     */
    public static boolean shouldShowRequestPermissionRationale(Activity activity , String permission){
        boolean status = ActivityCompat.shouldShowRequestPermissionRationale(activity,permission);
        return status;
    }

    /**
     * 应用可以通过这个方法动态申请权限，调用后会弹出一个对话框提示用户授权所申请的权限。
     * @param activity
     * @param permission
     * @param requestCode
     */
    public static void requestPermissions(Activity activity , String[] permission , int requestCode){
        ActivityCompat.requestPermissions(activity,permission,requestCode);
    }

}
