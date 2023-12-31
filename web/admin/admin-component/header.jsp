<%-- 
    Document   : nav.jsp
    Created on : Jul 14, 2023, 6:04:29 PM
    Author     : Admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<c:set var="current" value="${pageContext.request.requestURI}"/>
<nav class="navbar navbar-expand-xl">
    <div class="container h-100">
        <a class="navbar-brand" href="admin.jsp">
            <h1 class="tm-site-title mb-0">Product Admin</h1>
        </a>
        <button
                class="navbar-toggler ml-auto mr-0"
                type="button"
                data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent"
                aria-expanded="false"
                aria-label="Toggle navigation"
        >
            <i class="fas fa-bars tm-nav-icon"></i>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mx-auto h-100">
                <li class="nav-item">
                    <a class="nav-link ${current.contains('admin.jsp')? 'active': ''}" href="home">
                        <i class="fas fa-tachometer-alt"></i> Dashboard
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${current.contains('products.jsp')? 'active': ''}" href="products">
                        <i class="fas fa-shopping-cart"></i> Products
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link ${current.contains('accounts.jsp')? 'active': ''}" href="accounts">
                        <i class="far fa-user"></i> Accounts
                    </a>
                </li>
                <li class="nav-item dropdown
${current.contains('category.jsp') || current.contains('branding.jsp')? 'active': ''}">
                    <a
                            class="nav-link dropdown-toggle"
                            href="#"
                            id="navbarDropdown"
                            role="button"
                            data-toggle="dropdown"
                            aria-haspopup="true"
                            aria-expanded="false">
                        <i class="fas fa-cog"></i>
                        <span> Properties <i class="fas fa-angle-down"></i> </span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="category">Category</a>
                        <a class="dropdown-item" href="branding">Branding</a>
                        <!-- <a class="dropdown-item" href="#">Customize</a> -->
                    </div>
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link d-block" href="../home">
                        Back Home</b>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
