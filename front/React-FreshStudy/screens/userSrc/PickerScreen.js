import React, { useState, useEffect } from 'react';
import { View, StyleSheet } from 'react-native';
import { Picker } from '@react-native-picker/picker';

import { createAxiosObject } from '../toServer/API_BASE';


const cities = ["강원도", "경기도", "경상남도", "경상북도", "광주광역시", "대구광역시", "부산광역시", "서울특별시", "세종특별자치시", "울산광역시", "인천광역시", "전라남도", "전라북도", "충청남도", "충청북도"];
let districts = {
  '부산광역시': ['중구', '서구', '동구', "강서구"],
  '서울특별시': ['강남구', '강서구', '서초구'],
  '대구광역시': ['중구', '동구', '남구']
};

function PickerScreen ({onCityChange, onDistrictChange, selectedCity, selectedDistrict}) {
  // const [selectedCity, setSelectedCity] = useState(null);
  // const [selectedDistrict, setSelectedDistrict] = useState(null);
  useEffect(() => {
    fetchApplicationDetails();
  }, []);

  const fetchApplicationDetails = async () => {
  try {
    const axiosObject = createAxiosObject();
    const response = await axiosObject.get('api/v1/locations'); // Replace with the actual center name
    let inArr = response.data
    console.log(inArr)
    let test = {}
    inArr.forEach((item, _) => {
      if(test[item.state] === undefined){
        test[item.state] = [item.name]
      } else{
        test[item.state].push(item.name)
       }
    })
    console.log(test)
    districts = test;
  } catch (error) {
    console.log('Error fetching application details:', error);
  }
  };


  const handleCityChange = (city) => {
    onCityChange(city);
    onDistrictChange(null); // Reset district when city changes)
  };

  return (
    <View style={styles.container}>
      <Picker
        selectedValue={selectedCity}
        onValueChange={handleCityChange}
        style={{ color: "white" }}
      >
        {cities.map((city) => (
          <Picker.Item key={city} label={city} value={city} />
        ))}
      </Picker>

      {selectedCity && (
        <Picker
          selectedValue={selectedDistrict}
          onValueChange={(district) => onDistrictChange(district)}
          style={{ color: "white" }}
        >
          {districts[selectedCity].map((district) => (
            <Picker.Item key={district} label={district} value={district} />
          ))}
        </Picker>
      )}
    </View>
  );
};

const styles = StyleSheet.create({ 
  container: {
      flex: 1,
      justifyContent: 'center',
      //alignItems: 'center',
      backgroundColor: '#4B8A08',
      margin: '10%',
      padding: '10%',
      borderRadius: 10,
      paddingVertical: 12,
      paddingHorizontal: 16,
  },

 block: {

 }
})

export default PickerScreen;