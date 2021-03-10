import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
	state: {
		historyList: []
	},
	mutations: {
		setHistoryList(state, history) {
			let list = state.historyList;
			list.unshift(history)
		},
		clearHistoryList(state) {
			state.historyList = []
		}
	},
	actions: {
		addHistory({commit,store}, history) {
			commit("setHistoryList", history)
		},
		clearHistoryList({commit, store}) {
			commit("clearHistoryList")
		}
	}
})

export default store;