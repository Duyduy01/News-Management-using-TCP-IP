// const { defineConfig } = require("@vue/cli-service");
// module.exports = defineConfig({,
//   transpileDependencies: true,
// });
// module.exports = {
//   configureWebpack: {
//     devServer: {
//       headers: { "Access-Control-Allow-Origin": "*" },
//     },
//   },
// };

module.exports = {
  devServer: {
    proxy: "http://localhost:8901/",
  },
};
