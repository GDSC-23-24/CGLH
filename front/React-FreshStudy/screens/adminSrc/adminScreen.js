import React from 'react';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { View, Text, StyleSheet } from 'react-native';

import AdminApplicationDetails from './AdminApplicationDetails';
import AdminMy from './AdminMy';

const Tab = createBottomTabNavigator();

const AdminScreen = () => {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Admin Screen</Text>
      <Text style={styles.subtitle}>Welcome</Text>
      <Text style={styles.description}>
        여기서 당신은 유저정보와 신청 내역을 확인하고 관리 할 수 있습니다.
      </Text>
      <View style={styles.tabContainer}>
        <Tab.Navigator>
          <Tab.Screen name="유저 정보와 신청 내역 관리" component={AdminApplicationDetails} />
          <Tab.Screen name="관리자 정보" component={AdminMy} />
        </Tab.Navigator>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#f5f5f5',
    paddingHorizontal: 20,
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 10,
  },
  subtitle: {
    fontSize: 18,
    marginBottom: 20,
  },
  description: {
    fontSize: 16,
    marginBottom: 20,
    textAlign: 'center',
  },
  tabContainer: {
    flex: 1,
    width: '100%',
  },
});

export default AdminScreen;
