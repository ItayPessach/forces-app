import {NativeModules} from 'react-native';
const {SaveLocationBackgroundModule} = NativeModules;

interface SaveLocationBackgroundInterface {
    startReactNativeLocationSampling: Function;
    stopReactNativeLocationSampling: Function;
    startAndroidLocationSampling: Function;
    stopAndroidLocationSampling: Function;
}

export default SaveLocationBackgroundModule as SaveLocationBackgroundInterface;
