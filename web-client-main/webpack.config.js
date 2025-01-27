module.exports = {
  module: {
    entry: './src/main.js',
    rules: [
      {
        test: /\.css$/,
        use: ['vue-style-loader', 'css-loader', 'postcss-loader'],
      },
    ],
  },
};
