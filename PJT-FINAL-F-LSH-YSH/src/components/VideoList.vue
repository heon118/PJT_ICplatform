<template>
    <v-app id="inspire">
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

      <v-sheet class="pa-4" color="grey-lighten-2" />
      <v-sheet class="pa-4" color="grey-lighten-2" />
  
      <v-main class="bg-grey-lighten-2">
        <v-container>
          <v-row>
  
            <!-- 종목별 분류 사이트 탭-->
            <v-col cols="2">
              <v-sheet rounded="lg">
                <v-list rounded="lg">
                  <h3 class="txxt">종목 별 분류</h3>
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
            <v-col>
              <v-sheet min-height="70vh" rounded="lg">
                <div class="videohead">
                <h1 class="txxt">조회수 TOP 10 🔥</h1>
              </div>
                <!-- {{ cntVideo }} -->
  
                <Carousel :itemsToShow="3" :wrapAround="true" :transition="500"
                :autoplay="5000">
                  <Slide v-for="v in cntVideo" :key="p">
                    <div class="carousel__item">
                      <div class="player-container">
                        <div class="play" @click="godetail(v.videoId)">
                          <div class="player-info">
                            <img :src="`http://i.ytimg.com/vi/${v.videoId}/mqdefault.jpg`"
                        width="300"
                        @click="godetail(v.videoId), putRequest(v.videoId)" >
                        <h3> 조회 수 : {{v.videoCnt}}회</h3>
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
                  <div class="videohead">
                  <h2 class="txxt">영상 목록 </h2>
                  <input  class="search-input" type="text" placeholder="영상제목을 입력하세요" @input="searchName = $event.target.value" />
                  <!--이름으로 검색  한글은 양방향 실시간 적용안돼,, @input 사용-->
                </div>
              </div>

                <!--선수 목록 리스트 출력(사진, 종목, 이름)-->
                <div class="video-grid">
                  <div v-for="v in videoShow" :key="v.videoId" class="video-item-grid">
                    <div class="video-item-each">
                      <!-- <iframe
                        :src="`https://www.youtube.com/embed/${v.videoId}`"
                        frameborder="0"
                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                        allowfullscreen
                      ></iframe> -->

                        <img :src="`http://i.ytimg.com/vi/${v.videoId}/mqdefault.jpg`"
                        width="330"
                        @click="godetail(v.videoId), putRequest(v.videoId)" >

                      <div>
                       </div>
                    </div>
                    <div class="video-info">
                      <h4 class="video-title">{{ v.videoTitle }}</h4>
                      <h3>조회수: {{ v.videoCnt }}회</h3>
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
  import { useVideoStore } from "@/stores/video";
  import { useRouter } from 'vue-router';
  import { onMounted, ref, computed, watch } from "vue";
  import axios from 'axios';
  // import YoutubeIframeLoader from "youtube-iframe";
  
  import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel'
  import 'vue3-carousel/dist/carousel.css'
  const store = useVideoStore()
  const router = useRouter()

  const cntVideo = ref([]);
  onMounted(async () => {
    await store.getVideoList();
    if (sortVideolist.value === null) {
      videoShow.value = store.videoList;
    }
  });

  onMounted(async () => {
  try {
    const response = await axios.get(`http://localhost:8080/api/video/popular`);
    cntVideo.value = response.data;

  } catch (error) {
    console.error('Error fetching player detail:', error);
  }
});
  
  



  const videoShow = ref([]);
  const sortVideolist = ref(null);
  const searchName = ref([]);


const selectSport = function (val) {
  sortVideolist.value = val
}

const reset = function () {
  sortVideolist.value = null;
  videoShow.value = store.videoList;
}

watch(sortVideolist, async (newValue) => {
  if (newValue !== null) {
    try {
      const response = await axios.get(`http://localhost:8080/api/video/${newValue}`);
      videoShow.value = response.data;
    } catch (error) {
      console.error('Error fetching player detail:', error);
    }
  } else {
    videoShow.value = store.videoList;
  }
});

watch(searchName, (newValue) => {
  if (newValue.trim() != '') {
    videoShow.value = store.videoList.filter((video) => {
      return video.videoTitle.toLowerCase().includes(newValue.toLowerCase());
    });
  } else {
    videoShow.value = store.videoList;
  }
});



const godetail = function (id) {
  router.push(`/video/${id}`);
}

async function putRequest(id) {
  try {
    const url = `http://localhost:8080/api/video/${id}`;
    const data = {
      videoId: id,
    };
    const response = await axios.put(url, data);
    console.log(response.data);

  } catch (error) {
    console.error('PUT 요청 보내기 오류:', error);
  }
}


  //test용 종목 (c++위로 해놓음)
  const games = [
    
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
  const links = [
    'cal',
    'video',
    'player',
  ]
  
  
  
  </script>
    
    
  <style scoped>
  .video-grid {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
  }
  
  .video-item-grid {
    width: calc(30% - 2rem); /* 가로로 3개씩 정렬하되 간격을 조정 */
    margin-bottom: 0.4rem;
    border: 0.2rem solid #f4f2f2;
    border-radius: 10%;
    background-color: #f4f2f2;
    padding: 0.5rem;
    margin: 0.5rem;
  }

  .video-item-each {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  .video-item-grid:hover {
    transform: translateY(-3px);
  }
  
  .video-wrapper {
    position: relative;
    width: 100%;
    padding-bottom: 56.25%; /* 16:9 비율에 맞게 조정 */
  }
  
  .video-wrapper iframe {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
  }
  
  .video-info {
    margin-top: 10px;
    text-align: center;
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
  
  .txxt{
    text-align: center;
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
  .videohead{
  padding-top: 1rem;
  padding-bottom: 1rem;
}
.video-title {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  /* 필요한 경우 최대 너비를 지정할 수 있습니다. */
  /* max-width: 200px; */
}

  </style>