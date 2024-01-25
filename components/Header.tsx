import { StyleSheet, Text, View } from 'react-native';

function Header() {
   return  (
       <View style={styles.header}>
           <Text style={styles.title}>Forces Location App</Text>
       </View>
   )
}

const styles = StyleSheet.create({
    header: {
        height: 60,
        backgroundColor: '#00C2E8',
        width: '100%',
        paddingTop: 16
    },
    title: {
        textAlign: 'center',
        color: 'white',
        fontSize: 20,
        fontWeight: 'bold',
    },
})

export default Header
