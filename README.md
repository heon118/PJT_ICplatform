# PJT-FINAL-F-LSH-YSH
## 국제 대회 플랫폼 🌏<br />
- SSAFY 서울 7반 헤일리의 아픈 두 손가락 <br />
  > 이승헌 + 유승호<br />
- 기간:2023.11.16 ~ 2023.11.24
<br />
<hr />

## 🤘 헤일리의 아픈 두 손가락 🤘

<table align="center">
    <tr align="center">
        <td style="min-width: 220px;">
            <a href="https://github.com/heon118">
              <img src="./project_assets/heon.png" width="250">
              <br />
              <b>이승헌(팀장)</b>
            </a> 
        </td>
          <td style="min-width: 220px;">
          <img src="./project_assets/ho.png" width="250">
            <a href="https://github.com/muyahoya">
                <br />
              <b>유승호</b>
            </a> 
        </td>
    </tr>
    <tr align="left">
        <td>
        🙋 백엔드 리더<br /> 
        </td>
        <td>
        🙋 프론트엔드 리더<br />
        </td>
    </tr>
</table>
<br />
<hr />

## 🔍 기술 스택

### 📌 프로젝트 소개
올림픽, 아시안 게임, 월드컵 등 다양한 국제 대회가 열리는데 있어 대회의 진행 상황을 알려주는 플랫폼은 여럿 있지만 우리 선수에게 직접 응원할 수 있는 플랫폼은 없습니다. 선수들 또한 본인을 응원해주는 국민들에게 메세지를 전달할 수 있는 수단이 부족하기에 선수와 팬이 소통할 수 있는 공간을 만들었습니다.<br /><br />
대회 기간 동안의 모든 일정과 결과, 관련 동영상 시청, 출전 선수와의 소통을 모두 한 사이트에서 해결할 수 있습니다.
<br /><br />

### 📌 프로젝트 목표
- 웹 아키텍처 활용하여 웹 프로젝트를 설계 및 구현
- Spring Boot와 Vue의 이해
- Rest API와 VueRouter 활용
- DB 설계와 MySQL을 통한 데이터 활용
- 한 가지 이상의 API 적용
- 자신만의 아이디어를 다양한 방식으로 기획부터 구현까지 완성
<br /><br />

### 📌 요구사항
- 운동 관련된 주제를 통해 Final Project를 완성한다.
- SpringBoot와 MyBatis를 활용한 RestAPI 서버를 구현한다.
- Vue3를 활용해 SPA 클라이언트를 구현한다.

<br />
<hr />

## 🔍 기술 스택
- Java8, Spring Boot, MyBatis, MySQL, Swagger
- JavaScript, Vue3, HTML, CSS
- Git, Github
- Notion

<br />
<hr />

## 🔍 전체 일정(wbs)
![wbs](/project_assets/wbs.PNG)

<br />
<hr />

## 🔍 요구사항 명세서
![요구사항 명세서](/project_assets/요구사항정의서.PNG)

<br />
<hr />

## 🔍 ERD
<p align="center">
  <img src="./project_assets/PJT_FINAL_F_ERD.png" width="750">
</p>

<br />
<hr />


## 🔍 화면 정의서
<table align="center">
    <tr align="center">
        <td>
        메인 페이지
        </td>
        <td>
        전체 선수 페이지
        </td>
    </tr>
    <tr align="center">
        <td style="min-width: 220px;">
              <img src="./project_assets/wireframe/wireframe_main.png" width="500">
        </td>
          <td style="min-width: 220px;">
              <img src="./project_assets/wireframe/wireframe_playerList.png" width="500">
        </td>
    </tr>
    <tr align="center">
        <td>
        선수 상세 페이지
        </td>
        <td>
        선수 응원 페이지
        </td>
    </tr>
    <tr align="center">
        <td style="min-width: 220px;">
              <img src="./project_assets/wireframe/wireframe_playerDetail.png" width="500">
        </td>
          <td style="min-width: 220px;">
              <img src="./project_assets/wireframe/wireframe_playerFanMsg.png" width="500">
        </td>
    </tr>
    <tr align="center">
        <td>
        대회 일정 페이지
        </td>
        <td>
        대회 영상 페이지
        </td>
    </tr>
    <tr align="center">
        <td style="min-width: 220px;">
              <img src="./project_assets/wireframe/wireframe_event.png" width="500">
        </td>
          <td style="min-width: 220px;">
              <img src="./project_assets/wireframe/wireframe_videoList.png" width="500">
        </td>
    </tr>
    <tr align="center">
        <td>
        선수 관리자 페이지
        </td>
        <td>
        관리자 페이지
        </td>
    </tr>
    <tr align="center">
        <td style="min-width: 220px;">
              <img src="./project_assets/wireframe/wireframe_playerManage.png" width="500">
        </td>
          <td style="min-width: 220px;">
              <img src="./project_assets/wireframe/wireframe_manage.png" width="500">
        </td>
    </tr>
</table>
<br />
<hr />


## 🔍 주요 기능
🙋 대회 출전 선수와의 소통
  - 각 선수마다 개인 페이지 보유
  - 선수가 직접 한 줄 소개, 팬들에게 하고 싶은 메세지 작성 가능
  - 원하는 사진을 업로드하고 보여줄 수 있는 사진첩 기능
  - 선수에게 응원 메세지 등록 가능
  - Word Cloud API를 통한 응원 메세지 출력

📅 진행 중인 대회 일정 열람
  - 캘린더를 통한 경기 일정 제공
  - 기대되는 경기의 기대(좋아요)를 통한 기대되는 경기 일정 제공  
  - 경기 일정 열람 시 해당 경기 출전 선수 확인 가능
  - 일정 추가, 수정, 삭제(관리자)

🎬 대회 기간 중 업로드 영상 제공
  - 최신 업로드, 조회수 순 영상 목록 제공
  - 종목 선택을 통해 해당 종목에 해당하는 영상 시청 가능

<br />
<hr />

## 🔍 실제 구현 화면

<table align="center">
    <tr align="center">
        <td>
        메인 페이지
        </td>
        <td>
        전체 선수 페이지
        </td>
    </tr>
    <tr align="center">
        <td style="min-width: 220px;">
              <img src="./project_assets/page/mainPage.png" width="500">
        </td>
          <td style="min-width: 220px;">
              <img src="./project_assets/page/playerListPage.png" width="500">
        </td>
    </tr>
    <tr align="center">
        <td>
        선수 상세 페이지
        </td>
        <td>
        선수 응원 페이지
        </td>
    </tr>
    <tr align="center">
        <td style="min-width: 220px;">
              <img src="./project_assets/page/playerPage.png" width="500">
        </td>
          <td style="min-width: 220px;">
              <img src="./project_assets/page/playerFanPage.png" width="500">
        </td>
    </tr>
    <tr align="center">
        <td>
        대회 일정 페이지
        </td>
        <td>
        대회 영상 페이지
        </td>
    </tr>
    <tr align="center">
        <td style="min-width: 220px;">
              <img src="./project_assets/page/eventPage.png" width="500">
        </td>
          <td style="min-width: 220px;">
              <img src="./project_assets/page/videoPage.png" width="500">
        </td>
    </tr>
</table>
<br />
<hr />


## ✔ 프로젝트 구조
<details>
<summary>&nbsp;📂 Spring Boot 디렉토리 구조</summary>

📦IC_Platform<br />
 ┣ 📂.mvn<br />
 ┣ 📂.settings<br />
 ┣ 📂src<br />
 ┃ ┣ 📂main<br />
 ┃ ┃ ┣ 📂java<br />
 ┃ ┃ ┃ ┣ 📂com<br />
 ┃ ┃ ┃ ┃ ┣ 📂shsh<br />
 ┃ ┃ ┃ ┃ ┃ ┣ 📂ic<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂config<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DBConfig.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SwaggerConfig.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WebConfig.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CountryRestController.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜EventRestController.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FanMsgRestController.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PlayerMsgRestController.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PlayerRestController.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostRestController.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜VideoRestController.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂model<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dao<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CountryDao.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜EventDao.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FanMsgDao.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PlayerDao.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PlayerMsgDao.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostDao.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜VideoDao.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Country.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Event.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FanMsg.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Player.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PlayerMsg.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Posts.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Video.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CountryService.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CountryServiceImpl.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜EventService.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜EventServiceImpl.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FanMsgService.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FanMsgServiceImpl.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PlayerMsgService.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PlayerMsgServiceImpl.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PlayerService.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PlayerServiceImpl.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostService.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostServiceImpl.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜VideoService.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜VideoServiceImpl.java<br />
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜IcPlatformApplication.java<br />
 ┃ ┃ ┣ 📂resources<br />
 ┃ ┃ ┃ ┣ 📂mappers<br />
 ┃ ┃ ┃ ┃ ┣ 📜countryMapper.xml<br />
 ┃ ┃ ┃ ┃ ┣ 📜eventMapper.xml<br />
 ┃ ┃ ┃ ┃ ┣ 📜fanMsgMapper.xml<br />
 ┃ ┃ ┃ ┃ ┣ 📜playerMapper.xml<br />
 ┃ ┃ ┃ ┃ ┣ 📜playerMsgMapper.xml<br />
 ┃ ┃ ┃ ┃ ┣ 📜postMapper.xml<br />
 ┃ ┃ ┃ ┃ ┗ 📜videoMapper.xml<br />
 ┃ ┃ ┃ ┣ 📜application.properties<br />
 ┃ ┃ ┃ ┗ 📜banner.txt<br />
 ┗ 📜pom.xml<br />
</details>

<br />

<details>
<summary>&nbsp;📂 Vue3 디렉토리 구조</summary>
 📦PJT-FINAL-F-LSH-YSH<br />
  ┣📦src <br />
  ┃ ┣ 📂assets <br />
  ┃ ┣ 📂components <br />
  ┃ ┃ ┣ 📂player <br />
  ┃ ┃ ┃ ┣ 📜AdminMedal.vue <br />
  ┃ ┃ ┃ ┣ 📜PlayerCheer.vue <br />
  ┃ ┃ ┃ ┣ 📜PlayerDetail.vue <br />
  ┃ ┃ ┃ ┣ 📜PlayerList.vue <br />
  ┃ ┃ ┃ ┗ 📜testPost.vue <br />
  ┃ ┃ ┣ 📜Calender.vue <br />
  ┃ ┃ ┣ 📜VideoDetail.vue <br />
  ┃ ┃ ┗ 📜VideoList.vue <br />
  ┃ ┣ 📂router <br />
  ┃ ┃ ┗ 📜index.js <br />
  ┃ ┣ 📂stores <br />
  ┃ ┃ ┣ 📜countries.js <br />
  ┃ ┃ ┣ 📜player.js <br />
  ┃ ┃ ┣ 📜playerMsg.js <br />
  ┃ ┃ ┣ 📜postImg.js <br />
  ┃ ┃ ┗ 📜video.js <br />
  ┃ ┣ 📂views <br />
  ┃ ┃ ┣ 📜AdminView.vue <br />
  ┃ ┃ ┣ 📜CalenderView.vue <br />
  ┃ ┃ ┣ 📜CheerView.vue <br />
  ┃ ┃ ┣ 📜HomeView.vue <br />
  ┃ ┃ ┣ 📜PlayerView.vue <br />
  ┃ ┃ ┣ 📜PostView.vue <br />
  ┃ ┃ ┗ 📜VideoView.vue <br />
  ┃ ┣ 📜App.vue <br />
  ┗ ┗ 📜main.js <br />
 </details>