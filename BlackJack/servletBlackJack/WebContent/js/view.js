function refreshView(){
    let i;
    for (i = 0; i < cardQuantity; i++) {
        jQuery(`#playerCard${i.toString()}`).hide('slow');
    }

    for (i = 0; i < dealerCardQuantity; i++) {
        jQuery(`#dealerCard${i.toString()}`).hide('slow');
    }

    cardQuantity = parseInt('0');
    dealerCardQuantity = parseInt('0');
    setPlayerScore(-1);
    setDealerScore(-1);
    pot = parseInt('0');
    jQuery('.sbutton').toggle('slow');
    setBid('Bid: <br />&nbsp');
    setInfoText('');
    bj = false;
}

function setBid(income) {
    document.getElementById('bid').innerHTML = income;
}

function setSlider(maxValue) {
    jQuery('#slider').slider({
        range: 'min',
        value: 1,
        min: 1,
        max: maxValue,
        slide: function(event, ui) {
            jQuery('#amount').val(ui.value);
        },
    });

    jQuery('#amount').val( jQuery('#slider').slider('value'));

    jQuery('#amount').keydown(function(event) {
        let regex = new RegExp(/[0-9]+/);
        let key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
        if (!regex.test(key)) {
            if (key.charCodeAt(0) == 8 || key.charCodeAt(0) == 9 ||
                key.charCodeAt(0) == 46 || key.charCodeAt(0) == 37 ||
                key.charCodeAt(0) == 39) {
                return true;
            }
            event.preventDefault();
            return false;
        }
    });

    jQuery('#amount').keyup((event) => {
        if (parseInt(data) < 1) {
            jQuery('#amount').val('1');
            jQuery('#slider').slider('option', 'value', '1');
        }

        let data = jQuery('#amount').val();

        if (data == ''){
            jQuery('#amount').val(1);
        }

        if (data.length > 0) {
            if (parseInt(data) > 0 && parseInt(data) <=
                jQuery('#slider').slider('option','max')) {
                jQuery('#slider').slider('option', 'value', data);
            } else {
                if (parseInt(data) < 1) {
                    jQuery('#amount').val('1');
                    jQuery('#slider').slider('option', 'value', '1');
                }
                if (parseInt(data) > jQuery('#slider').slider('option','max')) {
                    jQuery('#amount').val(jQuery('#slider').slider('option', 'max'));
                    jQuery('#slider').slider('option', 'value',
                        jQuery('#slider').slider('option', 'max'));
                }
            }
        } else {
            jQuery('#slider').slider('option', 'value', '1');
        }
    });

    jQuery('#amount').change((event) => {
        let data = jQuery('#amount').val();
        if (data.length > 0) {
            if (parseInt(data) > 0 && parseInt(data) <=
                jQuery('#slider').slider('option','max')) {
                jQuery('#slider').slider('option', 'value', data);
            }

            else {
                if (parseInt(data) < 1) {
                    jQuery('#amount').val('1');
                    jQuery('#slider').slider('option', 'value', '1');
                }

                if (parseInt(data) > jQuery('#slider').slider('option','max')) {
                    jQuery('#amount').val(jQuery('#slider').slider('option', 'max'));
                    jQuery('#slider').slider('option', 'value',
                        jQuery('#slider').slider('option','max'));
                }
            }
        } else {
            jQuery('#slider').slider('option', 'value', '1');
        }
    });
}

function showGameResult(data) {
    const status = data['gameStatus'];

    switch(status){
    case 'PLAYER_BUSTED':
        countWins=0;
        swal('You lost.', 'Your bid is taken to the casino. Try again!');
        break;
    case 'DEALER_BUSTED':
        if (bj) {
            swal('Black Jack! yeah! ',
                `You're paid 3:2 to your bid - ${data['winSum']}`);
        } else {
            if(countWins == 0) {
                swal('Well played! ',
                    `You're paid 1:1 to your bid - ${(data['winSum'] / 2)}`);
            }
            if(countWins == 1) {
                swal('Wow! Second in a row! Your win is - ' +
                    data['winSum'] / 2, 'Wanna try again, huh?');
            }
            if(countWins == 2) {
                swal("Come on! It's impossible! ",
                    `We bet you're cheating! Now you stole ${data['winSum'] / 2} from us!`);
            }
            if(countWins > 2) {
                swal('COME FREAKING ON! NO WAY!', data['winSum'] / 2);
            }
            countWins++;
        }
        break;
    case 'PLAYER_WINS':
        if (bj) {
            swal('Black Jack! yeah!',"You're paid 3:2 to your bid - " +
                data['winSum']);
        } else {
            if (countWins == 0) {
                swal('Well played! ', "You're paid 1:1 to your bid - "
                    + data['winSum']/2);
            }
            if (countWins == 1) {
                swal('Wow! Second in a row! Your win is - ' +
                    data['winSum'] / 2, 'Wanna try again, huh?');
            }
            if (countWins == 2) {
                swal("Come on! It's impossible! ",
                    "We bet you're cheating! Now you stole " +
                        data['winSum'] / 2 + ' from us!');
            }
            if (countWins > 2) {
                swal('COME FREAKING ON! NO WAY!','You won ' +
                    data['winSum'] / 2);
            }
            countWins++;
        }
        break;
    case 'DEALER_WINS':
        countWins = 0;
        swal('You lost.', 'Your bid is taken to the casino. Try again!');
        break;
    case 'TIE':
        countWins = 0;
        swal("It's a PUSH! ", 'Nobody wins, your bid is given back to you.');
        break;
    default:
        swal('Oops','game did some mistake');
        break;
    }
}

function setCardImage(cardOwner, cardName, index) {
    if(cardName != undefined){
        if(cardName == 'background') {
            jQuery(`#${cardOwner}${index}`).css({'height': '157px','margin-top': '1px'});
        } else {
            jQuery(`#${cardOwner}${index}`).css({'height': '160px','margin-top': '0'});
        }

        jQuery(`#${cardOwner}${index}`).show('slow');
        document.getElementById(cardOwner + index).src = `img/cards/${cardName}.png`;
    } else {
        fatalError();
    }
}

function setPlayerScore(score) {
    if (score != -1) {
        document.getElementById('playerSum').innerHTML =
            `Player's score:<br />${(score).toString()}`;
    } else {
        document.getElementById('playerSum').innerHTML = '';
    }
}

function setDealerScore(score) {
    if(score != -1) {
        document.getElementById('dealerSum').innerHTML =
            `Dealer's score:<br />${(score).toString()}`;
    } else {
        document.getElementById('dealerSum').innerHTML = '';
    }
}

function setInfoText(text) {
    document.getElementById('infotext').innerHTML = text;
}
