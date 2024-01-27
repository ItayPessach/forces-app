import {StyleSheet, Text, TouchableOpacity} from 'react-native';
import {MapSampler} from '../types';
import theme from '../theme'
interface Props {
  text: string;
  isActive: boolean;
  mapSampler: MapSampler;
  onClick: (newMapSampler: MapSampler) => void;
}

function MapSamplerButton({text, isActive, mapSampler, onClick}: Props) {
  return (
    <TouchableOpacity
      style={[
        styles.mapSamplerButton,
        isActive
          ? {backgroundColor: theme.colors.primary, transform: [{scale: 0.95}]}
          : {backgroundColor: 'white'},
      ]}
      onPress={() => onClick(mapSampler)}>
      <Text style={{color: 'black', fontSize: 16}}>{text}</Text>
    </TouchableOpacity>
  );
}

export default MapSamplerButton;

const styles = StyleSheet.create({
  mapSamplerButton: {
    width: 150,
    height: 50,
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 16,
  },
});
