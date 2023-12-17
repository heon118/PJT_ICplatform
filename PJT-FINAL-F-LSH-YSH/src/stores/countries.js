import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from "@/router";
import axios from "axios";

const REST_COUNTRIES_URL = `http://localhost:8080/api/countries`

export const useCountryStore = defineStore('Country', () => {
    const CountryList = ref([])
    const getCountryList = function () {
        axios.get(REST_COUNTRIES_URL)
            .then((response) => {
                CountryList.value = response.data
            })
    }

    
    return { CountryList, getCountryList }
})
