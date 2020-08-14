const MongoClient = require('mongodb').MongoClient;
let url = "mongodb://localhost:27017/";
let db_name = "enter-the-dungeon";
let db_collection = "keys";

function validatekey(paraKey) {
    return new Promise(resolve => {
        MongoClient.connect(url, { useUnifiedTopology: true }, function (err, db) {
        if (err) console.log(err)
        var dbo = db.db(db_name)
        dbo.collection(db_collection).find({ key: paraKey }).toArray(function(err, result) {
            if (err) console.log(err);
            if (result.length > 0) {
                if(result[0].valid == true){
                    resolve(true)
                    /*var newvalues = { $set: { valid: false } }
                    dbo.collection(db_collection).updateOne(result[0], newvalues, function(err,res) {
                        console.log(`Removed Key ${result[0].key}`)
                    })*/
                    db.close()
                }else{
                    console.log(`Key ${paraKey} Already used`)
                    resolve(false)                }
            } else {
                console.log(`No Such Key avaible`);
                    resolve(false)
            }
        })
    })
    })
};

exports.validatekey = validatekey;