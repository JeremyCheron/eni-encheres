function changeEndDateAccordingToStartDate() {
	var inputStart = document.getElementById('startDate');
	var inputEnd = document.getElementById('endDate');
	var endMaxDate= new Date(inputStart.valueAsDate.setDate(inputEnd.valueAsDate.getDate() + 90))
	inputEnd.min = inputStart.value;
	inputEnd.max = endMaxDate.toISOString().split('T')[0];
	inputEnd.value = inputEnd.max;
}

	