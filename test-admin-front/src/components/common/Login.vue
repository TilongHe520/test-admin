<template>
    <div class="login-wrapper">
        <div class="login-content">
            <div class="login-main">
                <h2 class="login-main-title">管理员登录</h2>
                <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" status-icon>
                    <el-form-item prop="userName">
                        <el-input v-model="dataForm.userName" placeholder="帐号"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input v-model="dataForm.password" type="password" placeholder="密码"></el-input>
                    </el-form-item>
                    <el-form-item prop="verifycode">
	                    <div style="display:flex">
	                    <el-input
	                    v-model="ruleForm.verifycode"
	                    placeholder="请输入验证码"
	                    @keyup.enter.native="login('ruleForm')"
	                    ></el-input>
	                    <span @click="refreshCode"
	                    ><s-identify :identifyCode="identifyCode"></s-identify
	                    ></span>
	                    </div>
                    </el-form-item>
                    <el-form-item>
                        <el-button class="login-btn-submit" type="primary" @click="dataFormSubmit()">登录</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
    import SIdentify from '../common/identify'
	
    export default {
        components: { SIdentify },
        data() {
            // 验证码自定义验证规则
            const validateVerifycode = (rule, value, callback) => {
            const newVal = value.toLowerCase()
            const identifyStr = this.identifyCode.toLowerCase()
            if (newVal === '') {
                callback(new Error('请输入验证码'))
            } else if (newVal !== identifyStr) {
                console.log('validateVerifycode:', value)
                callback(new Error('验证码不正确!'))
            } else {
                callback()
            }
        }
            return {
                dataForm: {
                    userName: '',
                    password: ''
                },
                dataRule: {
                    userName: [{
                        required: true,
                        message: '帐号不能为空',
                        trigger: 'blur'
                    }],
                    password: [{
                        required: true,
                        message: '密码不能为空',
                        trigger: 'blur'
                    }]
                },
                identifyCodes: '3456789ABCDEFGHGKMNPQRSTUVWXY',
                identifyCode: '',
                ruleForm: {
                userName: '',
                password: '',
                verifycode: ''
            },
                rules: {
                verifycode: [
                    { required: true, trigger: 'blur', validator: validateVerifycode }
                ]
            }
            }
        },
        methods: {
            // 提交表单
            dataFormSubmit() {
                if(this.dataForm.userName===""|| this.dataForm.password === ""){
                    this.$message.error('用户名密码不能为空')
                }else if (this.ruleForm.verifycode.toLowerCase() !== this.identifyCode.toLowerCase()) {
                    this.$message.error('请填写正确验证码')
                    this.refreshCode()
                    return
                }else{
                this.$axios.post('/login',{
                    userName: this.dataForm.userName,
                    password: this.dataForm.password
                }).then(successResponse =>{
                    if (successResponse.data.code === 200) {
                    this.$router.replace({name: 'Home'})
                    }
                }).catch(failResponse => {
                })
            }},
            // 生成随机数
            randomNum(min, max) {
                return Math.floor(Math.random() * (max - min) + min)
            },
            // 切换验证码
            refreshCode() {
                this.identifyCode = ''
                this.makeCode(this.identifyCodes, 4)
            },
            // 生成四位随机验证码
            makeCode(o, l) {
                for (let i = 0; i < l; i++) {
                    this.identifyCode += this.identifyCodes[
                    this.randomNum(0, this.identifyCodes.length)
                    ]
                }
            }
            
        },
        mounted() {
                // 验证码初始化
            this.identifyCode = ''
            this.makeCode(this.identifyCodes, 4)
        }
    }
</script>

<style>
    .login-wrapper {
        position: absolute;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        overflow: hidden;
        background-color: rgba(38, 50, 56, .6);
        background: url(~@/assets/bg.png) no-repeat;
        background-size: 100% 100%;
    }

    .login-content {
        position: absolute;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        margin: auto;
        height: 340px;
        width: 400px;
        background-color: #112234;
        opacity: .8;
    }

    .login-main {
        color: beige;
        padding: 20px 20px 10px 20px;
    }
</style>