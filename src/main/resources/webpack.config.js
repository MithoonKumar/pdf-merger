var eslintConf = {
    configFile: './.eslintrc.js',
    failOnError: true
};

module.exports = {
    //define entry point
    entry: './static/js/index',
    //define output point
    output: {
        path:  'static/dist',
        filename: 'bundle.js'
    },
    module: {
         loaders: [
                    { test: /\.css$/, loader: 'style-loader!css-loader' }
            ]
    }
}