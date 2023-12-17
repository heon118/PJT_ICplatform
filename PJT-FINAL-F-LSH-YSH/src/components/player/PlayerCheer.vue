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
          <v-col cols="3">
            <v-sheet rounded="lg">
              <!-- <h2 class="selfh4">순위표</h2> -->
              <!-- {{ store.CountryList }} -->
              <div class="table-container">
                <div >
                  <h2 class="txxt">최근 응원 메세지🎈</h2>
                  <div class="textt" v-for="(p, index) in arr.slice(0, 8)" :key="p.uploadDate" >
                    <div>
                      <h3>{{p.nickname}}님의 응원 : "{{ p.content }}"</h3>
                    </div>
                  </div>
                  </div>
                  <hr>
                  <h2 class="txxt">응원하기 💌</h2>
                  <div>
                    <div class="fan-input">
                        <section class="data-box">
                          <input
                            v-model="inputValue"
                            placeholder="인증 코드를 작성하세요"
                            class="input"
                            type="text"
                            style="border: 1px solid black;"
                          />
        
                          <VueClientRecaptcha
                            :value="inputValue"
                            :textColors="[
                              'blue',
                              'red',
                              'purple',
                              'green',
                              '#e83e8c',
                              '#ff5578',
                              '#53b29f',
                              '#d64a37',
                              '#094899',
                              '#f64141',
                              'rgb(77,190,255)',
                            ]"
                            @getCode="getCaptchaCode"
                            @isValid="checkValidCaptcha"
                          />
                    
                          <!-- <pre class="data">{{ data }}</pre> -->
                        </section>
                    
                        <section class="captcha-box">
                          <!-- create list for carachters and select random color for each item -->
                          
                        </section>
                      </div>
        
                      <!-- <div v-if="data.isValid && fanMsg.length !== 0" class="fan-input"> -->
                        <!-- <h2 class="fan-input-title">마 한마디 해바라</h2> -->
        
                        <div class="fan-input" >
        
        
                          <div class="fan-input-content" v-if="data.isValid">
                            <p>내용</p>
                            <input class="box" type="text" v-model="Msg" placeholder="내용을 입력해주세요"  />
                        
                            <p>닉네임</p>
                            <input class="box" type="text" v-model="nickname1" placeholder="닉네임을 적어주세요" />
                        
                            <button class="submit-button" @click="sendPostRequest">등록하기</button>
                          </div>
                          <div v-else>
                            <p class="ttt">인증이 필요합니다. 인증 후 등록이 가능합니다.</p>
                          </div>
                        </div>
                      </div>
              </div>
            </v-sheet>
          </v-col>

          <v-col cols="9">
            <v-sheet min-height="70vh" rounded="lg" class="player-sheet">
              <h1 class="player-name">🎈{{ detailPlayer.name }}를 향한 응원🎈</h1>
              
              

              <div class="fan-message">

                <div v-if="fanMsg && fanMsg.length === 0" class="fan-message-empty">
                  <h2>아직 응원이 없습니다. 응원해주세요!</h2>
                </div>

                <div v-else>
                  <vue-word-cloud class="cheer-cloud"
                    style="height: 600px; width: 90%;"
                    :words="getWordCloudData()"
                    :color="getWordColor"
                    :font-family="'Pretendard SemiBold'"
                  />
                </div>
                
              </div>


              <!--https://github.com/SeregPie/VueWordCloud-->
              <!--
                https://vue-client-recaptcha.netlify.app/usage/#global-registration
              -->
             
                
              <!-- </div> -->
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>


<script setup>
import { useRouter, useRoute } from 'vue-router';
import { usePlayerStore } from "@/stores/player";
import { onMounted, ref, createApp, watch, reactive } from "vue";
import axios from 'axios'
import { defineProps} from "vue";
import VueClientRecaptcha from "vue-client-recaptcha";
import { isValid } from 'date-fns';


const route = useRoute();
const detailPlayer = ref([]);
const fanMsg = ref([]);
const Msg = ref('');
const nickname1 = ref('');
const inputValue = ref(null);


const data = reactive({
  captchaCode: null,
  isValid: false,
});

function getCaptchaCode(value) {
  data.captchaCode = value;
}

const isAuthenticated = ref(false);

function checkValidCaptcha(value) {
  data.isValid = value;
  isAuthenticated.value = value;
  if (value) {
    alert("인증에 성공했습니다. 등록할 수 있습니다.");
  }
}


onMounted(async () => {
  try {
    const response = await axios.get(`http://localhost:8080/api/player/detail/${route.params.id}`);
    detailPlayer.value = response.data;
  } catch (error) {
    console.error('Error fetching player detail:', error);
  }
});

onMounted(async () => {
  try {
    const response = await axios.get(`http://localhost:8080/api/fanMsg/${route.params.id}`);
    fanMsg.value = response.data;
  } catch (error) {
    console.error('Error fetching player detail:', error);
  }
});
const arr = ref([])
onMounted(async () => {
  try {
    const response = await axios.get(`http://localhost:8080/api/fanMsg/${route.params.id}/latest`);
    arr.value = response.data;
  } catch (error) {
    console.error('Error fetching player detail:', error);
  }
});

async function sendPostRequest() {
    try {
      const url = `http://localhost:8080/api/fanMsg/${route.params.id}`;
      const data = {
        content: Msg.value,
        nickname: nickname1.value,
        playerId: route.params.id,
      };
      const response = await axios.post(url, data);
      putRequest();
      console.log(response.data);
      alert("성공적으로 등록되었습니다.")
    location.reload();
  } catch (error) {
    console.error('POST 요청 보내기 오류:', error);
  }
}

async function putRequest() {
  try {
    const url = `http://localhost:8080/api/fanMsg/${route.params.id}`;
    const data = {
      playerId: route.params.id,
    };
    const response = await axios.put(url, data);
    console.log(response.data);
  } catch (error) {
    console.error('POST 요청 보내기 오류:', error);
  }
}

function getWordCloudData() {
  const wordCloudData = fanMsg.value.map(msg => [msg.content, msg.contentCnt]);
  const existingWords = [];
  return existingWords.concat(wordCloudData);
}

function getWordColor([, weight]) {
  if (weight > 12) {
    return '#87CEEB'; // 연한 살구색 (PeachPuff)
  } else if (weight > 11) {
    return '#7071E8'; // 금색 (Gold)
  } else if (weight > 10) {
    return '#7071E8'; // 연한 파란색 (LightBlue)
  } else if (weight > 9) {
    return '#B15EFF'; // 연한 분홍색 (LightPink)
  } else if (weight > 8) {
    return '#FFA07A'; // 연한 파란색 (LightBlue)
  } else if (weight > 7) {
    return '#87C4FF'; // 연한 녹색 (PaleGreen)
  } else if (weight > 6) {
    return '#ED9ED6'; // 연한 파란색 (LightBlue)
  } else if (weight > 5) {
    return '#98FB98'; // 연한 하늘색 (SkyBlue)
  } else if (weight > 4) {
    return '#FF69B4'; // 분홍색 (HotPink)
  } else if (weight > 3) {
    return '#FFA500'; // 오렌지색 (Orange)
  } else if (weight > 2) {
    return '#83A2FF'; // 노란색 (Yellow)
  } else if (weight > 1) {
    return '#00FFFF'; // 청록색 (Cyan)
  } else {
    return '#FF0000'; // 빨간색 (Red)
  }
}

const links = [
  'cal',
  'video',
  'player',
]

</script>

<style scoped>
.ttt{
 color: red
}

.sample-captcha {
  display: flex;
  flex-direction: row;
  justify-content: center;
  border: 1px solid black;
}
.sample-captcha .data-box .input {
  border: 1px solid black;
  padding: 10px;
  border: 1px solid #a8a6a6;
  border-radius: 4px;
  width: 100%;
}
.sample-captcha .data-box .input:focus {
  background: #f0f0f00d;
  outline: none;
  box-shadow: inset 0 -2px 0 #0077ff;
}
.sample-captcha .data-box .data {
  width: 100%;
  margin-top: 10px;
  padding: 20px 0 20px 20px;
  background-color: #ebebeb;
  border-radius: 4px;
}
.sample-captcha .captcha-box {
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 10px auto;
}
.player-sheet {
  min-height: 70vh;
  border-radius: 8px;
  padding: 24px;
}

.player-name {
  font-size: 32px;
  margin-bottom: 16px;
  text-align: center;
}

.fan-message {
  margin-bottom: 24px;
}

.fan-message-title {
  font-size: 24px;
  margin-bottom: 16px;
  text-align: center;
}

.cheer-cloud {
  margin: 2rem auto;
}

.fan-message-empty {
  text-align: center;
  
  margin-top: 24px;
}

.fan-input {
  display: flex;
  justify-content: center; /* 수정된 부분 */
  align-items: center; /* 수정된 부분 */
  flex-direction: column; /* 수정된 부분 */
  padding: 1rem;
}



.box {
  border: 1px solid black;
  padding: 8px;
}

.submit-button {
  padding: 8px 16px;
  background-color: #f7aefc;
  color: white;
  border: none;
  cursor: pointer;
}

.submit-button:hover {
  background-color: #fd6dff;
}
.fan-input-content {
  display: flex;
  justify-content: center;
  flex-direction: column;
  gap: 0.2rem;
  max-width: 100%;
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
.txxt{
  text-align: center;
  padding: 0.4rem;
}

.ttt {
  color: red;
}

.box {
  border: 1px solid black;
  padding: 8px;
}

.table-container {
  overflow-x: auto;
  padding: 1rem;
}

.textt{
  gap: 1rem;
  padding: 0.3rem;  
}
</style>
