<script setup lang="ts">
import MdEditor from "@/components/MdEditor.vue";
import { ref } from "vue";
import CodeEditor from "@/components/CodeEditor.vue";

const mdValue = ref("");
const codeValue = ref("");
const handleMdChange = (v: string) => {
  mdValue.value = v;
};
const handleCodeChange = (v: string) => {
  codeValue.value = v;
};
</script>

<template>
  <div id="exampleView">
    <MdEditor :value="codeValue" :handle-change="handleCodeChange" />
    <CodeEditor :value="mdValue" :handle-change="handleMdChange" />
  </div>
</template>

<style scoped></style>
