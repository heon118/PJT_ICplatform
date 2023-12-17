<template>
  <div>
    <h2>선수 개별 메달 추가하기</h2>
      <input class="box" type="text" v-model.number="inputid" placeholder="선수id 입력"/>
      <!-- <hr> -->
      <button class="box" @click="putRequestgold" >금메달 획득</button>
      <button class="box" @click="putRequestsilver" >은메달 획득</button>
      <button class="box" @click="putRequestbronze" >동메달 획득</button>
      <hr>
      <h2>국가 메달 추가하기</h2>
        <input class="box" type="text" v-model="name" placeholder="나라 입력"/>
        <!-- <hr> -->
        <button class="box" @click="putRequestgoldCountry" >금메달 획득</button>
        <button class="box" @click="putRequestsilverCountry" >은메달 획득</button>
        <button class="box" @click="putRequestbronzeCountry" >동메달 획득</button>
      <hr>
      <h2>영상 삭제하기</h2>
      <input class="box" type="text" v-model="videoId" placeholder="videoId 입력"/>
      <button class="box" @click="deleteVideo(videoId)" >삭제</button>
  </div>
</template>

<script setup>
import {ref} from 'vue'
import axios from 'axios';

const inputid = ref('')
const name = ref('')
const videoId = ref('')

async function putRequestgold() {
  try {
    const url = `http://localhost:8080/api/player/${inputid.value}/gold`;
    const data = {
      id: inputid.value,
    };

    const response = await axios.put(url, data);
    console.log(response.data);
  } catch (error) {
    console.error('Put 요청 보내기 오류:', error);
  }
};

async function putRequestsilver() {
  try {
    const url = `http://localhost:8080/api/player/${inputid.value}/silver`;
    const data = {
      id: inputid.value,
    };

    const response = await axios.put(url, data);
    console.log(response.data);
  } catch (error) {
    console.error('Put 요청 보내기 오류:', error);
  }
}

async function putRequestbronze() {
  try {
    const url = `http://localhost:8080/api/player/${inputid.value}/bronze`;
    const data = {
      id: inputid.value,
    };
    const response = await axios.put(url, data);
    console.log(response.data);
  } catch (error) {
    console.error('Put 요청 보내기 오류:', error);
  }
}

async function putRequestgoldCountry() {
  try {
    const url = `http://localhost:8080/api/countries/${name.value}/gold`;
    const data = {
      name: name.value,
    };
    const response = await axios.put(url, data);
    console.log(response.data);
  } catch (error) {
    console.error('Put 요청 보내기 오류:', error);
  }
}

async function putRequestsilverCountry() {
  try {
    const url = `http://localhost:8080/api/countries/${name.value}/silver`;
    const data = {
      name: name.value,
    };
    const response = await axios.put(url, data);
    console.log(response.data);
  } catch (error) {
    console.error('Put 요청 보내기 오류:', error);
  }
}

async function putRequestbronzeCountry() {
  try {
    const url = `http://localhost:8080/api/countries/${name.value}/bronze`;
    const data = {
      name: name.value,
    };
    const response = await axios.put(url, data);
    console.log(response.data);
  } catch (error) {
    console.error('Put 요청 보내기 오류:', error);
  }
}

async function deleteVideo(id) {
  try {
    const url = `http://localhost:8080/api/video/${id}`;
    const response = await axios.delete(url, data);
    console.log(response.data);
    alert("삭제되었습니다!")
    location.reload();
  } catch (error) {
    console.error('delete 요청 보내기 오류:', error);
  }
}


</script>

<style scoped>

.box {
  border: 2px solid black;
  margin: 5%;
}

</style>