import { createRouter, createWebHistory } from "vue-router";
import Home from "./components/HomeA.vue";
import Category from "./components/MyCategories.vue";
import News from "./components/MyNews.vue";
import ViewCategory from "./components/ViewCategory.vue";
import ViewNews from "./components/ViewNews.vue";
import AddCategory from "./components/AddCategory.vue";
import AddNews from "./components/AddNews.vue";
import UpdateCategory from "./components/UpdateCategory.vue";
import UpdateNews from "./components/UpdateNews.vue";

const routes = [
  {
    path: "",
    component: Home,
  },
  {
    path: "/Category",
    component: Category,
  },
  {
    path: "/News",
    component: News,
  },
  {
    path: "/View-Category/:MaLoai",
    component: ViewCategory,
  },
  {
    path: "/View-News/:MaTinTuc",
    component: ViewNews,
  },
  {
    path: "/Add-Category",
    component: AddCategory,
  },
  {
    path: "/Add-News",
    component: AddNews,
  },
  {
    path: "/Update-Category/:MaLoai",
    component: UpdateCategory,
  },
  {
    path: "/Update-News/:MaTinTuc",
    component: UpdateNews,
  },
  // {
  //   path: "/about",
  //   name: "about",
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () =>
  //     import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
  // },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
