function empty(lst){
	return lst.length == 0;
}
function car(x) {
	return _.first(x);
}
function cdr(x) {
	return _.rest(x);
}
function cadr(x) {
	return car(cdr(x));
}

function existy(x){
	return x != null};

function truthy(x) {return (x !== false) && existy(x) };

function allOf(x, f){
	return _.reduceRight(x, function(elem, rest) {return elem && f(rest);}, true);
}
function identity(x){return x;}
function interpose(inter, coll){
	return butLast(flatmap(function(e){
		return construct(e, inter);
	}, coll));
}

function warn(thing){
	alert(["WARNING: ", thing].join(' '));
}

function note(thing){
}

function fail(thing){
	throw new Error(thing);
}
