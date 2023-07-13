<%-- 
    Document   : profile
    Created on : Jun 29, 2023, 12:57:34 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="icon" type="image/x-icon" href="./img/icons8-favicon-64.png">
        <link rel="stylesheet" href="./css/style_1.css">
        <link rel="stylesheet" href="./css/bootstrap.min.css">
    </head>
    <body>
        <div class="board">
            <form id="profile-form" action="profile" method="POST">
                <div class="container light-style flex-grow-1 container-p-y">
                    <h4 class="font-weight-bold py-3 mb-4">
                        <a href="home">Account settings</a>
                    </h4>
                    <div class="card overflow-hidden">
                        <div class="row no-gutters row-bordered row-border-light">
                            <div class="col-md-3 pt-0">
                                <div class="list-group list-group-flush account-settings-links">
                                    <a class="list-group-item list-group-item-action active" data-toggle="list" href="#account-general">General</a>
                                    <a class="list-group-item list-group-item-action" data-toggle="list" href="#account-change-password">Change password</a>
                                </div>
                            </div>
                            <div class="col-md-9">
                                <div class="tab-content">
                                    <div class="tab-pane fade active show" id="account-general">

                                        <div style="display: block" class="card-body media align-items-center">
                                            <img style="border-radius: 50%" src="${profile.image_url}" alt="" class="d-block ui-w-80">
                                            <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
                                            <br>
                                            <label class="form-label">Image URL</label>
<!--                                            <div id="replybutton" class="btn4 like">
                                                <button style="text-align: right" class="btn reply" id="replyb">Update Avatar
                                            </div>-->
                                            <input type="text"  id="reply" name="img_url" class="form-control pull-right"  value="${profile.image_url}"/>
<!--                                            <script>
                                                $(document).ready(function () {
                                                    $('#replyb').click(function () {
                                                        $('#reply').toggle();
                                                    });
                                                });
                                            </script>-->
                                        </div>
                                        <hr class="border-light m-0">

                                        <div class="card-body">
                                            <div class="form-group">
                                                <label class="form-label">Username</label>
                                                <input type="text" name="pusername" class="form-control mb-1" value="${profile.username}">
                                            </div>
                                            <div class="form-group">
                                                <label class="form-label">First Name</label>
                                                <input type="text" name="pfname" class="form-control" value="${profile.first_name}">
                                            </div>
                                            <div class="form-group">
                                                <label class="form-label">Last Name</label>
                                                <input type="text" name="plname" class="form-control" value="${profile.last_name}">
                                            </div>
                                            <div class="form-group">
                                                <label class="form-label">E-mail</label>
                                                <input type="text" name="pemail" class="form-control mb-1" value="${profile.email}">
                                            </div>
                                        </div>

                                    </div>
                                    <div class="tab-pane fade" id="account-change-password">
                                        <div class="card-body pb-2">

                                            <div class="form-group">
                                                <label class="form-label">Current password</label>
                                                <input type="password" name="currentpass" class="form-control">
                                            </div>

                                            <div class="form-group">
                                                <label class="form-label">New password</label>
                                                <input type="password" name="newpass" id="pass" class="form-control">
                                            </div>

                                            <div class="form-group">
                                                <label class="form-label">Repeat new password</label>
                                                <input type="password" id="re_pass" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="text-right mt-3">
                        <input type="submit" name="name" value="Save changes" class="btn btn-primary"> 
                    </div>
                </div>
            </form>
        </div>

    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
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
</html>
