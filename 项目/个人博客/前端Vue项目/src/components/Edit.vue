<template>
    <div>
        <mavon-editor
          ref="md"
          id="editor"
          v-model="article"
          @save="$saveArticle"
          @imgAdd="$addImg"
          >
        </mavon-editor>
    </div>
</template>
<script>
//引入mark编辑器
import {mavonEditor} from 'mavon-editor';
import "mavon-editor/dist/css/index.css";
export default {
    name:'Edit',
    components:{mavonEditor},
    data()
    {
    return{
        article:"",
        md:"",
    }},
    mounted()
    {
        if(!this.$store.state.power)
        {
            this.$router.push("/");
        }
    },
    methods:{

        $addImg(pos,$file)
        {
            var form=new FormData();
            form.append('file',$file);
            this.$axios.post("/saveImg",form).then((url)=>{
                console.log(url);
                this.$refs.md.$img2Url(pos,url.data);
            })
        },
        
        $saveArticle(value,render)
        {
            var form=new FormData();
            form.append("body",value);
            this.$axios.post("/saveArticle",form).then(res=>{
                console.log("保存成功");
                this.$router.push('/')
            })
        },
    },
}
</script>