<template>
  <el-container>
    {{articleId}}
  </el-container>
</template>

<script>
    export default {
        data() {
            return {
                article: null,
            }
        },
        methods: {},
        mounted() {
            const api = this.$loadArticleUrl + '?articleId=' + this.$route.params.articleId;
            const _this = this;
            this.axios.get(api).then((response) => {
                if (response.data.code !== 200) {
                    this.$alert(response.data.errorMessage);
                    return;
                }
                document.title = this.$route.params.articleId;
            });
        },
    }
</script>

<style>

</style>
