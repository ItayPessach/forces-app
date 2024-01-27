import {useState} from 'react';
import {StyleSheet, Switch, Text, View} from 'react-native';
import {MapSampler} from '../types';
import MapSamplerButton from '../components/MapSamplerButton';
import SaveLocationBackgroundModule from '../native-modules/SaveLocationBackgroundModule';
import saveLocationBackgroundModule from '../native-modules/SaveLocationBackgroundModule';

function Body() {
    const [currentMapSampler, setCurrentMapSampler] = useState(
        MapSampler.REACT_NATIVE,
    );

    const [isLocationEnabled, setIsLocationEnabled] = useState(false);

    const setMapSampler = (mapSampler: MapSampler) => {
        setCurrentMapSampler(mapSampler);
        if (mapSampler !== currentMapSampler && isLocationEnabled) {
            if (mapSampler === MapSampler.REACT_NATIVE) {
                saveLocationBackgroundModule.stopAndroidLocationSampling();
                saveLocationBackgroundModule.startReactNativeLocationSampling();
            } else {
                saveLocationBackgroundModule.stopReactNativeLocationSampling();
                saveLocationBackgroundModule.startAndroidLocationSampling();
            }
        }
    }

    const toggleIsLocationEnabled = async (newValue: boolean) => {
        setIsLocationEnabled(newValue);
        if (newValue) {
            if (currentMapSampler === MapSampler.REACT_NATIVE) {
                saveLocationBackgroundModule.startReactNativeLocationSampling()
            } else {
                saveLocationBackgroundModule.startAndroidLocationSampling()
            }
        } else {
            SaveLocationBackgroundModule.stopReactNativeLocationSampling();
            SaveLocationBackgroundModule.stopAndroidLocationSampling();
        }
    };

    return (
        <View style={styles.body}>
            <View style={styles.activateLocationContainer}>
                <Text style={{fontSize: 20, fontWeight: 'bold'}}>
                    Activate location Service
                </Text>
                <Switch
                    style={{marginTop: 2}}
                    trackColor={{false: '#767577', true: '#81b0ff'}}
                    thumbColor="#f4f3f4"
                    onValueChange={value => toggleIsLocationEnabled(value)}
                    value={isLocationEnabled}
                />
            </View>
            <View style={styles.bodyContainer}>
                <Text style={styles.secondaryTitle}>
                    Choose Your Preferred Map Sampler
                </Text>
                <View style={styles.mapSamplerContainer}>
                    <MapSamplerButton
                        text="Android Native"
                        isActive={currentMapSampler === MapSampler.NATIVE_ANDROID}
                        mapSampler={MapSampler.NATIVE_ANDROID}
                        onClick={setMapSampler}
                    />

                    <MapSamplerButton
                        text="React Native"
                        isActive={currentMapSampler === MapSampler.REACT_NATIVE}
                        mapSampler={MapSampler.REACT_NATIVE}
                        onClick={setMapSampler}
                    />
                </View>
            </View>
        </View>
    )
}

const styles = StyleSheet.create({
    body: {
        width: '100%',
        height: '100%',
        backgroundColor: '#bae6fd',
    },
    activateLocationContainer: {
        display: 'flex',
        flexDirection: 'row',
        justifyContent: 'center',
        marginTop: 16,
    },
    bodyContainer: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        display: 'flex',
        gap: 16,
    },
    secondaryTitle: {
        fontWeight: '600',
        fontSize: 16,
        color: 'white',
    },
    mapSamplerContainer: {
        display: 'flex',
        flexDirection: 'row',
        columnGap: 24,
    },
})

export default Body
