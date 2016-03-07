const
    restAdress = "http://localhost:8080/servletBlackJack/rest",
    hitParams = {
        action : 'HIT',
        userId : 'casualPlayer',
    },
    restartParams = {
        action : 'RESTART',
        userId : 'casualPlayer',
    },
    standParams = {
        action : 'STAND',
        userId : 'casualPlayer',
    },
    gameStartParams = {
        action : 'GAMESTART',
        userId : 'casualPlayer',
    },
    endGameParams = {
        action : 'RESTART',
        userId : 'casualPlayer',
    },
    betParams = {
        action : 'PLACEBET',
        userId : 'casualPlayer',
    },
    balanceParams = {
        action : 'GETBALANCE',
        userId : 'casualPlayer',
    },
    refillParams = {
        action : 'REFILL',
        userId : 'casualPlayer',
    },
    REFILLMESSAGE = 'You have just refilled your wallet with 1000 coins',
    DUPLICATEGAMETEXT = 'You are trying to play more than 1 game at once! ' +
        'Please, close this window and continue playing';
