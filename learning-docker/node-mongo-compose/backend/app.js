const express = require('express')
const restful = require('node-restful')
const server = express()
const mongoose = restful.mongoose

//database 
mongoose.Promise = global.Promise
mongoose.connect('mongodb://db/mydb')

//teste 
server.get('/', (req, res, next) => res.send('Backend'))

// Start Server
server.listen(3000)