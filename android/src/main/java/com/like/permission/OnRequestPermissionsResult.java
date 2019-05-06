package com.like.permission;

/**
 * 作者：like on 2019-05-06 13:46
 * <p>
 * 邮箱：like@tydic.com
 * <p>
 * 描述：权限申请回调
 */
public interface OnRequestPermissionsResult {

    void result(int requestCode,String permissions[],int[] grantResults);
}
