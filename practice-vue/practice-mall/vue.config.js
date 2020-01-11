module.exports = {
  // 静态路径, 公共路径
  publicPath: "",

  devServer: {
    host: "localhost",
    port: 8080,
    // 代理，将某个内部路径代理到另外一个外部路径
    proxy: {
      "/mock": {
        target: "http://localhost:8080",
        //websocket功能
        ws: false,
        // /mock/cart.json   false-> /mock/cart.json   true->/cart.json
        // 将主机头的原点改为目标地址
        changeOrigin: false
      }
    }
  }
};
