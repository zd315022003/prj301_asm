<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Product Page - Admin HTML Template</title>
    <%@include file="admin-component/admin-import.jsp" %>
</head>

<body id="reportsPage">
<%@include file="admin-component/header.jsp" %>
<div class="container mt-5">
    <div class="row tm-content-row">
        <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 tm-block-col">
            <form action="products" method="post">
                <div class="tm-bg-primary-dark tm-block tm-block-products">
                    <div class="tm-product-table-container">
                        <table class="table table-hover tm-table-small tm-product-table">
                            <thead>
                            <tr>
                                <th scope="col">&nbsp;</th>
                                <th scope="col">PRODUCT NAME</th>
                                <th scope="col">UNIT SOLD</th>
                                <th scope="col">IN STOCK</th>
                                <th scope="col">CREATED DATE</th>
                                <th scope="col">&nbsp;</th>
                            </tr>
                            </thead>
                            <tbody>
                            <jsp:useBean id="products" scope="request" type="java.util.List<model.Product>"/>
                            <c:forEach items="${products}" var="product">
                                <tr>
                                    <th scope="row"><label>
                                        <input type="checkbox" name="selected" value="${product.product_id}"/>
                                    </label></th>
                                    <td class="tm-product-name">
                                        <label for="edit-${product.product_id}">${product.title}</label>
                                        <input style="display: none" id="edit-${product.product_id}"
                                               type="submit" name="edit" value="${product.product_id}">
                                    </td>
                                    <td>${product.sold}</td>
                                    <td>${product.quantity}</td>
                                    <td>${product.created_at}</td>
                                    <td>
                                        <label class="tm-product-delete-link" for="delete-${product.product_id}">
                                            <i class="far fa-trash-alt tm-product-delete-icon"></i>
                                        </label>
                                        <input style="display: none" id="delete-${product.product_id}"
                                               type="submit" name="delete" value="${product.product_id}">
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- table container -->
                    <a
                            href="add-product"
                            class="btn btn-primary btn-block text-uppercase mb-3">Add new product</a>
                    <button class="btn btn-primary btn-block text-uppercase" type="submit" name="action" value="delete-all">
                        Delete selected products
                    </button>
            </form>
        </div>
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