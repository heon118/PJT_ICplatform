import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from "@/router";
import axios from "axios";

const REST_PLAYERMSG_URL = `http://localhost:8080/api/fanMsg`

export const usePlayerMsgStore = defineStore('playerMsg', () => {
    const playerMsg = ref([])
    const getPlayerMsg = function () {
        axios.get(REST_PLAYERMSG_URL)
            .then((response) => {
                playerMsg.value = response.data
            })
    }

    
    return { playerMsg, getPlayerMsg }
})


