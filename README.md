# react-native-lk-permission

## Getting started

`$ npm install react-native-lk-permission --save`

### Mostly automatic installation

`$ react-native link react-native-lk-permission`

### Manual installation

### Notice

```
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtil.getInstance().onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
```

add to MainActivity.java for Android

#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-permission` and add `RNPermission.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNPermission.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`

- Add `import com.like.permission.RNPermissionPackage;` to the imports at the top of the file
- Add `new RNPermissionPackage()` to the list returned by the `getPackages()` method

2. Append the following lines to `android/settings.gradle`:
   ```
   include ':react-native-permission'
   project(':react-native-permission').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-permission/android')
   ```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
   ```
     compile project(':react-native-permission')
   ```

## Usage

```javascript
import Permission from 'react-native-lk-permission'

// TODO: What to do with the module?
Permission
```
