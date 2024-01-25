import {NativeModules} from 'react-native';
const {FusedLocationModule} = NativeModules;

interface FusedLocationInterface {
    getLastLocation: Function;
}

export default FusedLocationModule as FusedLocationInterface;
