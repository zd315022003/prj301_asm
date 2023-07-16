<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Product Page - Admin HTML Template</title>
    <%@include file="admin-component/admin-import.jsp" %>
    <style>
        a {
            text-decoration: none;
            color: #fff;
        }
    </style>
</head>

<body id="reportsPage">
<%@include file="admin-component/header.jsp" %>
<div class="container mt-5 ">
    <div class="row tm-content-row justify-content-center">
        <div class="col-sm-6 col-md-6 col-lg-6 col-xl-6 tm-block-col">
            <div class="tm-bg-primary-dark tm-block tm-block-product-categories">
                <h2 class="tm-block-title">Product Categories</h2>
                <div class="tm-product-table-container">
                    <form action="category" method="post">
                        <table class="table tm-table-small tm-product-table">
                            <tbody>
                            <jsp:useBean id="categories" scope="request" type="java.util.List<dto.CategoryDTO>"/>
                            <c:forEach items="${categories}" var="category">
                                <tr>
                                    <td class="tm-product-name">${category.name} (${category.count})</td>
                                    <td class="text-center">
                                        <label for="delete-${category.id}" href="#" class="tm-product-delete-link">
                                            <i class="far fa-trash-alt tm-product-delete-icon"></i>
                                        </label>
                                        <input type="submit" value="${category.id}"
                                               id="delete-${category.id}" style="display: none" name="delete">
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </div>
                <!-- table container -->
                <button class="btn btn-primary btn-block text-uppercase mb-3">
                    <a href="add-category">Add new category</a>
                </button>
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
<script src="js/bootstrap3.min.js"></script>
<!-- https://getbootstrap.com/ -->
<script>
    $(function () {
        $(".tm-product-name").on("click", function () {
            window.location.href = "edit-product.jsp";
        });
    });
</script>
</body>
</html>