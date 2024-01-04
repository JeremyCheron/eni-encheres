function disableAddress() {
	var defaultAddress = document.getElementById('defaultAddress');
	var street = document.getElementById('street');
	var postalCode = document.getElementById('postalCode')
	var city = document.getElementById('city');
	// Add more input elements as needed

	if (defaultAddress.checked) {
		street.disabled = true;
		postalCode.disabled = true;
		city.disabled = true;
		// Enable more inputs as needed
	} else {
		street.disabled = false;
		postalCode.disabled = false;
		city.disabled = false;
		
		// Disable more inputs as needed
	}
}


