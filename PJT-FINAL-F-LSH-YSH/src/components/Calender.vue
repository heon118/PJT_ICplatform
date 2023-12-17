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

    <v-main class="bg-grey-lighten-2">
      <v-container>
        <v-row>
          <v-col cols="3">
            <v-sheet rounded="lg">
              <h2 class="selfh4">순위 🏆</h2>
              
              <!-- {{ store.CountryList }} -->
              
              <div class="table-container">
                <table class="styled-table">
                  <thead>
                    <tr>
                      <th>순위표</th>
                      <th>나라</th>
                      <th>🥇</th>
                      <th>🥈</th>
                      <th>🥉</th>
                      <th>메달 수</th>
                    </tr>
                  </thead>
                  <tbody  v-for="(p,index) in store.CountryList" :key="index">
                    <tr>
                      <!-- 여기서부터 반복문-->
                      <td>{{index+1}}</td>
                      <td>
                        {{ p.name }}
                      </td>
                      <td style="color:goldenrod">{{p.gold}}</td>
                      <td style="color: silver;">{{p.silver}}</td>
                      <td style="color: brown;">{{p.bronze}}</td>
                      <td>{{p.totalMedal}}</td>
                    </tr>
                    
                  </tbody>
                </table>
              </div>
            </v-sheet>
          </v-col>

          <v-col>
            <v-sheet min-height="150vh" rounded="lg">

              

              <div class="ttop">
              <!--ver1-->
              <VCalendar :attributes="attributes" v-model="date"
              @dayclick="show"
              :columns="columns" 
              :min-date="'2023-09-19'"
              :initial-page="'2023-10-18'"
              expanded/>
            </div>
              <div>
                <h2 class="txxt">선택된 날짜의 경기</h2>
                <!-- <button @click="toggleSortByExpects">이거 누르면 내림차순</button> -->
                
                <ul class="selected-date-todos">
                  <li v-for="(todo,index) in selectedDateTodos" :key="todo.description">
                    <h3>{{ formatDate(todo.dates) }}</h3>
                    <h2>{{ todo.description }}</h2>
                    
                    <button @click="handleAccordion(index),getRequest(todo.eventIds)">
                      <h3>출전선수 보기 🏃‍♂️ <img src="../../public/assets/arrow-toggle-down.svg"></h3>
                    </button>

                    <Collapse :when="todo.isExpanded" class="v-collapse">
                      <div v-for="(p,index) in athlete">
                        <h3 > {{index+1}}. {{p.name}}</h3>
                      </div>
                    </Collapse>

                    <!--기대값-->
                    <div class="fan-input">
                    <p class="marg">{{ todo.expects }}</p>
                    <button class="selected-date-todos"
                    @click="putRequest(todo.eventIds)"
                    >💜</button>
                  </div>
                  </li>
                </ul>
              </div>

            
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup>
import { ref, onMounted, computed, reactive, watch } from "vue";
import axios from "axios";
import 'v-calendar/style.css';
import { useScreens } from 'vue-screen-utils';
import { useCountryStore } from "@/stores/countries";
import { Collapse } from 'vue-collapsed'

const isExpanded = ref(false)


const { mapCurrent } = useScreens({ xs: '0px', sm: '640px', md: '768px', lg: '1024px' });
const columns = mapCurrent({ lg: 2 }, 1);

const todos = ref([]);
const eventList = ref([]);
const date = ref(new Date());
const selectedDate = ref(null);
const store = useCountryStore();

onMounted(async () => {
  await store.getCountryList();
});


const show = (day) => {
  const clickedDate = day.id;
  
  // 선택된 날짜 업데이트
  selectedDate.value = clickedDate;
};


const isSameDay = (date1, date2) => {
  return (
    date1.getFullYear() === date2.getFullYear() &&
    date1.getMonth() === date2.getMonth() &&
    date1.getDate() === date2.getDate()
  );
};

const selectedDateTodos = computed(() => {
  if (selectedDate.value) {
    return todos.value.filter((todo) =>
      todo.dates.some((d) => isSameDay(d, new Date(selectedDate.value)))
    );
  }
  return [];
});

const formatDate = (date) => {
  const options = {
    year: "numeric",
    month: "2-digit",
    day: "2-digit",
    hour: "2-digit",
    minute: "2-digit",
    hour12: false,
  };
  const formattedDate = new Date(date).toLocaleString("ko-KR", options);
  return formattedDate;
};

function handleAccordion(selectedIndex) {
  selectedDateTodos.value.forEach((todo, index) => {
    todo.isExpanded = index === selectedIndex ? !todo.isExpanded : false;
  });
}

onMounted(async () => {
  try {
    const response = await axios.get(`http://localhost:8080/api/event`);
    eventList.value = response.data;

    // eventList의 각 요소에 대한 todos 생성
    todos.value = eventList.value.map((event) => ({
      description: event.content,
      dates: [new Date(event.eventDate)],
      expects: event.expect,
      eventIds: event.eventId,
      color: 'purple',
    }));

  } catch (error) {
    console.error('Error fetching player detail:', error);
  }
});

const athlete = ref([])
async function getRequest(value) {
  try {
    const url = `http://localhost:8080/api/event/players/${value}`;
    const data = {
      eventId: value,
    };
    const response = await axios.get(url, data);
    athlete.value = response.data;
  } catch (error) {
    console.error('PUT 요청 보내기 오류:', error);
  }
}



async function putRequest(value) {
  try {
    const url = `http://localhost:8080/api/event/${value}`;
    const data = {
      eventId: value,
    };
    const response = await axios.put(url, data);
    console.log(response.data);
    alert('기대지수가 올라갔어요!')
    // location.reload();

    // 기대값 업데이트
    const todo = todos.value.find((todo) => todo.eventIds === value);
    if (todo) {
      todo.expects += 1; // 기대값 +1 증가
    }
  } catch (error) {
    console.error('PUT 요청 보내기 오류:', error);
  }
}


const attributes = computed(() => [
  // Attributes for todos
  ...todos.value.map((todo) => ({
    dates: todo.dates,
    bar:{
      color : todo.color,
    },
    popover: {
      label: todo.description,
    },
  })),
]);



const goathlete = function (id) {
  router.push(`/player/${id}`);
}





const links = ["cal", "video", "player"];
</script>

<style scoped>
.v-collapse {
  transition: height 300ms ease-out;
  /* or transition: height var(--vc-auto-duration) ease-in-out */
}

.fan-input {
  display: flex;
  justify-content: flex-end;
}

.txxt {
  text-align: center;
}

.selected-date-todos {
  list-style: none;
  padding-right: 20px;
  margin: 2%;
}

.selected-date-todos li {
  margin-bottom: 10px;
  padding: 10px;
  background-color: #f2f2f2;
  border-radius: 4px;
  border: 1px solid #e0e0e0;
  transition: background-color 0.3s;
}

.selected-date-todos li:hover {
  background-color: #ebebeb;
}

/* 테이블 스타일 */
.styled-table {
  width: 100%;
  border-radius: 14%;
  border-collapse: collapse;
  border: 1px solid #ccc;
  font-size: 14px;
}

.styled-table th,
.styled-table td {
  padding: 12px;
  text-align: center;
}

.styled-table thead th {
  background-color: rgb(245, 245, 245);
  color: #333;
}

.styled-table tbody tr:nth-child(even) {
  background-color: #f9f9f9;
}

.styled-table tbody tr:hover {
  background-color: #e0e0e0;
}

/* 등수 칸 스타일 */
.styled-table tbody td:first-child {
  font-weight: bold;
}
.styled-table tbody td:nth-child(2) {
  font-size: 15px;
}

/* 메달 아이콘 스타일 */
.styled-table tbody td:nth-child(3),
.styled-table tbody td:nth-child(4),
.styled-table tbody td:nth-child(5) {
  font-size: 15px;
}

/* 테이블 컨테이너 스타일 */
.table-container {
  overflow-x: auto;
}

/* 수정된 CSS */
.selected-date-todos p {
  margin-bottom: 5px;
}

.selected-date-todos .fan-input {
  display: flex;
  align-items: center;
  justify-content: flex-end;
}

.selected-date-todos .fan-input p {
  margin-right: 5px;
}

.selected-date-todos .fan-input button {
  background-color: transparent;
  border: none;
  font-size: 20px;
  cursor: pointer;
  transition: color 0.3s ease;
}

.selected-date-todos .fan-input button:hover {
  color: purple;
}
.my-header {
  height: 6rem;
  background-image:linear-gradient(270deg,#423ee5,#b338ff,#db3283) !important;

  display: flex;
  justify-content: center;
  align-items: center;
}

.ttop {
  padding: 1rem;
  z-index: 999;
}

.my-nav {
  width: 90%;

  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}
.selfh4{
  text-align: center;
  background-color: rgb(237, 237, 237);
  border: 1px solid rgb(206, 205, 205);
  border-radius: 1%;
}
.my-container {
  width:80%;
}
</style>
