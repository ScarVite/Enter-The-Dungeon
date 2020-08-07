const express = require('express');
var helmet = require('helmet')
const bodyParser = require('body-parser');

const app = express();

const leaderboard = require('./functions/leaderboard.js')
const keys = require('./functions/keys.js')
const users = require('./functions/users.js')

var listener = app.listen(6, function() {
    console.log(`Enter The API listening on port ${listener.address().port}`)
});

app.use(helmet())
app.use(bodyParser.urlencoded({ extended: true }));

app.get('/api/validatekey', async (req, res) => {
    var valid = await keys.validatekey(parseInt(req.query.key))
    res.send(valid)
//    if(req.connection.remoteAddress.substring(7) === "82.165.163.17") return;
//    if(valid === false) console.log(`${req.connection.remoteAddress.substring(7)}  tried to use the key  ${req.query.key}`);
//    if(valid === true) console.log(`${req.connection.remoteAddress.substring(7)} used key  ${req.query.key}`);
});

app.post('/api/updateleaderboard', async (req, res) => {
    if(req.body.username !== undefined && req.body.score !== undefined && req.body.token !== undefined ){
        var done = await leaderboard.addEntry(req.body.username, parseInt(req.body.score), req.body.token);
        res.send(done)
    }
    res.send("Invalid Arguments Supplied");
});

app.get('/api/getleaderboard', async (req, res) => {
    var current_leaderboard = await leaderboard.getLeaderBoard();
    res.send(current_leaderboard)
});

app.get('/api/status', (req, res) => {
    res.sendStatus(200);
});


app.post('/api/addUser', async (req,res) => {
    if(req.body.username !== undefined && req.body.password !== undefined && req.body.email !== undefined)
    var done = await users.addUser(req.body.email, req.body.username, req.body.password)
    res.send(done)
});

app.post('/api/login', async (req,res) => {
    var userObject = await users.login(req.body.email, req.body.password)
    res.send(userObject)
});
