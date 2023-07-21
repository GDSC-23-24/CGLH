import React, { useEffect, useState } from 'react';
import { View, Text, TextInput, Button } from 'react-native';
import { useNavigation } from '@react-navigation/native';

const User = {
  username: 'yunji0604',
  pw: 'test2323@@@',
};

export default function SignUp() {
  const navigation = useNavigation();

  const [loginId, setLoginId] = useState('');
  const [password, setPassword] = useState('');
  const [nickname, setNickname] = useState('');

  const [loginIdValid, setLoginIdValid] = useState(false);
  const [passwordValid, setPasswordValid] = useState(false);
  const [nicknameValid, setNicknameValid] = useState(false);
  const [notAllow, setNotAllow] = useState(true);

  useEffect(() => {
    if (loginIdValid && passwordValid && nicknameValid) {
      setNotAllow(false);
    } else {
      setNotAllow(true);
    }
  }, [loginIdValid, passwordValid, nicknameValid]);

  const handleLoginIdChange = (text) => {
    setLoginId(text);
    const regex = /^[a-zA-Z0-9._-]{3,}$/;
    if (regex.test(text)) {
      setLoginIdValid(true);
    } else {
      setLoginIdValid(false);
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

  const handleNicknameChange = (text) => {
    setNickname(text);
   
    if (text.length > 0) {
      setNicknameValid(true);
    } else {
      setNicknameValid(false);
    }
  };

  const handleSignUpButton = async () => {
    if (loginIdValid && passwordValid && nicknameValid) {
      try {
        const response = await fetch('http://localhost:8080/api/user/signup', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            loginId: loginId,
            password: password,
            nickname: nickname,
          }),
        });
  
        if (response.ok) {
          alert('회원가입이 완료되었습니다.');
          navigation.navigate('Login');
        } else {
          alert('회원가입에 실패했습니다. 다시 시도해주세요.');
        }
      } catch (error) {
        console.log('Error signing up:', error);
        alert('회원가입 중에 오류가 발생했습니다. 다시 시도해주세요.');
      }
    } else {
      alert('입력된 정보가 올바르지 않습니다.');
    }
  };
  

  return (
    <View>
      <View>
        <Text style={{ marginTop: 87, fontSize: 26, fontWeight: 'bold', color: '#262626' }}> 회원가입하여  </Text>
        <Text style={{ fontSize: 26, fontWeight: 'bold', color: '#262626' }}> CGLH를 사용해 보세요 🌱 </Text>
      </View>

      <View>
        <Text style={{ marginTop: 17, marginBottom: 5, marginLeft: 10, fontSize: 13, fontWeight: '600', color: '#262626' }}>로그인 아이디</Text>
        <View style={{ margin: 10, borderWidth: 1, padding: 5, marginBottom: 10 }}>
          <TextInput
            style={{ height: 40 }}
            placeholder="yunji0604"
            value={loginId}
            onChangeText={handleLoginIdChange}
          />
        </View>
        {!loginIdValid && loginId.length > 0 && <Text>Please enter a valid login ID.</Text>}
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

      <View style={{ marginTop: 10 }}>
        <Text style={{ marginTop: 1, marginBottom: 5, marginLeft: 10, fontSize: 13, fontWeight: '600', color: '#262626' }}>닉네임</Text>
        <View style={{ margin: 10, borderWidth: 1, padding: 5 }}>
          <TextInput
            style={{ height: 40 }}
            placeholder="닉네임을 입력해주세요."
            value={nickname}
            onChangeText={handleNicknameChange}
          />
        </View>
        {!nicknameValid && nickname.length > 0 && <Text>Please enter a valid nickname.</Text>}
      </View>

      <View style={{ marginTop: 20, flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <View style={{ width: 80, height: 40 }}>
          <Button title="회원가입" color="#4B8A08" onPress={handleSignUpButton} disabled={!loginId || !password || !nickname } />
        </View>
      </View>
    </View>
  );
}
