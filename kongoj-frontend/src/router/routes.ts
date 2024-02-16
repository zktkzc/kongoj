import { RouteRecordRaw } from "vue-router";
import ACCESS_ENUM from "@/access/accessEnum";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/user",
    name: "用户",
    component: () => import("../layouts/UserLayout.vue"),
    meta: {
      hideInMenu: true,
    },
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
    component: () => import("../views/question/QuestionsView.vue"),
  },
  {
    path: "/questions",
    name: "浏览题目",
    component: () => import("../views/question/QuestionsView.vue"),
  },
  {
    path: "/view/question/:id",
    name: "查看题目",
    component: () => import("../views/question/ViewQuestionView.vue"),
    props: true,
    meta: {
      hideInMenu: true,
      access: ACCESS_ENUM.USER,
    },
  },
  {
    path: "/add/question",
    name: "添加题目",
    component: () => import("../views/question/AddQuestionView.vue"),
    meta: {
      access: ACCESS_ENUM.USER,
    },
  },
  {
    path: "/update/question",
    name: "更新题目",
    component: () => import("../views/question/AddQuestionView.vue"),
    meta: {
      access: ACCESS_ENUM.USER,
      hideInMenu: true,
    },
  },
  {
    path: "/manage/question",
    name: "管理题目",
    component: () => import("../views/question/ManageQuestionView.vue"),
    meta: {
      access: ACCESS_ENUM.ADMIN,
    },
  },
  {
    path: "/noAuth",
    name: "无权限",
    component: () => import("../views/NoAuthView.vue"),
    meta: {
      hideInMenu: true,
    },
  },
];

export default routes;
