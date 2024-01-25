import {NativeModules} from 'react-native';
const {FileStorageModule} = NativeModules;
interface FileStorageInterface {
    saveDataToFile(data: String, file: String): void;
}
export default FileStorageModule as FileStorageInterface;
