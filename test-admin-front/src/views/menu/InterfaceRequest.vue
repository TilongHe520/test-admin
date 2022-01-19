<template>
    <div class="interfaceRequest" style="text-align:left">
        <div class="curl">
            <el-form :model="curlData" 
                :rules="curlDataRule"
                ref="curlData"
                @keyup.enter.native="curlDataSubmit()"
                status-icon="">
                <el-form-item prop="description" style="text-align:left" >
                    <el-input v-model="curlData" type="textarea" :rows="8"
                    placeholder="请输入curl" ></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button @click="demo()">重置</el-button>
                    <el-button class="login-btn-submit"
                        type="primary"
                        @click="curlDataSubmit()">提交</el-button>
            </el-form-item>
        </el-form>
        </div>
        <div class="response" style="text-align:left;overflow:auto;height: 400px;" >
            <json-viewer 
                :value="jsonData"
                :expand-depth="5"
                copyable
                boxed
                sort>
            </json-viewer>
        </div>
    </div>
</template>

<script>

    export default{
        data(){
            return{
                curlData:'',
                jsonData:{}
            }
        },
        methods:{
            curlDataSubmit(){
                if(this.curlData===""){
                    this.$message.error('不能为空');
                }else{
                    this.$axios.post('/interfaceRes',{
                        curlData: this.curlData,
                    }).then(successResponse =>{
                        if (successResponse.data.code === 200) {
                            this.jsonData = JSON.parse(successResponse.data.data)
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