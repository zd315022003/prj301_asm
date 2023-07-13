<%--
    Document   : checkout
    Created on : Jun 26, 2023, 10:52:11 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <%@include file="components/common_import.jsp" %>
    <link rel="stylesheet" href="css/style_2.css" type="text/css">
</head>

<body>
<%@include file="components/header.jsp" %>

<!-- Breadcrumb Section Begin -->
<c:if test="${empty orderId}">
<section class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__text">
                    <h4>Check Out</h4>
                    <div class="breadcrumb__links">
                        <a href="home">Home</a>
                        <a href="shop">Shop</a>
                        <span>Check Out</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Breadcrumb Section End -->

<!-- Checkout Section Begin -->
<section class="checkout spad">
    <div class="container">
        <div class="checkout__form">
            <form action="checkout" method="POST">
                <div class="row">
                    <div class="col-lg-8 col-md-6">
                        <h6 class="checkout__title">Billing Details</h6>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="checkout__input">
                                    <p>
                                        <label for="first-name">
                                            Fist Name<span>*</span>
                                        </label>
                                    </p>
                                    <input type="text" id="first-name" name="first-name" value="${profile.first_name}">
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="checkout__input">
                                    <p>
                                        <label for="last-name">
                                            Last Name<span>*</span>
                                        </label>
                                    </p>
                                    <input type="text" id="last-name" name="last-name" value="${profile.last_name}">
                                </div>
                            </div>
                        </div>

                        <div class="checkout__input">
                            <p>
                                <label for="address">
                                    Address<span>*</span>
                                </label>
                            </p>
                            <input type="text" id="address" name="address" placeholder="Departerment, Street,..."
                                   class="checkout__input__add">
                        </div>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="checkout__input">
                                    <p>
                                        <label for="phone-number">
                                            Phone<span>*</span>
                                        </label>
                                    </p>
                                    <input type="text" id="phone-number" name="phone-number"
                                           value="${profile.phoneNumber}">
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="checkout__input">
                                    <p>
                                        <label for="email">
                                            Email<span>*</span>
                                        </label>
                                    </p>
                                    <input type="text" id="email" name="email" value="${profile.email}">
                                </div>
                            </div>
                        </div>
                        <%--                                <div class="checkout__input__checkbox">--%>
                        <%--                                    <label for="diff-acc">--%>
                        <%--                                        Note about your order, e.g, special noe for delivery--%>
                        <%--                                        <input type="checkbox" id="diff-acc">--%>
                        <%--                                        <span class="checkmark"></span>--%>
                        <%--                                    </label>--%>
                        <%--                                </div>--%>
                        <div class="checkout__input">
                            <p>
                                <label for="note">
                                    Order notes<span>*</span>
                                </label>
                            </p>
                            <input type="text"
                                   id="note"
                                   name="note"
                                   placeholder="Notes about your order, e.g. special notes for delivery.">
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <div class="checkout__order">
                            <h4 class="order__title">Your order</h4>
                            <div class="checkout__order__products">Product <span>Total</span></div>
                            <ul class="checkout__total__products">
                                <c:forEach begin="0" end="${cartItems.size() - 1}" var="i">
                                    <c:set var="cartItem" value="${cartItems.get(i)}"/>
                                    <li>${i < 9 ? "0" : ""}${i + 1}. ${cartItem.productName}
                                        <span>$ ${cartItem.productPrice}</span></li>
                                </c:forEach>
                            </ul>
                            <ul class="checkout__total__all">
                                <li>Total <span>$${Math.round(total * 100) / 100}</span></li>
                            </ul>
                            <button type="submit" class="site-btn">PLACE ORDER</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>
<!-- Checkout Section End -->

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
</c:if>
<!-- Search End -->

<%@include file="components/common_js.jsp" %>
<c:if test="${not empty orderId}">
    <script defer="defer">
        alert("Add sucessfully");
        window.location.href = "home";
    </script>
</c:if>
</body>

</html>
