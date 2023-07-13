
<%-- 
    Document   : index
    Created on : Jun 26, 2023, 10:43:44 AM
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

        <!-- Hero Section Begin -->
        <section class="hero">
            <div class="hero__slider owl-carousel">
                <div class="hero__items set-bg" data-setbg="img/hero/hero-1.jpg">
                    <div class="container">
                        <div class="row">
                            <div class="col-xl-5 col-lg-7 col-md-8">
                                <div class="hero__text">
                                    <h6>Summer Collection</h6>
                                    <h2>Fall - Winter Collections 2030</h2>
                                    <p>A specialist label creating luxury essentials. Ethically crafted with an unwavering
                                        commitment to exceptional quality.</p>
                                    <a href="shop" class="primary-btn">Shop now <span class="arrow_right"></span></a>
                                    <div class="hero__social">
                                        <a href="https://www.facebook.com/profile.php?id=100085345008245" target="_blank"><i class="fa fa-facebook"></i></a>
                                        <a href="https://www.facebook.com/profile.php?id=100085345008245" target="_blank"><i class="fa fa-twitter"></i></a>
                                        <a href="https://www.pinterest.com/dungnd15022k3/" target="_blank"><i class="fa fa-pinterest"></i></a>
                                        <a href="https://www.instagram.com/dungnd15022k3/" target="_blank"><i class="fa fa-instagram"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="hero__items set-bg" data-setbg="img/hero/hero-2.jpg">
                    <div class="container">
                        <div class="row">
                            <div class="col-xl-5 col-lg-7 col-md-8">
                                <div class="hero__text">
                                    <h6>Summer Collection</h6>
                                    <h2>Fall - Winter Collections 2030</h2>
                                    <p>A specialist label creating luxury essentials. Ethically crafted with an unwavering
                                        commitment to exceptional quality.</p>
                                    <a href="shop" class="primary-btn">Shop now <span class="arrow_right"></span></a>
                                    <div class="hero__social">
                                        <a href="https://www.facebook.com/profile.php?id=100085345008245" target="_blank"><i class="fa fa-facebook"></i></a>
                                        <a href="https://www.facebook.com/profile.php?id=100085345008245" target="_blank"><i class="fa fa-twitter"></i></a>
                                        <a href="https://www.pinterest.com/dungnd15022k3/" target="_blank"><i class="fa fa-pinterest"></i></a>
                                        <a href="https://www.instagram.com/dungnd15022k3/" target="_blank"><i class="fa fa-instagram"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Hero Section End -->

        <!-- Banner Section Begin -->
        <section class="banner spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-7 offset-lg-4">
                        <div class="banner__item">
                            <div class="banner__item__pic">
                                <img src="img/banner/banner-1.jpg" alt="">
                            </div>
                            <div class="banner__item__text">
                                <h2>Clothing Collections 2030</h2>
                                <a href="shop">Shop now</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-5">
                        <div class="banner__item banner__item--middle">
                            <div class="banner__item__pic">
                                <img src="img/banner/banner-2.jpg" alt="">
                            </div>
                            <div class="banner__item__text">
                                <h2>Accessories</h2>
                                <a href="shop">Shop now</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-7">
                        <div class="banner__item banner__item--last">
                            <div class="banner__item__pic">
                                <img src="img/banner/banner-3.jpg" alt="">
                            </div>
                            <div class="banner__item__text">
                                <h2>Shoes Spring 2030</h2>
                                <a href="shop">Shop now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Banner Section End -->

        <!-- Product Section Begin -->
        <section class="product spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <ul class="filter__controls">
                            <li>Best Sellers</li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <c:forEach items="${listproduct}" var="product">
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
        <section class="product spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <ul class="filter__controls">
                            <li>New Arrivals</li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <c:forEach items="${newproduct}" var="product">
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
        <section class="product spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <ul class="filter__controls">
                            <li>Hot Sales</li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <c:forEach items="${saleproduct}" var="product">
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
        <!-- Product Section End -->

        <!-- Categories Section Begin -->
        <section class="categories spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3">
                        <div class="categories__text">
                            <h2>Clothings Hot <br /> <span>Shoe Collection</span> <br /> Accessories</h2>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="categories__hot__deal">
                            <img src="img/product-sale.png" alt="">
                            <div class="hot__deal__sticker">
                                <span>Sale Of</span>
                                <h5>$29.99</h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 offset-lg-1">
                        <div class="categories__deal__countdown">
                            <span>Deal Of The Week</span>
                            <h2>Multi-pocket Chest Bag Black</h2>
                            <div class="categories__deal__countdown__timer" id="countdown">
                                <div class="cd-item">
                                    <span>3</span>
                                    <p>Days</p>
                                </div>
                                <div class="cd-item">
                                    <span>1</span>
                                    <p>Hours</p>
                                </div>
                                <div class="cd-item">
                                    <span>50</span>
                                    <p>Minutes</p>
                                </div>
                                <div class="cd-item">
                                    <span>18</span>
                                    <p>Seconds</p>
                                </div>
                            </div>
                            <a href="shop" class="primary-btn">Shop now</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Categories Section End -->

        <!-- Instagram Section Begin -->
        <section class="instagram spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="instagram__pic">
                            <div class="instagram__pic__item set-bg" data-setbg="img/instagram/instagram-1.jpg"></div>
                            <div class="instagram__pic__item set-bg" data-setbg="img/instagram/instagram-2.jpg"></div>
                            <div class="instagram__pic__item set-bg" data-setbg="img/instagram/instagram-3.jpg"></div>
                            <div class="instagram__pic__item set-bg" data-setbg="img/instagram/instagram-4.jpg"></div>
                            <div class="instagram__pic__item set-bg" data-setbg="img/instagram/instagram-5.jpg"></div>
                            <div class="instagram__pic__item set-bg" data-setbg="img/instagram/instagram-6.jpg"></div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="instagram__text">
                            <h2>Instagram</h2>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
                                labore et dolore magna aliqua.</p>
                            <h3>#Male_Fashion</h3>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Instagram Section End -->

        <!-- Latest Blog Section Begin -->
        <section class="latest spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title">
                            <span>Latest News</span>
                            <h2>Fashion New Trends</h2>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-4 col-md-6 col-sm-6">
                        <div class="blog__item">
                            <div class="blog__item__pic set-bg" data-setbg="img/blog/blog-1.jpg"></div>
                            <div class="blog__item__text">
                                <span><img src="img/icon/calendar.png" alt=""> 16 February 2020</span>
                                <h5>What Curling Irons Are The Best Ones</h5>
                                <a href="blog-details.jsp">Read More</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 col-sm-6">
                        <div class="blog__item">
                            <div class="blog__item__pic set-bg" data-setbg="img/blog/blog-2.jpg"></div>
                            <div class="blog__item__text">
                                <span><img src="img/icon/calendar.png" alt=""> 21 February 2020</span>
                                <h5>Eternity Bands Do Last Forever</h5>
                                <a href="blog-details.jsp">Read More</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 col-sm-6">
                        <div class="blog__item">
                            <div class="blog__item__pic set-bg" data-setbg="img/blog/blog-3.jpg"></div>
                            <div class="blog__item__text">
                                <span><img src="img/icon/calendar.png" alt=""> 28 February 2020</span>
                                <h5>The Health Benefits Of Sunglasses</h5>
                                <a href="blog-details.jsp">Read More</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Latest Blog Section End -->

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
