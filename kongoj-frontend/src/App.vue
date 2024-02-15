<template>
  <div id="app">
    <basic-layout />
  </div>
</template>

<style scoped>
#app {
}
</style>

<script setup lang="ts">
import BasicLayout from "@/layouts/BasicLayout.vue";
import { onMounted } from "vue";

/**
 * 全局初始化函数，有全局单次调用的代码，可以放在这里
 */
const doInit = () => {
  console.log("Hello，欢迎来到我的项目！");
};
onMounted(() => {
  doInit();
});
</script>
