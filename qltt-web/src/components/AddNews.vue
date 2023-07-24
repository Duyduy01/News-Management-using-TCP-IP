<template>
  <div class="bg">
    <div class="root">
      <h1>Thêm Tin tức mới</h1>
      <form class="form" @submit.prevent="addNews" action="">
        <div class="header-form">
          <!-- <label for=""
            >Mã tin tức
            <input type="text" name="MaLoai" v-model="neww.MaTinTuc" />
          </label> -->
          <label for=""
            >Tiêu đề
            <input type="text" name="TenLoai" v-model="neww.TieuDe" />
          </label>
          <label for=""
            >Chi tiết
            <input type="text" name="MoTa" v-model="neww.ChiTiet" />
          </label>
          <label for=""
            >Link hình ảnh
            <input type="text" name="MaLoai" v-model="neww.LinkHinhAnh" />
          </label>
          <label for=""
            >Mã loại tin
            <input type="text" name="TenLoai" v-model="neww.MaLoaiTin" />
          </label>
          <label for=""
            >Ngày đăng
            <input type="text" name="MoTa" v-model="neww.NgayDang" />
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
      neww: {
        // MaTinTuc: "",
        TieuDe: "",
        ChiTiet: "",
        LinkHinhAnh: "",
        MaLoaiTin: "",
        NgayDang: "",
      },
    };
  },
  methods: {
    async addNews() {
      const result = await axios.post(`http://localhost:8902/newss/store`, {
        // MaTinTuc: this.neww.MaTinTuc,
        TieuDe: this.neww.TieuDe,
        ChiTiet: this.neww.ChiTiet,
        LinkHinhAnh: this.neww.LinkHinhAnh,
        MaLoaiTin: this.neww.MaLoaiTin,
        NgayDang: this.neww.NgayDang,
      });
      console.log(result);
      alert("Thêm tin tức thành công");
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
  height: 560px;
  position: relative;
  top: 100px;
  padding-top: 70px;
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
