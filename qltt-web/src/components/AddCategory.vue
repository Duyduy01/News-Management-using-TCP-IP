<template>
  <div class="bg">
    <div class="root">
      <h1>Thêm thể loại tin mới</h1>
      <form class="form" @submit.prevent="addCategory" action="">
        <div class="header-form">
          <!-- <label for=""
            >Mã Loại
            <input type="text" name="MaLoai" v-model="category.MaLoai" />
          </label> -->
          <label for=""
            >Tên Loại
            <input type="text" name="TenLoai" v-model="category.TenLoai" />
          </label>
          <label for=""
            >Mô tả
            <input type="text" name="MoTa" v-model="category.MoTa" />
          </label>
        </div>
        <button type="submit">Thêm mới</button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      category: {
        MaLoai: "",
        TenLoai: "",
        MoTa: "",
      },
    };
  },
  methods: {
    async addCategory() {
      const result = await axios.post(
        `http://localhost:8902/categories/store`,
        {
          // MaLoai: this.category.MaLoai,
          TenLoai: this.category.TenLoai,
          MoTa: this.category.MoTa,
        }
      );
      console.log(result);
      alert("Thêm loại tin tức thành công");
    },
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
  height: 510px;
  position: relative;
  top: 100px;
  padding-top: 100px;
}
.form {
  position: relative;
  top: 60px;
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

button {
  background-color: #3498db;
  padding: 10px 20px;
  margin-top: 10px;
  border: none;
  color: white;
}
</style>
