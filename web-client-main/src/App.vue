<script>
import Navbar from "./components/navbar/Navbar.vue";

export default {
  name: "App",
  components: {
    Navbar,
  },
};
</script>

<template>
  <header>
    <Navbar />
  </header>
  <div class="container">
    <router-view />
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.container {
  padding-top: 5em;
}
</style>
