<script setup lang="ts">
import { defineProps, withDefaults } from "vue";
import { Viewer } from "@bytemd/vue-next";
import highlight from "@bytemd/plugin-highlight";
import gfm from "@bytemd/plugin-gfm";

/**
 * 定义组件属性的类型
 */
interface Props {
  value: string;
}

/**
 * 给组件定义初始值
 */
const props = withDefaults(defineProps<Props>(), {
  value: () => "",
});
const plugins = [gfm(), highlight()];
</script>

<template>
  <Viewer :value="props.value" @plugin="plugins" />
</template>

<style>
.bytemd-toolbar-icon.bytemd-tippy.bytemd-tippy-right:last-child {
  display: none;
}
</style>
