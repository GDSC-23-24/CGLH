import React, { useEffect } from 'react';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { NavigationContainer } from '@react-navigation/native';
import RootStack from './screens/RootStack';
import SplashScreen from 'react-native-splash-screen';

import Welcome from './screens/Welcome';
import Login from './screens/Login';
import SignUp from './screens/SignUp';
import adminScreen from './screens/adminScreen';
import MainTab from './screens/MainTab';
import Splash from './screens/Splash';

const Stack = createNativeStackNavigator();

export default function App() {
    useEffect(() => {
      SplashScreen.hide();
    }, []);

    return (
        <NavigationContainer>
            <Stack.Navigator initialRouteName="Splash" screenOptions={{ headerShown: false }}>
                <Stack.Screen name="Splash" component={Splash} />
                <Stack.Screen name="Welcome" component={Welcome} />
                <Stack.Screen name="Login" component={Login} />
                <Stack.Screen name="Sign up" component={SignUp} />
                <Stack.Screen name="adminScreen" component={adminScreen} />
                <Stack.Screen name="MainTab" component={MainTab} />
            </Stack.Navigator>
        </NavigationContainer> 
    )
}
