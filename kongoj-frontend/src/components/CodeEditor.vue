<script setup lang="ts">
import * as monaco from "monaco-editor";
import { defineProps, onMounted, ref, toRaw, watch, withDefaults } from "vue";

interface Props {
  value: string;
  handleChange: (v: string) => void;
  language: string;
}

/**
 * 给组件定义初始值
 */
const props = withDefaults(defineProps<Props>(), {
  value: () => "",
  handleChange: (v: string) => {
    console.log(v);
  },
  language: "java",
});

watch(
  () => props.language,
  () => {
    codeEditor.value = monaco.editor.create(codeEditorRef.value, {
      value: props.value,
      language: props.language,
      automaticLayout: true,
      lineNumbers: "on",
      readOnly: false,
      theme: "vs-dark",
      colorDecorators: true,
      minimap: {
        enabled: true,
      },
    });
  }
);

const codeEditorRef = ref();
const codeEditor = ref();
onMounted(() => {
  if (!codeEditorRef.value) return;
  codeEditor.value = monaco.editor.create(codeEditorRef.value, {
    value: props.value,
    language: props.language,
    automaticLayout: true,
    lineNumbers: "on",
    readOnly: false,
    theme: "vs-dark",
    colorDecorators: true,
    minimap: {
      enabled: true,
    },
  });
  // 监听内容变化
  codeEditor.value.onDidChangeModelContent(() => {
    props.handleChange(toRaw(codeEditor.value).getValue());
  });
});
</script>

<template>
  <div id="code-editor" ref="codeEditorRef" style="min-height: 400px" />
</template>

<style scoped></style>
