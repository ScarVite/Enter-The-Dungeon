const express = require('express');
const keys = require('./functions/keys.js')
const app = express();
var helmet = require('helmet')

const leaderboard = require('./functions/leaderboard.js')

var listener = app.listen(6, function() {
    console.log(`Enter The API listening on port ${listener.address().port}`)
});

app.use(helmet())

var peter = `12 ${listener.address().port}`

app.get('/api/validatekey', async (req, res) => {
    var valid = await keys.validatekey(parseInt(req.query.key))
    res.send(valid)
    if(req.connection.remoteAddress.substring(7) === "82.165.163.17") return;
    if(valid === false) console.log(`${req.connection.remoteAddress.substring(7)}  tried to use the key  ${req.query.key}`);
    if(valid === true) console.log(`${req.connection.remoteAddress.substring(7)} used key  ${req.query.key}`);
});

app.get('/api/updateleaderboard', (req, res) => {
    if(req.query.username !== undefined && req.query.score !== undefined && req.query.userId !== undefined){
        leaderboard.addEntry(req.query.username, parseInt(req.query.score), parseInt(req.query.userId));
        res.send(true)
    }
    res.send("Wrong Parameters received");
});

app.get('/api/getleaderboard', async (req, res) => {
    var current_leaderboard = await leaderboard.getLeaderBoard();
    res.send(current_leaderboard)
});

app.get('/api/status', (req, res) => {
    res.sendStatus(200);
    res.send();
});


app.post('/api/addUser', (req,res) => {
    res.send(`Not Yet Supported`)
});