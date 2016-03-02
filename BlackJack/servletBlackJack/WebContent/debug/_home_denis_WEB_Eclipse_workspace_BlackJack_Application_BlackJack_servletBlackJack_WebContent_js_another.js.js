
// Instrumentation Header
{
    var fs = require('fs');
    var __statement_o7OIAR, __expression_YNCT3x, __block_svn8cu;
    var store = require('/home/denis/WEB/Eclipse/workspace/BlackJack/Application/BlackJack/servletBlackJack/WebContent/node_modules/gulp-coverage/contrib/coverage_store.js');
    
    __statement_o7OIAR = function(i) {
        var fd = store.register('/home/denis/WEB/Eclipse/workspace/BlackJack/Application/BlackJack/servletBlackJack/WebContent/js/another.js');
        fs.writeSync(fd, '{"statement": {"node": ' + i + '}},\n');
    }; 
    
    __expression_YNCT3x = function(i) {
        var fd = store.register('/home/denis/WEB/Eclipse/workspace/BlackJack/Application/BlackJack/servletBlackJack/WebContent/js/another.js');
        fs.writeSync(fd, '{"expression": {"node": ' + i + '}},\n');
    }; 
    
    __block_svn8cu = function(i) {
        var fd = store.register('/home/denis/WEB/Eclipse/workspace/BlackJack/Application/BlackJack/servletBlackJack/WebContent/js/another.js');
        fs.writeSync(fd, '{"block": ' + i + '},\n');
    }; 
    __intro_jAYhNX = function(id, obj) {
        // console.log('__intro: ', id, ', obj.__instrumented_miss: ', obj.__instrumented_miss, ', obj.length: ', obj.length);
        (typeof obj === 'object' || typeof obj === 'function') &&
            Object.defineProperty && Object.defineProperty(obj, '__instrumented_miss', {enumerable: false, writable: true});
        obj.__instrumented_miss = obj.__instrumented_miss || [];
        if ('undefined' !== typeof obj && null !== obj && 'undefined' !== typeof obj.__instrumented_miss) {
            if (obj.length === 0) {
                // console.log('interim miss: ', id);
                obj.__instrumented_miss[id] = true;
            } else {
                obj.__instrumented_miss[id] = false;
            }
        }
        return obj;
    };
    function isProbablyChainable(obj, id) {
        return obj &&
            obj.__instrumented_miss[id] !== undefined &&
            'number' === typeof obj.length;
    }
    __extro_JOLZTw = function(id, obj) {
        var fd = store.register('/home/denis/WEB/Eclipse/workspace/BlackJack/Application/BlackJack/servletBlackJack/WebContent/js/another.js');
        // console.log('__extro: ', id, ', obj.__instrumented_miss: ', obj.__instrumented_miss, ', obj.length: ', obj.length);
        if ('undefined' !== typeof obj && null !== obj && 'undefined' !== typeof obj.__instrumented_miss) {
            if (isProbablyChainable(obj, id) && obj.length === 0 && obj.__instrumented_miss[id]) {
                // if the call was not a "constructor" - i.e. it did not add things to the chainable
                // and it did not return anything from the chainable, it is a miss
                // console.log('miss: ', id);
            } else {
                fs.writeSync(fd, '{"chain": {"node": ' + id + '}},\n');
            }
            obj.__instrumented_miss[id] = undefined;
        } else {
            fs.writeSync(fd, '{"chain": {"node": ' + id + '}},\n');
        }
        return obj;
    };
};
////////////////////////

// Instrumented Code
function random() {
    __block_svn8cu(0);
    {
        __statement_o7OIAR(0);
        const wtf = 4;
    }
    return __expression_YNCT3x(1), wtf;
}
{
    __statement_o7OIAR(2);
    module.exports = random;
}