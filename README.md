# CGLH
![image](https://github.com/GDSC-CGLH/CGLH/assets/80165014/cd160ea3-3253-48e6-919d-f266c8f6fe52) 

<br>

![image](https://github.com/GDSC-CGLH/CGLH/assets/80165014/cfbfea7d-22d0-4b17-af14-68a15d2fb123) 

<br>

![image](https://github.com/GDSC-CGLH/CGLH/assets/80165014/eca8aa47-26d2-4272-9770-1af41f83d799) 

<br><br>

# Used Skills & Version

![image-20230726035531984](https://github.com/GDSC-CGLH/CGLH/assets/80165014/28fb81b3-3c84-4bdb-a552-f994e37ad4bb)  

<br><br>

## Front

* node - version : 16
* jdk - version : 17
* sdk(API) - version : 30

<br><br>

## Backend

* spring boot - version : 2.7
* jdk - version : 17
* db : h2

<br><br>

# Architecture Document

![image](https://github.com/GDSC-CGLH/CGLH/assets/80165014/9c761f99-a712-4c85-8b97-e692ed8786ab) 

<br><br>

# DB Structure

![image](https://github.com/GDSC-CGLH/CGLH/assets/80165014/d47a783f-4a2c-46df-bc4c-6b564f8e5617) 

<br><br>

# Folder Structure

## Front

* **[`/front/React-FreshStudy/index.js`](./front/React-FreshStudy/index.js)**
  * **최상위 실행 파일(App.js를 실행)**

* **[`/front/React-FreshStudy/App.js`](./front/React-FreshStudy/App.js)**

  * **Stack 네비게이터 구조 - Splash, Welcome, Login, Sign up, AdminScreen, MainTab**

  * 기본값 : Splash (로딩화면)

* **[`/front/React-FreshStudy/screens/Splash.js`](./front/React-FreshStudy/screens/Splash.js)**
  * **4초간 로딩화면 후 "Welcome" 화면으로 이동**

* **[`/front/React-FreshStudy/screens/Welcome.js`](./front/React-FreshStudy/screens/Welcome.js)**
  * **앱의 초기화면으로써 로그인, 회원가입 버튼으로 이루어짐**

* **[`/front/React-FreshStudy/screens/adminSrc/adminScreen.js`](./front/React-FreshStudy/screens/adminSrc/adminScreen.js)**
  * **초기 관리자 화면이며, "유저 신청내역 탭" 과 "관리자 정보 탭" 으로 이루어짐**
  * [`/front/React-FreshStudy/screens/adminSrc/AdminMy.js`](./front/React-FreshStudy/screens/adminSrc/AdminMy.js)
    * "관리자 정보 탭" 의 화면
    * 캘린더로 이루어져 있으며, 일정이 있으면 체크 표시를 진행
  * [`/front/React-FreshStudy/screens/adminSrc/AdminApplicationDetails.js`](./front/React-FreshStudy/screens/adminSrc/AdminApplicationDetails.js)
    * "유저 신청내역 탭" 이며, 관리자 자신의 센터에 신청한 유저들 목록을 볼 수 있음
    * 관리자로써 승인, 거절을 진행
* **[`/front/React-FreshStudy/screens/userSrc/MainTab.js`](./front/React-FreshStudy/screens/userSrc/MainTab.js)**
  * **초기 사용자 화면이며, "소개공간" 과 "파쇄신청" 과 "내정보" 탭으로 이루어짐**
  * [`/front/React-FreshStudy/screens/userSrc/IntroduceScreen.js`](./front/React-FreshStudy/screens/userSrc/IntroduceScreen.js)
    * "소개공간 탭" 의 화면
    * 영농 폐기물의 올바른 폐기 방법과 앱의 소개를 진행
  * [`/front/React-FreshStudy/screens/userSrc/ApplicationScreen.js`](./front/React-FreshStudy/screens/userSrc/ApplicationScreen.js)
    * "파쇄신청 탭" 의 화면
    * "PickerScreen, DateScreen" 함수 사용
    * [`/front/React-FreshStudy/screens/userSrc/PickerScreen.js`](./front/React-FreshStudy/screens/userSrc/PickerScreen.js)
      * 각 지역 "농업 센터" 위치 정보로 Picker 구성
    * [`/front/React-FreshStudy/screens/userSrc/DateScreen.js`](./front/React-FreshStudy/screens/userSrc/DateScreen.js)
      * 자신이 원하는 날짜를 선택하는 Picker 구성
  * [`/front/React-FreshStudy/screens/userSrc/MyScreen.js`](./front/React-FreshStudy/screens/userSrc/MyScreen.js)
    * "내정보 탭" 의 화면
    * 자신이 신청한 폐기 정보들을 확인
* **[`/front/React-FreshStudy/screens/loginProcess/Login.js`](./front/React-FreshStudy/screens/loginProcess/Login.js)**
  * **"로그인" 화면**
  * [`/front/React-FreshStudy/screens/loginProcess/SignUp.js`](./front/React-FreshStudy/screens/loginProcess/SignUp.js)
    * "회원가입" 화면
* **[`/front/React-FreshStudy/screens/toServer/API_BASE.js`](./front/React-FreshStudy/screens/toServer/API_BASE.js)**
  * **axios 방식으로 get, post 방식을 간편히 하기위한 BASE 역할**
  * 현재 로컬로 테스트하므로 내부 IP를 기록
  * [`/front/React-FreshStudy/screens/toServer/atom.js`](./front/React-FreshStudy/screens/toServer/atom.js)
    * JWT 토큰을 기록해두기 위한 recoil

<br><br>

## Backend

* **[`/back-end/CGLH/src/main/resources/application.yml`](./back-end/CGLH/src/main/resources/application.yml)**
  * **db연결 등등 환경 설정**
  * 공공데이터 포털 키가 있는데, 만료시켰기 때문에 그대로 두겠음
* **[`/back-end/CGLH/build.gradle`](./back-end/CGLH/build.gradle)**
  * **의존성 설정 (jwt, lombok, h2 등등)**
* **[`/back-end/CGLH/src/main/java/com/gdsc/CGLH/CglhApplication.java`](./back-end/CGLH/src/main/java/com/gdsc/CGLH/CglhApplication.java)**
  * **제일 최상의 루트 파일이며, 이 파일을 실행해서 서버를 오픈**
* **[`/back-end/CGLH/src/main/java/com/gdsc/CGLH/entity/Waste.java`](./back-end/CGLH/src/main/java/com/gdsc/CGLH/entity/Waste.java)**
  * **엔티티 - 영농폐기물 신청 기록**
  * [`/back-end/CGLH/src/main/java/com/gdsc/CGLH/entity/Location.java`](./back-end/CGLH/src/main/java/com/gdsc/CGLH/entity/Location.java)
    * 위치정보 엔티티 (공공 데이터포털에서 가져옴)
  * [`/back-end/CGLH/src/main/java/com/gdsc/CGLH/entity/Member.java`](./back-end/CGLH/src/main/java/com/gdsc/CGLH/entity/Member.java)
    * 회원정보 엔티티
  * [`/back-end/CGLH/src/main/java/com/gdsc/CGLH/entity/WasteStatus.java`](./back-end/CGLH/src/main/java/com/gdsc/CGLH/entity/WasteStatus.java)
    * 상태 정보 - 승인, 대기, 거절
* **[`/back-end/CGLH/src/main/java/com/gdsc/CGLH/repository/WasteRepository/WasteRepository.java`](./back-end/CGLH/src/main/java/com/gdsc/CGLH/repository/WasteRepository/WasteRepository.java)**
  * **레퍼지토리 - 쓰레기 신청**
  * findAllByMemberId, findByCenterName, updateWaste 기능
  * [`/back-end/CGLH/src/main/java/com/gdsc/CGLH/repository/LocationRepository.java`](./back-end/CGLH/src/main/java/com/gdsc/CGLH/repository/LocationRepository.java)
    * 위치정보 레퍼지토리
    * save, findOne, findAll 기능
  * [`/back-end/CGLH/src/main/java/com/gdsc/CGLH/repository/UserRepository.java`](./back-end/CGLH/src/main/java/com/gdsc/CGLH/repository/UserRepository.java)
    * 회원정보 레퍼지토리
    * findByLoginId 기능
* **[`/back-end/CGLH/src/main/java/com/gdsc/CGLH/service/WasteService.java`](./back-end/CGLH/src/main/java/com/gdsc/CGLH/service/WasteService.java)**
  * **서비스 - 쓰레기 신청**
  * 신청 내역 저장, 조회, 취소, 작성자 조회, 상태 수정 기능
  * [`/back-end/CGLH/src/main/java/com/gdsc/CGLH/service/LocationService.java`](./back-end/CGLH/src/main/java/com/gdsc/CGLH/service/LocationService.java)
    * 위치정보 서비스
    * save, findOne, findAll 기능
  * [`/back-end/CGLH/src/main/java/com/gdsc/CGLH/service/UserService.java`](./back-end/CGLH/src/main/java/com/gdsc/CGLH/service/UserService.java)
    * 회원정보 서비스
    * register, login, 중복검증 기능
* **[`/back-end/CGLH/src/main/java/com/gdsc/CGLH/controller/WasteController.java`](./back-end/CGLH/src/main/java/com/gdsc/CGLH/controller/WasteController.java)**
  * **컨트롤러(API) - 쓰레기 신청**
  * 신청내역 저장, 조회, 취소, 작성자 조회, 상태 수정(=관리자 승인, 거절)
  * [`/back-end/CGLH/src/main/java/com/gdsc/CGLH/controller/LocationController.java`](./back-end/CGLH/src/main/java/com/gdsc/CGLH/controller/LocationController.java)
    * 위치정보 컨트롤러(API)
    * 조회 기능, 위치정보 API 공공데이터 호출 기능(스케줄링은 생략)
  * [`/back-end/CGLH/src/main/java/com/gdsc/CGLH/controller/UserController.java`](./back-end/CGLH/src/main/java/com/gdsc/CGLH/controller/UserController.java)
    * 회원정보 컨트롤러(API)
    * 로그인, 로그아웃, 회원가입 => JWT 방식
* **[`/back-end/CGLH/src/main/java/com/gdsc/CGLH/config/JwtUtil.java`](./back-end/CGLH/src/main/java/com/gdsc/CGLH/config/JwtUtil.java)**
  * **JWT 설정**
  * JWT 생성 함수 - generateToken
  * JWT에서 회원정보 추출 함수 - extractloginId



