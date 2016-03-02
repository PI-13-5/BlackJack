import { random } from '../js/another';


describe('test suit example', () => {
    it('has to sum correctly', () => {
        expect(1 + 2).toBe(false);
    });

    it('has to return a number and not null', () => {
        const testVar = random();
        expect(testVar).not.toBeNull();
    });
});
