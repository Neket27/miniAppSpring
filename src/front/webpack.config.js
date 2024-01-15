const webpack = require("webpack");
const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

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
            { test: /\.(html)$/, use: ['html-loader'] },
            {
                // test: /\.(js|jsx|ts)$/,
                test: /\.(ts||tsx||js||jsx)$/,
                exclude: /node_modules/,
                use: ["ts-loader"],
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
        extensions: ["*", ".js", ".jsx", ".scss",".ts,",".tsx"],
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
    mode: production ? 'production' : 'development'
};
