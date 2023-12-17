<template>
  <div>
    <h2>{{ detailPlayer.name }}님의 포스팅 페이지</h2>

    <p>내용</p>
    <input class="box" type="text" v-model="Msg" placeholder="팬들에게 한마디!" />
    <!-- <p>플레이어 아이디 지금 23</p> -->
    <!-- <input class="box" type="text" v-model.number="playID" /> -->
    <button class="box" @click="sendPostRequest">업로드!</button>
    <hr>

    <div>
      <h2>{{ detailPlayer.name }}님의 사진첩 업로드!</h2>
      <input type="file" ref="fileInput" @change="handleFileChange" />
      <button class="box" @click="uploadPhoto" >Upload Photo</button>
    </div>
    <hr>
    <Carousel id="gallery" :items-to-show="3" :wrap-around="false" v-model="currentSlide">
      <Slide v-for="pic in posting" :key="slide">
        <div class="carousel__item">
          <img :src="`../../assets/post/${pic.postName}`" width="200">
          <div>
          <button class="box" @click="deletephoto(pic.postId)"> 삭제 !</button>
          <p>{{pic.postId}}번</p>
        </div>
        </div>
      </Slide>
      <template #addons>
        <Navigation />
        <Pagination />
      </template>
    </Carousel>

    <hr>
    <h2>{{ detailPlayer.name }} 의 한줄소개</h2>

    <p>내용</p>
    <input class="box" type="text" v-model="letter" placeholder="한줄소개 작성" />
    <button class="box" @click="putRequest(letter)">업로드!</button>
    <hr>
     



  </div>
</template> 
  
<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios';
import { usePostStore } from '@/stores/postImg';
import { useRouter, useRoute } from "vue-router";

import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel'
import 'vue3-carousel/dist/carousel.css'
const route = useRoute();

const store = usePostStore();
const Msg = ref('');
const letter = ref('');
const playID = ref('');
// const data = { file: null, playerId: 868};
const data = ref('');
const detailPlayer = ref([]);
const posting = ref([]);


//선수 데이터 가져옴 GET
onMounted(async () => {
  try {
    const response = await axios.get(`http://localhost:8080/api/player/detail/${route.params.id}`);
    detailPlayer.value = response.data;
  } catch (error) {
    console.error('Error fetching player detail:', error);
  }
});

//사진가져옴
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


async function sendPostRequest() {
  try {
    const url = `http://localhost:8080/api/playerMsg/${route.params.id}`;
    const data = {
      content: Msg.value,
      // playerId: playID.value,
    };
    const response = await axios.post(url, data);

    console.log(response.data);
    alert("성공적으로 업로드 되었습니다!")
    location.reload();
    
  } catch (error) {
    console.error('POST 요청 보내기 오류:', error);
  }
}

async function deletephoto(id) {
  try {
    const url = `http://localhost:8080/api/post/${id}`;
    const response = await axios.delete(url, data);
    console.log(response.data);
    alert("삭제되었습니다!")
    location.reload();
  } catch (error) {
    console.error('POST 요청 보내기 오류:', error);
  }
}

function handleFileChange(event) {
  const file = event.target.files[0];
  data.file = file;
}

function uploadPhoto() {
  const formData = new FormData();
  formData.append('file', data.file); // 'file' 필드에 선택한 파일 추가
  // 'playerId'는 path parameter로 전달되어야 함 (URL에 추가)
  // const playerId = data.playerId;
  const url = `http://localhost:8080/api/post/${route.params.id}`;

  axios.post(url, formData, {
    headers: {
      'Content-Type': 'multipart/form-data', // 파일 업로드를 위한 헤더 설정
    },
  })
    .then((response) => {
      console.log('성공적으로 업로드됨:', response.data);
      alert("성공적으로 업로드 되었습니다!")
      location.reload();
    })
    .catch((error) => {
      console.error('업로드 오류:', error);
    });
}

async function putRequest(letter) {
  try {
    const url = `http://localhost:8080/api/player/${route.params.id}`;
    const data = {
      id: route.params.id,
      intro : letter,
    };
    const response = await axios.put(url, data);
    console.log(response.data);
    alert("성공적으로 업로드 되었습니다!")
    location.reload();

  } catch (error) {
    console.error('PUT 요청 보내기 오류:', error);
  }
}



</script>
  
<style scoped>
.box {
  border: 1px solid black;
}
</style>
  