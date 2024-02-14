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

const router = useRouter();
const store = useStore();
router.beforeEach((to, from, next) => {
  if (to.meta?.access === "canAdmin") {
    if (store.state.user?.loginUser?.role === "admin") {
      next();
    } else {
      next("/noAuth");
      return;
    }
  } else {
    next();
  }
});
</script>
