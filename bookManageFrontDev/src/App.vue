<template>
  <div id="app">
    
    <!-- <img alt="Vue logo" src="./assets/logo.png" /> -->
    <!-- <h1>Welcom to LuckDraw</h1>
    <svg-icon iconClass="畅游花市" className="wuhu" /> -->
    <!-- <HelloWorld msg="Welcome to Your Vue.js App" /> -->
    <router-view></router-view>
  </div>
</template>

<script>
// import HelloWorld from "./components/HelloWorld.vue";

export default {
  name: "App",
  components: {
    // HelloWorld,
  },
};
</script>

<style>

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /* margin-top: 60px; */
}
</style>
