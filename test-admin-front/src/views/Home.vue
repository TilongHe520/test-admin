<template>
    <!-- 
        v-loading、element-loading-text、element-loading-background、element-loading-spinner 用于定义加载的样式
     -->
    <el-container class="container" v-loading="false" element-loading-text="拼命加载中" element-loading-background="rgba(0, 0, 0, 0.8)"
     element-loading-spinner="el-icon-loading">
        <!-- 侧边栏 -->
        <Aside :foldAside="foldAside" />
        <!-- 
            direction="vertical"  用于垂直布局
        -->
        <el-container direction="vertical">
            <!-- 头部导航栏 -->
            <Header @foldOrOpenAside="foldOrOpen" />
            <!-- 内容 -->
            <Content />
            <Footer />
        </el-container>
    </el-container>
</template>

<script>
    import Header from '@/views/home/Header.vue'
    import Aside from '@/views/home/Aside.vue'
    import Content from '@/views/home/Content.vue'
    import Footer from '@/views/home/Footer.vue'

    export default {
        name: 'Home',
        components: {
            Header,
            Aside,
            Content,
            Footer
        },
        data() {
            return {
                foldAside: true
            }
        },
        methods: {
            foldOrOpen(data) {
                this.foldAside = data
            },
            // 展开密码修改框
            showPasswordBox() {
                this.UpdatePasswordVisible = true
                // this.$nextTick 表示数据渲染后，执行密码框初始化
                this.$nextTick(() => {
                this.$refs.updatePassowrd.init()
    })
},
        }
    }
</script>
<style>
    .container {
        height: 100%;
    }
</style>