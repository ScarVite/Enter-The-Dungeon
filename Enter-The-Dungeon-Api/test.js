async function add(){
var score = 220;
var token = "33317-200-10-5-5-9921";
var tokenArr = token.split("-");
console.log(tokenArr);
var token1 = tokenArr[0].split("")
var token2 = [];
for(var i = 1; i < tokenArr.length-1;i++){
    token2.push(tokenArr[i])
}
var token3 = tokenArr[tokenArr.length-1].split("")
var value = 0;
token1.forEach(token => {
    value += parseInt(token);
});
var value2 = 0;
token2.forEach(token => {
    value2 += parseInt(token);
});
var value3 = 0;
token3.forEach(token => {
    value3 += parseInt(token);
});
}
add()