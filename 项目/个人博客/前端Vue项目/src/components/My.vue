<template>
    <div>
        <el-row class="head_body">
        <div class="menu">
        <el-dropdown trigger="click" @command="menu">
         <span class="el-dropdown-link">
            更多<i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command='m'>获取管理权限</el-dropdown-item>
        <el-dropdown-item command='w'>写博客</el-dropdown-item>
        <el-dropdown-item command='t'>开发测试页面</el-dropdown-item>
        <el-dropdown-item>待续</el-dropdown-item>
        <el-dropdown-item>待续</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
        </el-row>
        <!-- 头尾分割线-->
        <el-row>
            <el-col class="ce_body">
            </el-col>
        <el-col class="ar_body">
            <el-row v-for="(i,index) in articles" :key="index" class="article">
                <p @click="deleteA(index)">删除</p>
                <h3>{{i.title}}({{i.date}})</h3>
                <el-row>{{i.idea}}...</el-row>
                <el-row>
                <a @click="toArticle(index)">阅读全部</a>
            </el-row>
            <el-divider></el-divider>
            </el-row>
            <div style="font-size:10px;text-align:center">2020年4月10日</div>
        </el-col>
        <el-col class="ce_body"></el-col>
        </el-row>
    </div>
</template>
<script>
export default {
    name:"My",
    data()
    {
      return{
            articles:[],
            text:"",
            countPage:0,
    }},
    mounted()
    {
        this.getArticle();
    },
    methods:{
        deleteA(index)
        {
            if(this.$store.state.power)
            {
                var form =new FormData();
                form.append("path",this.articles[index].path);
                form.append("date",this.articles[index].date);
                this.$axios.post("/deleteA",form).then(res=>
                {
                    this.getArticle();
                })
            }else
            {
                this.$alert('博主的真诚安慰：气不气，略略略', '无管理员权限', {confirmButtonText: '确定',});
            }
            
        },
        show(index){
            this.dates[index].sign=true;
        },
        getArticle()
        {
            var form=new FormData()
            form.append("countPage",this.countPage);
            this.$axios.post("/api/getAll",form).then(res=>{
                if(res.data!=null)
                {
                    this.articles=res.data;
                }
            })
        },
        toArticle(index)
        {
            this.$router.push("/Read?path="+this.articles[index].path)
        },
        toWrite()
        {
            if(this.$store.state.power)
            {
                this.$router.push("/Edit");
            }else
            {
                 this.$alert('来自博主的安慰：这权利真香嗷~~', '无管理员权限', {confirmButtonText: '确定',});
            }
        },
        menu(sign)
        {
            switch(sign){
                case 'w': this.toWrite();break;
                case 'm': this.$router.push("/Login");break;
                case 't': this.$router.push("/Test");break;
            }
        }
    }
}
</script>
<style scoped>
.article{
    background: -moz-element()
}
.theend{
    text-align: center
}
.menu{
    text-align: right
}
.head_body{
    background: bisque;
    height: 60px;
    line-height: 60px
}
a{
    color: blue;
}
p{
    margin-left: 90%;
    color:darkcyan
}
h3{
    color: cadetblue
}
@media(min-width: 980px){
.ar_body{
    overflow:auto;
    height: 87vh;
    width: 60%;
}
.ce_body{
    height: 87vh;
    width: 20%;
    text-align: center
}
}
@media (max-width:970px)
{
.ar_body{
    overflow:auto;
    height: 87vh;
}
}
</style>