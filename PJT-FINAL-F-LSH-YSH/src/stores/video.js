import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from "@/router";
import axios from "axios";

const REST_VIDEO_URL = `http://localhost:8080/api/video/latest`

export const useVideoStore = defineStore('video', () => {
    const videoList = ref([])
    const getVideoList = function () {
        axios.get(REST_VIDEO_URL)
            .then((response) => {
                videoList.value = response.data
            })
    }

    
    return { videoList, getVideoList }
})
