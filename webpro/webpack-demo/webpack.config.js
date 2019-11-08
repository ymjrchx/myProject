var webpack = require('webpack')
module.exports = {
    entry: {
        bundle1:'./a.js',
        bundle2:'./b.js'
    },
    output: {
        filename: '[name].js'
    },
   plugins:[
        new webpack.HotModuleReplacementPlugin()
    ],
   devServer: {
        port:8088,
        inline:true,
        hot:true
    }
}
