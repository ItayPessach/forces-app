import GetLocation from 'react-native-get-location';
import {Location, MapSampler, SavedLocation} from '../types';
import {AppState} from 'react-native';
import FileStorageModule from '../native-modules/FileStorageModule';
import FusedLocationModule from '../native-modules/FusedLocationModule';

export const getLocationByReactLibrary = async (): Promise<
  Location | undefined
> => {
  try {
    const location = await GetLocation.getCurrentPosition({
      enableHighAccuracy: true,
      timeout: 60000,
    });

    return {latitude: location.latitude, longitude: location.longitude};
  } catch (err: any) {
    console.error(err.code, err.message);
  }
};

export const getLocation = async (currentMapSampler: MapSampler) => {
  if (currentMapSampler === MapSampler.REACT_NATIVE) {
    return await getLocationByReactLibrary();
  } else {
    try {
      return await FusedLocationModule.getLastLocation();
    } catch (err: any) {
      console.error(err.code, err.message);
    }
  }
}

export const saveLocationToJsonFile = async (currentMapSampler: MapSampler) => {
  const location = await getLocation(currentMapSampler);
  console.log(location);

  const savedLocation: SavedLocation = {
    location: location!,
    display: AppState.currentState,
    date: new Date().toLocaleString('he-it')
  }

  console.log(savedLocation);

  FileStorageModule.saveDataToFile(
      JSON.stringify(savedLocation),
      'locations.json',
  );
}
