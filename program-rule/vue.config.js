module.exports = {
  outputDir: "dist",
  publicPath: "",

  devServer: {
    host: "localhost",
    port: 8081,
    // proxy: {
    //   "/api": {
    //     target: "http://localhost:8080",
    //     ws: false,
    //     changeOrigin: true,
    //     pathRewrite: {
    //       "^/api": ""
    //     }
    //   }
    // }
  },

  lintOnSave: false
};
