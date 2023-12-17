<template>
  <v-app id="inspire">
    <v-app-bar flat dense class="my-header">
      <v-container class="my-nav">
        <div class="nav-logo">
          <RouterLink to="/">
            <img src="../../assets/siu.png" width="80">
          </RouterLink>
        </div>
        <!-- <v-spacer></v-spacer> -->
        <div class="nav-content">
          <RouterLink to="/cal"> 
            <v-btn style="color:white">
              <h3>일정/결과</h3>
              </v-btn>
          </RouterLink>
          <RouterLink to="/video"> 
            <v-btn style="color:white">
              <h3>영상</h3>
            </v-btn>
          </RouterLink>
          <RouterLink to="/player"> 
            <v-btn style="color:white">
              <h3>선수</h3>
            </v-btn>
          </RouterLink>
          <!-- <v-responsive></v-responsive> -->
        </div>
      </v-container>
    </v-app-bar>
    <v-sheet class="pa-4" color="grey-lighten-2" />
    <v-sheet class="pa-4" color="grey-lighten-2" />

    <v-main class="bg-grey-lighten-2">
      <v-container>
        <v-row>

          <!-- 종목별 분류 사이트 탭-->
          <v-col cols="2">
            <v-sheet rounded="lg">
              <v-list rounded="lg">
                <p class="txxt">종목 별 분류</p>
                <v-list-item color="grey-lighten-4" link  @click="reset">
                  <p class="txxt">초기화 </p>
                </v-list-item>
                <v-divider class="my-2"></v-divider>
                <v-list-item v-for="sport in games" :key="sport" link @click="selectSport(sport)">
                 <p class="txxt"> {{ sport }}</p>
                </v-list-item>
              </v-list>
            </v-sheet>
          </v-col>

          <!-- 오늘 경기 있는 선수 carousel-->
          <v-col cols="10">
            <v-sheet rounded="lg">
              <div class="videoheader">
              <h1 class="txxt">📢 오늘 경기하는 선수목록</h1>
                </div>


              <Carousel :itemsToShow="5" :wrapAround="true" :transition="200">
                <Slide v-for="p in playertoday" :key="p">
                  <div class="carousel__item">
                    <div class="player-container">
                      <div class="play" @click="godetail(p.id)">
                        <div class="player-info">
                          <img :src="getPlayerImage(p.id)" alt="Player Image" width="130">
                          <h4>{{ p.sports }}</h4>
                          <h4>{{ p.name }}</h4>
                        </div>
                      </div>
                    </div>
                  </div>
                </Slide>
                <template #addons>
                  <Navigation />
                  <Pagination />
                </template>
              </Carousel>



              <!--구분선-->
              <v-sheet class="pa-4" color="grey-lighten-2" />

              <div>
                <div class="video-header">
                <h2 class="txxt">🎯 선수 리스트</h2>
              </div>
                <!--이름으로 검색  한글은 양방향 실시간 적용안돼,, @input 사용-->
                <input  class="search-input" type="text" placeholder="이름을 입력하세요" @input="searchName = $event.target.value" />
              </div>

              <!--선수 목록 리스트 출력(사진, 종목, 이름)-->
              <div class="player-container">
                <div v-for="player in sportPlayer" :key="player.id">
                  <div class="play" @click="godetail(player.id)">
                    <div class="player-info">
                      <img :src="getPlayerImage(player.id)" alt="Player Image" width="100">
                      <h4>{{ player.sports }}</h4>
                      <h4>{{ player.name }}</h4>
                      <h4>💜 {{ player.msgCnt }}</h4>
                    </div>
                  </div> 
                </div>
              </div>
              
              
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup>
import { usePlayerStore } from "@/stores/player";
import { useRouter } from 'vue-router';
import { onMounted, ref, computed, watch } from "vue";
import axios from 'axios';

import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel'
import 'vue3-carousel/dist/carousel.css'


const store = usePlayerStore()
const router = useRouter()

//기본 전체 선수 출력 + reset구현
onMounted(async () => {
  if (selectedSport.value === null) {
    sportPlayer.value = store.playerList;
  }
});

//기본 선택 스포츠 초기화
const selectedSport = ref(null);
//선택 종목 선수들 배열 초기화
const sportPlayer = ref([]);
//이름 검색 선수들 초기화
const searchName = ref([]);
const playertoday = ref([]);

//선택한 종목 양방향 바인딩 (v-model)
const selectSport = function (val) {
  selectedSport.value = val
}

//reset 구현 (선택종목 초기화)
const reset = function () {
  selectedSport.value = null;
  sportPlayer.value = store.playerList;
}

//제일 위 캐러젤 테스트용
onMounted(async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/player/e스포츠`
    );
    playertoday.value = response.data;
  } catch (error) {
    console.error("Error fetching player detail:", error);
  }
});

//선택종목 실시간 반영 (get)
watch(selectedSport, async (newValue) => {
  if (newValue !== null) {
    try {
      const response = await axios.get(`http://localhost:8080/api/player/${newValue}`);
      sportPlayer.value = response.data;
    } catch (error) {
      console.error('Error fetching player detail:', error);
    }
  } else {
    sportPlayer.value = store.playerList;
  }
});

// 검색어가 있는 경우 검색어를 포함하는 선수만 출력
watch(searchName, (newValue) => {
  if (newValue.trim() != '') {
    sportPlayer.value = store.playerList.filter((player) => {
      return player.name.toLowerCase().includes(newValue.toLowerCase());
    });
  } else {
    sportPlayer.value = store.playerList;
  }
});

//선택 선수 가져오는 메소드
const getPlayerImage = (id) => {
  return `../../assets/players/${id}.png`;
}

//클릭시 선수 detail 페이지로 router push
const godetail = function (id) {
  router.push(`/player/${id}`);
}


//test용 종목 (c++위로 해놓음)
const games = [
  "헤일리 찬양",
  "3X3 농구",
  "e스포츠",
  "가라테",
  "골프",
  "근대 5종",
  "농구",
  "다이빙",
  "럭비",
  "레슬링",
  "롤러스케이팅",
  "마라톤 수영",
  "바둑",
  "배구",
  "배드민턴",
  "복싱",
  "브레이크 댄스",
  "브리지",
  "비치발리볼",
  "사격",
  "사이클링",
  "세팍타크로",
  "소프트볼",
  "소프트테니스",
  "수구",
  "수영",
  "스케이트보드",
  "스쿼시",
  "스포츠 클라이밍",
  "승마",
  "싱크로",
  "야구",
  "양궁",
  "역도",
  "요트",
  "용선",
  "우슈",
  "유도",
  "육상",
  "조정",
  "주짓수",
  "철인 3종",
  "체스",
  "체조",
  "축구",
  "카누",
  "카바디",
  "크라쉬",
  "탁구",
  "태권도",
  "테니스",
  "펜싱",
  "필드 하키",
  "핸드볼"
];

//header 세팅



</script>
  
  
<style scoped>

.txxt{
  text-align: center;
}
.carousel__slide {
  padding: 5px;
}

.carousel__viewport {
  perspective: 2000px;
}

.carousel__track {
  transform-style: preserve-3d;
}

.carousel__slide--sliding {
  transition: 0.5s;
}

.carousel__slide {
  opacity: 0.9;
  transform: rotateY(-20deg) scale(0.9);
}

.carousel__slide--active~.carousel__slide {
  transform: rotateY(20deg) scale(0.9);
}

.carousel__slide--prev {
  opacity: 1;
  transform: rotateY(-10deg) scale(0.95);
}

.carousel__slide--next {
  opacity: 1;
  transform: rotateY(10deg) scale(0.95);
}

.carousel__slide--active {
  opacity: 1;
  transform: rotateY(0) scale(1.1);
}


.player-container {
  border-radius: 20px;
  margin: 20px;
  display: flex;
  justify-content: center;

  flex-wrap: wrap;
  gap: 5px;
  width: 100%;
  /* Set the container width to 100% */
}

.play {
  border-radius: 20px;
  margin: 15px;
  /* Adjust margin for proper spacing */
  display: flex;
  justify-content: center;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
  /* Box shadow */
  transition: transform 0.3s ease-in-out;
  /* Transition effect */
}

.play:hover {
  transform: translateY(-3px);
  /* Adding a subtle lift on hover */
}

.player-container {
  border-radius: 20px;
  margin: 10px;
  display: flex;
  flex-wrap: wrap;
  /* Allow multiple rows of players */
  gap: 4%;
  /* Spacing between players */
}

.player-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 15px;
}

.player-info img {
  border-radius: 20px;
  margin-bottom: 10px;
}

.search-input {
  display: block;
  padding: 8px;
  margin: 10px auto;
  width: 320px;
  font-size: 16px;
  outline: none;
  border: 1px solid #ccc;
  border-radius: 4px;
  background-color: #f7f7f7;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.search-input:focus {
  border-color: #888;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
}

.search-input::placeholder {
  color: #999;
}
.my-header {
  height: 6rem;
  background-image:linear-gradient(270deg,#423ee5,#b338ff,#db3283) !important;

  display: flex;
  justify-content: center;
  align-items: center;
}

.my-nav {
  width: 90%;

  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}
.video-header{
  margin-top: 1rem;
}
.videoheader{
  padding: 1rem;
}
.my-container {
  width:80%;
}
</style>