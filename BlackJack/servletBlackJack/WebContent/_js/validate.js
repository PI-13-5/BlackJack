function valid_password(){	
	var passwords = _.filter(document.getElementsByTagName("input"),
			function(x){
				return x.getAttribute("type") == "password";
			});
	var confirmed = passwords[0].value == passwords[1].value;
	if(!confirmed)
		alert("Passwords must be equal!");
	return confirmed;
}
function processError(){
	
}
function redirect(){
	_.forEach(document.getElementsByTagName("input"), function(x){
		x.disabled = true;
	});
	if(valid_password()){
		window.setTimeout(function(){
			window.location.href = "https://www.google.co.in";
		}, 5000);
	}
}
