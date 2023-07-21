import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { StyleSheet, View, Text, Button, ScrollView } from 'react-native';

import { tokens } from '../toServer/atom';
import { useRecoilState } from 'recoil';

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

function AdminApplicationDetails() {
  const [applicationData, setApplicationData] = useState([]);
  const [token, setToken] = useRecoilState(tokens); 

  useEffect(() => {
    fetchApplicationDetails();
  }, []);

  const fetchApplicationDetails = async () => {
    try {
      const axiosObject = createAxiosObject();
      const response = await axiosObject.get('/api/waste/schedule/수원시 농업기술센터',{headers: {Accept: "application/json",Authorization:token},}); // Replace with the actual center name
      setApplicationData(response.data);
    } catch (error) {
      console.log('Error fetching application details:', error);
    }
  }

  // 승인 post
  const PERMITApplication = async (id) => {
    try {
        const data = {
          wasteId: id,
          status: 'PERMIT',
        };
  
        const axiosObject = createAxiosObject();
        await axiosObject.post('/api/waste/update', data,{headers: {Accept: "application/json",Authorization:token},});
        fetchApplicationDetails();
        console.log('Application PERMIT');
        // Add logic to handle the success response, if needed
      } catch (error) {
        console.log('Error rejecting application:', error);
        // Add logic to handle the error, if needed
      }
  };

  // 거절 post
  const REFUSEApplication = async (id) => {
    try {
      const data = {
        wasteId: id,
        status: 'REFUSE',
      };

      const axiosObject = createAxiosObject();
      await axiosObject.post('/api/waste/update', data,{headers: {Accept: "application/json",Authorization:token},});
      fetchApplicationDetails();
      console.log('Application rejected');
      setToken(response.data)
      // Add logic to handle the success response, if needed
    } catch (error) {
      console.log('Error rejecting application:', error);
      // Add logic to handle the error, if needed
    }
  };

  return (
    <ScrollView style={styles.container}>
      {applicationData.map((application, index) => (
        <View key={application.id} style={[styles.applicationContainer, index > 0 && styles.borderTop]}>
          <Text style={styles.label}>no: {application.id}</Text>
          <Text style={styles.label}>닉네임: {application.nickname}</Text>
          <Text style={styles.label}>지역명: {application.centerName}</Text>
          <Text style={styles.label}>센터명: {application.centerName}</Text>
          <Text style={styles.label}>신청일자: {application.requestDate}</Text>
          
          {application.status === 'WAITING' ? (
            <View style={styles.buttonContainer}>
              <Button
                title="승인"
                onPress={() => PERMITApplication(application.id)}
                color="#4B8A08"
              />
              <Button
                title="반려"
                onPress={() => REFUSEApplication(application.id)}
                color="#8b0808"
              />
            </View>
          ) : (
            <Text style={styles.confirmationText}>
              {application.status === 'PERMIT' ? '승인 완료' : '반려 완료'}
            </Text>
          )}
        </View>
      ))}
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  applicationContainer: {
    justifyContent: 'flex-start', // Align items to the left
    alignItems: 'flex-start', // Align items to the left
    paddingVertical: 20,
    borderWidth: 1, // Add a border
    borderColor: '#ccc', // Border color
    borderRadius: 5, // Border radius for rounded corners
  },
  borderTop: {
    borderTopWidth: 1, // Add top border for all but the first item
    borderColor: '#ccc', // Border color
  },
  label: {
    margin: 15,
    fontSize: 15,
    textAlign: 'left',
    fontWeight: 'bold',
  },
  buttonContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '30%',
    margin:15
  },
  confirmationText: {
    fontSize: 18,
    fontWeight: 'bold',
    margin: 15,
    color: '#4B8A08',

  },
});

export default AdminApplicationDetails;
