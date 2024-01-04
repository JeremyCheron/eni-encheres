function toggleInputs() {
    console.log("Toggle Inputs function called");

    var enableInput = document.getElementById("defaultAddress");
    var input1 = document.getElementById("street");
    var input2 = document.getElementById("postalCode");
    var input3 = document.getElementById("city");

    if (!enableInput.checked) {
		input1.setAttribute("disabled", true);
        input2.setAttribute("disabled", true);
        input3.setAttribute("disabled", true);
    } else {
        input1.removeAttribute("disabled");
        input2.removeAttribute("disabled");
        input3.removeAttribute("disabled");
    }
}
