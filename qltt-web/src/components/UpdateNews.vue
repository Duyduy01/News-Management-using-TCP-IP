<template>
  <div class="bg">
    <div class="root">
      <h1>Sửa thông tin của Tin tức</h1>
      <form @submit.prevent="updateNews" action="">
        <div class="header-form">
          <!-- <label for=""
              >Mã Loại
              <input type="text" name="MaLoai" v-model="category.MaLoai" />
            </label> -->
          <label for=""
            >Tiêu đề
            <input type="text" name="TieuDe" v-model="neww.TieuDe" />
          </label>
          <label for=""
            >Chi tiết
            <input type="text" name="ChiTiet" v-model="neww.ChiTiet" />
          </label>
          <label for=""
            >Link hình ảnh
            <input type="text" name="LinkHinhAnh" v-model="neww.LinkHinhAnh" />
          </label>
          <label for=""
            >Mã loại tin
            <input type="text" name="MaLoaiTin" v-model="neww.MaLoaiTin" />
          </label>
          <label for=""
            >Ngày đăng
            <input type="text" name="NgayDang" v-model="neww.NgayDang" />
          </label>
        </div>
        <button type="submit">Sửa thông tin</button>
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
        // MaLoai: "",
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
    // this.upload();
  },
  methods: {
    async updateNews() {
      const update = await axios.put(
        "http://localhost:8902/newss/" +
          this.$route.params.MaTinTuc +
          "/update",
        {
          // MaLoai: this.category.MaLoai,
          TieuDe: this.neww.TieuDe,
          ChiTiet: this.neww.ChiTiet,
          LinkHinhAnh: this.neww.LinkHinhAnh,
          MaLoaiTin: this.neww.MaLoaiTin,
          NgayDang: this.neww.NgayDang,
        }
      );
      if (update.status == 200) {
        alert("update thành công");
        this.$router.push(`/News`);
        // console.log('1')
      }
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

button {
  background-color: #3498db;
  padding: 10px 20px;
  margin-top: 10px;
  border: none;
  color: white;
}
</style>
