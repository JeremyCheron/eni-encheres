function validatePassword() {
    console.log("Validation function called");
    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("confirmPassword").value;

    console.log("Password:", password);
    console.log("Confirm Password:", confirmPassword);

    if (password !== confirmPassword) {
        alert("Les mots de passe ne correspondent pas");
        return false;
    }

    return true;
}

// Attacher la fonction validatePassword à l'événement onsubmit du formulaire
var registrationForm = document.getElementById("registrationForm");
if (registrationForm) {
    registrationForm.onsubmit = validatePassword;
}