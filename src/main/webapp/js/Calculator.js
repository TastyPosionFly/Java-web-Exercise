const FirstNumber =document.querySelector("#FirstNumber");
const SecondNumber = document.querySelector("#SecondNumber");
const Symbol = document.querySelector("#Symbol");
const Equal = document.querySelector("#Equal");
const FinalNumber = document.querySelector("#FinalNumber");



Equal.addEventListener("click",()=>{
    let operator = Symbol.value;
    let result;
    if (operator === "+")
        result = Number(FirstNumber.value) + Number(SecondNumber.value);
    else if(operator === "-")
        result = FirstNumber.value - SecondNumber.value;
    else if(operator === "*")
        result = FirstNumber.value * SecondNumber.value;
    else if (operator === "/")
        result = FirstNumber.value / SecondNumber.value;

    FinalNumber.value = result;
});
