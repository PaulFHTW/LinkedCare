import * as Vue from 'vue'
// import { createApp } from 'vue'
import App from './App.vue'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
import axios from 'axios'
import VueAxios from 'vue-axios'
import router from './router'

Vue.createApp(App).use(router,VueAxios,axios).mount('#app')

