<template>
  <el-container style="background-color: #E2E2E2;overflow:auto; max-height: 98vh;">
    <el-header style="height: 45px">
      <app_header></app_header>
    </el-header>
    <router-view></router-view>
  </el-container>
</template>

<script>
    import app_header from '@/components/Header.vue';

    export default {
        data() {
            return {
            }
        },
        methods: {},
        components: {
            app_header,
        }
    };
</script>

<style scoped>

  .el-header {
    text-align: center;
    background-color: #E9EEF2;
    border-bottom: 1px solid rgba(0, 0, 0, .22);
    padding: 0 20px;
  }

</style>
