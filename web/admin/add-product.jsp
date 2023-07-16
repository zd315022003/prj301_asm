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
      <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
        <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
          <div class="row">
            <div class="col-12">
              <h2 class="tm-block-title d-inline-block">Add Product</h2>
            </div>
          </div>
          <div class="row tm-edit-product-row">
            <div class="col-xl-6 col-lg-6 col-md-12">
              <form action="" class="tm-edit-product-form">
                <div class="form-group mb-3">
                  <label for="name">Product Name
                  </label>
                  <input id="name" name="name" type="text" class="form-control validate" required />
                </div>
                <div class="form-group mb-3">
                  <label for="description">Description</label>
                  <textarea class="form-control validate" rows="3" required></textarea>
                </div>
                <div class="form-group mb-3">
                  <label for="category">Categories</label>
                  <select class="custom-select tm-select-accounts" id="category">
                    <option selected>Select Categories</option>
                    <option value="1">Categories 1</option>
                    <option value="2">Categories 2</option>
                    <option value="3">Categories 3</option>
                  </select>
                </div>
                <div class="form-group mb-3">
                  <label for="branding">Branding</label>
                  <select class="custom-select tm-select-accounts" id="branding">
                    <option selected>Select Brand</option>
                    <option value="1">Brand 1</option>
                    <option value="2">Brand 2</option>
                    <option value="3">Brand 3</option>
                  </select>
                </div>
                <div class="form-group mb-3">
                  <label for="filter_price">Filter Price</label>
                  <select class="custom-select tm-select-accounts" id="filter_price">
                    <option selected>Select Filter Price</option>
                    <option value="1">Filter 1</option>
                    <option value="2">Filter 2</option>
                    <option value="3">Filter 3</option>
                  </select>
                </div>
                <div class="row">
                  <div class="form-group mb-3 col-xs-12 col-sm-6">
                    <label for="price">Price
                    </label>
                    <input id="price" name="price" type="text" class="form-control validate" required />
                  </div>
                  <div class="form-group mb-3 col-xs-12 col-sm-6">
                    <label for="sale">Sale
                    </label>
                    <input id="sale" name="sale" type="text" class="form-control validate" required />
                  </div>
                  <div class="form-group mb-3 col-xs-12 col-sm-6">
                    <label for="quantity">Quantity
                    </label>
                    <input id="quantity" name="quantity" type="text" class="form-control validate" required />
                  </div>
                </div>

            </div>
            <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">
              <div class="tm-product-img-dummy mx-auto">
                <i class="fas fa-cloud-upload-alt tm-upload-icon"
                  onclick="document.getElementById('fileInput').click();"></i>
              </div>
              <div class="custom-file mt-3 mb-3">
                <input id="fileInput" type="file" style="display:none;" />
                <input type="button" class="btn btn-primary btn-block mx-auto" value="UPLOAD PRODUCT IMAGE"
                  onclick="document.getElementById('fileInput').click();" />
              </div>
            </div>
            <div class="col-12">
              <button type="submit" class="btn btn-primary btn-block text-uppercase">Add Product Now</button>
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
  <script>
    $(function () {
      $("#expire_date").datepicker();
    });
  </script>
</body>

</html>