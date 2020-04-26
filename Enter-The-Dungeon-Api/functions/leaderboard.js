const MongoClient = require('mongodb').MongoClient
let url = "mongodb://localhost:27017/"
let db_name = "enter-the-dungeon"
let db_collection = "leaderboard"

function addEntry(name, points, id) {
    MongoClient.connect(url, function (err, db) {
        if (err) console.log(err)
        var dbo = db.db(db_name)
        var myobj = {
            username: name,
            userId: id,
            score: points
        }
        dbo.collection(db_collection).insertOne(myobj, function (err, res) {
            db.close()
            if (err) return false
            console.log(`added entry for player ${name} `)
            return true;
        })
    })
}

function getLeaderBoard() {
    return new Promise(resolve => {
        MongoClient.connect(url, function (err, db) {
            if (err) console.log(err)
            var dbo = db.db(db_name)
            dbo.collection(db_collection).find({}).toArray(function (err, result) {
                db.close();
                if (result.length > 0) {
                    var sorted_result = result;
                    console.log(result)
                    for (var i = 0; i < result.length; i++) {
                        for (var a = 0; a <= i; a++) {
                            console.log( a + "   :   " + i)
                            if (result[i].score > sorted_result[a].score) {
                                var speicher = sorted_result[a]
                                if(i == 5) console.log(speicher)
                                sorted_result[a] = result[i];
                                sorted_result[i] = speicher;
                            }
                        }
                    }
                    resolve(sorted_result)
                } else {
                    result = undefined;
                    resolve(result);
                }
            })
        })
    })
}

exports.addEntry = addEntry;
exports.getLeaderBoard = getLeaderBoard;