import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Edit from '@/components/Edit'
import Read from '@/components/Read'
import My from "@/components/My"
import Login from '@/components/others/Login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/Test',
      name: 'Test',
      component: HelloWorld
    },
    {
      path:"/Edit",
      name:'Edit',
      component:Edit
    },
    {
      path:"/Read",
      name:"Read",
      component:Read
    },
    {
      path:"/",
      name:'My',
      component:My
    },
    {
      path:"/Login",
      name:"Login",
      component:Login
    }
  ]
})
