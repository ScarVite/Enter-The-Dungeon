const express = require('express');
const fs = require('fs');
const keys = require('./keys.json')
const app = express();
const app2 = express();

const leaderboard = require('./functions/leaderboard.js')

app.listen(6);

app.get('/api/validatekey', (req, res) => {
    let response = validatekey(req.query.key);
    res.send(response);
    if(req.connection.remoteAddress.substring(7) === "82.165.163.17") return;
    if(response === false) console.log(req.connection.remoteAddress.substring(7) + ' tried to use the key ' + req.query.key);
    if(response === true) console.log(req.connection.remoteAddress.substring(7) + ' used key ' + req.query.key);
});

app.get('/api/updateleaderboard', (req, res) => {
    if(req.query.username !== undefined && req.query.score !== undefined && req.query.userId !== undefined){
        leaderboard.addEntry();
        res.send(true)
    }
    res.send("Wrong Parameters received");
});

app.get/'/api/getleaderboard', (req, res) => {
    
}

app.get('/api/status', (req, res) => {
    res.status(200);
    res.send();
});

function validatekey(key) {
    for (var i = 0; i < keys.length-1; i++) {
        if (key === keys[i].key && keys[i].valid === true) {
            return true;
        }
    }
    return false;
};

app.post('/', (req,res) => {

});

