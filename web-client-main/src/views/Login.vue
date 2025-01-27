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
      const myHeaders = new Headers();
      myHeaders.append("Content-Type", "application/x-www-form-urlencoded");

      const urlencoded = new URLSearchParams();
      urlencoded.append("grant_type", "password");
      urlencoded.append("client_id", "JavaBackend");
      urlencoded.append("client_secret", "BnwoJuydDpZD5z2wVEDU34WJ8vJlpD3A");
      urlencoded.append("username", this.loginData.email);
      urlencoded.append("password", this.loginData.password);

      const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: urlencoded,
        redirect: "follow"
      };

      let cookie;
      let value;

      fetch("http://172.29.16.61:8080/realms/data-facade-app/protocol/openid-connect/token", requestOptions)
        .then((response) => response.json())
        .then(result => {value = result;})
        //.then(() => console.log(value["access_token"]))
        .then(() => {cookie = value["access_token"];})
        //.then(() => console.log(cookie))
        .then(() => {document.cookie = `${cookie};SameSite=None Secure`;})
        .catch((error) => console.error(error)); 
    }
  }
}
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