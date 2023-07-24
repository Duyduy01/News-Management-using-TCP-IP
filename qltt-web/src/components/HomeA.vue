<template>
  <div class="bg">
    <h1>My home</h1>
    <div class="btn">
      <button class="viewCategory" @click="$router.push('/Category')">
        Category
      </button>
      <button class="viewNews" @click="$router.push('/News')">News</button>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.bg {
  width: 100vw;
  min-height: 100vh;
  background: #5a91c9;
}
h1 {
  position: relative;
  top: 120px;
  font-size: 3rem;
}

.btn {
  justify-content: space-around;
  align-items: center;
  display: flex;
  position: relative;
  top: 250px;
  height: 100px;
}
.viewCategory {
  border-radius: 20px;
  width: 200px;
  height: 100px;
  background-color: #b45211;
  color: white;
}
.viewNews {
  border-radius: 20px;
  width: 200px;
  height: 100px;
  background-color: #b45211;
  color: white;
}
</style>
