<script setup lang="ts">
import { reactive } from "vue";
import { UserControllerService, UserLoginRequest } from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

const router = useRouter();
const store = useStore();
/**
 * 表单信息
 */
const form = reactive<UserLoginRequest>({
  userAccount: "",
  userPassword: "",
});
/**
 * 提交表单
 */
const handleSubmit = async () => {
  const res = await UserControllerService.userLoginUsingPost(form);
  if (res.code === 0) {
    // 修改全局登录状态，获取登录用户信息成功之后，才跳转到主页
    await store.dispatch("user/getLoginUser");
    // 登录成功，跳转到主页
    router.push({
      path: "/",
      replace: true,
    });
    message.success("登录成功");
  } else {
    message.error("登录失败: ", res.message);
  }
};
</script>

<template>
  <div id="userLogin">
    <h2 style="margin-bottom: 16px">用户登录</h2>
    <a-form
      :form="form"
      @submit="handleSubmit"
      rules="required"
      label-align="left"
      auto-label-width
      style="max-width: 480px; margin: 0 auto"
    >
      <a-form-item label="账号" tooltip="请输入账号" field="userAccount">
        <a-input v-model="form.userAccount" placeholder="请输入账号" />
      </a-form-item>
      <a-form-item
        label="密码"
        tooltip="密码不少于8位"
        field="userPassword"
        rules="required"
      >
        <a-input-password
          v-model="form.userPassword"
          placeholder="请输入密码"
        />
      </a-form-item>
      <a-form-item>
        <a-button type="primary" html-type="submit" style="width: 480px">
          登录
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<style scoped></style>
