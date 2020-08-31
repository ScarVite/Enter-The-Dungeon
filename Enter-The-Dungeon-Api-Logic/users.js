const MongoClient = require('mongodb').MongoClient
let url = "mongodb://localhost:27017/"
let db_name = "enter-the-dungeon"
let db_collection = "users"

function addUser(paraEmail, paraUser, ParaPassword) {
    return new Promise(resolve => {
        MongoClient.connect(url, { useUnifiedTopology: true }, function (err, db) {
            if (err) console.log(err)
            var dbo = db.db(db_name)
            dbo.collection(db_collection).find({ username: paraUser }).toArray(function (err, result) {
                if (result.length > 0) {
                    var answer = [false, "Username bereits verwendet"]
                    resolve(answer)
                    return;
                }
                dbo.collection(db_collection).find({ email: paraEmail }).toArray(function (err, result) {
                    if (result.length > 0) {
                        var answer = [false, "Email bereits vorhanden"]
                        resolve(answer)
                        return;
                    }
                    var myobj = {
                        username: paraUser,
                        email: paraEmail,
                        password: ParaPassword
                    }
                    dbo.collection(db_collection).insertOne(myobj, function (err, res) {
                        db.close()
                        if (err) resolve(false);
                        console.log(`added user ${paraUser} into the db `)
                        var answer = [true, "Inserted User"]
                        resolve(answer)
                        return;
                    })
                })
            })
        })
    })
}

function login(paraEmail, paraPassword) {
    return new Promise(resolve => {
        MongoClient.connect(url, { useUnifiedTopology: true }, function (err, db) {
            if (err) console.log(err)
            var dbo = db.db(db_name)
            console.log(paraEmail, paraPassword)
            dbo.collection(db_collection).find({ email: paraEmail }).toArray(function (err, result) {
                db.close()
                if (err) console.log(err);
                if (result.length > 0) {
                    if (result[0].password == paraPassword) {
                        var answer = [true, result[0]]
                        resolve(answer)
                        console.log(`Succesfully Logged User ${result[0].username} in`)
                    }
                    else {
                        console.log(`${result[0].username} tried to login`)
                        var answer = [false, "Wrong Password"]
                        resolve(answer)
                    }
                }
                else {
                    var answer = [false, "Wrong Email"]
                    resolve(answer)
                }
            })
        })
    })
}

exports.addUser = addUser;
exports.login = login;