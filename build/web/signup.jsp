<%-- 
    Document   : signup
    Created on : Jun 26, 2023, 11:05:35 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign up</title>
        <!-- Font Icon -->
        <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

        <!-- Main css -->
        <link rel="stylesheet" href="css/stylesign.css">
    </head>

    <body>
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                    <div class="signup-form">
                        <h2 class="form-title">Sign up</h2>
                        <form method="POST" class="register-form" id="register-form">
                            <div class="form-group">
                                <label for="fname"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="fname" id="fname" placeholder="First Name" required />
                            </div>
                            <div class="form-group">
                                <label for="lname"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="lname" id="lname" placeholder="Last Name" required />
                            </div>
                            <div class="form-group">
                                <label for="email"><i class="zmdi zmdi-email"></i></label>
                                <input type="email" name="email" id="email" placeholder="Your Email" required />
                            </div>
                            <div class="form-group">
                                <label for="phone"><i class="zmdi zmdi-phone"></i></label>
                                <input type="number" name="phone" id="phone" placeholder="Phone Number" required />
                            </div>
                            <div class="form-group">
                                <label for="username"><i class="zmdi zmdi-account "></i></label>
                                <input type="text" name="username" id="username" placeholder="Username" required />
                            </div>
                            <div class="form-group">
                                <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="pass" id="pass" placeholder="Password" required />
                            </div>
                            <div class="form-group">
                                <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                <input type="password" name="re_pass" id="re_pass" placeholder="Repeat your password" required />
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="signup" id="signup" class="form-submit" value="Register" required />
                            </div>
                        </form>
                    </div>
                    <div class="signup-image">
                        <figure><a href="home"><img style="border-radius: 10%;" src="img/ZD3.png" alt="sign up image"></a></figure>
                        <a href="signin.jsp" class="signup-image-link">I am already member</a>

                    </div>
                </div>
            </div>
        </section>
        <script src="jquery/jquery.min.js"></script>
        <script src="jquery/jquery-ui.min.js"></script>
        <script src="js/main.js"></script>
        <script>
            $('#pass, #re_pass').on('keyup', function () {
                if ($('#pass').val() == $('#re_pass').val()) {
                    document.getElementById('re_pass').setCustomValidity("");
                } else
                    document.getElementById('re_pass').setCustomValidity("Not match!");
                
            });
        </script>
    </body>

</html>
