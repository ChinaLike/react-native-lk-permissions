import { NativeModules, Platform } from 'react-native'

const RNPermission = NativeModules.RNPermission

/**
 * 检测单个权限
 */
checkSelfPermission = (permission, callback) => {
  if (Platform.OS == 'android') {
    RNPermission.checkSelfPermission(permission, callback)
  } else {
    let obj = {}
    obj[`${permission}`] = true
    callback(obj)
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
      let result = {}
      for (let index = 0; index < permissions.length; index++) {
        const element = permissions[index];
        result[`${element}`] = true
      }
      callback && callback(result)
    }
  } else {
    throw("Parameter format is incorrect, please check again!")
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
    let obj = {}
    obj[`${permission}`] = true
    callback(obj)
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
      let result = {}
      for (let index = 0; index < permissions.length; index++) {
        const element = permissions[index];
        result[`${element}`] = true
      }
      callback && callback(result)
    }
  } else {
    throw("Parameter format is incorrect, please check again!")
  }
}

/**
 * 查看悬浮窗状态
 */
checkFloatWindowPermissions = (callback)=>{
  if (Platform.OS == 'android') {
    RNPermission.checkFloatWindowPermissions(callback)
  }else{
    callback && callback(true)
  }
}

/**
 * 打开悬浮窗设置
 */
openFloatWindowSetting = ()=>{
  if (Platform.OS == 'android') {
    RNPermission.openFloatWindowSetting()
  }
}

/**
 * 打开系统设置界面
 */
openSetting = () => {
  if (Platform.OS == 'android') {
    RNPermission.openSetting()
  }
}

const Permission = {
  ...RNPermission,
  checkSelfPermission,
  checkSelfPermissions,
  shouldShowRequestPermissionRationale,
  requestPermission,
  requestPermissions,
  checkFloatWindowPermissions,
  openFloatWindowSetting,
  openSetting
}

export default Permission
