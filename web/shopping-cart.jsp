<%-- 
    Document   : shopping-cart
    Created on : Jun 26, 2023, 11:02:08 AM
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
<section class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__text">
                    <h4>Shopping Cart</h4>
                    <div class="breadcrumb__links">
                        <a href="home">Home</a>
                        <a href="./shop.jsp">Shop</a>
                        <span>Shopping Cart</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Breadcrumb Section End -->

<!-- Shopping Cart Section Begin -->
<section class="shopping-cart spad">
    <form action="checkout" method="GET">
        <div class="container">
            <div class="row">
                <div class="col-lg-8">
                    <div class="shopping__cart__table">
                        <table>
                            <thead>
                            <tr>
                                <th>Product</th>
                                <th>Quantity</th>
                                <th>Total</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${cartItems}" var="cartItem">
                                <tr>
                                    <td class="product__cart__item">
                                        <div class="product__cart__item__pic">
                                            <img src="img/product/${cartItem.productImage}" alt="${cartItem.productName}">
                                        </div>
                                        <div class="product__cart__item__text">
                                            <h6>${cartItem.productName}</h6>
                                            <h5>$${cartItem.productPrice}</h5>
                                        </div>
                                    </td>
                                    <td class="quantity__item">
                                        <div class="quantity">
                                            <div class="pro-qty-2">
                                                <label>
                                                    <input name="quantity"
                                                           type="text"
                                                           value="${cartItem.quantity}"
                                                           onchange="changeTotal(${cartItem.productID}, ${cartItem.productPrice}, this)"/>
                                                </label>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="cart__price">$ ${Math.round(cartItem.productPrice * cartItem.quantity * 100) / 100}</td>
                                    <td class="cart__close"><label for="remove-${cartItem.productID}">
                                        <i class="fa fa-close"></i>
                                        <input id="remove-${cartItem.productID}"
                                               name="productID"
                                               value="${cartItem.productID}"
                                               type="submit" style="display: none"
                                               formaction="shopping-cart"
                                               formmethod="POST">
                                    </label>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <div class="continue__btn">
                                <a href="shop">Continue Shopping</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="cart__total">
                        <h6>Cart total</h6>
                        <ul>
                            <li>Total <span>$ 169.50</span></li>
                        </ul>
                        <%--TODO: add action checkout--%>
                        <a href="./checkout.jsp" class="primary-btn">Proceed to checkout</a>
                    </div>
                </div>
            </div>
        </div>
    </form>
</section>
<!-- Shopping Cart Section End -->

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
<script>
    function calcSum() {
        let sum = 0;
        $('.cart__price').each(function () {
            sum += parseFloat($(this).text().replaceAll('$ ', ''));
        });
        $('.cart__total ul li:last-child span').text('$ ' + sum);
    }

    function changeTotal(productId, productPrice, quantityInp) {
        console.log("changeTotal");
        $(quantityInp).closest('tr').find('.cart__price').text(productPrice * quantityInp.val());
        // change value in cookie cart= {productId_quantity}_{productId_quantity}_...
        let cartValue = $.cookie('cart');
        let regex = new RegExp('\\{' + productId + '_\\d+\\}');
        cartValue = cartValue.replace(regex, '{' + productId + '_' + quantityInp.val() + '}');
        $.cookie('cart', cartValue, {expires: 7, path: '/'});
        calcSum();
    }

    calcSum();
</script>
</body>

</html>
