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
                    resolve({
                        error: {
                            message: "Username bereits verwendet",
                            code: 1
                        }
                    })
                    return;
                }
                dbo.collection(db_collection).find({ email: paraEmail }).toArray(function (err, result) {
                    if (result.length > 0) {
                        resolve({
                            error: {
                                message: "Email bereits verwendet",
                                code: 1
                            }
                        })
                        return;
                    }
                    var token = Math.random().toString(36).substr(3) + Math.random().toString(36).substr(3) + Math.random().toString(36).substr(3) + Math.random().toString(36).substr(3) + Math.random().toString(36).substr(3);
                    var myobj = {
                        username: paraUser,
                        email: paraEmail,
                        password: ParaPassword,
                        token: token
                    }
                    dbo.collection(db_collection).insertOne(myobj, function (err, res) {
                        db.close()
                        if (err) resolve(false);
                        console.log(`added user ${paraUser} into the db `)
                        resolve({
                            user: {
                                username: paraUser,
                                email: paraEmail,
                                token: token
                            }
                        })
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
            dbo.collection(db_collection).find({ email: paraEmail }).project({ _id: 0 }).toArray(function (err, result) {
                db.close()
                if (err) console.log(err);
                if (result.length > 0) {
                    if (result[0].password == paraPassword) {
                        delete result[0]["password"];
                        resolve(result[0]);
                        console.log(`Succesfully Logged User ${result[0].username} in`);
                    }
                    else {
                        console.log(`${result[0].username} tried to login`)
                        resolve({
                            error: {
                                message: "Wrong Password/Email",
                                code: 3
                            }
                        })
                    }
                }
                else {
                    resolve({
                        error: {
                            message: "Wrong Password/Email",
                            code: 3
                        }
                    })
                }
            })
        })
    })
}

exports.addUser = addUser;
exports.login = login;