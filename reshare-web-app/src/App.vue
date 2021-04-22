<template>
  <router-view :key="this.$route.fullPath"></router-view>
</template>

<script>
    export default {
        name: 'App'
    }

</script>

<style>
</style>
