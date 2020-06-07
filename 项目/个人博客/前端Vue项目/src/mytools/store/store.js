//Vue标准模块化样式
import Vuex from "vuex"
import Vue from 'vue'
Vue.use(Vuex)
 export default new Vuex.Store({
    state:{
        power:false     //存储状态信息
    },
    mutations:{
        getpower(state,pwd)
        {
           if(pwd=="zhurui")
           state.power=true
        }
    }
})