import { AppStateStatus } from "react-native";

export interface Location {
  latitude: number;
  longitude: number;
}

export enum MapSampler {
  'NATIVE_ANDROID',
  'REACT_NATIVE',
}

export interface SavedLocation {
  location: Location;
  display: AppStateStatus;
  date: string;
}
