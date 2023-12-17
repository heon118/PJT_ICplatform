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

        <v-main>
            <v-container class="my-container">
              <v-row>
                <v-col>
                  <v-sheet  rounded="lg" class="video-sheet">
                    <!-- {{ route.params.id }} -->
                    <iframe :src="`https://www.youtube.com/embed/${route.params.id}?autoplay=1`"
                      title="YouTube video player" frameborder="0"
                      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                      allowfullscreen></iframe>
                  </v-sheet>
                </v-col>
              </v-row>
            </v-container>
          </v-main>
    </v-app>
</template>
  
<script setup>
import { ref, onMounted, computed, reactive, watch } from "vue";
import { useRouter, useRoute  } from 'vue-router';
import axios from "axios";

const route = useRoute();
const router = useRouter();


const links = ["cal", "video", "player"];
</script>
  
<style scoped>
.video-sheet {
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .video-sheet iframe {
    width: 100%;
    height: 60rem;
    border: none;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
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
    width:90%;
  }
</style>
  