<template>
<div>
  <!--写入区域-->
  <mavon-editor
    :ishljs = "true"
    ref="md"
    id="editor"
    v-model="text">
  </mavon-editor>
  <!--预览区域
  <mavon-editor
  v-model="text"
  :toolbarsFlag="false"
  :boxShadow="false"
  :subfield="false"
  defaultOpen="preview"
  >

  </mavon-editor>
  -->
</div>
</template>
<script>
//引入mark编辑器
import {mavonEditor} from 'mavon-editor';
import "mavon-editor/dist/css/index.css";
export default {
  name:"markdown",
  components:{
    mavonEditor
  },
  data(){
    return{
      text:"",
      md:"",
    }},
  methods:{
  },
}
</script>