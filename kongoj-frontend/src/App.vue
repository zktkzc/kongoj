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
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { onMounted } from "vue";

const router = useRouter();
const store = useStore();
router.beforeEach((to, from, next) => {
  if (to.meta?.access === "admin") {
    if (store.state.user?.loginUser?.userRole === "admin") {
      next();
    } else {
      next("/noAuth");
      return;
    }
  } else {
    next();
  }
});
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
