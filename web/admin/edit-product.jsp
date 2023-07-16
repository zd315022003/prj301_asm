<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Add Product - Dashboard HTML Template</title>
    <%@include file="admin-component/admin-import.jsp" %>
</head>

<body>
<%@include file="admin-component/header.jsp" %>
<div class="container tm-mt-big tm-mb-big">
    <jsp:useBean id="product" scope="request" type="model.Product"/>
    <div class="row">
        <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
            <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                <div class="row">
                    <div class="col-12">
                        <h2 class="tm-block-title d-inline-block">${product.product_id == -1? 'Add Product' : 'Update Project'}</h2>
                    </div>
                </div>
                <div class="row tm-edit-product-row">
                    <form action="edit-product" method="post" class="tm-edit-product-form">
                        <div class="col-xl-6 col-lg-6 col-md-12">
                            <div class="form-group mb-3">
                                <label for="name">Product Name
                                </label>
                                <input type="hidden" name="product-id" value="${product.product_id}">
                                <input id="name" name="name" type="text" class="form-control validate"
                                       value="${product.title}" required/>
                            </div>
                            <div class="form-group mb-3">
                                <label for="description">Description</label>
                                <textarea id="description" name="description"
                                          class="form-control validate" rows="3" required>
                                    ${product.description}
                                </textarea>
                            </div>
                            <div class="form-group mb-3">
                                <label for="category">Categories</label>
                                <div id="categories-wrapper" class="selected-categories">
                                    <jsp:useBean id="productCategories" scope="request"
                                                 type="java.util.List<model.Category>"/>
                                    <c:forEach items="${productCategories}" var="category">
                                        <div id="selected-${category.category_id}">
                                        <span class="category">${category.name}</span>
                                            <button type="button" onclick="removeCategory(${category.category_id})">x
                                            </button>
                                        </div>
                                        <input name="selected-categories" value="${category.category_id}" type="hidden">
                                    </c:forEach>
                                </div>
                                <select class="custom-select tm-select-accounts" id="category"
                                        onchange="addCategory(this.value)">
                                    <option selected>Select Category</option>
                                    <jsp:useBean id="categories" scope="request"
                                                 type="java.util.List<dto.CategoryDTO>"/>
                                    <c:forEach items="${categories}" var="category">
                                        <option value="${category.id}">${category.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group mb-3">
                                <label for="branding">Branding</label>
                                <select class="custom-select tm-select-accounts" id="branding" name="branding">
                                    <option selected>Select Brand</option>
                                    <jsp:useBean id="brands" scope="request" type="java.util.List<dto.BrandDTO>"/>
                                    <c:forEach items="${brands}" var="brand">
                                        <option value="${brand.id}" ${brand.id == product.branding_id? 'selected' : ''}>
                                                ${brand.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <%--                            <div class="form-group mb-3">--%>
                            <%--                                <label for="filter_price">Filter Price</label>--%>
                            <%--                                <select class="custom-select tm-select-accounts" id="filter_price">--%>
                            <%--                                    <option selected>Select Filter Price</option>--%>
                            <%--                                    <option value="1">Filter 1</option>--%>
                            <%--                                    <option value="2">Filter 2</option>--%>
                            <%--                                    <option value="3">Filter 3</option>--%>
                            <%--                                </select>--%>
                            <%--                            </div>--%>
                            <div class="row">
                                <div class="form-group mb-3 col-xs-12 col-sm-6">
                                    <label for="price">Price
                                    </label>
                                    <input id="price" name="price" type="text" class="form-control validate"
                                           value="${product.price}" required/>
                                </div>
                                <div class="form-group mb-3 col-xs-12 col-sm-6">
                                    <label for="sale">Sale
                                    </label>
                                    <input id="sale" name="sale" type="text" class="form-control validate"
                                           value="${product.sale}" required/>
                                </div>
                                <div class="form-group mb-3 col-xs-12 col-sm-6">
                                    <label for="quantity">Quantity
                                    </label>
                                    <input id="quantity" name="quantity" type="text" class="form-control validate"
                                           value="${product.quantity}" required/>
                                </div>
                            </div>

                        </div>
                        <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">
                            <div class="tm-product-img-dummy mx-auto">
                                    <img id="img-thumbnail" src="../img/product/${product.thumbnail == null? 'default.jpg' : product.thumbnail}" alt="Product image"
                                         onclick="document.getElementById('fileInput').click();"
                                         class="img-fluid d-block mx-auto"/>
                            </div>
                            <div class="custom-file mt-3 mb-3">
                                <input id="fileInput" name="thumbnail" type="file" style="display:none;" onchange="changeImg(this.value)"/>
                                <input type="button" class="btn btn-primary btn-block mx-auto"
                                       value="UPLOAD PRODUCT IMAGE"
                                       onclick="document.getElementById('fileInput').click();"/>
                            </div>
                        </div>
                        <div class="col-12">
                            <button type="submit" class="btn btn-primary btn-block text-uppercase">Save</button>
                        </div>
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
<script src="jquery-ui-datepicker/jquery-ui.min.js"></script>
<!-- https://jqueryui.com/download/ -->
<script src="js/bootstrap3.min.js"></script>
<!-- https://getbootstrap.com/ -->
<script src="https://cdn.ckeditor.com/ckeditor5/38.1.1/classic/ckeditor.js"></script>
<script>
    function removeCategory(categoryId) {
        $('#category').append('<option value="' + categoryId + '">' + $("#selected-" + categoryId + " span").text() + '</option>');
        $("#selected-" + categoryId).remove();
    }

    function addCategory(value) {
        $('#categories-wrapper').append('<div id="selected-' + value + '"><span class="category">' + $("#category option:selected").remove().text() + '</span><button type="button" onclick="removeCategory(' + value + ')">x</button><input name="selected-categories" value="'+ value + '" type="hidden"></div>');
    }

    function changeImg(value) {
        //get file name from C:/fakepath/abc.txt --> abc.txt
        var fileName = value.split("\\").pop();
        $('#img-thumbnail').attr('src', '../img/product/' + fileName);
    }

    $(function () {
        $("#expire_date").datepicker();
    });
    ClassicEditor
        .create(document.querySelector('#description'))
        .catch(error => {
            console.error(error);
        });
</script>
</body>

</html>