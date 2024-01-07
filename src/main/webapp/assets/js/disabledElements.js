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
	
function toggleBuyingsSales() {
	
	var enableInputBuyings = document.getElementById("buyingsButton");
	var enableInputSales = document.getElementById("salesButton");
	var inputOpenedBids = document.getElementById("openedBids");
	var inputMyCurrentBids = document.getElementById("myCurrentBids");
	var inputMyWonBids = document.getElementById("myWonBids");
	var inputMyCurrentSales = document.getElementById("myCurrentSales");
	var inputNonBeginnedSales = document.getElementById("nonBeginnedSales");
	var inputEndedSales = document.getElementById("endedSales");
	var buyingsBloc = document.getElementById("buyingCheckboxes");
	var salesBloc = document.getElementById("salesCheckboxes");
		

	if (enableInputBuyings.checked && !enableInputSales.checked) {
		console.log("Toggle Buying function called");
		inputOpenedBids.removeAttribute("disabled");
		inputMyCurrentBids.removeAttribute("disabled");
		inputMyWonBids.removeAttribute("disabled");	
		inputMyCurrentSales.setAttribute("disabled", true);
		inputNonBeginnedSales.setAttribute("disabled", true);
		inputEndedSales.setAttribute("disabled", true);	
		buyingsBloc.classList.replace("disabledGroup","enableGroup");
		salesBloc.classList.replace("enableGroup", "disabledGroup");
	} else {
		console.log("Toggle Sales function called");
		inputMyCurrentSales.removeAttribute("disabled");
		inputNonBeginnedSales.removeAttribute("disabled");
		inputEndedSales.removeAttribute("disabled");	
		inputOpenedBids.setAttribute("disabled", true);
		inputMyCurrentBids.setAttribute("disabled", true);
		inputMyWonBids.setAttribute("disabled", true);
		buyingsBloc.classList.replace("enableGroup", "disabledGroup");		
		salesBloc.classList.replace("disabledGroup","enableGroup");
		
	}
}

