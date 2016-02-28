var DIRECTORY = "cards_png";
var DEALER = true;
var PLAYER = false;
var CLOSE_CARD;

function getJSON(dealer){
	if(dealer)
		return '{ "dealerSum" : 16, "gameResult": "OK", "dealerCards": "Qs"}';
	return '{ "playerSum" : 16, "playerCard":"Kd", "gameStatus": "OK"}';
}

function sendToServer(){

}

function hitButton(){

}

function attribute(pair){
	return pair[0];
}

function value(pair){
	return pair[1];
}

function combine(lst1, lst2, f){
	if(!_.isEmpty(lst1)){
		f(car(lst1), car(lst2));
		combine(cdr(lst1), cdr(lst2), f);
	}
}

function formPath(card, dir){
	return dir + "/" + card + ".png"; 
}

function adaptCard(maxCard){
	return (maxCard[1] + maxCard[0]).toLowerCase();
}

function addText(id, value){
	var elem = document.getElementById(id);
	if(existy(elem)){
		elem.innerHTML = value;
	}
	else
		warn("No html element with "+id+" id!");
}

function nextImage(elements, img_src){
	var image =  _.find(elements, function(img){
			return img.src == img_src;});	
	return image;
}

function addImage(clas, value){ 
	var elem = nextImage(document.getElementsByClassName(clas), CLOSE_CARD);
	if(elem.hidden)
		elem.hidden=false;

	var path = formPath(adaptCard(value), DIRECTORY);
	if(existy(elem)){
		elem.setAttribute("src", path);
	}
	else
		warn("No element with "+clas+" class!");
}

function displayJSON(source, actions){
	var jsonObj = JSON.parse(getJSON(source));
	combine(_.pairs(jsonObj), actions, function(obj, action){
		return action(attribute(obj), value(obj));
	});
}

function btnVisible(btn, visible){
	if(visible){
		btn.style.display = 'inline';
	}
	else{
		btn.style.display = 'none';
	}
}

function Game(beg){
	var buttons = [document.getElementById("hit"), document.getElementById("stand")];
	var restart = document.getElementById("restart");
	_.forEach(buttons, function(btn){ btnVisible(btn, beg); });
	btnVisible(restart, !beg);
}

function procGame(clas, value){
	Game((value=="OK"));
	addText(clas, value);
}

function init(){
	CLOSE_CARD = document.getElementsByClassName("playerCard")[0].src;
	Game(false);
}

function main(){
	init();
	displayJSON(PLAYER, [addText, addImage, procGame]);
	displayJSON(DEALER, [addText, procGame, addImage]);
	displayJSON(DEALER, [addText, procGame, addImage]);
}
