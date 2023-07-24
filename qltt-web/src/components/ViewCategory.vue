<template>
  <div class="bg">
    <div class="root">
      <h1>Xem thể loại tin</h1>
      <div>
        <label for="">
          Mã Loại
          <input type="text" name="MaLoai" v-model="category.MaLoai" readonly />
        </label>
        <label for="">
          Tên Loại
          <input
            type="text"
            name="TenLoai"
            v-model="category.TenLoai"
            readonly
          />
        </label>
        <label for="">
          Mô tả
          <input type="text" name="MoTa" v-model="category.MoTa" readonly />
        </label>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "ViewCategory",
  data() {
    return {
      category: {
        MaLoai: "",
        TenLoai: "",
        MoTa: "",
      },
    };
  },
  async mounted() {
    const result = await axios.get(
      "http://localhost:8902/categories/" + this.$route.params.MaLoai
      // + this.$route.params.MaLoai
    );
    console.log(result);
    let a = JSON.parse(JSON.stringify(result));
    this.category = a.data.category;
  },
};
</script>

<style scoped lang="css">
.bg {
  width: 100%;
  height: 100vh;
  background-color: green;
}
.root {
  width: 400px;
  margin: 0 auto;
  background-color: #fff;
  padding: 30px;
  border-radius: 20px;
  height: 420px;
  position: relative;
  top: 120px;
  padding-top: 70px;
}

h1 {
  position: relative;
  top: -30px;
}
input {
  border: 1px solid black;
  /* outline: none; */
  border-bottom: 1px solid #ddd;
  font-size: 1em;
  padding: 5px 0;
  margin: 10px 0 5px 0;
  width: 100%;
}
</style>
