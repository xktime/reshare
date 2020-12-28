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
            const api = this.$loadArticleDetailUrl + '?articleId=' + this.$route.params.articleId;
            const _this = this;
            this.axios.get(api).then((response) => {
                if (response.data.code !== 200) {
                    this.$alert(response.data.errorMessage);
                    return;
                }
                _this.article = response.data;
                document.title = _this.article.title;
            });
        },
    }
</script>

<style>

</style>
