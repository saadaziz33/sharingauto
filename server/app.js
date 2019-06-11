var express = require('express');
var app = express();
//var passport = require('passport');
//var googleStrategy = require('passport-google-oauth20').Strategy;

var bodyParser = require('body-parser');
var mongoose = require('mongoose') ;

app.use(bodyParser.json());

credentials = require('./model/credentials');

//passport.use(new googleStrategy());

//Connect to Database
mongoose.connect('mongodb://localhost/sharingauto', { useNewUrlParser: true } );
var db = mongoose.connection;

//Route to Homepage
app.get('/', function(req, res){
    res.send('Hello');
});

//Route to get data from Credentials
app.get('/api/credentials', function(req, res){
    credentials.getcredentials(function(err, credentials){
        if(err){
            throw err;
        }
        res.json(credentials);
    });
});

//Route to signin
app.get('/api/signin', function(req, res){
    var Name = req.body;
    credentials.signin(Name, function(err, ame){
        if(err){
            throw err;
        }
        res.json(ame);
    });
});

//Route to signup
app.post('/api/signup', function(req, res){
    var Name = req.body;
    credentials.addcredentials(Name, function(err, ame){
        if(err){
            throw err;
        }
        res.json(ame);
    });
});

//Route to update data in Credentials
app.put('/api/credentials/:_id', function(req, res){
    var id = req.params._id;
    var Name = req.body;
    credentials.updatecredentials(id, Name, {}, function(err, ame){
        if(err){
            throw err;
        }
        res.json(ame);
    });
});

//Route to delete data in Credentials
app.delete('/api/credentials/:_id', function(req, res){
    var id = req.params._id;
    credentials.removecredentials(id, function(err, ame){
        if(err){
            throw err;
        }
        res.json(ame);
    });
});

app.listen(9000);
console.log('Starting Server at 9000 ... ');
