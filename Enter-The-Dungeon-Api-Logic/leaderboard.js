const MongoClient = require('mongodb').MongoClient
let url = "mongodb://localhost:27017/"
let db_name = "enter-the-dungeon"
let db_collection = "leaderboard"

function addEntry(name, score, token) {
    return new Promise(async function(resolve) {
        if(await CheckToken(token, score)){
            MongoClient.connect(url, { useUnifiedTopology: true }, function (err, db) {
            if (err) console.log(err)
            var dbo = db.db(db_name)
            var myobj = {
                username: name,
                score: score
            }
            dbo.collection(db_collection).insertOne(myobj, function (err, res) {
                db.close()
                if (err){
                    resolve({
                        error: {
                            message: "Something went Wrong",
                            code: 4
                        }
                    })
                }
                console.log(`added entry for player ${name} `)
                resolve(true);
            })
        })
        } else{
            console.log(`${name} provided an invalid token`)
            resolve({
                error: {
                    message: "Your Provided Token was Invalid",
                    code: 5
                }
            })
        }
    })
}

function getLeaderBoard() {
    return new Promise(resolve => {
        MongoClient.connect(url, { useUnifiedTopology: true }, function (err, db) {
            if (err) console.log(err)
            var dbo = db.db(db_name)
            dbo.collection(db_collection).find().sort({ score: -1 }).project({ _id: 0 }).toArray(function (err, result) {
                db.close();
                if (result.length > 0) {
                    resolve(result)
                } else {
                    resolve({
                        error: {
                            message: "Something went Wrong",
                            code: 4
                        }
                    })
                }
            })
        })
    })
}

async function CheckToken(token, score){
    //Key vor strich muss 17 sein, mitte muss score sein, schluss muss 21 sein
    return new Promise(resolve => {
        var tokenArr = token.split("-");
        var token1 = tokenArr[0].split("")
        var value2 = 0;
        
        var token3 = tokenArr[tokenArr.length-1].split("")
        var value = 0;
        token1.forEach(token => {
            value += parseInt(token);
        });
        for(var i = 1; i < tokenArr.length-1;i++){
            value2 += parseInt(tokenArr[i]);
        }
        var value3 = 0;
        token3.forEach(token => {
            value3 += parseInt(token);
        });
        if(value == 17 && value2 == score && value3 == 21) resolve(true);
        else resolve(false);
    });
}

exports.addEntry = addEntry;
exports.getLeaderBoard = getLeaderBoard;
