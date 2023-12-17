import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from "@/router";
import axios from "axios";

const REST_PLAYER_URL = `http://localhost:8080/api/player`

export const usePlayerStore = defineStore('player', () => {
    const playerList = ref([])
    const getPlayerList = function () {
        axios.get(REST_PLAYER_URL)
            .then((response) => {
                playerList.value = response.data
            })
    }

    
    return { playerList, getPlayerList }
})
