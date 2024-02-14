import { StoreOptions } from "vuex";

/**
 * 存储的状态信息
 */
const state = () => ({
  loginUser: {
    userName: "未登录",
  },
});

const getters = {};

export default {
  namespaced: true,
  state,
  getters,
  actions: {
    getLoginUser({ commit, state }, payload) {
      commit("updateUser", payload);
    },
  },
  mutations: {
    updateUser(state, payload) {
      state.loginUser = payload;
    },
  },
} as StoreOptions<any>;
