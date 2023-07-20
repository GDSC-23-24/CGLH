import React, { useEffect, useState } from 'react';
import { View, Image } from 'react-native';
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";

import IntroduceScreen from "./IntroduceScreen";
import ApplicationScreen from "./ApplicationScreen";
import MyScreen from "./MyScreen";

const Tab = createBottomTabNavigator();

function MainTab() {
    return (
        <Tab.Navigator 
            initialRouteName={ApplicationScreen}
            screenOptions={{
                tabBarActiveTintColor: 'green', 
                tabBarInactiveTintColor: 'black',
            }}
        >
            <Tab.Screen 
                name="소개공간" 
                component={IntroduceScreen}
                options={{
                    tabBarIcon: ({ focused }) => {
                        return (
                            <View style={{ alignContent: "center", justifyContent: "center" }}>
                                <Image
                                    source={
                                        focused
                                        ? require("../../img/introduceFill.png")
                                        : require("../../img/introduce.png")
                                    }
                                    style={{ width: 25, height: 25 }}
                                />
                            </View>
                        )
                    },
                }}
            />
            <Tab.Screen 
                name="파쇄 신청" 
                component={ApplicationScreen}
                options={{
                    tabBarIcon: ({ focused }) => {
                        return (
                            <View style={{ alignContent: "center", justifyContent: "center" }}>
                                <Image
                                    source={
                                        focused
                                        ? require("../../img/applicationFill.png")
                                        : require("../../img/application.png")
                                    }
                                    style={{ width: 25, height: 25 }}
                                />
                            </View>
                        )
                    },
                }}
            />
            <Tab.Screen 
                name="내 정보" 
                component={MyScreen} 
                options={{
                    unmountOnBlur:true,
                    tabBarIcon: ({ focused }) => {
                        return (
                            <View style={{ alignContent: "center", justifyContent: "center" }}>
                                <Image
                                    source={
                                        focused
                                        ? require("../../img/myFill.png")
                                        : require("../../img/my.png")
                                    }
                                    style={{ width: 25, height: 25 }}
                                />
                            </View>
                        )
                    },
                }}
            />
        </Tab.Navigator>
    );
}

export default MainTab;