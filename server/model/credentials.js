var mongoose = require('mongoose');

var credentialschema = mongoose.Schema({
   
    Name: {
        type: String,
        required: false
    },
    Email: {
        type: String,
        required: false
    },
    Password: {
        type: String,
        required: false
    }
}, {versionKey:false});

var credentials = module.exports = mongoose.model('credentials', credentialschema);

//Get Data
module.exports.getcredentials = function(callback, limit){
    credentials.find(callback).limit(limit);
}

//Add Data
module.exports.addcredentials = function(Name, callback){
    credentials.create(Name, callback);
}

//Update Data
module.exports.updatecredentials = function(_id, atri, options, callback){
    var query = {_id: _id};
    var update = {
        Name: atri.Name,
        Email: atri.Email,
        Password: atri.Password
    }
    credentials.findOneAndUpdate(query, update, options, callback);
}

//Delete Data
module.exports.removecredentials = function(_id, callback){
    var query = {_id: _id};
    credentials.remove(query, callback);
}