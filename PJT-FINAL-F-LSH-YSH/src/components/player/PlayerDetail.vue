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

    
    <!--해당 선수 소개하는 사이드 바-->
    <v-main class="bg-grey-lighten-2">
      <v-container>
        <v-row>
          <v-col cols="3">
            <v-sheet rounded="lg" class="player-profile">
              <v-list rounded="lg">
                <div class="text-center">
                  <img :src="`../../assets/players/${route.params.id}.png`" width="150" />
                  <!-- <p>체크용 id: {{ detailPlayer.id }}</p> -->
                  <h2>이름 : {{ detailPlayer.name }}</h2>
                  <h2>성별 : {{ detailPlayer.gender }}</h2>
                  <!-- <p>생일 : {{ detailPlayer.birth}}</p> -->
                  <h2 v-if="detailPlayer.birth">생일 : {{ detailPlayer.birth.split(' ')[0] }}</h2>

                  <h2>종목 : {{ detailPlayer.sports }}</h2>
                  <h2>한줄소개 : </h2>
                  <h2>"{{ detailPlayer.intro }}"</h2>

                  <!-- <hr> -->
                </div>
              </v-list>
            </v-sheet>
            <v-sheet class="pa-4" color="grey-lighten-2" />

            <!-- {{detailPlayer}} -->
            <v-sheet rounded="lg" class="player-profile">
              <v-list rounded="lg">
                <div class="text-center">
                  <h1>🎀응원 수 : {{ detailPlayer.msgCnt }}</h1>
                  <hr>
                  <h2>
                  <span style="font-size: 25px;">🥇 : {{ detailPlayer.gold }} |</span>
                  <span style="font-size: 25px;">🥈 : {{ detailPlayer.silver }} |</span>
                  <span style="font-size: 25px;">🥉 : {{ detailPlayer.bronze }} </span>
                </h2>
                </div>
              </v-list>
            </v-sheet>

          </v-col>

          

          <!--해당 선수 메인 페이지 -->
          <v-col>
            <v-sheet min-height="72vh" rounded="lg">
              <div class="toppage">
              <h1 class="txxt">{{ detailPlayer.name }}의 페이지 🖐</h1>
            </div>
              <div class="center-btn">
                <button class="awesome-button" @click="goCheer(route.params.id)">
                  {{ detailPlayer.name }} 응원하러 가기!
                </button>
              </div>
              <!-- <hr /> -->

              <div>
                <div class="video-header">
                <h2 class="txxt">💌 {{ detailPlayer.name }}의 한마디 💌</h2>
              </div>
                <!-- playermsg가 존재하고, 요소가 있을 경우 -->
                <div v-if="playermsg && playermsg.length > 0">
                  <div v-for="(p, index) in playermsg.slice(0, 5)" :key="index" class="bang">
                    <div>
                      <h2 class="txxt">{{ p.content }}</h2>
                    </div>
                  </div>
                </div>
                <div v-else>
                  <!-- playermsg가 null이거나 요소가 없을 경우 -->
                  <p class="txxt">아직 소식이 없어요..</p>
                </div>
              </div>
            </v-sheet>
            
          </v-col>
        </v-row>

        <!--구분 선-->
        <v-sheet class="pa-4" color="grey-lighten-2" />

        <v-sheet min-height="20vh" rounded="lg">
          <h1 class="txxt">🎁 {{ detailPlayer.name }}의 사진첩 🎁</h1>

          <div v-if="playermsg && playermsg.length > 0">

          <Carousel id="gallery" :items-to-show="1" :wrap-around="false" v-model="currentSlide">
            <Slide v-for="pic in posting" :key="slide">
              <div class="carousel__item">
                <img :src="`../../assets/post/${pic.postName}`" width="400">
  
              </div>
            </Slide>
            <template #addons>
              <Navigation />
              <Pagination />
            </template>
          </Carousel>
        
          <Carousel
            id="thumbnails"
            :items-to-show="4"
            :wrap-around="true"
            v-model="currentSlide"
            ref="carousel"
          >
            <Slide v-for="pic in posting" :key="slide">
              <div class="carousel__item" @click="slideTo(slide - 1)"> 
                <img :src="`../../assets/post/${pic.postName}`" width="50">
              </div>
            </Slide>
          </Carousel>

        </div>
        <div v-else>
          <h2 class="txxt">아직 사진이 없어요..</h2>

        </div>
        </v-sheet>

        
      </v-container>
    </v-main>

  </v-app>
</template>

<script setup>
import { useRouter, useRoute } from "vue-router";
import { usePostStore } from "@/stores/postImg";
import { onMounted, ref } from "vue";
import axios from "axios";
import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel'
import 'vue3-carousel/dist/carousel.css'

// const store = usePostStore();
const route = useRoute();
const router = useRouter();

// 플레이어 정보를 담을 배열
const detailPlayer = ref([]);
const posting = ref([]);

// const birthday = ref([])

//가져온 params 으로 정보 얻는 get 방식
onMounted(async () => {
  // 가져온 데이터를 detailPlayer 배열에 할당
  try {
    const response = await axios.get(
      `http://localhost:8080/api/player/detail/${route.params.id}`
    );
    detailPlayer.value = response.data;
  } catch (error) {
    console.error("Error fetching player detail:", error);
  }
});

// const setBirth = () => {
//   birthday.value = detailPlayer.value.map(event => event.birth.split(' '));
// };

onMounted(async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/post/${route.params.id}`
    );
    posting.value = response.data;
  } catch (error) {
    console.error("Error fetching player detail:", error);
  }
});

//선수 개인 메세지 초기화
const playermsg = ref([]);
// 가져온 데이터를 배열에 할당
onMounted(async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/playerMsg/${route.params.id}`
    );
    playermsg.value = response.data;
  } catch (error) {
    console.error("Error fetching player detail:", error);
  }
});

const getPlayerDetailImg = (id) => {
  return `../../assets/post/${id}.png`;
}

//헤더
const links = ["cal", "video", "player"];

//응원페이지로 보내기
const goCheer = function () {
  router.push(`/cheer/${route.params.id}`);
};

const currentSlide = ref(0)

const slideTo = (val) => {
  currentSlide.value = val
}


</script>

<style scoped>
.txxt {
  text-align: center;
}
.player-profile {
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  padding: 20px;
  /* 적절한 여백 추가 */
}

.play {
  border-radius: 20px;
  margin: 20px;
  display: flex;
  justify-content: center;
  padding: 10px;
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
  margin: 20px;
  display: flex;
  flex-wrap: wrap;
  /* Allow multiple rows of players */
  gap: 3px;
  /* Spacing between players */
}

.player-info {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.player-info img {
  border-radius: 20px;
  margin-bottom: 8px;
}

.btnOrange.btnPush:hover {
  box-shadow: 0px 0px 0px 0px #a66615;
}

.btnOrange.btnPush {
  box-shadow: 0px 5px 0px 0px #ff9595;
}

.btnPush:hover {
  margin-top: 15px;
  margin-bottom: 5px;
}

.button {
  display: block;
  position: relative;
  float: left;
  width: 30%;
  padding: 0;
  margin: 10px 20px 10px 0;
  font-weight: 600;
  text-align: center;
  line-height: 50px;
  color: #ffffff;
  border-radius: 5px;
  transition: all 0.2s;
}

.btnOrange {
  background: #ff4040;
}

.bang {
  background-color: #fccbfc;
  /* Change background color */
  padding: 2.4%;
  margin: 1%;
  border-radius: 8px;
  box-shadow: 0px 3px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease-in-out;
}

.bang:hover {
  transform: translateY(-3px);
  /* Adding a subtle lift on hover */
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

.my-container {
  width:80%;
}


.awesome-button {
  background-color: rgb(234, 164, 255);
  color: white;
  align-items: center;
  border: none;
  padding: 10px 20px;
  font-size: 30px;
  font-weight: bold;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}
.center-btn {
  align-items: center;
  text-align: center;
  margin: 1rem;
}
.toppage {
  padding-top: 1rem;
}

.awesome-button:hover {
  background-color: rgb(198, 119, 246);
}
</style>
