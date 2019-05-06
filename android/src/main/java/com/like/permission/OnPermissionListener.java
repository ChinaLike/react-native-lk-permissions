package com.like.permission;

/**
 * 作者：like on 2019-05-06 11:55
 * <p>
 * 邮箱：like@tydic.com
 * <p>
 * 描述：权限检测监听
 */
public interface OnPermissionListener {
    /**
     * 检测结果状态
     * @param status
     */
    void status(boolean status);

}
