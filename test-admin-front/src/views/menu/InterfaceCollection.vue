<template>
    <div class="stress">
        <el-form :model="environmentForm" ref="environmentForm">              
	<el-row :gutter="24" v-for="(item, index) in environmentForm.items" :key="item.key">
		<el-col :span="6">                 
			<el-form-item :prop="'items.' + index + '.name'" :rules="{required: true, message: '名称不能为空', trigger: 'blur'}">
				<el-input v-model="item.name" placeholder="variable"></el-input>
			</el-form-item>                  
		</el-col>              
		<el-col :span="6">                  
			<el-form-item :prop="'items.' + index + '.params'" :rules="{required: true, message: '參數不能为空', trigger: 'blur'}">
				<el-input v-model="item.params" placeholder="params"></el-input>
			</el-form-item>                  
		</el-col>
		<el-col :span="6">
			<el-form-item :prop="'items.' + index + '.api_code'" :rules="{required: true, message: 'api_code不能为空', trigger: 'blur'}">
				<el-input v-model="item.api_code" placeholder="api_code"></el-input>
			</el-form-item>
		</el-col>
		<el-col :span="3" v-if="environmentForm.items.length !== 1">
			<i @click="removeEnvironmentForm(item)" style="font-size:28px;cursor:pointer;" class="el-icon-delete"></i>
		</el-col>
		<el-col :span="3" >
			<el-button @click="addEnvironmentForm" size="mini" class="el-icon-plus"></el-button>
		</el-col>
	</el-row>               

	<el-form-item>
		<el-button type="primary" @click="submitForm()">提交</el-button>                
		<el-button @click="resetForm()">重置</el-button>
	</el-form-item>
</el-form>

    </div>
</template>

<script>
export default {
	data () {
		return {
			environmentForm: {
				items: [{
					name: '',
					params: '',
					api_code: ''
				}]
			}
		}
	},
	methods: {
		//提交事件
		submitForm() {
			this.$axios.post('/stress',{
                items: this.environmentForm.items
            }).then(response=>{
				if(response.data.code === 200){
					Object.assign(this.$data,this.$options.data())
				}
			})
		},
		//重置事件
		resetForm() {
			Object.assign(this.$data, this.$options.data())
		},
		//移除表单项事件
		removeEnvironmentForm(item) {   
			var index = this.environmentForm.items.indexOf(item)
			if (index !== -1) {
				this.environmentForm.items.splice(index, 1)
			}    
		},
		//添加表单项事件
		addEnvironmentForm() {
			this.environmentForm.items.push({
				name: '',
				params: '',
				api_code: ''
			});
		}
	}
}
</script>
