import React from 'react';
import { View, Text, StyleSheet, Button, Image } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import img from './main.png';


export default function Welcome() {
  const navigation = useNavigation();

  const handleLoginPress = () => {
    navigation.navigate('Login');
  };

  const handleSignUpPress = () => {
    navigation.navigate('Sign up');
  };

  return (
    <View style={styles.container}>
      
      <Image source={img} style={styles.image} />
      <Text style={styles.title}>책임감 있는 소비와 생산</Text>
      <Text style={styles.subtitle}>함께하시겠습니까?</Text>
      <View style={styles.buttonContainer}>
        <View style={styles.button}>
          <Button title="Login" onPress={handleLoginPress} color="#4B8A08" />
        </View>
        <View style={styles.button}>
          <Button title="Sign Up" onPress={handleSignUpPress} color="#4B8A08" />
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#f5f5f5',
    paddingHorizontal: 20,
  },
  image: {
    height: 500,
    width: 500,
    marginBottom: 20,
  },
  title: {
    color: 'black',
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 10,
    textAlign: 'center',
  },
  subtitle: {
    color: 'black',
    fontSize: 24,
    fontWeight: 'bold',
    margin: 10,
    textAlign: 'center',
  },
  buttonContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '80%',
    paddingHorizontal: 40,
    marginTop: 10,
    marginBottom: 30,
  },
  button: {
    flex: 1,
    marginHorizontal: 5,
  },
});

// Rest of the code remains the same
