package com.example.exo1

class Mois(var nb:Int, var nom:String,  var tmMin:Double,var tmMax:Double,var pmTot:Double ) {

    var tmMoy:Double = (tmMax+tmMin)/2;

}