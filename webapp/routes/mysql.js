var ejs = require('ejs');
var mysql = require('mysql');

function getConnection(){
	var connection = mysql.createConnection({
		host : 'aasha123.c6ru0tjv6z1q.us-west-1.rds.amazonaws.com',
		port : 3306 , 
		user : 'aasha123',
		password : 'aasha123',
		database : 'aasha' 
		
		 // host : 'localhost',
		 // user : 'raghu',
		 // password : 'raghu',
		 // database : 'rising' 
	});
	return connection ;
}


function fetchData(callback,sqlQuery){
	
	console.log("\nSQL Query::"+sqlQuery);
	
	var connection=getConnection();
	connection.query(sqlQuery,function(err, rows, fields) {
		if(err){
			console.log("ERROR: " + err.message);
		}
		else 
		{	// return err or result
			console.log("DB Results:"+rows);
			callback(err, rows);
		}
	});
	console.log("\nConnection closed..");
	connection.end();
}	


var executequery = function(callback, query, param){
	//var con = getSQLConnection();
	var con = getConnection();
	con.query(query,param , function(err, rows, fields) {
			if (err) {
				console.log("ERROR in fetching the data");
			} else {
				console.log("no rows");
				callback(err,rows);
			}
		});
	con.end();
	
};


exports.fetchData=fetchData;
exports.executequery=executequery;