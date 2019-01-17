'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  // BASE_API: '"https://www.easy-mock.com/mock/5c20aac59341b12d391cb6f8/"'
  BASE_API: '"http://localhost:8081/management/"'
})
