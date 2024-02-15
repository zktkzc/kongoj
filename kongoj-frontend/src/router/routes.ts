import { RouteRecordRaw } from "vue-router";
import ACCESS_ENUM from "@/access/accessEnum";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/user",
    name: "用户",
    component: () => import("../layouts/UserLayout.vue"),
    children: [
      {
        path: "/user/login",
        name: "用户登录",
        component: () => import("../views/user/UserLoginView.vue"),
      },
      {
        path: "/user/register",
        name: "用户注册",
        component: () => import("../views/user/UserRegisterView.vue"),
      },
    ],
  },
  {
    path: "/",
    name: "主页",
    component: () => import("../views/HomeView.vue"),
  },
  {
    path: "/about",
    name: "关于",
    component: () => import("../views/AdminView.vue"),
  },
  {
    path: "/noAuth",
    name: "无权限",
    component: () => import("../views/NoAuthView.vue"),
    meta: {
      hideInMenu: true,
    },
  },
  {
    path: "/admin",
    name: "管理员可见",
    meta: {
      access: ACCESS_ENUM.ADMIN,
    },
    component: () => import("../views/AdminView.vue"),
  },
];

export default routes;
