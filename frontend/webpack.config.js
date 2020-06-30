const path = require('path');
const Webpack = require('webpack');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');
const OptimizeCSSAssetsPlugin = require("optimize-css-assets-webpack-plugin");

module.exports = {
    mode: 'production',
    stats: 'errors-only',
    bail: true,
    entry: {
        main: path.resolve(__dirname, './src/index.js')
    },
    output: {
        filename: '[name].js'
    },
    optimization: {
        minimize: true,
        minimizer: [
            new UglifyJsPlugin(),
            new OptimizeCSSAssetsPlugin({
                cssProcessorPluginOptions: {
                  preset: ['default', { discardComments: { removeAll: true } }],
                }
            })
        ],
    },
    plugins: [
        new CleanWebpackPlugin(),
        new MiniCssExtractPlugin({filename: 'bundle.css'})
    ],
    resolve: {
        alias: {
        '~': path.resolve(__dirname, './src')
        }
    },
    module: {
        rules: [
            {
                test: /\.(ttf|eot|woff|woff2|svg)(\?.*)?$/,
                use: {
                    loader: 'file-loader',
                        options: {
                            name: 'fonts/[name].[ext]'
                    }
                }
            },
            {
                test: /\.s?css/i,
                use : [
                    MiniCssExtractPlugin.loader,
                    'css-loader',
                    'sass-loader'
                ]
            }
        ]
    }
};