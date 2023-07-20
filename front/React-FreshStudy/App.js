import React, { useEffect } from 'react';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { NavigationContainer } from '@react-navigation/native';
import SplashScreen from 'react-native-splash-screen';
import { RecoilRoot } from 'recoil';

import Welcome from './screens/Welcome';
import Login from './screens/loginProcess/Login';
import SignUp from './screens/loginProcess/SignUp';
import AdminScreen from './screens/adminSrc/AdminScreen';

import Splash from './screens/Splash';

const Stack = createNativeStackNavigator();

export default function App() {
    useEffect(() => {
      SplashScreen.hide();
    }, []);

    return (
      <RecoilRoot>
        <NavigationContainer>
            <Stack.Navigator initialRouteName="Splash" screenOptions={{ headerShown: false }}>
                <Stack.Screen name="Splash" component={Splash} />
                <Stack.Screen name="Welcome" component={Welcome} />
                <Stack.Screen name="Login" component={Login} />
                <Stack.Screen name="Sign up" component={SignUp} />
                <Stack.Screen name="AdminScreen" component={AdminScreen} />
            </Stack.Navigator>
        </NavigationContainer> 
      </RecoilRoot>
    )
}
