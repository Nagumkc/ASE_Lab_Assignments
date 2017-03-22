/**
 * Created by VenkatNag on 3/18/2017.
 */

var express = require('express');
var app = express();
var request = require('request');
app.get('/calories', function (req, res) {
    var result = {
        'calo': []
    };

    request('https://api.edamam.com/api/nutrition-data?app_id=254d36ea&app_key=383e71430a323ae94ec781cab4b8918a&ingr=1%20large%20apple', function (error, response, body) {
        //Check for error
        if (error) {
            return console.log('Error:', error);
        }
        if (response.statusCode !== 200) {
            return console.log('Invalid Status Code Returned:', response.statusCode);
        }
        body = JSON.parse(body);
        result.calo.push(body.calories);
        res.contentType('application/json');
        res.write(JSON.stringify(result));
        res.end();
    });
    console.log(result);
}
var server = app.listen(8081, function () {
    var host = server.address().address
    var port = server.address().port

    console.log("Example app listening at http://%s:%s", host, port)
})




