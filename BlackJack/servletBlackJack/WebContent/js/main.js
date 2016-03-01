import * from './another';

class Hello {
    constructor(a = 5) {
        console.log(`Constructor. ${a}.`);
    }

    get getter() {
        return 6;
    }

    static echo() {
        console.log('hi');
    }

    async sayHi() {
        console.log('say_hi');
    }
}

let a = random();

const h = new Hello(10);
h.sayHi();
Hello.echo();
