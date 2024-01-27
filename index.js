/**
 * @format
 */

import {AppRegistry} from 'react-native';
import App from './App';
import {name as appName} from './app.json';
import SaveReactNativeLocation from './headless-js-tasks/SaveReactNativeLocation';
import SaveAndroidLocation from './headless-js-tasks/SaveAndroidLocation';

AppRegistry.registerComponent(appName, () => App);
AppRegistry.registerHeadlessTask('SaveReactNativeLocation', () => SaveReactNativeLocation)
AppRegistry.registerHeadlessTask('SaveAndroidLocation', () => SaveAndroidLocation)
