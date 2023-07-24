<template>
  <div class="bg">
    <a href="/">Home</a>
    <h1 class="header">Thể Loại tin</h1>
    <button class="add" @click="$router.push('/Add-Category')">
      Thêm thể loại tin
    </button>
    <table class="table">
      <thead>
        <tr>
          <th>Mã Loại</th>
          <th>Tên Loại</th>
          <th>Mô tả</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="value in categories" :key="value.MaLoai">
          <td>{{ value.MaLoai }}</td>
          <td>{{ value.TenLoai }}</td>
          <td>{{ value.MoTa }}</td>
          <td>
            <button @click="$router.push('/View-Category/' + value.MaLoai)">
              View
            </button>
            <button @click="$router.push('/Update-Category/' + value.MaLoai)">
              Update
            </button>
            <button v-on:click="deleteCategory(value.MaLoai)">Delete</button>
          </td>
        </tr>
      </tbody>
      <!-- <div>
            <router-link to="/posts">Go to posts page</router-link>
          </div> -->
    </table>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      categories: {
        MaLoai: "",
        TenLoai: "",
        MoTa: "",
      },
    };
  },
  async mounted() {
    this.loadData();
    // let result = await axios.get("http://localhost:8902/categories");
    // let a = JSON.parse(JSON.stringify(result));
    // this.categories = a.data.categories;
    // console.log(a);
  },
  methods: {
    async deleteCategory(MaLoai) {
      let delete1 = await axios.delete(
        "http://localhost:8902/categories/" + MaLoai + "/delete"
      );
      if (delete1.status == 200) {
        this.loadData();
        // this.loadData();
      }
    },
    async loadData() {
      let result = await axios.get("http://localhost:8902/categories");
      console.log(result.data);
      let a = JSON.parse(JSON.stringify(result));
      this.categories = a.data.categories;
      console.log(a);
    },
  },
};
</script>

<style scoped lang="css">
.bg {
  width: 100vw;
  min-height: 100vh;
  background: #5a91c9;
}
body {
  /* background: #fafafa url(https://jackrugile.com/images/misc/noise-diagonal.png); */
  color: #444;
  font: 100%/30px "Helvetica Neue", helvetica, arial, sans-serif;
  text-shadow: 0 1px 0 #fff;
  /* background-color: #2c3e50; */
}

.bg a {
  text-decoration: none;
  font-size: 1.5rem;
  display: flex;
  height: 6vh;
  width: 100%;
  padding: 30px 50px;
  color: #f5f5f5;
}
.header {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 12vh;
  width: 100%;
  color: black;
  font-size: 3rem;
}
.add {
  position: relative;
  height: 50px;
  width: 150px;
  border-radius: 10px;
  left: 30%;
}
strong {
  font-weight: bold;
}

em {
  font-style: italic;
}

table {
  background: #f5f5f5;
  border-collapse: separate;
  box-shadow: inset 0 1px 0 #fff;
  font-size: 12px;
  line-height: 24px;
  margin: 30px auto 0;
  text-align: left;
  width: 70%;
  height: 100%;
}

thead {
  height: 4.5rem;
  font-size: 1.5rem;
  vertical-align: middle;
  text-align: center;
}

th {
  background: linear-gradient(#777, #444);
  border-left: 1px solid #555;
  border-right: 1px solid #777;
  border-top: 1px solid #555;
  border-bottom: 1px solid #333;
  box-shadow: inset 0 1px 0 #999;
  color: #fff;
  font-weight: bold;
  padding: 10px 15px;
  position: relative;
  text-shadow: 0 1px 0 #000;
}

th:after {
  background: linear-gradient(
    rgba(255, 255, 255, 0),
    rgba(255, 255, 255, 0.08)
  );
  content: "";
  display: block;
  height: 25%;
  left: 0;
  margin: 1px 0 0 0;
  position: absolute;
  top: 25%;
  width: 100%;
}

th:first-child {
  border-left: 1px solid #777;
  box-shadow: inset 1px 1px 0 #999;
}

th:last-child {
  box-shadow: inset -1px 1px 0 #999;
}

td {
  border-right: 1px solid #fff;
  border-left: 1px solid #e8e8e8;
  border-top: 1px solid #fff;
  border-bottom: 1px solid #e8e8e8;
  padding: 10px 15px;
  position: relative;
  transition: all 300ms;
  height: 3.5rem;
  vertical-align: middle;
  font-size: 1.5rem;
}

td:first-child {
  box-shadow: inset 1px 0 0 #fff;
}

td:last-child {
  border-right: 1px solid #e8e8e8;
  box-shadow: inset -1px 0 0 #fff;
}

/* tr {
  background: url(https://jackrugile.com/images/misc/noise-diagonal.png);
} */

tr:nth-child(odd) td {
  background: #f1f1f1;
}

tr:last-of-type td {
  box-shadow: inset 0 -1px 0 #fff;
}

tr:last-of-type td:first-child {
  box-shadow: inset 1px -1px 0 #fff;
}

tr:last-of-type td:last-child {
  box-shadow: inset -1px -1px 0 #fff;
}

/* tbody:hover td {
  color: transparent;
  text-shadow: 0 0 3px #aaa;
} */

tbody:hover tr:hover td {
  color: red;
  text-shadow: 0 1px 0 #fff;
}

button {
  background-color: #b45211;
  padding: 10px 20px;
  margin-top: 10px;
  border: none;
  border-radius: 10px;
  color: white;
  margin: 0px 15px;
}
</style>
