import React, { useState, useEffect } from 'react';
import { View, StyleSheet, Button, SafeAreaView, Text } from 'react-native';
import { useRecoilState } from "recoil"

import PickerScreen from './PickerScreen';
import DateScreen from './DateScreen';
import { createAxiosObject } from '../toServer/API_BASE';
import { tokens } from "../toServer/atom"

import {fetchApplicationDetails} from './MyScreen'

function ApplicationScreen() {
    const [selectedState, setSelectedCity] = useState(null);
    const [selectedCenterName, setSelectedDistrict] = useState(null);
    const [selectedDate, setSelectedDate] = useState(null);
    
    const [token, setToken] = useRecoilState(tokens); 
    
    const handleCityChange = (state) => {
        setSelectedCity(state);
    };

    const handleDistrictChange = (centerName) => {
        setSelectedDistrict(centerName);
    };

    const handleDateChange = (requestDate) => {
        setSelectedDate(requestDate);
    };

    const handleSendData = async  () => {
        const data = {
            state : selectedState,
            centerName : selectedCenterName,
            requestDate : selectedDate,
          };

          console.log(data)

          console.log(typeof(data.requestDate))

          const axiosObject = createAxiosObject()
          await axiosObject
              .post("api/waste", data, {headers: {Accept: "application/json",Authorization:token},})
              .then(response => {
                  console.log("성공")
                  console.log(response)
              })
              .catch(error => {
                  console.log(error)
              })
    };



    return (
        <SafeAreaView style={styles.safeContainer}>
            <Text style={styles.title}> 신청할 구역과 날짜를 선택해주세요.</Text>
            <View style={styles.container}>
                <View style={styles.pickerView}>
                    <PickerScreen
                        onCityChange={handleCityChange}
                        onDistrictChange={handleDistrictChange}
                        selectedCity={selectedState}
                        selectedDistrict={selectedCenterName}
                    />
                </View>
                <View style={styles.dateView}>
                    <DateScreen
                        onDateChange={handleDateChange}
                        selectedDate={selectedDate}
                    />
                </View>
                <View style={styles.buttonView}>
                    <Button
                        title="데이터 전송"
                        onPress={handleSendData}
                        color="#4B8A08" // Change the text color of the button
                    />
                </View>
            </View>
        </SafeAreaView>
    );
}

const styles = StyleSheet.create({
    title: {
        fontSize: 24,
        fontWeight: 'bold',
        marginTop: 20,
        marginLeft: 10,
        marginBottom: -20,
      },
    safeContainer: {
        flex: 1,
    },
    container: {
        flex: 1,
        margin: '10%',
        backgroundColor: 'white',
        paddingVertical: 20,
        borderWidth: 2, 
        borderColor: '#ccc',
        borderRadius: 5,
    },
    pickerView: {
        flex: 4,
    },
    dateView: {
        flex: 4,
        //backgroundColor: '#4A994E',
    },
    buttonView: {
        flex: 2,
        justifyContent: 'center', 
        marginLeft: 50,
        marginRight: 50,
        borderRadius: 10, // Add border radius for a rounded look
        
    },
});

export default ApplicationScreen;