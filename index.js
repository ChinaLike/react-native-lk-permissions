import { NativeModules, Platform } from 'react-native'

const RNPermission = NativeModules.RNPermission

/**
 * 检测单个权限
 */
checkSelfPermission = (permission, callback) => {
  if (Platform.OS == 'android') {
    RNPermission.checkSelfPermission(permission, callback)
  } else {
    callback(true)
  }
}

/**
 * 检测多个权限
 */
checkSelfPermissions = (permissions, callback) => {
  if (permissions instanceof Array) {
    if (Platform.OS == 'android') {
      RNPermission.checkSelfPermissions(permissions, callback)
    } else {
      callback(true)
    }
  } else {
    alert('请传递多个权限（以数组形式）')
  }
}

/**
 * 用户请求过权限，被拒绝后的状态
 */
shouldShowRequestPermissionRationale = (permission, callback) => {
  if (Platform.OS == 'android') {
    RNPermission.shouldShowRequestPermissionRationale(permission, callback)
  } else {
    callback(true)
  }
}

/**
 * 请求单个权限
 */
requestPermission = (permission, callback) => {
  if (Platform.OS == 'android') {
    RNPermission.requestPermission(permission, callback)
  } else {
    callback(true)
  }
}

/**
 * 请求权限组
 */
requestPermissions = (permissions, callback) => {
  if (permissions instanceof Array) {
    if (Platform.OS == 'android') {
      RNPermission.requestPermissions(permissions, callback)
    } else {
      callback(true)
    }
  } else {
    alert('请传递多个权限（以数组形式）')
  }
}

/**
 * 打开系统设置界面
 */
appSetting = () => {
  if (Platform.OS == 'android') {
    RNPermission.appSetting()
  } else {
  }
}

const Permission = {
  ...RNPermission,
  checkSelfPermission,
  checkSelfPermissions,
  shouldShowRequestPermissionRationale,
  requestPermission,
  requestPermissions,
  appSetting
}

export default Permission
