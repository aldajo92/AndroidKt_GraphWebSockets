import express from 'express'
import { Server } from 'socket.io'
import { createServer } from 'http'

// Express
const app = express()
const port = 3000

// Socket.io
const server = createServer(app);
const io = new Server(server);

// Express endpoints
app.get('/', (req, res) => {
  res.send('Hello World!')
})

server.listen(3000, () => {
  console.log(`socket.io app listening at http://localhost:${port}`)
});

// Socket.io events
io.on('connection', (socket) => {
  console.log('a user connected');
  socket.on('disconnect', () => {
    console.log('user disconnected');
  })
})

setInterval(() => {
  io.emit('sensor_message', `${1000*Math.random()}`)
}, 1000)
