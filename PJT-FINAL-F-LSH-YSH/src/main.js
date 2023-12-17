import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";

// Vuetify
import "vuetify/styles";
import { createVuetify } from "vuetify";
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";
import { aliases, mdi } from "vuetify/iconsets/mdi";
// material-icon
import "@mdi/font/css/materialdesignicons.css";
import { setupCalendar, Calendar, DatePicker } from 'v-calendar';
import 'v-calendar/style.css';
import VueWordCloud from 'vuewordcloud';
// import VueClientRecaptcha from 'vue-client-recaptcha/dist/vue-client-recaptcha.es'
import VueClientRecaptcha from 'vue-client-recaptcha'
    
const app = createApp(App);
const pinia = createPinia()


const vuetify = createVuetify({
  components,
  directives,
  icons: {
    defaultSet: "mdi",
    aliases,
    sets: {
      mdi,
    },
  },
});

app.use(setupCalendar, {})
// Use the components
app.component('VCalendar', Calendar)
app.component('VDatePicker', DatePicker)
app.component(VueWordCloud.name, VueWordCloud);
// app.component("VueClientRecaptcha", VueClientRecaptcha);  
app.component("VueClientRecaptcha", VueClientRecaptcha);  
app.use(createPinia());
app.use(router);
app.use(vuetify);
app.use(pinia)

// Use plugin defaults (optional)


app.mount("#app");
