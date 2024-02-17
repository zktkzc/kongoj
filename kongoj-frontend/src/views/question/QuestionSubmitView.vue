<script setup lang="ts">
import { onMounted, ref, watchEffect } from "vue";
import {
  QuestionControllerService,
  QuestionSubmitQueryRequest,
} from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import moment from "moment";

const router = useRouter();
const dataList = ref([]);
const columns = [
  {
    title: "提交记录 ID",
    dataIndex: "id",
  },
  {
    title: "编程语言",
    dataIndex: "language",
  },
  {
    title: "判题信息",
    dataIndex: "judgeInfo",
    slotName: "message",
  },
  {
    title: "消耗时间",
    dataIndex: "judgeInfo",
    slotName: "time",
  },
  {
    title: "消耗内存",
    dataIndex: "judgeInfo",
    slotName: "memory",
  },
  {
    title: "判题状态",
    dataIndex: "status",
    slotName: "status",
  },
  {
    title: "题目 ID",
    dataIndex: "questionId",
  },
  {
    title: "提交者 ID",
    dataIndex: "userId",
  },
  {
    title: "提交时间",
    dataIndex: "createTime",
    slotName: "createTime",
  },
];
const total = ref(0);
const searchParams = ref<QuestionSubmitQueryRequest>({
  language: "",
  questionId: "",
  current: 1,
  pageSize: 10,
});
const loadData = async () => {
  const res = await QuestionControllerService.listQuestionSubmitByPageUsingPost(
    {
      ...searchParams.value,
      sortField: "createTime",
      sortOrder: "descend",
    }
  );
  if (res.code === 0) {
    dataList.value = res.data.records;
    total.value = res.data.total;
  } else {
    message.error("获取提交记录列表失败：" + res.message);
  }
};
/**
 * 监听 searchParams 的变化，改变时触发页面的重新加载
 */
watchEffect(() => {
  loadData();
});
onMounted(() => {
  loadData();
});
const onPageChange = (page: number) => {
  searchParams.value = {
    ...searchParams.value,
    current: page,
  };
};
/**
 * 确认搜素
 */
const doSubmit = () => {
  // 需要重置搜索页号
  searchParams.value = {
    ...searchParams.value,
    current: 1,
  };
};
</script>

<template>
  <div id="questionSubmitView">
    <a-form :model="searchParams" layout="inline">
      <a-form-item field="questionId" label="题目 ID" style="min-width: 200px">
        <a-input
          v-model="searchParams.questionId"
          placeholder="请输入题目 ID"
        />
      </a-form-item>
      <a-form-item field="language" label="语言" style="min-width: 200px">
        <a-select
          v-model="searchParams.language"
          style="min-width: 320px"
          placeholder="请选择编程语言"
        >
          <a-option value="java">Java</a-option>
          <a-option value="go">Golang</a-option>
          <a-option value="cpp">C++</a-option>
        </a-select>
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="doSubmit">搜索</a-button>
      </a-form-item>
    </a-form>
    <a-divider size="0" />
    <a-table
      :columns="columns"
      :data="dataList"
      :pagination="{
        pageSize: searchParams.pageSize,
        current: searchParams.current,
        total,
        showTotal: true,
      }"
      @page-change="onPageChange"
    >
      <template #createTime="{ record }">
        {{ moment(record.createTime).format("yyyy-MM-DD HH:mm:ss") }}
      </template>
      <template #message="{ record }">
        {{
          JSON.stringify(record.judgeInfo.message) === "null"
            ? "无"
            : JSON.stringify(record.judgeInfo.message).replaceAll('"', "")
        }}
      </template>
      <template #time="{ record }">
        {{
          JSON.stringify(record.judgeInfo.time) === "null"
            ? 0
            : JSON.stringify(record.judgeInfo.time).replaceAll('"', "")
        }}
        ms
      </template>
      <template #memory="{ record }">
        {{
          JSON.stringify(record.judgeInfo.memory) === "null"
            ? 0
            : JSON.stringify(record.judgeInfo.memory).replaceAll('"', "")
        }}
        KB
      </template>
      <template #status="{ record }">
        {{
          record.status === 0
            ? "等待判题"
            : record.status === 1
            ? "判题中"
            : record.status === 2
            ? "判题完成"
            : record.status === 3
            ? "判题失败"
            : "未知状态"
        }}
      </template>
    </a-table>
  </div>
</template>

<style scoped>
#questionSubmitView {
  max-width: 1280px;
  margin: 0 auto;
}
</style>
