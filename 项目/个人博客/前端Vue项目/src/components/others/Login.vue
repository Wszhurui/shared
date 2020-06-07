<template>
    <div class="bac">
        <h1>管理员登录</h1>
        <p style="color:red">{{error}}</p>
        <el-row>
        <el-input placeholder="管理员口令" class="password" v-model="password"/>
        </el-row>
        <el-row>
        <el-button type="primary" class="login" @click="login">验明正身</el-button>
        </el-row>
    </div>
</template>
<script>
export default {
    name:"Login",
    data()
    {
        return{
            password:"",
            error:""
        }
    },
    methods:
    {
        login()
        {
            this.$store.commit('getpower',this.password)
            if(this.$store.state.power)
            this.$router.push("/");
            else
            this.error="密码错误！"
        }
    }
}
</script>
<style scoped>
.bac{
    text-align: center
}
.password{
    width: 30%
}
.login{
    width: 30%;
    margin-top: 10px
}
</style>