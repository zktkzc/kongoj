<script setup lang="ts"></script>

<template>
  <div id="userLayout">
    <a-layout style="min-height: 100vh">
      <a-layout-header class="header">
        <a-space>
          <img class="logo" src="@/assets/oj-logo.svg" alt="logo" />
          <div class="title">空 OJ</div>
        </a-space>
      </a-layout-header>
      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
      <a-layout-footer class="footer">
        <a href="https://github.com/zktkzc" target="_blank">
          空OJ-在线判题系统 by tkzc00
        </a>
      </a-layout-footer>
    </a-layout>
  </div>
</template>

<style scoped>
#userLayout {
  text-align: center;
}

#userLayout .header {
  margin-top: 16px;
}

#userLayout .header .logo {
  width: 64px;
  height: 64px;
}

#userLayout .header .title {
  font-size: 24px;
}

#userLayout .content {
  margin-bottom: 16px;
  padding: 20px;
}

#userLayout .footer {
  padding: 16px;
  position: sticky;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
}

.footer a {
  text-decoration: none;
  color: #444;
}
</style>
