<script setup lang="ts">
import { defineProps, ref, withDefaults } from "vue";
import { Editor } from "@bytemd/vue-next";
import highlight from "@bytemd/plugin-highlight";
import gfm from "@bytemd/plugin-gfm";

/**
 * 定义组件属性的类型
 */
interface Props {
  value: string;
  handleChange: (v: string) => void;
}

/**
 * 给组件定义初始值
 */
const props = withDefaults(defineProps<Props>(), {
  value: () => "",
  handleChange: (v: string) => {
    console.log(v);
  },
});
const plugins = [gfm(), highlight()];
</script>

<template>
  <Editor :value="value" @plugin="plugins" @change="handleChange" />
</template>

<style scoped></style>
