<%-- 
    Document   : blog
    Created on : Jun 26, 2023, 10:51:04 AM
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

        <!-- Breadcrumb Section Begin -->
        <section class="breadcrumb-blog set-bg" data-setbg="img/breadcrumb-bg.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <h2>Our Blog</h2>
                    </div>
                </div>
            </div>
        </section>
        <!-- Breadcrumb Section End -->

        <!-- Blog Section Begin -->
        <section class="blog spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 col-md-6 col-sm-6">
                        <div class="blog__item">
                            <div class="blog__item__pic set-bg" data-setbg="img/blog/blog-1.jpg"></div>
                            <div class="blog__item__text">
                                <span><img src="img/icon/calendar.png" alt=""> 16 February 2020</span>
                                <h5>What Curling Irons Are The Best Ones</h5>
                                <a href="#">Read More</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 col-sm-6">
                        <div class="blog__item">
                            <div class="blog__item__pic set-bg" data-setbg="img/blog/blog-2.jpg"></div>
                            <div class="blog__item__text">
                                <span><img src="img/icon/calendar.png" alt=""> 21 February 2020</span>
                                <h5>Eternity Bands Do Last Forever</h5>
                                <a href="#">Read More</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 col-sm-6">
                        <div class="blog__item">
                            <div class="blog__item__pic set-bg" data-setbg="img/blog/blog-3.jpg"></div>
                            <div class="blog__item__text">
                                <span><img src="img/icon/calendar.png" alt=""> 28 February 2020</span>
                                <h5>The Health Benefits Of Sunglasses</h5>
                                <a href="#">Read More</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 col-sm-6">
                        <div class="blog__item">
                            <div class="blog__item__pic set-bg" data-setbg="img/blog/blog-4.jpg"></div>
                            <div class="blog__item__text">
                                <span><img src="img/icon/calendar.png" alt=""> 16 February 2020</span>
                                <h5>Aiming For Higher The Mastopexy</h5>
                                <a href="#">Read More</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 col-sm-6">
                        <div class="blog__item">
                            <div class="blog__item__pic set-bg" data-setbg="img/blog/blog-5.jpg"></div>
                            <div class="blog__item__text">
                                <span><img src="img/icon/calendar.png" alt=""> 21 February 2020</span>
                                <h5>Wedding Rings A Gift For A Lifetime</h5>
                                <a href="#">Read More</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 col-sm-6">
                        <div class="blog__item">
                            <div class="blog__item__pic set-bg" data-setbg="img/blog/blog-6.jpg"></div>
                            <div class="blog__item__text">
                                <span><img src="img/icon/calendar.png" alt=""> 28 February 2020</span>
                                <h5>The Different Methods Of Hair Removal</h5>
                                <a href="#">Read More</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 col-sm-6">
                        <div class="blog__item">
                            <div class="blog__item__pic set-bg" data-setbg="img/blog/blog-7.jpg"></div>
                            <div class="blog__item__text">
                                <span><img src="img/icon/calendar.png" alt=""> 16 February 2020</span>
                                <h5>Hoop Earrings A Style From History</h5>
                                <a href="#">Read More</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 col-sm-6">
                        <div class="blog__item">
                            <div class="blog__item__pic set-bg" data-setbg="img/blog/blog-8.jpg"></div>
                            <div class="blog__item__text">
                                <span><img src="img/icon/calendar.png" alt=""> 21 February 2020</span>
                                <h5>Lasik Eye Surgery Are You Ready</h5>
                                <a href="#">Read More</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 col-sm-6">
                        <div class="blog__item">
                            <div class="blog__item__pic set-bg" data-setbg="img/blog/blog-9.jpg"></div>
                            <div class="blog__item__text">
                                <span><img src="img/icon/calendar.png" alt=""> 28 February 2020</span>
                                <h5>Lasik Eye Surgery Are You Ready</h5>
                                <a href="#">Read More</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Blog Section End -->

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
