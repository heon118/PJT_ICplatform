import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/views/HomeView.vue";
import CalenderView from "@/views/CalenderView.vue";
import CheerView from "@/views/CheerView.vue";
import AdminView from "@/views/AdminView.vue";
import PlayerView from "@/views/PlayerView.vue";
import PostView from "@/views/PostView.vue";
import VideoView from "@/views/VideoView.vue";
import PlayerList from "@/components/player/PlayerList.vue";
import PlayerDetail from "@/components/player/PlayerDetail.vue";
import PlayerCheer from "@/components/player/PlayerCheer.vue";
import TestPost from "../components/player/TestPost.vue"
import AdminMedal from "../components/player/AdminMedal.vue"
import Calender from "../components/Calender.vue"
import VideoList from "../components/VideoList.vue"
import VideoDetail from "../components/VideoDetail.vue"


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },

    {
      path: "/posting",
      name: "post",
      component: PostView,
      children :[
        {
          path: ":id",
          name: "testpost",
          component: TestPost,
        }
      ]
    },
    {
      path: "/cal",
      name: "calender",
      component: Calender,
    },
    
    {
      path: "/cheer",
      name: "Cheer",
      component: CheerView,
      children :[
        {
          path: ":id",
          name: "playerCheer",
          component: PlayerCheer,
        }
      ]
    },
    {
      path: "/admin",
      name: "Admin",
      component: AdminView,
      children :[
        {
          path: "haley",
          name: "AdminMedal",
          component: AdminMedal,
        }
      ]
    },
    {
      path: "/player",
      name: "player",
      component: PlayerView,
      children: [
        {
          path: "",
          name: "playerList",
          component: PlayerList,
        },
        {
          path: ":id",
          name: "playerDetail",
          component: PlayerDetail
        },
      ],
    },
    {
      path: "/video",
      name: "video",
      component: VideoView,
      children: [
        {
          path: "",
          name: "videoList",
          component: VideoList,
        },
        {
          path: ":id",
          name: "videoDetail",
          component: VideoDetail,
        },
      ],
    },
  ],
});

export default router;
