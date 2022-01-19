<template>
    <div class="ui">
        <div>
        <el-form :model="UIDataForm" 
        :rules="UIDataRule"
        ref="UIDataForm"
        @keyup.enter.native="UIDataFormSubmit()"
        status-icon="">
            <el-form-item prop="description">
                <span>ui描述:</span>
                <el-input v-model="UIDataForm.description"
                placeholder="ui描述"></el-input>
            </el-form-item>
            <el-form-item prop="xpath">
                <span></span>
                <el-input v-model="UIDataForm.xpath" placeholder="XPATH">
                </el-input>
            </el-form-item>
            <el-form-item>
                <el-button @click="demo()">重置</el-button>
                <el-button class="login-btn-submit"
                 type="primary"
                 @click="UIDataFormSubmit()">提交</el-button>
            </el-form-item>
        </el-form>
        </div>
        <div class="test-json-viewer">
            <json-viewer
                :value="jsonData"
                :expand-depth="5"
                copyable
                boxed
                sort
            ></json-viewer>
        </div>
    </div>
</template>

<script>

    export default{
        data(){
            return{
                UIDataForm:{
                    description:'',
                    xpath:''
                },
                jsonData:[]
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
                            this.jsonData = JSON.parse(successResponse.data.data.xpath)
                        }
                    }).catch(failResponse => {
                    })
                }
            },
            demo(){
                Object.assign(this.$data, this.$options.data())
            }
        }
    }
</script>
