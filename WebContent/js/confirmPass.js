 document.addEventListener('DOMContentLoaded', function() {
       document.getElementById('login_form').addEventListener('submit', function(event) {
           console.log("Form submit event triggered"); // Debugging statement
           var password = document.getElementById('new_pw').value;
           var confirmPassword = document.getElementById('rePw').value;
           var errorMessage = document.getElementById('error_message');

           console.log("Password:", password); // Debugging statement
           console.log("Confirm Password:", confirmPassword); // Debugging statement

           if (password !== confirmPassword) {
               event.preventDefault(); // Prevent the form from submitting
               errorMessage.style.display = 'block'; // Show the error message
               console.log("Passwords do not match"); // Debugging statement
           } else {
               errorMessage.style.display = 'none'; // Hide the error message
               console.log("Passwords match"); // Debugging statement
           }
       });
   });