const webpack = require("webpack");
const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const {options} = require("axios");
// const NodePolyfillPlugin = require("node-polyfill-webpack-plugin")

const production = process.env.NODE_ENV === 'production';


module.exports = {
    entry: { myAppName: path.resolve(__dirname, "./index.tsx") },
    // entry: "./js/ts/index.ts",
    devtool: 'inline-source-map',
    output: {
        path: path.resolve(__dirname, "./dist"),
        publicPath: '/',
        filename: production ? '[name].[contenthash].js' : '[name].js',
    },
    module: {
        rules: [
            { test:( /\.(html)$/)|| (/\.[jt]s$/),
                use: ['html-loader'] },
            {
                test:/\.(ts||tsx||js||jsx||json)$/,
                exclude: /node_modules/,
                use: 'babel-loader',
                // presets: ['@babel/preset-env'],

            },
            {
                // test: /\.(js|jsx|ts)$/,
                test: /\.(ts||tsx||js||jsx||json)$/,
                exclude: /node_modules/,
                use: {
                    loader: 'ts-loader',
                    options: {
                        compilerOptions: {
                            transpileOnly: true,
                            noEmit: false, // this option will solve the issue
                        },
                    },
                },
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
        extensions: [".","*", ".js", ".jsx", ".scss",".ts,",".tsx",".scss"],

    },
    plugins: [
        new CleanWebpackPlugin(),
        new webpack.HotModuleReplacementPlugin(),
        new HtmlWebpackPlugin({
            title: "Webpack & React",
            template: "./Html/index.html",
            favicon: "./favicon.ico"
        }),
        new MiniCssExtractPlugin({
            filename: production ? '[name].[contenthash].css' : '[name].css',
        }),

    ],
    devServer: {
              // Enable compression
        compress: true,

        // Enable hot reloading
        hot: true,

        port: 3000,
        historyApiFallback: true,

    },
    mode: production ? 'production' : 'development',

};
