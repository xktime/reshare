<template>
  <el-container style="background-color: #E2E2E2;">
    <el-header>Header</el-header>
    <el-container class="container">
      <el-main>
        <el-row v-for="data in tableData">
          <!--头像-->
          <el-col :span="5">
            <el-avatar v-if="data.url != null" :size="85" fit="contain" :src="data.url"></el-avatar>
            <el-avatar v-else :size="85" fit="contain" icon="el-icon-user-solid"></el-avatar>
          </el-col>
          <el-col :span="19">
              <span class="item_title">
                <el-link :href="data.url" target="_blank" :underline="false">{{data.title}}</el-link>
              </span><br><br>
            <span class="text-desc"><span>{{data.value}}</span> 个回复 •
                <span>{{data.value}}</span> 次浏览 •
                <span>{{new Date(data.date).toLocaleString()}}</span>
              </span>
            <el-divider></el-divider>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
    export default {
        data() {
            return {
                tableData: [{
                    url: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
                    value: 1,
                    title: "123",
                    date: 1598430915144
                },
                    {
                        url: null,
                        value: 2,
                        title: "123",
                        date: 1598430915144
                    },
                    {
                        url: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
                        value: 3,
                        title: "123",
                        date: 1598430915144
                    },
                    {
                        url: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
                        value: 4,
                        title: "123",
                        date: 1598430915144
                    },
                    {
                        url: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
                        value: 4,
                        title: "123",
                        date: 1598430915144
                    },
                    {
                        url: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
                        value: 4,
                        title: "123",
                        date: 1598430915144
                    },
                    {
                        url: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
                        value: 4,
                        title: "123",
                        date: 1598430915144
                    }
                    ,
                    {
                        url: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
                        value: 4,
                        title: "123",
                        date: 1598430915144
                    }],
            }
        },
    }
</script>

<style scoped>
  .el-header, .el-footer {
    text-align: center;
    background-color: #09dac6;
    border-bottom: 1px solid rgba(0, 0, 0, .22);
    padding: 0 20px;
  }


  .el-main {
    background-color: #E9EEF2;
    color: #333;
    text-align: left;
    margin: 15px 310px 0 20px;
    min-width: 80%;
    max-width: 80%;
    /*min-height: 92%;*/
    /*max-height: 92%;*/
  }

  .container {
    background-color: #E2E2E2;
    color: #333;
    text-align: center;
    margin: 0 auto;
    max-width: 100%;
    min-height: 93%;
    max-height: 93%;
  }

  .text-desc {
    font-size: 12px;
    font-weight: normal;
    color: #999;
  }

  .el-row {
    min-width: 100%;
    max-width: 100%;
  }

  .el-col {
    border-radius: 4px;
  }

  .item_title {
    font-size: 18px;
    line-height: 130%;
    text-shadow: 0 1px 0 #fff;
    word-break: break-word;
    hyphens: auto;
  }

</style>
