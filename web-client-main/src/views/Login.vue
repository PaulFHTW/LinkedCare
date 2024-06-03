<template>
  <div class="container">
    <div class="row">
      <h2>Login</h2>
      <div class="col-3">
      </div>
      <div class="login col-6 card">
        <form @submit.prevent="validateForm">
          <div class="mb-3">
            <label for="inputEmail" class="form-label">Email-Adresse</label>
            <input type="email" v-model=loginData.email class="form-control" id="inputEmail" required/>
          </div>
          <div class="mb-3">
            <label for="inputPassword" class="form-label">Passwort</label>
            <input type="password" v-model=loginData.password class="form-control" id="inputPassword" required/>
          </div>
          <button type="submit" class="btn btn-secondary mb-3">Einloggen</button>
          <b v-if="errorMsg.length">
            <ul>
                <li v-for="(error, index) in errorMsg" :key="index">{{ error }}</li>
            </ul>
          </b>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import Keycloak from 'keycloak-js'
import vue from 'vue'

let initOptions = {
  url: 'https://0.0.0.0:8445/auth', realm: 'keycloak-demo', clientId: 'vue-test-app', onLoad:'login-required'
}

let keycloak = Keycloak(initOptions);

keycloak.init({ onLoad: initOptions.onLoad }).success((auth) =>{
    
    if(!auth) {
      window.location.reload();
    } else {
      Vue.$log.info("Authenticated");
    }
 
    new Vue({
      render: h => h(App),
    }).$mount('#app')
  

    localStorage.setItem("vue-token", keycloak.token);
    localStorage.setItem("vue-refresh-token", keycloak.refreshToken);

    setInterval(() =>{
      keycloak.updateToken(70).success((refreshed)=>{
        if (refreshed) {
          Vue.$log.debug('Token refreshed'+ refreshed);
        } else {
          Vue.$log.warn('Token not refreshed, valid for '
          + Math.round(keycloak.tokenParsed.exp + keycloak.timeSkew - new Date().getTime() / 1000) + ' seconds');
        }
      }).error(()=>{
          Vue.$log.error('Failed to refresh token');
      });


    }, 60000)

}).error(() =>{
  Vue.$log.error("Authenticated Failed");
});
/*
export default {
  setup(){},
  data(){
    return {
      loginData: {
        email: "",
        password: "",
      },
      errorMsg: []
    }
  },
  methods: {
    validateForm(){
      this.errorMsg = [];
      if(this.loginData.email == "" || this.loginData.email == null){
        this.errorMsg.push("Bitte geben Sie Ihre Email-Adresse an!");
      }

      if(this.loginData.password == "" || this.loginData.password == null){
        this.errorMsg.push("Bitte geben Sie Ihr Passwort ein!");
      }

      if(!this.errorMsg.length) {
        this.loginUser();
      }
    },
    loginUser(){
      // TODO check loginData with DB
      const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username : this.loginData.email, password : this.loginData.password})
      };
      fetch('http://172.29.16.61:81/login', requestOptions)
          .then(response => response.json())

 
      //alert(this.loginData.email + " " + this.loginData.password)
      //window.location="/";
    }
  }
};*/
</script>

<style scoped>
.login {
  padding: 1rem;
  /* background-color: #e3f2fd;
  padding: 1rem;
  border-radius: 0.5rem;
  border: solid;
  border-width: thin; */
}

form {
  text-align: left;
}

ul {
  list-style-type: none;
  color: rgb(192, 34, 34);
}

</style>