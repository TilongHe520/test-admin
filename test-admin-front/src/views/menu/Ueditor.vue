<template>
    <div>
      <el-form ref="form" :model="form"
          label-width="80px"
          style="width: 80%;margin:0 auto">

        <div class="button" style="width:3%;float:right;">
          <P><el-button class="el-icon-plus" @click.prevent="addRow()"></el-button></P>
          <p><el-button class="el-icon-minus" @click.prevent="delData()"></el-button></p>
        </div>

        <el-table
          :data="tableData"
        ref="table"
        tooltip-effect="dark"
        border
        stripe
        style="width: 95%"
        @selection-change='selectRow'
        id="insertTableData"
        >
          
          <el-table-column label="序号"  type="index" width="60" align="center"></el-table-column>
          <el-table-column  label="decription" align="center">
            <template slot-scope="scope">
              <el-input v-model="scope.row.decription"></el-input>
            </template>
          </el-table-column>
          <el-table-column  label="uri" align="center">
            <template slot-scope="scope">
              <el-input v-model="scope.row.uri"></el-input>
            </template>
          </el-table-column>
          <el-table-column  label="uriParams" align="center">
            <template slot-scope="scope">
              <el-input v-model="scope.row.uriParams"></el-input>
            </template>
          </el-table-column>
          <el-table-column  label="requestParams" align="center">
            <template slot-scope="scope">
              <el-input v-model="scope.row.address"></el-input>
            </template>
          </el-table-column>
          <el-table-column  label="requestMethod" align="center">
            <template slot-scope="scope">
              <el-input v-model="scope.row.requestMethod"></el-input>
            </template>
          </el-table-column>

          <el-table-column type="selection" width="45" align="center"></el-table-column>
        </el-table>
      </el-form>
    </div>
</template>

<script>
  export default {
    data () {
      return {
        tableData: [
          {
          decription: '',
          uri: '',
          uriParams: '',
          requestParams: '',
          requestMethod: ''
        }
        ],
        selectlistRow: []
      }
    },
    methods: {
      // 获取表格选中时的数据
      selectRow (val) {
        this.selectlistRow = val
      },
      // 增加行
      addRow () {
        var list = {
          rowNum: '',
          address: this.address,
          name: this.name,
          weather: this.weather,
          phone: this.phone,
          date: this.date,
          mdate: this.mdate,
          loveer: this.loveer}
        this.tableData.unshift(list)
      },
      // 删除方法
      // 删除选中行
      delData () {
        for (let i = 0; i < this.selectlistRow.length; i++) {
          let val = this.selectlistRow
          // 获取选中行的索引的方法
          // 遍历表格中tableData数据和选中的val数据，比较它们的rowNum,相等则输出选中行的索引
          // rowNum的作用主要是为了让每一行有一个唯一的数据，方便比较，可以根据个人的开发需求从后台传入特定的数据
          val.forEach((val, index) => {
            this.tableData.forEach((v, i) => {
              if (val.rowNum === v.rowNum) {
                // i 为选中的索引
                this.tableData.splice(i, 1)
              }
            })
          })
        }
        // 删除完数据之后清除勾选框
        this.$refs.tableData.clearSelection()
      }
    }
  }
</script>


<style>
</style>
