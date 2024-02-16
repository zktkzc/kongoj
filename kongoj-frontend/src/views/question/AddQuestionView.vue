<script setup lang="ts">
import { onMounted, reactive } from "vue";
import MdEditor from "@/components/MdEditor.vue";
import { JudgeConfig, QuestionControllerService } from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRoute } from "vue-router";
import router from "@/router";

const route = useRoute();
// 如果页面路由包含update视为更新页面
const updatePage = route.path.includes("update");
const form = reactive({
  answer: "",
  content: "",
  judgeCase: [
    {
      input: "",
      output: "",
    },
  ],
  judgeConfig: {
    timeLimit: 0,
    memoryLimit: 0,
    stackLimit: 0,
  },
  tags: [],
  title: "",
});
/**
 * 根据题目id获取老的数据
 */
const loadData = async () => {
  const id = route.query.id;
  if (!id) return;
  const res = await QuestionControllerService.getQuestionByIdUsingGet(
    id as any
  );
  if (res.code === 0) {
    form.answer = (res.data?.answer as string) || "";
    form.content = (res.data?.content as string) || "";
    form.judgeCase = JSON.parse(res.data?.judgeCase as string) || [
      {
        input: "",
        output: "",
      },
    ];
    form.judgeConfig = JSON.parse(res.data?.judgeConfig as string) || {
      timeLimit: 0,
      memoryLimit: 0,
      stackLimit: 0,
    };
    form.tags = JSON.parse(res.data?.tags as string) || [];
    form.title = (res.data?.title as string) || "";
  } else {
    message.error("获取数据失败：" + res.message);
  }
};
onMounted(() => {
  if (updatePage) {
    loadData();
  }
});
/**
 * 添加测试用例
 */
const handleAdd = () => {
  form.judgeCase?.push({
    input: "",
    output: "",
  });
};
/**
 * 删除测试用例
 * @param index
 */
const handleDelete = (index: number) => {
  form.judgeCase?.splice(index, 1);
};
const doSubmit = async () => {
  if (updatePage) {
    const res = await QuestionControllerService.updateQuestionUsingPost({
      ...form,
      id: route.query.id as any,
    });
    if (res.code === 0) {
      message.success("更新成功");
      await router.push({
        path: "/manage/question",
        replace: true,
      });
    } else {
      message.error("更新失败：" + res.message);
    }
  } else {
    const res = await QuestionControllerService.addQuestionUsingPost(form);
    if (res.code === 0) {
      message.success("创建成功");
    } else {
      message.error("创建失败：" + res.message);
    }
  }
};
const onContentChange = (v: string) => {
  form.content = v;
};
const onAnswerChange = (v: string) => {
  form.answer = v;
};
</script>

<template>
  <div id="add-question-view">
    <h1>创建题目</h1>
    <a-form :form="form" label-align="left">
      <a-form-item label="题目" field="title">
        <a-input v-model="form.title" placeholder="请输入标题" />
      </a-form-item>
      <a-form-item field="tags" label="标签">
        <a-input-tag v-model="form.tags" allow-clear placeholder="请输入标签" />
      </a-form-item>
      <a-form-item field="content" label="题目内容">
        <MdEditor
          style="min-width: 800px"
          :value="form.content"
          :handle-change="onContentChange"
        />
      </a-form-item>
      <a-form-item field="answer" label="答案">
        <MdEditor
          style="min-width: 800px"
          :value="form.answer"
          :handle-change="onAnswerChange"
        />
      </a-form-item>
      <a-form-item
        field="judgeConfig"
        :content-flex="false"
        :merge-props="false"
        label="判题配置"
      >
        <a-space direction="vertical" style="min-width: 640px">
          <a-form-item field="judgeConfig.timeLimit" label="时间限制">
            <a-input-number
              v-model="form.judgeConfig.timeLimit"
              placeholder="请输入时间限制"
              mode="button"
              size="large"
              min="0"
            />
          </a-form-item>
          <a-form-item field="judgeConfig.memoryLimit" label="内存限制">
            <a-input-number
              v-model="form.judgeConfig.memoryLimit"
              placeholder="请输入内存限制"
              mode="button"
              size="large"
              min="0"
            />
          </a-form-item>
          <a-form-item field="judgeConfig.stackLimit" label="堆栈限制">
            <a-input-number
              v-model="form.judgeConfig.stackLimit"
              placeholder="请输入堆栈限制"
              mode="button"
              size="large"
              min="0"
            />
          </a-form-item>
        </a-space>
      </a-form-item>
      <a-form-item
        :content-flex="false"
        :merge-props="false"
        label="测试用例"
        no-style
      >
        <a-form-item
          v-for="(judgeCaseItem, index) of form.judgeCase"
          field="judgeCase"
          :label="`测试用例 ${index + 1}`"
          :key="index"
        >
          <a-space direction="vertical" style="min-width: 480px">
            <a-form-item
              :field="`form.judgeCase[${index}].input`"
              :label="`输入用例 ${index + 1}`"
            >
              <a-input
                v-model="judgeCaseItem.input"
                placeholder="请输入测试输入用例"
              />
            </a-form-item>
            <a-form-item
              :field="`form.judgeCase[${index}].output`"
              :label="`输出用例 ${index + 1}`"
            >
              <a-input
                v-model="judgeCaseItem.output"
                placeholder="请输入测试输出用例"
              />
            </a-form-item>
            <a-button @click="handleDelete(index)" status="danger">
              删除
            </a-button>
          </a-space>
        </a-form-item>
        <a-form-item>
          <a-button
            @click="handleAdd"
            type="outline"
            status="success"
            style="margin-top: 32px; width: 100%"
          >
            添加测试用例
          </a-button>
        </a-form-item>
      </a-form-item>
      <div style="margin-top: 16px"></div>
      <a-form-item>
        <a-button
          type="primary"
          html-type="submit"
          style="width: 100%"
          size="large"
          @click="doSubmit"
        >
          提交
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<style scoped></style>
