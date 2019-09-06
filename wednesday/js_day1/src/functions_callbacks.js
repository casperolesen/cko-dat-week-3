// function declaration
function add(n1, n2) {
    return n1 + n2;
}

// function expression
var sub = function(n1, n2) {
    return n1 - n2;
};

// callback example
var cb = function(n1, n2, callback) {
    if(typeof n1 !== "number") throw new Error("n1 is not a number");
    if(typeof n2 !== "number") throw new Error("n2 is not a number");
    if(typeof callback !== "function") throw new Error("callback is not a function");
    return "Result from the two numbers: "+n1+"+"+n2+"="+callback(n1,n2);
};

// 2 + 3
console.log( add(1,2) ); // 3
console.log( add ); // function
console.log( add(1,2,3) ); // 3 (not error)
console.log( add(1) ); // NaN
console.log( cb(3,3,add) ); // 3+3=6
console.log( cb(4,3,sub) ); // 4+3=1

try {
    console.log( cb(3,3,add() )); // error: callback is not a function
} catch (e) {
    console.log(e.name + ": " + e.message);
}

try {
    console.log( cb(3,"hh",add)); // error: n2 is not a number
} catch (e) {
    console.log(e.name + ": " + e.message);
}

// 4 + 5
function nonAnonymousFunction(a) {
    return function (b) {
        console.log("Non anonymous param: " + a + " Anonymous param: " + b);
    };
}
nonAnonymousFunction("Hello")("World");

function divideAnonymous(n1) {
    return function (n2) {
        return n1 / n2;
    };
}

try {
    console.log( cb(10,2,divideAnonymous));
} catch(e) {
    console.log(e.name + ": " + e.message);
}


// 1
var names = ["Lars", "Jan", "Peter", "Bo", "Frederik"];
const shortNames = names.filter(name => name.length <= 3);
names.forEach(name => console.log(name));
console.log();
shortNames.forEach(name => console.log(name));

// 2
const namesUpper = names.map(name => name.toUpperCase());
console.log(namesUpper);

// 3
function buildList(array) {
    return array.map(item => `<li>${item}</li>`).join('');
}
console.log(buildList(names));



