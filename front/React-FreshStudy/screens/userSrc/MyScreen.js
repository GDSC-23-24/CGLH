import React, { useEffect, useState } from 'react';
import { View, StyleSheet, Text, ScrollView, Button } from 'react-native';
import { useRecoilState } from "recoil"

import { createAxiosObject } from '../toServer/API_BASE';
import { tokens } from "../toServer/atom"

function MyScreen() {
    const [applicationData, setApplicationData] = useState([]);
    const [token, setToken] = useRecoilState(tokens); 

    useEffect(() => {
        fetchApplicationDetails();
     }, []);

    const fetchApplicationDetails = async () => {
      try {
        const axiosObject = createAxiosObject();
        const response = await axiosObject.get('/api/waste/list', {headers: {Accept: "application/json",Authorization:token},}); // Replace with the actual center name
          setApplicationData(response.data);
      } catch (error) {
        console.log('Error fetching application details:', error);
      }
    };

  const DeleteApplication = async (id) => {
    try {
        // const data = {
        //   wasteId: id,
        // };
  
        const axiosObject = createAxiosObject();
        const url = "/api/waste/" + id;
        await axiosObject.delete(url, {headers: {Accept: "application/json",Authorization:token},});
        fetchApplicationDetails();
        console.log('Application DELECT');
        // Add logic to handle the success response, if needed
      } catch (error) {
        console.log('Error rejecting application:', error);
        // Add logic to handle the error, if needed
      }
  };

  return (
    <ScrollView style={styles.container}>
      {applicationData.map((application) => {
        return(
            <View key={application.id} style={styles.applicationContainer}>
                <Text style={styles.label}>No: {application.id}</Text>
                <Text style={styles.label}>닉네임: {application.nickname}</Text>
                <Text style={styles.label}>지역명: {application.state}</Text>
                <Text style={styles.label}>센터명: {application.centerName}</Text>
                <Text style={styles.label}>신청일자: {application.requestDate}</Text>
                <Text style={styles.label}>상태: {application.status}</Text>
                <View style={styles.buttonContainer}>
                    <Button
                        title="신청 취소"
                        onPress={() =>DeleteApplication(application.id)}
                        color="#4B8A08"
                    />
                </View>
            </View>
        )
        })}
    </ScrollView>
  );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
    applicationContainer: {
        justifyContent: 'center',
        alignItems: 'center',
        paddingVertical: 20,
        backgroundColor: '#F5F5F5',
        borderRadius: 8,
        marginVertical: 10,
        marginHorizontal: 20,
        backgroundColor: '#FFE4C4'
    },
    label: {
        marginBottom: 10,
        fontSize: 16,
        fontWeight: 'bold',
    },
    buttonContainer: {
        flexDirection: 'row',
        justifyContent: 'center',
        width: '60%',
        marginTop: 10,
    },
    
});

export default MyScreen;
