const webpack = require("webpack");
const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

const production = process.env.NODE_ENV === 'production';


module.exports = {
    entry: { myAppName: path.resolve(__dirname, "./index.js") },
    output: {
        path: path.resolve(__dirname, "./dist"),
        filename: production ? '[name].[contenthash].js' : '[name].js',
    },
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                use: ["babel-loader"],
            },
            {
                test: /\.s(a|c)ss$/,
                exclude: /node_modules/,
                use: [
                    production ? MiniCssExtractPlugin.loader : 'style-loader',
                    {
                        loader: 'css-loader',
                        options: {
                            modules: true,
                            sourceMap: !production
                        }
                    },
                    {
                        loader: 'sass-loader',
                        options: {
                            sourceMap: !production
                        }
                    }
                ]
            },

        ],
    },
    resolve: {
        extensions: ["*", ".js", ".jsx", ".scss"],
    },
    plugins: [
        new CleanWebpackPlugin(),
        new webpack.HotModuleReplacementPlugin(),
        new HtmlWebpackPlugin({
            title: "Webpack & React",
            template: "./index.html",
            favicon: "./favicon.ico"
        }),
        new MiniCssExtractPlugin({
            filename: production ? '[name].[contenthash].css' : '[name].css',
        }),
    ],
    devServer: {
        port: 3001,
        hot: true,
    },
    mode: production ? 'production' : 'development'
};









// const path = require('path');
//
// module.exports = {
//     entry: './index.js',
//     output: {
//         filename: 'main.js',
//         path: path.resolve(__dirname, 'dist')
//     },
//     devServer: {
//         static: path.resolve(__dirname, 'dist'),
//         compress: true,
//         port: 8080,
//     },
//     // mode: 'development',
//     resolve: {extensions: ['.js', '.jsx']},
//     // plugins: [
//     //     new webpack.LoaderOptionsPlugin({
//     //         debug: true}),
//     //     new webpack.DefinePlugin({
//     //         "process.env": {
//     //             NODE_ENV: JSON.stringify("development")
//     //         }
//     //     })
//     // ],
//     module: {
//         rules: [
//             {
//                 test: /\.jsx?$/,
//                 loader: 'babel-loader',
//                 exclude: /node_modules/
//             }
//         ]
//     }
//
// };
//
// //
// // // var packageJSON = require('./package.json');
// // var path = require('path');
// // var webpack = require('webpack');
// // module.exports = {
// //     devtool: 'source-map',
// //     entry: './index.js',
// //     output: {
// //         path: path.join(__dirname, 'generated'),
// //         filename: 'app-bundle.js'},
// //     resolve: {extensions: ['.js', '.jsx']},
// //     plugins: [
// //         new webpack.LoaderOptionsPlugin({
// //             debug: true}),
// //         new webpack.DefinePlugin({
// //             "process.env": {
// //                 NODE_ENV: JSON.stringify("development")
// //             }
// //         })
// //     ],
// //     module: {
// //         rules: [
// //             {
// //                 test: /\.jsx?$/,
// //                 loader: 'babel-loader',
// //                 exclude: /node_modules/
// //             }
// //         ]
// //     },
// //     devServer: {
// //         noInfo: false,
// //         quiet: false,
// //         lazy: false,
// //         watchOptions: {
// //             poll: true
// //         }
// //     }
// // }