jQuery.support.cors = true;
$.ajaxSetup({ cache: false });
$(function() {
	$(".refillButton").prop("disabled", true);

	setNickName();
	$.getJSON(restAdress, balanceParams, balance);

	$(".refillButton").click(function() {
		$.getJSON(restAdress, refillParams, refill);
	});
	$(".sbutton")
			.click(
					function() {
						$("#sendBet").prop("disabled",false);
						if (balanceAmount < 1) {
							$(".refillButton").prop("disabled", false);
							swal({
								title : "We're sorry, but you're out of money. ",
								text : "If you want to contin	ue playing Black Jack, you can refill your balance by pushing ''Refill'' button. It's  vailable only for registered users!"
							});
							return;
						}
						bj = false;
						refreshView();
						$.getJSON(restAdress, gameStartParams, gameStart);
						$("#betbar").toggle('slow');
						$(".refillButton").toggle('slow');
						setInfoText("Please, make your bet");
						document.getElementById('amount').focus();
					});
	$("#sendBet").click(function() {
		$("#sendBet").prop("disabled", true);
		var bid = document.getElementById('amount').value;
		var maxAmount = parseInt($("#slider").slider("option", "max"));
		if (bid > maxAmount || bid <= 0) {
			swal("Warning", "please make correct bet!");
			return;
		}
		betParams.action = "PLACEBET" + bid;
		document.getElementById('dealerSum').innerHTML = "Dealer is waiting";
		$.getJSON(restAdress, betParams, processBet);
		setBid("Bid:<br />" + bid);

		$("#betbar").toggle('slow');
		$("#panel").toggle('slow');
		setInfoText("What's next? HIT or STAND?");
		document.getElementById('hit').focus();
	});
	$("#hit").click(function() {
		if (!buttonBlock) {
			$.getJSON(restAdress, hitParams, hitResponse);
		}
	});
	$("#stand").click(function() {
		if (!buttonBlock) {
			setInfoText("STAND? Alright.");
			buttonBlock = true;
			$.getJSON(restAdress, standParams, viewStandResponse);
		}
	});
	document.getElementById('startButton').focus();
	setTimeout(function() {
		// swal("Here we are", "LET'S SHUFFLE UP AND DEAL");
	}, 500);
});
function duplicateGameError() {
	buttonBlock = true;
	$('.sbutton').blur();
	swal({
		title : "Oops!",
		text : DUPLICATEGAMETEXT,
		showConfirmButton : false
	});
	cardQuantity = parseInt("0");
}
function balance(data) {
	if (data['error'] != undefined) {
		duplicateGameError();
		return;
	}
	document.getElementById("balance").innerHTML = "Balance: <br/>"
			+ data['balance'];
	balanceAmount = data['balance'];
	if (data['balance'] < 1) {
		$(".refillButton").prop("disabled", false);
	}
}
function refill(data) {
	$(".refillButton").prop("disabled", true);
	swal(REFILLMESSAGE);
	$.getJSON(restAdress, balanceParams, balance);
}
function redirectToLogin() {
	redirect('login', 'post');
}
function redirectToRegister() {
	window.location.replace("registration.jsp");
}
function redirectToProfile() {
	window.location.replace("user");
}
window.onbeforeunload = function() {
	if (gameState != "Finished") {
		$.getJSON(restAdress, endGameParams, function(data) {
		});
	}
};
function fatalError() {

}
function logout() {
	var url = 'login';
	var form = $('<form action="login" method="post">'
			+ '<input type="hidden" class="logButton" name="Logout" value="Logout" />'
			+ '<input type="submit" value="Log out" /></form>');
	$('body').append(form);
	$(form).submit();
}
var redirect = function(url, method) {
	var form = document.createElement('form');
	form.method = method;
	form.appendChild(document.getElementById('loginName'));
	form.appendChild(document.getElementById('loginPassword'));
	form.action = url;
	form.submit();
};
function addCSSRule(sheet, selector, rules, index) {
	if (sheet.insertRule) {
		sheet.insertRule(selector + "{" + rules + "}", index);
	} else {
		sheet.addRule(selector, rules, index);
	}
}
function setNickName() {
	var date = new Date();
	var currentTime = date.getTime() + date.getMilliseconds();
	userNickName = currentTime;
	hitParams.userId = userNickName;
	betParams.userId = userNickName;
	restartParams.userId = userNickName;
	standParams.userId = userNickName;
	gameStartParams.userId = userNickName;
	balanceParams.userId = userNickName;
	refillParams.userId = userNickName;
}
var countWins = 0;
var gameState = "Finished";
var bj = false;
var userNickName = "";
var balanceAmount = parseInt("0");
var pot = parseInt("0");
var cardQuantity = parseInt("0");
var dealerCardQuantity = parseInt("0");
var buttonBlock = false;
var hitBut = document.getElementById('hit');
function gameStart(data) {
	gameState = "Started";
	var maxValue = data['balance'];
	document.getElementById('balance').innerHTML = "Balance: <br />" + maxValue;
	setSlider(maxValue);
}
function processBet(data) {
	if (data['error'] != undefined) {
		swal("error! game is restarted, refresh your page");
		return;
	}
	document.getElementById("balance").innerHTML = "Balance: <br/>"
			+ data['balance'];
	dealerBetPlay(data);
	playerPlay(data);
}
function playerPlay(data) {
	// used only for 2 cards after bet yet;
	var playerCards;
	playerCards = data["playerCards"];
	var partsOfStr = playerCards.split(';');
	partsOfStr.pop();
	for (var i = 0; i < partsOfStr.length; i++) {
		setCardImage("playerCard", partsOfStr[i], cardQuantity);
		cardQuantity++;
	}
	setPlayerScore(data["playerSum"]);
}
function viewStandResponse(data) {
	if (data['error'] != undefined) {
		swal("error! game is restarted, refresh your page");
		return;
	}
	buttonBlock = true;
	dealerPlay(data);
	endGame(data);
}
function hitResponse(data) {
	setCardImage("playerCard", data["playerCard"], cardQuantity);
	cardQuantity++;
	setPlayerScore(data["playerSum"]);
	if (data['playerSum'] == 21) {
		if (!buttonBlock) {
			bj = true;
			buttonBlock = true;
			setInfoText("21 points, auto-stand");
			$.getJSON(restAdress, standParams, viewStandResponse);
		}
		return;
	}
	if (data["gameStatus"] != "OK") {
		buttonBlock = true;
		endGame(data);
	}
}
function dealerBetPlay(data) {
	setCardImage("dealerCard", "background", dealerCardQuantity++);
	setCardImage("dealerCard", data["dealerCards"].toString(),
			dealerCardQuantity++);
	setDealerScore(data["dealerSum"]);
}
function dealerPlay(data) {
	var dC;
	dC = data["dealerCards"];
	var partsOfStr = dC.split(';');
	partsOfStr.pop();
	setCardImage("dealerCard", partsOfStr[0], 0);
	for (var i = 2; i < partsOfStr.length; i++) {
		setCardImage("dealerCard", partsOfStr[i], dealerCardQuantity);
		dealerCardQuantity++;
	}
	setDealerScore(data["dealerSum"]);
}
function endGame(data) {
	setTimeout(function() {
		processGameOver(data);
	}, 2500);
}
function processGameOver(data) {
	showGameResult(data);
	$("#sendBet").prop("disabled", false);
	var maxValue = data['balance'];
	balanceAmount = data['balance'];
	if (balanceAmount < 1) {
		$(".refillButton").prop("disabled", false);
	}
	document.getElementById('balance').innerHTML = "Balance: <br />" + maxValue;
	$(".sbutton").toggle('slow');
	$("#panel").toggle('slow');
	$(".refillButton").toggle('slow');
	setInfoText("Game over. Try again!");
	$(".sbutton").html("Play again");
	buttonBlock = false;
	bj = false;
	gameState = "Finished";
}