const path = require("path");

const resolve = dir => {
    return path.join(__dirname, dir)
}
const Timestamp = new Date().getTime();
module.exports = {
    configureWebpack: {
        name: 'LuckDraw',
        output: {
            filename: `[name].${Timestamp}.js`,
            chunkFilename: `[name].${Timestamp}.js`
        },
        resolve: {
            alias: {
                '@': resolve('src')
            }
        }
    },
    pluginOptions: {
        "style-resources-loader": {
            preProcessor: "less",
            patterns: [
                path.resolve(__dirname, "./src/assets/styles/common.less")
            ]
        }
    },
    devServer: {
        port: 8088,
        host: "localhost",
        proxy: { //跨域配置
            '/luckdraw': {
                target: "http://localhost:8080/luckdraw",
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    '^/luckdraw': '/'
                }
            }
        },
    },
    chainWebpack(config) {
        config.module
            .rule('svg')
            .exclude.add(resolve('src/assets/icons'))
            .end()
        config.module
            .rule('icons')
            .test(/\.svg$/)
            .include.add(resolve('src/assets/icons'))
            .end()
            .use('svg-sprite-loader')
            .loader('svg-sprite-loader')
            .options({
                symbolId: 'icon-[name]'
            })
            .end()
    }
}