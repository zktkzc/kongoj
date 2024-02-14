<script setup lang="ts">
import routes from "@/router/routes";
import { useRouter } from "vue-router";
import { ref } from "vue";
import { useStore } from "vuex";

const router = useRouter();
const visibleRoutes = routes.filter((route, index) => !route.meta?.hideInMenu);
const selectedKeys = ref(["/"]);
router.afterEach((to, from, failure) => {
  selectedKeys.value = [to.path];
});
const doMenuClick = (key: string) => {
  router.push({
    path: key,
  });
};
const store = useStore();
setTimeout(() => {
  store.dispatch("user/getLoginUser", {
    userName: "天空之城",
    role: "admin",
  });
}, 3000);
</script>

<template>
  <a-row id="globalHeader" align="center" :wrap="false">
    <a-col flex="auto">
      <a-menu
        mode="horizontal"
        :selected-keys="selectedKeys"
        @menu-item-click="doMenuClick"
      >
        <a-menu-item key="0" style="padding: 0; margin-right: 30px" disabled>
          <div class="title-bar">
            <img src="@/assets/oj-logo.svg" class="logo" alt="logo" />
            <div class="title">空 OJ</div>
          </div>
        </a-menu-item>
        <a-menu-item v-for="route in visibleRoutes" :key="route.path"
          >{{ route.name }}
        </a-menu-item>
      </a-menu>
    </a-col>
    <a-col flex="100px">
      <div>{{ store.state.user?.loginUser?.userName ?? "未登录" }}</div>
    </a-col>
  </a-row>
</template>

<style scoped>
.title-bar {
  display: flex;
  align-items: center;
}

.title-bar .logo {
  height: 48px;
}

.title-bar .title {
  font-size: 24px;
  margin-left: 16px;
  color: #444;
}
</style>
