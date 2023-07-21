import React, { useEffect, useState } from 'react';
import { StyleSheet, View } from 'react-native';
import { Calendar } from 'react-native-calendars';
import axios from 'axios';
import { useRecoilState } from "recoil"

import { tokens } from "../toServer/atom"

const createAxiosObject = () => {
  // AxiosObject
  const { CancelToken } = axios;
  const source = CancelToken.source();
  const axiosObject = axios.create({
    baseURL: 'http://192.168.0.6:8080/',
    headers: {
      Accept: 'application/json',
    },
    cancelToken: source.token,
  });

  const timeout = setTimeout(() => {
    source.cancel(-1);
  }, 10000);

  axiosObject.interceptors.response.use(
    (response) => {
      clearTimeout(timeout);
      return response;
    },
    (error) => {
      return Promise.reject(error);
    }
  );

  return axiosObject;
};

const AdminMy = () => {
  const [markedDates, setMarkedDates] = useState({});
  const [token, setToken] = useRecoilState(tokens); 

  useEffect(() => {
    fetchSchedule();
  }, []);

  const fetchSchedule = async () => {
      const axiosObject = createAxiosObject()
      await axiosObject
          .get("/api/waste/schedule/수원시 농업기술센터", {headers: {Accept: "application/json",Authorization:token},})
          .then(response => {
              console.log("성공")
              console.log(response.data)
              const scheduleData = response.data;
              const markedDatesData = {};
              // Extract requestDate for schedules with status 'PERMIT' and mark them on the calendar
              scheduleData.forEach((schedule) => {
              if (schedule.status === 'PERMIT') {
                const date = schedule.requestDate.split('T')[0]; // Extract the date portion from requestDate
                markedDatesData[date] = { selected: true }; // Use the ISO 8601 date format (YYYY-MM-DD)
              }
              
          });
          setMarkedDates(markedDatesData);
          })
          .catch(error => {
              console.log(error)
          })

  };
  

  return (
    <View style={styles.container}>
      <Calendar
        markedDates={markedDates}
        markingType="simple"
        theme={{
          calendarBackground: '#ffffff',
          selectedDayBackgroundColor: '#4B8A08',
          selectedDayTextColor: '#ffffff',
          todayTextColor: '#4B8A08',
          dayTextColor: '#2d4150',
          textDisabledColor: '#d9e1e8',
          arrowColor: '#4B8A08',
          monthTextColor: '#4B8A08',
          indicatorColor: '#4B8A08',
          textDayFontFamily: 'monospace',
          textMonthFontFamily: 'monospace',
          textDayHeaderFontFamily: 'monospace',
          textDayFontSize: 16,
          textMonthFontSize: 16,
          textDayHeaderFontSize: 16,
        }}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
  },
});

export default AdminMy;