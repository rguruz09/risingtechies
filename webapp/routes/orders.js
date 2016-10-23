var ejs = require("ejs");
var mysql = require('./mysql');

exports.getOrdersBySeller=getOrdersBySeller;
exports.getAmountBySeller=getAmountBySeller;
exports.cart_page=cart_page;
exports.getOrderbySeller = getOrderbySeller;
exports.getOrderbyCustomer = getOrderbyCustomer;
exports.deleteOrderbyCustomer = deleteOrderbyCustomer;


function deleteOrderbyCustomer(req,res){
	var custname = req.param("custname");
	var query = "delete from order_details where customer_name = '"+ custname +"';";
	
	console.log(query);
	mysql.fetchData(function(err, results) {
		if(err){ 
			res.json({
					"stscode" : 210 
				});
		}
		else{
				console.log("query done");
				//callback(err,results);
				res.json({
					"stscode" : 200,
					"data" :  results
				});
		}
	},query);
}

function getOrderbySeller(req,res){
	//var custname = req.param("custname");
	var query = "select A.customer_name, sum(tot_cost) from order_details A, items B where A.item_id = B.item_id and seller_id = 1 group by A.customer_name ";
	console.log(query);
	mysql.fetchData(function(err, results) {
		if(err){ 
			res.json({
					"stscode" : 210 
				});
		}
		else{
				console.log("query done");
				//callback(err,results);
				res.json({
					"stscode" : 200,
					"data" :  results
				});
		}
	},query);
}	


function getOrderbyCustomer(req,res){
	var custname = req.param("custname");
	var query = "select item_name, description, tot_cost, B.count, img_url from items A, order_details B where A.item_id = B.item_id and customer_name = '"+ custname +"';";
	console.log(query);
	mysql.fetchData(function(err, results) {
		if(err){ 
			res.json({
					"stscode" : 210 
				});
		}
		else{
				console.log("query done");
				//callback(err,results);
				res.json({
					"stscode" : 200,
					"data" :  results
				});
		}
	},query);
}

function cart_page(req,res){
	res.render('cart-page.ejs');
}

function getOrdersBySeller(req,res) {

	var sid = req.param("sid");
	//var query ="select * from salesinfo where sid = "+ sid + ";" ; 
	var query ="select * from salesinfo where sid;" ; 
	console.log(query);
	mysql.fetchData(function(err, results) {
		if(err){ 
			res.json({
					"stscode" : 210 
				});
		}
		else{
				console.log("query done");
				//callback(err,results);
				res.json({
					"stscode" : 200,
					"data" :  results
				});
		}
	},query);
}


function getAmountBySeller(req,res) {

	var sid = req.param("sid");
	var query ="SELECT cid, SUM(A.amount) FROM salesinfo A , products B WHERE A.sid = " + sid + 
	"  AND A.pid = B.pid GROUP By cid;" ; 
	console.log(query);
	mysql.fetchData(function(err, results) {
		if(err){ 
			res.json({
					"stscode" : 210 
				});
		}
		else{
				console.log("query done");
				//callback(err,results);
				res.json({
					"stscode" : 200,
					"data" :  results
				});
		}
	},query);
}




