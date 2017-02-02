/**
 * Created by VenkatNag on 1/27/2017.
 */

function register() {
    window.location ="../Html/Register.html";
}
function store() {
var a =document.getElementById("name").value;
var b =document.getElementById("email").value;
var c =document.getElementById("username").value;
var d =document.getElementById("password").value;
var e =document.getElementById("confirm").value;
if(a===""|b===""|c===""|d===""|e==="")
{
    document.getElementById('fuck').innerHTML ="Please enter all details";
}
else {
    if(document.getElementById("confirm").value===document.getElementById("password").value & document.getElementById("confirm").value!=="")
    {

        localStorage.setItem("user",document.getElementById("username").value);
        localStorage.setItem("pass",document.getElementById("password").value);
        if (confirm("Thanks for Registering") == true) {
            window.location ="../Html/index.html";
        } else {
            window.location ="app.js";
        }
    }
    else
    {
        document.getElementById("fuck").innerHTML ="password mismatch";
    }
}
}

function log()
{
    window.location ="../Html/index.html";
}
function validate() {

    if(document.getElementById("usr").value ==="" |document.getElementById("pwd").value ==="" )
    {
        alert("Please enter all your details!");
    }
    else {
        if (localStorage.getItem("user") === document.getElementById("usr").value & document.getElementById("usr").value !== " ") {

            if (localStorage.getItem("pass") === document.getElementById("pwd").value) {

                window.location = "../Html/Home.html";
            }
            else {
                alert("Please enter correct details");
            }
        }
        else {
            alert("Please enter correct details!");
        }
    }
}


function sign() {
    window.location ="../Html/index.html";
    
}