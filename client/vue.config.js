module.exports = {
  devServer: {
    proxy: {
      '': {
        target: 'http://localhost:8080',
        ws: true
        // pathRewrite: { '^/api': '' }
      }
    },
    port: 8081
  }
}
// module.exports = {
//   devServer: {
//     proxy: {
//       '': {
//         target: 'http://localhost:8080/',
//         ws: true
//       }
//     }
//   }
// }
