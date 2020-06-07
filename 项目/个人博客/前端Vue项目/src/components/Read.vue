<template>
    <el-row>
      <mavon-editor
        v-model="text"
        :toolbarsFlag="false"
        :boxShadow="false"
        :subfield="false"
        defaultOpen="preview"
      >
      </mavon-editor>
    </el-row>
</template>
<script>
//引入mark编辑器
import {mavonEditor} from 'mavon-editor';
import "mavon-editor/dist/css/index.css";
export default {
    name:"Read",
    components:{
      mavonEditor
      },
    data(){
        return{
           text:"",
    }},
    mounted()
    {
        this.get(this.$route.query.path);
    },
    methods:{
         get(path)
         {
            if(path!=null)
            {
            var form =new FormData();
            form.append("path",path)
            this.$axios.post("/read",form).then(res=>{
            this.text=res.data})}
            },
            }

}
</script>
