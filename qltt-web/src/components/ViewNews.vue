<template>
  <div class="bg">
    <div class="root">
      <h1>Xem tin</h1>
      <div>
        <label for="">
          Mã Tin tức
          <input type="text" name="MaTinTuc" v-model="neww.MaTinTuc" readonly />
        </label>
        <label for="">
          Tiêu đề
          <input type="text" name="TieuDe" v-model="neww.TieuDe" readonly />
        </label>
        <label for="">
          Chi tiết
          <input type="text" name="ChiTiet" v-model="neww.ChiTiet" readonly />
        </label>
        <label for="">
          Link hình ảnh
          <input
            type="text"
            name="LinkHinhAnh"
            v-model="neww.LinkHinhAnh"
            readonly
          />
        </label>
        <label for="">
          Mã loại tin
          <input
            type="text"
            name="MaLoaiTin"
            v-model="neww.MaLoaiTin"
            readonly
          />
        </label>
        <label for="">
          Mô tả
          <input type="text" name="NgayDang" v-model="neww.NgayDang" readonly />
        </label>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "ViewNews",
  data() {
    return {
      neww: {
        MaTinTuc: "",
        TieuDe: "",
        ChiTiet: "",
        LinkHinhAnh: "",
        MaLoaiTin: "",
        NgayDang: "",
      },
    };
  },
  async mounted() {
    const result = await axios.get(
      "http://localhost:8902/newss/" + this.$route.params.MaTinTuc
      // + this.$route.params.MaLoai
    );
    console.log(result);
    let a = JSON.parse(JSON.stringify(result));
    this.neww = a.data.news;
    // console.log(this.$route.params.MaTinTuc);
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
