import React, { useState, useEffect } from 'react';
import { View, StyleSheet, Button } from 'react-native';
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
        <View style={styles.block}>
            <PickerScreen onCityChange={handleCityChange} onDistrictChange={handleDistrictChange} selectedCity = {selectedState} selectedDistrict = {selectedCenterName}/>
            <DateScreen onDateChange={handleDateChange} selectedDate = {selectedDate} />

            <Button title="데이터 전송" onPress={handleSendData} />
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: 'white',
    },

    block: {},
});

export default ApplicationScreen;
