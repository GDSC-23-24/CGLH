import React, { useEffect, useState } from 'react';
import { View, Text, TextInput, Button, onClickConfirmButton } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import { useRecoilState } from "recoil"

import { createAxiosObject } from '../toServer/API_BASE';
import { tokens } from "../toServer/atom"
import RootStack from '../RootStack';

const User = {
  username: 'yunji0604',
  pw: 'test2323@@@',
};

export default function Login() {
  const navigation = useNavigation();

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const [usernameValid, setUsernameValid] = useState(false);
  const [passwordValid, setPasswordValid] = useState(false);
  const [notAllow, setNotAllow] = useState(true);

  const [token, setToken] = useRecoilState(tokens); 

  useEffect(() => {
    if (usernameValid && passwordValid) {
      setNotAllow(false);
    } else {
      setNotAllow(true);
    }
  }, [usernameValid, passwordValid]);

  const handleUsernameChange = (text) => {
    setUsername(text);
    const regex = /^[a-zA-Z0-9._-]{3,}$/;
    if (regex.test(text)) {
      setUsernameValid(true);
    } else {
      setUsernameValid(false);
    }
  };

  const handlePasswordChange = (text) => {
    setPassword(text);
    const regex =
      /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+])(?!.*[^a-zA-z0-9$`~!@$!%*#^?&\\(\\)\-_=+]).{8,20}$/;
    if (regex.test(text)) {
      setPasswordValid(true);
    } else {
      setPasswordValid(false);
    }
  };

  // data 통신 확인 => GET 방식
  useEffect(() => {
    async function fetchMyAPI() {
      const axiosObject = createAxiosObject()
      await axiosObject
          .get("api/v1/locations")
          .then(response => {
              // console.log("성공")
              // console.log(response)
          })
          .catch(error => {
              console.log(error)
          })
    }
    // fetchMyAPI()
    async function fetchMyAPI2() {
      const axiosObject = createAxiosObject()
      await axiosObject
          .get("api/waste/list", {headers: {Accept: "application/json",Authorization:token},})
          .then(response => {
              console.log("성공")
              console.log(response)
          })
          .catch(error => {
              console.log(error)
              // console.log(token)
          })
    }
    fetchMyAPI2();
  }, [])
  // data 통신 확인 => POST 방식 (로그인)
  const handleLoginButton = async () => {
    if (usernameValid && passwordValid) {    

      const data = {
        loginId: username,
        password: password
      };

      const axiosObject = createAxiosObject()
      await axiosObject
          .post("api/member/login", data)
          .then(response => {
              console.log("성공")
              setToken(response.data)
              if(username == "abcde1"){
                navigation.navigate('AdminScreen');
              }
              else{
                navigation.navigate("MainTab");
              }
          })
          .catch(error => {
              console.log(error)
          })
      }
    } 
  
  const handleLoginPress = () => {
    // onClickConfirmButton();
    handleLoginButton();
  };

  return (
    <View>
      <View>
        <Text style={{ marginTop: 87, fontSize: 26, fontWeight: 'bold', color: '#262626' }}> 로그인 하여</Text>
        <Text style={{ fontSize: 26, fontWeight: 'bold', color: '#262626' }}> CGLH를 사용해 보세요 🌱</Text>
      </View>

      <View>
        <Text style={{ marginTop: 17, marginBottom: 5, marginLeft: 10, fontSize: 13, fontWeight: '600', color: '#262626' }}>아이디</Text>
        <View style={{ margin: 10, borderWidth: 1, padding: 5, marginBottom: 10 }}>
          <TextInput
            style={{ height: 40 }}
            placeholder="yunji0604"
            value={username}
            onChangeText={handleUsernameChange}
          />
        </View>
        {!usernameValid && username.length > 0 && <Text>Please enter a valid username.</Text>}
      </View>

      <View style={{ marginTop: 10 }}>
        <Text style={{ marginTop: 1, marginBottom: 5, marginLeft: 10, fontSize: 13, fontWeight: '600', color: '#262626' }}>비밀번호</Text>
        <View style={{ margin: 10, borderWidth: 1, padding: 5 }}>
          <TextInput
            style={{ height: 40 }}
            placeholder="영문, 숫자, 특수문자 포함 8자 이상 입력해주세요."
            secureTextEntry
            value={password}
            onChangeText={handlePasswordChange}
          />
        </View>
        {!passwordValid && password.length > 0 && (
          <Text>Please enter a valid password.</Text>
        )}
      </View>

      <View style={{ marginTop: 20, flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <View style={{ width: 80, height: 40 }}>
          <Button
            title="Login"
            color="#4B8A08"
            onPress={handleLoginPress}
            disabled={!username || !password}
          />
        </View>
      </View>
    </View>
  );
}
