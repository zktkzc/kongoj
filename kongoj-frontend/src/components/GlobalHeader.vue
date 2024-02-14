<script setup lang="ts">
import routes from "@/router/routes";
import { useRouter } from "vue-router";
import { computed, ref } from "vue";
import { useStore } from "vuex";
import checkAccess from "@/access/checkAccess";
import ACCESS_ENUM from "@/access/accessEnum";

const router = useRouter();
const store = useStore();
const visibleRoutes = computed(() => {
  return routes.filter((route, index) => {
    if (route.meta?.hideInMenu) return false;
    if (
      !checkAccess(store.state.user?.loginUser, route?.meta?.access as string)
    )
      return false;
    return true;
  });
});
const selectedKeys = ref(["/"]);
router.afterEach((to, from, failure) => {
  selectedKeys.value = [to.path];
});
const doMenuClick = (key: string) => {
  router.push({
    path: key,
  });
};
setTimeout(() => {
  store.dispatch("user/getLoginUser", {
    userName: "天空之城",
    userRole: ACCESS_ENUM.ADMIN,
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
