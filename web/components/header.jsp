<%-- 
    Document   : header
    Created on : Jul 7, 2023, 7:23:12 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Page Preloder -->
<!--<div id="preloder">
    <div class="loader"></div>
</div>-->

<!-- Offcanvas Menu Begin -->
<div class="offcanvas-menu-overlay"></div>
<div class="offcanvas-menu-wrapper">
    <div class="offcanvas__option">
        <div class="offcanvas__links">
            <a href="login">Sign in</a>
        </div>
    </div>
    <div class="offcanvas__nav__option">
        <a href="#" class="search-switch"><img src="img/icon/search.png" alt=""></a>
        <a href="./favorite.jsp"><img src="img/icon/heart.png" alt=""></a>
        <a href="./shopping-cart.jsp"><img src="img/icon/cart.png" alt=""> <span>0</span></a>
        <div class="price">$0.00</div>
    </div>
    <div id="mobile-menu-wrap"></div>
    <div class="offcanvas__text">
        <p>Free shipping, 30-day return or refund guarantee.</p>
    </div>
</div>
<!-- Offcanvas Menu End -->

<!-- Header Section Begin -->
<header class="header">
    <div class="header__top">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-7">
                    <div class="header__top__left">
                        <p>Free shipping, 30-day return or refund guarantee.</p>
                    </div>
                </div>
                <div class="col-lg-6 col-md-5">
                    <div class="header__top__right">
                        <c:if test="${sessionScope.account == null}">
                            <div class="header__top__links">
                                <a style="color: white" href="login">Sign in</a>
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.account != null}">
                            <div class="header__top__links dropdown" style="z-index: 2">
                                <a href="profile"><img style="max-height: 20px; max-width: 20px;" src="./img/icons8-account-30.png" alt=""></a>
                                <div class="dropdown-content" style="text-align: left">
                                    <c:if test="${sessionScope.account.roleId == 1}">
                                        <a  href="admin/home">Admin site</a>
                                    </c:if>
                                    <a  href="profile">Account</a>
                                    <a  href="logout">Logout</a>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-3">
                <div class="header__logo">
                    <a href="home"><img src="img/logo.png" alt=""></a>
                </div>
            </div>
            <div class="col-lg-6 col-md-6">
                <nav class="header__menu mobile-menu">
                    <ul>
                        <li class="active"><a href="home">Home</a></li>
                        <li><a href="shop">Shop</a></li>
                        <li><a href="#">Pages</a>
                            <ul class="dropdown">
                                <li><a href="./about.jsp">About Us</a></li>
                                <li><a href="./shop-details.jsp">Shop Details</a></li>
                                <li><a href="./shopping-cart.jsp">Shopping Cart</a></li>
                                <li><a href="./checkout.jsp">Check Out</a></li>
                                <li><a href="./blog-details.jsp">Blog Details</a></li>
                            </ul>
                        </li>
                        <li><a href="./blog.jsp">Blog</a></li>
                        <li><a href="./contact.jsp">Contacts</a></li>
                    </ul>
                </nav>
            </div>
            <div class="col-lg-3 col-md-3">
                <div class="header__nav__option">
                    <a href="#" class="search-switch"><img src="img/icon/search.png" alt=""></a>
                    <a href="./favorite.jsp"><img src="img/icon/heart.png" alt=""></a>
                    <a href="./shopping-cart.jsp"><img src="img/icon/cart.png" alt=""> <span>0</span></a>
                    <div class="price">$0.00</div>
                </div>
            </div>
        </div>
        <div class="canvas__open"><i class="fa fa-bars"></i></div>
    </div>
</header>
<!-- Header Section End -->
