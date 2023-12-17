import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from "axios";

const REST_POST_URL = `http://localhost:8080/api/post`

export const usePostStore = defineStore('post', () => {
    const postList = ref([])

    const getPostList = function (playerId) {
        const url = `${REST_POST_URL}/${playerId}`
        axios.get(url)
            .then((response) => {
                postList.value = response.data
            })
            .catch((error) => {
                console.error('Error fetching post list:', error)
                // Handle error appropriately (e.g., show error message to the user)
            })
    }

    const postPost = function (playerId, postData){
        const url = `${REST_POST_URL}/${playerId}`
        axios.post(url, postData)
            .then((response) => {
                // Handle successful response (if needed)
            })
            .catch((error) => {
                console.error('Error posting data:', error)
                // Handle error appropriately
            })
    }

    const deletePost = function (postId){
        const url = `${REST_POST_URL}/${postId}`
        axios.delete(url)
            .then((response) => {
                // Handle successful deletion (if needed)
            })
            .catch((error) => {
                console.error('Error deleting post:', error)
                // Handle error appropriately
            })
    }

    return { postList, getPostList, postPost, deletePost }
})
