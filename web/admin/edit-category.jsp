<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Add Product - Dashboard HTML Template</title>
    <%@include file="admin-component/admin-import.jsp"%>
</head>

<body>
    <%@include file="admin-component/header.jsp" %>
    <div class="container tm-mt-big tm-mb-big">
        <div class="row">
            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 mx-auto">
                <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                    <div class="row">
                        <div class="col-6">
                            <h2 class="tm-block-title d-inline-block">Add Categories</h2>
                        </div>
                    </div>
                    <!-- <div class="row tm-edit-product-row"> -->
                        <div class="col-xl-12 col-lg-12 col-md-12">
                            <form action="" class="tm-edit-product-form">
                                <div class="form-group mb-3">
                                    <label for="name">Categories Name
                                    </label>
                                    <input id="name" name="name" type="text" class="form-control validate" required />
                                </div>
                        </div>
                        <div class="col-12">
                            <!-- <button type="submit" class="btn btn-primary btn-block text-uppercase">Add Product Now</button> -->
                            <a href="add-category.jsp" class="btn btn-primary btn-block text-uppercase mb-3">Save</a>
                        </div>
                        </form>
                    <!-- </div> -->
                </div>
            </div>
        </div>
    </div>
    <footer class="tm-footer row tm-mt-small">
        <div class="col-12 font-weight-light">
            <p class="text-center text-white mb-0 px-4 small">
                Copyright &copy; <b>2018</b> All rights reserved.

                Design: <a rel="nofollow noopener" href="https://templatemo.com" class="tm-footer-link">Template Mo</a>
            </p>
        </div>
    </footer>

    <script src="js/jquery3-3.3.1.min.js"></script>
    <!-- https://jquery.com/download/ -->
    <script src="jquery-ui-datepicker/jquery-ui.min.js"></script>
    <!-- https://jqueryui.com/download/ -->
    <script src="js/bootstrap3.min.js"></script>
    <!-- https://getbootstrap.com/ -->
    <script>
        $(function () {
            $("#expire_date").datepicker();
        });
    </script>
</body>

</html>