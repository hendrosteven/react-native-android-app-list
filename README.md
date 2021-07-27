
# react-native-android-app-list
This library will return all applications with its permissions in Android device

## Getting started

`$ npm install react-native-android-app-list --save`

### Mostly automatic installation

`$ react-native link react-native-android-app-list`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-android-app-list` and add `RNAndroidAppList.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNAndroidAppList.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up your application file `android/app/src/main/java/[...]/MainActivity.java` or `android/app/src/main/java/[...]/MainApplication.java` depending on your project.
  - Add `import com.reactlibrary.RNAndroidAppListPackage;` to the imports at the top of the file
  - Add `new RNAndroidAppListPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-android-app-list'
  	project(':react-native-android-app-list').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-android-app-list/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-android-app-list')
  	```

## Usage
```javascript
import RNAndroidAppList from 'react-native-android-app-list';

// TODO: What to do with the module?
RNAndroidAppList.getAllPermissions().then(data=>{
    console.log(data);
}); 
```
  
