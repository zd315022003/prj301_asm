<%-- 
    Document   : shop-details
    Created on : Jun 26, 2023, 10:58:27 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zxx">

    <head>
        <%@include file="components/common_import.jsp" %>
        <link rel="stylesheet" href="css/style_2.css" type="text/css">
    </head>

    <body>
        <%@include file="components/header.jsp" %>

        <!-- Shop Details Section Begin -->
        <section class="shop-details">
            <div class="product__details__pic">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="product__details__breadcrumb">
                                <a href="home">Home</a>
                                <a href="./shop.jsp">Shop</a>
                                <span>Product Details</span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-3 col-md-3">
                            <ul class="nav nav-tabs" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" data-toggle="tab" href="#tabs-1" role="tab">
                                        <div class="product__thumb__pic set-bg" data-setbg="img/shop-details/thumb-1.png">
                                        </div>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#tabs-2" role="tab">
                                        <div class="product__thumb__pic set-bg" data-setbg="img/shop-details/thumb-2.png">
                                        </div>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#tabs-3" role="tab">
                                        <div class="product__thumb__pic set-bg" data-setbg="img/shop-details/thumb-3.png">
                                        </div>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#tabs-4" role="tab">
                                        <div class="product__thumb__pic set-bg" data-setbg="img/shop-details/thumb-4.png">
                                            <i class="fa fa-play"></i>
                                        </div>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-lg-6 col-md-9">
                            <div class="tab-content">
                                <div class="tab-pane active" id="tabs-1" role="tabpanel">
                                    <div class="product__details__pic__item">
                                        <img src="img/shop-details/product-big-2.png" alt="">
                                    </div>
                                </div>
                                <div class="tab-pane" id="tabs-2" role="tabpanel">
                                    <div class="product__details__pic__item">
                                        <img src="img/shop-details/product-big-3.png" alt="">
                                    </div>
                                </div>
                                <div class="tab-pane" id="tabs-3" role="tabpanel">
                                    <div class="product__details__pic__item">
                                        <img src="img/shop-details/product-big.png" alt="">
                                    </div>
                                </div>
                                <div class="tab-pane" id="tabs-4" role="tabpanel">
                                    <div class="product__details__pic__item">
                                        <img src="img/shop-details/product-big-4.png" alt="">
                                        <a href="https://www.youtube.com/watch?v=8PJ3_p7VqHw&list=RD8PJ3_p7VqHw&start_radio=1" class="video-popup"><i class="fa fa-play"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="product__details__content">
                <div class="container">
                    <div class="row d-flex justify-content-center">
                        <div class="col-lg-8">
                            <div class="product__details__text">
                                <h4>${product.title}</h4>
                                <c:if test = "${product.price > product.sale}">
                                    <h3>$${product.price}<span>$${product.sale}</span></h3>
                                </c:if>
                                <c:if test = "${product.price == product.sale}">
                                    <h3>$${product.price}</h3>
                                </c:if>
                                <p>${product.description}</p>
                                <div class="product__details__option">
                                    <div class="product__details__option__size">
                                        <span>Size:</span>
                                        <label for="xxl">xxl
                                            <input type="radio" id="xxl">
                                        </label>
                                        <label class="active" for="xl">xl
                                            <input type="radio" id="xl">
                                        </label>
                                        <label for="l">l
                                            <input type="radio" id="l">
                                        </label>
                                        <label for="sm">s
                                            <input type="radio" id="sm">
                                        </label>
                                    </div>
                                    <div class="product__details__option__color">
                                        <span>Color:</span>
                                        <label class="c-1" for="sp-1">
                                            <input type="radio" id="sp-1">
                                        </label>
                                        <label class="c-2" for="sp-2">
                                            <input type="radio" id="sp-2">
                                        </label>
                                        <label class="c-3" for="sp-3">
                                            <input type="radio" id="sp-3">
                                        </label>
                                        <label class="c-4" for="sp-4">
                                            <input type="radio" id="sp-4">
                                        </label>
                                        <label class="c-9" for="sp-9">
                                            <input type="radio" id="sp-9">
                                        </label>
                                    </div> 
                                    <div>
                                        <br>
                                        <span><b>Categories:</b></span> Clothes
                                    </div>
                                </div>
                                <div class="product__details__cart__option">
                                    <div class="quantity">
                                        <div class="pro-qty">
                                            <input type="text" value="1">
                                        </div>
                                    </div>
                                    <a href="#" class="primary-btn">add to cart</a>
                                </div>
                                <div class="product__details__btns__option">
                                    <a href="#"><i class="fa fa-heart"></i> add to favourlist</a>
                                </div>
                                <div class="product__details__last__option">
                                    <h5><span>Guaranteed Safe Checkout</span></h5>
                                    <img src="img/shop-details/details-payment.png" alt="">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Shop Details Section End -->

        <!-- Related Section Begin -->
        <section class="related spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <h3 class="related-title">Related Product</h3>
                    </div>
                </div>
                <div class="row">
                    <c:forEach items="${lproduct}" var="product">
                        <div class="col-lg-3 col-md-6 col-sm-6">
                            <div class="product__item">
                                <div class="product__item__pic set-bg"
                                     data-setbg="img/product/${product.thumbnail}">
                                    <c:if test = "${product.price > product.sale}">
                                        <span style="background-color: black; color: white" class="label">Sale</span>
                                    </c:if>
                                    <c:set var="currentDate" value="${java.time.LocalDate.now()}"/>
                                    <c:set var="targetDate" value="${java.time.LocalDate.parse(product.create_at, java.time.format.DateTimeFormatter.ofPattern('yyyy-MM-dd'))}"/>
                                    <c:set var="monthsDifference" value="${currentDate.until(targetDate, java.time.temporal.ChronoUnit.MONTHS)}"/>

                                    <c:if test="${product.price == product.sale && monthsDifference eq -2}">
                                        <span style="background-color: white; color: black" class="label">New</span>
                                    </c:if>
                                    <%-- TODO: change this to action go to detail page --%>
                                    <a href="./shop-details.jsp?id=${product.id}"></a>
                                    <ul class="product__hover">
                                        <%-- TODO: change this to action add to favorite --%>
                                        <li><a href="#${product.id}"><img src="img/icon/heart.png" alt=""></a></li>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6>${product.title}</h6>
                                    <%-- action add to cart --%>
                                    <a class="add-cart">
                                        <label for="add-${product.id}-to-cart">+ Add To Cart </label>
                                        <input type="hidden" name="action" value="add"/>
                                        <input type="hidden" name="continueUrl" value="shop"/>
                                        <input type="submit" style="display: none"
                                               id="add-${product.id}-to-cart"
                                               formaction="shopping-cart"
                                               formmethod="POST"
                                               name="productID"
                                               value="${product.id}"/>
                                    </a>
                                    <c:if test = "${product.price > product.sale}">
                                        <h5 style="color: gray">
                                            <del>$${product.price}</del>
                                        </h5>
                                        <h5>$${product.sale}</h5>
                                    </c:if>
                                    <c:if test = "${product.price == product.sale}">
                                        <h5>$${product.price}</h5>
                                    </c:if>
                                    <div class="product__color__select">
                                        <label for="pc-4">
                                            <!--<input type="radio" id="pc-4">-->
                                        </label>
                                        <label class="active black" for="pc-5">
                                            <!--<input type="radio" id="pc-5">-->
                                        </label>
                                        <label class="grey" for="pc-6">
                                            <!--<input type="radio" id="pc-6">-->
                                        </label>
                                    </div>
                                    <!-- -->
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
        <!-- Related Section End -->

        <%@include file="components/footer.jsp" %>

        <!-- Search Begin -->
        <div class="search-model">
            <div class="h-100 d-flex align-items-center justify-content-center">
                <div class="search-close-switch">+</div>
                <form class="search-model-form">
                    <input type="text" id="search-input" placeholder="Search here.....">
                </form>
            </div>
        </div>
        <!-- Search End -->

        <%@include file="components/common_js.jsp" %>
    </body>

</html>
