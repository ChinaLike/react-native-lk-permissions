# react-native-lk-permission

    Android危险权限管理、悬浮窗权限管理，目前已经适配市场主流的手机。

## 如何使用

- `$ npm install react-native-lk-permission --save`或`$ yarn add react-native-lk-permission --save`

- 自动配置（针对 React Native 0.59 and lower）

  `$ react-native link react-native-lk-permission`

- 手动配置（针对 React Native 0.59 and lower）

  + Open up `android/app/src/main/java/[...]/MainActivity.java`

    * Add `import com.like.permission.RNPermissionPackage;` to the imports at the top of the file
    * Add `new RNPermissionPackage()` to the list returned by the `getPackages()` method

  + Append the following lines to `android/settings.gradle`:
    ```
    include ':react-native-permission'
    project(':react-native-permission').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-permission/android')
    ```
  + Insert the following lines inside the dependencies block in `android/app/build.gradle`:
   ```
     implementation project(':react-native-permission')
   ```

- 注意（任何 ReactNative 都需要配置）

    在`MainActivity.java`中引入`import com.like.permission.PermissionUtil;`,并添加如下代码

        ```
            @Override
            public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                PermissionUtil.getInstance().onRequestPermissionsResult(requestCode,permissions,grantResults);
            }
        ```

## API

- 常量

    | 常量名                            | 权限含义                                                   |
    | -------------------------------- | --------------------------------------------------------- |
    | RECORD_AUDIO                     | 允许程序录制声音通过手机或耳机的麦克                            |
    | GET_ACCOUNTS                     | 允许程序访问账户Gmail列表                                    |
    | READ_PHONE_STATE                 | 允许程序访问电话状态                                         |
    | CALL_PHONE                       | 允许程序从非系统拨号器里拨打电话                               |
    | CAMERA                           | 允许程序访问摄像头进行拍照                                    |
    | ACCESS_FINE_LOCATION             | 允许程序通过GPS芯片接收卫星的定位信息                          |
    | ACCESS_COARSE_LOCATION           | 允许程序通过WiFi或移动基站的方式获取用户错略的经纬度信息          |
    | READ_EXTERNAL_STORAGE            | 程序可以读取设备外部存储空间                                  |
    | WRITE_EXTERNAL_STORAGE           | 允许程序写入外部存储                                         |
    | READ_CALENDAR                    | 允许程序读取用户的日程信息                                    |
    | WRITE_CALENDAR                   | 允许程序写入日程，但不可读取                                  |
    | READ_CONTACTS                    | 允许程序访问联系人通讯录信息                                  |
    | WRITE_CONTACTS                   | 写入联系人,但不可读取                                        |
    | READ_CALL_LOG                    | 读取通话记录                                               |
    | WRITE_CALL_LOG                   | 允许程序写入（但是不能读）用户的联系人数据                      |
    | ADD_VOICEMAIL                    | 允许一个应用程序添加语音邮件系统                              |
    | USE_SIP                          | 允许程序使用SIP视频服务                                     |
    | PROCESS_OUTGOING_CALLS           | 允许程序监视，修改或放弃播出电话                              |
    | BODY_SENSORS                     | 自身状态                                                  |
    | SEND_SMS                         | 允许程序发送短信                                           |
    | RECEIVE_SMS                      | 允许程序接收短信                                           |
    | READ_SMS                         | 允许程序读取短信内容                                        |
    | RECEIVE_WAP_PUSH                 | 允许程序接收WAP PUSH信息                                   |
    | RECEIVE_MMS                      | 允许程序接收彩信                                           |

    ---

- checkSelfPermission(params,callback)  检测单个权限
  + params 权限字符串，可直接使用常量中参数
  + callback 回调，对象形式，key值为权限字符串，value是布尔型
  + 示例
  ```javascript
    import Permission from 'react-native-lk-permission'

    Permission.checkSelfPermission(Permission.RECORD_AUDIO, result => {
      console.log(result)
    })
    //{"android.permission.RECORD_AUDIO":false}
  ```
- checkSelfPermissions(params,callback)  检测多个权限
  + params 权限数组，可直接使用常量中参数
  + callback 回调，对象形式，key值为权限字符串，value是布尔型
  + 示例
  ```javascript
    import Permission from 'react-native-lk-permission'

    Permission.checkSelfPermissions([ Permission.RECORD_AUDIO, Permission.CALL_PHONE, Permission.SEND_SMS ], result => {
      console.log(result)
    })
    //{"android.permission.RECORD_AUDIO":false,"android.permission.CALL_PHONE":true,"android.permission.SEND_SMS":false}
  ```
- shouldShowRequestPermissionRationale(callback)  用户请求过权限，被拒绝后的状态
  + params 权限字符串，可直接使用常量中参数
  + callback 布尔型
  + 示例
  ```javascript
    import Permission from 'react-native-lk-permission'

    Permission.shouldShowRequestPermissionRationale(Permission.RECORD_AUDIO,status => {
      console.log(result)
    })
    //false
  ```
- requestPermission(params,callback)  请求单个权限
  + params 权限字符串，可直接使用常量中参数
  + callback 回调，对象形式，key值为权限字符串，value是布尔型
  + 示例
  ```javascript
    import Permission from 'react-native-lk-permission'

    Permission.requestPermission(Permission.RECORD_AUDIO, result => {
      console.log(result)
    })
    //{"android.permission.RECORD_AUDIO":false}
  ```
- requestPermissions(params,callback)  请求权限组
   + params 权限数组，可直接使用常量中参数
   + callback 回调，对象形式，key值为权限字符串，value是布尔型
   + 示例
   ```javascript
    import Permission from 'react-native-lk-permission'

    Permission.requestPermissions([ Permission.RECORD_AUDIO, Permission.CALL_PHONE, Permission.SEND_SMS ], result => {
      console.log(result)
    })
    //{"android.permission.RECORD_AUDIO":true,"android.permission.CALL_PHONE":true,"android.permission.SEND_SMS":false}
  ```
- checkFloatWindowPermissions(callback)  查看悬浮窗状态
  + callback 布尔型，true表示拥有悬浮窗权限，false没有权限
  + 示例
  ```javascript
    import Permission from 'react-native-lk-permission'

    Permission.checkFloatWindowPermissions(status => {
      console.log(result)
    })
    //true
  ```
- openFloatWindowSetting()  打开悬浮窗设置
  + 示例
  ```javascript
    import Permission from 'react-native-lk-permission'

    Permission.openFloatWindowSetting()
  ```
- openSetting()  打开系统设置界面
  + 示例
  ```javascript
    import Permission from 'react-native-lk-permission'

    Permission.openSetting()
  ```
