<template>
  <v-app id="inspire">

    <!-- header -->
    <v-app-bar flat dense class="my-header">
      <v-container class="my-nav">
        <div class="nav-logo">
          <RouterLink to="/">
            <img src="../assets/siu.png" width="80">
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
    <!-- header -->

    <v-sheet class="pa-4" color="grey-lighten-2" />
    <v-sheet class="pa-4" color="grey-lighten-2" />
    <!-- start 순위표 사이드 바-->
    <v-main class="bg-grey-lighten-2">
      <v-container class="my-container">
        <v-row class="">
          <!-- start #1. 선수 랭킹 -->
          <v-col cols="3">
            <v-sheet rounded="lg">
              <!-- <h2 class="selfh4">순위표</h2> -->
              <!-- {{ store.CountryList }} -->
              <div class="table-container">
                <table class="styled-table">
                  <thead>
                    <tr>
                      <th>등수</th>
                      <th>나라</th>
                      <th>🥇</th>
                      <th>🥈</th>
                      <th>🥉</th>
                      <th>메달 수</th>
                    </tr>
                  </thead>
                  <tbody  v-for="(p,index) in store2.CountryList.slice(0,14)" :key="index">
                    <tr>
                      <!-- 여기서부터 반복문-->
                      <td>{{index+1}}</td>
                      <td>
                        {{ p.name }}
                      </td>
                      <td>{{p.gold}}</td>
                      <td>{{p.silver}}</td>
                      <td>{{p.bronze}}</td>
                      <td>{{p.totalMedal}}</td>
                    </tr>
                    
                  </tbody>
                </table>
              </div>
            </v-sheet>
          </v-col>
          <!-- end  #1. 선수 랭킹 -->
          
          <!-- start #2. 선수 명단 -->
          <v-col cols="9">
            <v-sheet rounded="lg">

              <!--메인 일정 표시, 클릭시 일정페이지로 이동-->
              <!--일정 데이터 넣어야함-->
              <div id="nav">
                <div class="nav-item">
                  <h1>오늘의 가장 기대되는 일정! 📅</h1>
                  <div class="cal" @click="gocal">
                    
                    <div v-for="p in eventList">
                      <div v-if="p.eventId === 1114">
                        <h1 v-if="p.eventDate">
                          {{ p.eventDate.split(' ')[1].split(':')[0] }} :
                          {{ p.eventDate.split(' ')[1].split(':')[1] }}
                        </h1>
                        <h1 v-if="p.content">
                          {{ p.content.split(' ')[0] }}
                          <p></p>
                          <!-- {{ p.content.split(' ')[0] }} -->
                          {{ p.content.split(' ')[1] }}
                          {{ p.content.split(' ')[2] }}
                          {{ p.content.split(' ')[3] }}
                        </h1>
                        <!-- <h3 v-if="p.content">
                          {{ p.content.split(' ')[3] }}
                          {{ p.content.split(' ')[4] }}
                          {{ p.content.split(' ')[5] }} </h3> -->
                          <h3 v-if="p.result">
                            {{ p.result.split(' ')[4] }}
                            vs
                            {{ p.result.split(' ')[0] }}
                          </h3>
                        </div>
                      </div>
                  </div>
                </div>
                <div class="nav-item">
                  <VCalendar 
                  :attributes="attributes" 
                  v-model="date" 
                  @dayclick="show" 
                  :columns="columns"
                  :min-date="'2023-09-19'" 
                  :initial-page="'2023-10-18'" 
                  :borderless="true"
                  expanded
                  />
                </div>
              </div>
              <!--해당 일정 가진 선수 캐러젤로 출력해야함!-->
              <div>
                <div class="video-header">
                <h2 class="txxt">선수 명단 🙋‍♂️</h2></div>
              <Carousel :itemsToShow="6" :wrapAround="true"  :transition="500"
              :autoplay="5000">
                  <Slide v-for="p in playertoday" :key="p">
                    <div class="carousel__item">
                      <div class="player-container">
                        <div class="play" @click="godetail(p.id)">
                          <div class="player-info">
                            <img :src="getPlayerImage(p.id)" alt="Player Image" width="100">
                            <p>{{ p.sports }}</p>
                            <h3>{{ p.name }}</h3>
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
                </div>
            </v-sheet>
          </v-col>
          <!-- end #2. 선수 명단 -->

        </v-row>

        <!--구분 선-->
        <v-sheet class="pa-4" color="grey-lighten-2" />


        <!--조회수 핫클립 영상 출력-->
        <v-sheet rounded="lg">
          <div class="videohead">
          <h1 class="txxt">조회수 HOT 영상 🔥</h1>
        </div>
          <Carousel :itemsToShow="4" :wrapAround="true" :transition="500"
          :autoplay="4000">
            <Slide v-for="p in videoShow" :key="p">
              <div class="carousel__item">
                <div class="player-container">
                    
                      <img :src="`http://i.ytimg.com/vi/${p.videoId}/mqdefault.jpg`" width="340"
                        @click="govideo(p.videoId)" >
                    
                    <div class="player-info">
                      <!-- <h4>{{ p.category }}</h4> -->
                  </div>
                </div>
              </div>
            </Slide>
            <template #addons>
              <Navigation />
              <Pagination />
            </template>
          </Carousel>
        </v-sheet>
      </v-container>
    </v-main>
    <!-- end 순위표 사이드 바-->
    <RouterView />

  </v-app>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { usePlayerStore } from "@/stores/player";
import { useCountryStore } from "@/stores/countries";
import { useVideoStore } from "@/stores/video";
import { onMounted, ref, computed, watch } from "vue";
import axios from 'axios';
import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel'
import 'vue3-carousel/dist/carousel.css'

const store = usePlayerStore()
const store1 = useVideoStore()
const router = useRouter()

const store2 = useCountryStore();

onMounted(async () => {
  await store2.getCountryList();
});


const playertoday = ref('')

onMounted(async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/video/popular`
    );
    videoShow.value = response.data;
  } catch (error) {
    console.error("Error fetching player detail:", error);
  }
});

// onMounted(async () => {
//     await store1.getVideoList();
//     videoShow.value = store1.videoList;
// });
 
  
const videoShow = ref([]);


const eventList = ref([]);
const todos = ref([]);

onMounted(async () => {
  try {
    const response = await axios.get(`http://localhost:8080/api/event`);
    eventList.value = response.data;

    // eventList의 각 요소에 대한 todos 생성
    todos.value = eventList.value.map((event) => ({
      description: event.content,
      dates: [new Date(event.eventDate)],
      color: 'purple',
    }));

  } catch (error) {
    console.error('Error fetching player detail:', error);
  }
});

const attributes = computed(() => [
  // Attributes for todos
  ...todos.value.map((todo) => ({
    dates: todo.dates,
    bar:{
      color : todo.color,
    },
    // dot: {
    //   color: todo.color,
    //   ...(todo.isComplete && { class: 'opacity-5' }),
    // },
    popover: {
      label: todo.description,
    },
  })),
]);

onMounted(async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/player/야구`
    );
    playertoday.value = response.data;
  } catch (error) {
    console.error("Error fetching player detail:", error);
  }
});

//아직 어떻게 사용할지 일단 킵
onMounted(() => {
  store.getPlayerList()
})


//선수 가져오는 메소드
const getPlayerImage = (id) => {
  return `../../assets/players/${id}.png`;
}

//선수 클릭시 상세페이지 이동 메소드
const godetail = function (id) {
  router.push(`/player/${id}`);
}
const govideo = function (id) {
  router.push(`/video/${id}`);
}



//일정 클릭시 일정 페이지로 보내는 메소드
const gocal = function () {
  router.push('/cal');
}
</script>

<script>
import { defineComponent } from 'vue'
import { Carousel, Navigation, Slide } from 'vue3-carousel'

import 'vue3-carousel/dist/carousel.css'

export default defineComponent({
  name: 'WrapAround',
  components: {
    Carousel,
    Slide,
    Navigation,
  },
})
</script>
  
<style scoped>

.my-header {
  height: 6rem;
  background-image:linear-gradient(270deg,#423ee5,#b338ff,#db3283) !important;
  z-index: 99999;
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

.txxt{
  text-align: center;
}

#nav {
  display: flex;
  justify-content: space-between;
}
.nav-item {
  flex: 1;
  text-align: center;
  margin: 10px;
  padding: 20px;
  border-radius: 20px;
  background-color: #ffffff;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease-in-out;
  z-index: 99;
}

.nav-item:hover {
  transform: translateY(-3px);
}

.cal {
  margin-bottom: 20px;
  padding: 20px;
  border-radius: 20px;
  background-image: linear-gradient(270deg, #bdbcef, #dca4ff, #fe76b8);
  box-shadow: 1px 4px 6px rgba(0, 0, 0, 0.1);
}

.cal h1 {
  margin-bottom: 10px;
  font-size: 30px;
  color: #333333;
}

.cal h2 {
  margin-bottom: 5px;
  font-size: 20px;
  color: #666666;
}

.cal h4 {
  margin-bottom: 15px;
  font-size: 16px;
  color: #888888;
}

.my-container {
  width:80%;
}


.play {
  border-radius: 20px;
  margin: 20px;
  padding: 10px;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease-in-out;
}

.play:hover {
  transform: translateY(-3px);
}

.player-container {
  border-radius: 20px;
  margin: 20px;
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
 
}

.player-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  
}

.player-info img {
  border-radius: 20%;
  margin: 12px;
}

/* 테이블 스타일 */
.styled-table {
  width: 100%;
  border-collapse: collapse;
  border: 1px solid #ccc;
  font-size: 14px;
}

.styled-table th,
.styled-table td {
  padding: 12px;
  text-align: center;
}

.styled-table thead th {
  background-color: rgb(245, 245, 245);
  color: #333;
}

.styled-table tbody tr:nth-child(even) {
  background-color: #f9f9f9;
}

.styled-table tbody tr:hover {
  background-color: #e0e0e0;
}

/* 등수 칸 스타일 */
.styled-table tbody td:first-child {
  font-weight: bold;
}
.styled-table tbody td:nth-child(2) {
  font-size: 15px;
}

/* 메달 아이콘 스타일 */
.styled-table tbody td:nth-child(3),
.styled-table tbody td:nth-child(4),
.styled-table tbody td:nth-child(5) {
  font-size: 15px;
}

/* 테이블 컨테이너 스타일 */
.table-container {
  overflow-x: auto;
}
.video-header{
  margin-top: 1rem;
}

.videohead{
  padding-top: 1rem;
  margin-top: 2rem;
}
</style>