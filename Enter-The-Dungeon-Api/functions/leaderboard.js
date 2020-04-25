const MongoClient = require('mongodb').MongoClient
let url = "mongodb://localhost:27017/"
let db_name = "enter-the-dungeon"
let db_collection = "leaderboard"

function addEntry(name, id, points) {
    MongoClient.connect(url, function(err, db) {
        if (err) return err
        var dbo = db.db(db_name)
        var myobj = {
            username: name,
            userId: id,
            score: points
        }
        dbo.collection(db_collection).insertOne(myobj, function(err, res) {
            db.close()
            if (err) return false
            console.log("added entry: " )
            return true
        })
    })
}

exports.addEntry = addEntry;