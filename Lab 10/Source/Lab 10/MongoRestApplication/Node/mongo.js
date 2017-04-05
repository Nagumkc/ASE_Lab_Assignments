/**
 * Created by user on 23/10/2016.
 */
var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var bodyParser = require("body-parser");
var express = require('express');
var cors = require('cors');
var app = express();
var url = 'mongodb://root:dbc@ds139360.mlab.com:39360/asedemo';
var z;
app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.post('/register', function (req, res) {
    MongoClient.connect(url, function(err, db) {
        if(err)
        {
            res.write("Failed, Error while connecting to Database");
            res.end();
        }
        insertDocument(db, req.body, function() {
            res.write("Successfully inserted");
            res.end();
        });
    });
})
var insertDocument = function(db, data, callback) {
    db.collection('users').insertOne( data, function(err, result) {
        if(err)
        {
            res.write("Registration Failed, Error While Registering");
            res.end();
        }
        console.log("Inserted a document into the restaurants collection.");
        console.log(result);
        callback();
    });
};
app.get('/sign', function (req, res,next) {
    MongoClient.connect(url, function (err, db) {
        if (err) {
            res.write("Failed, Error while connecting to Database");
            res.end();
        }
        z = req.query.name;
        db.collection('users', function (err, collection) {
            collection.findOne({'email': z}, function (err, item) {
                if (err) {
                    res.write("failed to validate");
                    res.end();
                }
                if (item != null) {
                    res.send(item);
                    res.end();
                }

            });
        });

    });
});

app.get('/profile',function (req,res,next) {
    MongoClient.connect(url,function (err,db) {
        if(err)
        {
            res.write("Failed, Error while connecting to Database");
            res.end();
        }
        db.collection('users',function (err,collection) {
            collection.findOne({'email':z},function (err,item) {
                if(err)
                {
                    res.write("failed to fetch");
                    res.end();
                }
                if(item!=null)
                {
                    res.send(item);
                    res.end();
                }

            })
        })
    })
});
app.put('/update', function (req, res) {
    MongoClient.connect(url, function (err, db) {
        if (err) {
            res.write("Failed, Error while connecting to Database");
            res.end();
        }
        db.collection('users').update({'email':z},{$set:{'email':req.body.email,'fname':req.body.fname,'lname':req.body.lname}}, function (err, result) {
            if(err)
            {
                res.write("failed to update");
                res.end();
            }
            if(result!=null)
            {
                z=req.body.email;
                res.send("successfully updated");
                res.end();
            }
            console.log(result);
        })
    })
});
var server = app.listen(8081, function () {
    var host = server.address().address
    var port = server.address().port

    console.log("Example app listening at http://%s:%s", host, port)
})