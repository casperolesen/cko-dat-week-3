// a
var boys = ["Peter", "lars", "Ole"];
var girls = ["Janne", "hanne", "Sanne"];

// b
var all = boys.concat(girls);
console.log(all);

// c
console.log(all.join());
console.log(all.join('-'));

// d + e
all.push("Lone", "Gitte");
all.unshift("Hans", "Kurt");
console.log(all);

// f + g
all.shift();
all.pop();
console.log(all);

// h
all.splice(3,2);
console.log(all);

// i
console.log(all.reverse());

// j
console.log(all.sort());

// k
all.sort(function (a, b){
   return a.localeCompare(b); 
});
console.log(all);

// l
const map1 = all.map(name => name.toUpperCase());
console.log(map1);

// m
function startsWithL(word) {
    return word.toUpperCase().startsWith("L");
}
const filter1 = all.filter(startsWithL);
console.log(filter1);

