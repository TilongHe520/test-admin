<template>
    <div class="ui">
        <el-form :model="UIDataForm" 
        :rules="UIDataRule"
        ref="UIDataForm"
        @keyup.enter.native="UIDataFormSubmit()"
        status-icon="">
            <el-form-item prop="description">
                <el-input v-model="UIDataForm.description"
                placeholder="ui描述"></el-input>
            </el-form-item>
            <el-form-item prop="xpath">
                <el-input v-model="UIDataForm.xpath" placeholder="XPATH">
                </el-input>
            </el-form-item>
            <el-form-item>
                <el-button class="login-btn-submit"
                 type="primary"
                 @click="UIDataFormSubmit()">提交</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>

    export default{
        data(){
            return{
                UIDataForm:{
                    description:'',
                    xpath:''
                }
            }
        },
        methods:{
            UIDataFormSubmit(){
                if(this.UIDataForm.description==="" || this.UIDataForm.xpath === ""){
                    this.$message.error('不能为空');
                }else{
                    this.$axios.post('/uitest',{
                        description: this.UIDataForm.description,
                        xpath: this.UIDataForm.xpath
                    }).then(successResponse =>{
                        if (successResponse.data.code === 200) {
                            this.$router.replace({name: 'Home'})
                        }
                    }).catch(failResponse => {
                    })
                }
            }
        }
    }
</script>
