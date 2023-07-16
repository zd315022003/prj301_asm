<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Accounts - Product Admin Template</title>
    <%@include file="admin-component/admin-import.jsp" %>
</head>

<body id="reportsPage">
<div class="" id="home">
    <%@include file="admin-component/header.jsp" %>
    <div class="container mt-5">
        <div class="row tm-content-row">
            <div class="col-12 tm-block-col">
                <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                    <h2 class="tm-block-title">List of Accounts</h2>
                    <label for="selected-id"><p class="text-white">Accounts</p></label>
                    <select id="selected-id" name="selected-id" class="custom-select"
                            onchange="changeAccount(this.value)">
                        <jsp:useBean id="accounts" scope="request" type="java.util.List<model.Account>"/>
                        <c:forEach items="${accounts}" var="account">
                            <jsp:useBean id="selected" scope="request" type="java.lang.Integer"/>
                            <option
                                ${selected == account.account_id? 'selected' : ''}
                                    value="${account.account_id}">${account.username}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <!-- row -->
        <c:forEach items="${accounts}" var="account">
            <form action="accounts" method="post">
                <div class="account-wrapper row tm-content-row"
                     id="account-${account.account_id}" ${selected == account.account_id? '' : 'style="display:none"'}>
                    <div class="tm-block-col tm-col-avatar">
                        <div class="tm-bg-primary-dark tm-block tm-block-avatar">
                            <h2 class="tm-block-title">Change Avatar</h2>
                            <div class="tm-avatar-container">
                                <img
                                        src="${account.image_url}"
                                        alt="Avatar"
                                        class="tm-avatar img-fluid mb-4"
                                />
                            </div>
                        </div>
                    </div>
                    <div class="tm-block-col tm-col-account-settings">
                        <div class="tm-bg-primary-dark tm-block tm-block-settings">
                            <h2 class="tm-block-title">Account Settings</h2>
                            <div class="form-group col-lg-6">
                                <label for="username">Username</label>
                                <input
                                        id="username"
                                        name="username"
                                        type="text"
                                        value="${account.username}"
                                        class="form-control validate"
                                    ${account.username.equals(sessionScope.account.getUsername())? 'disabled' : ''}
                                />
                            </div>
                            <div class="form-group col-lg-6">
                                <label for="first-name">First Name</label>
                                <input
                                        id="first-name"
                                        name="first-name"
                                        type="text"
                                        value="${account.first_name}"
                                        class="form-control validate"
                                />
                            </div>
                            <div class="form-group col-lg-6">
                                <label for="last-name">Last Name</label>
                                <input
                                        id="last-name"
                                        name="last-name"
                                        type="text"
                                        value="${account.last_name}"
                                        class="form-control validate"
                                />
                            </div>
                            <div class="form-group col-lg-6">
                                <label for="email">Account Email</label>
                                <input
                                        id="email"
                                        name="email"
                                        type="email"
                                        value="${account.email}"
                                        class="form-control validate"
                                />
                            </div>
                            <div class="form-group col-lg-6">
                                <label for="phone">Phone</label>
                                <input
                                        id="phone"
                                        name="phone"
                                        type="tel"
                                        value="${account.phone_number}"
                                        class="form-control validate"
                                />
                            </div>
                            <div class="form-group col-lg-6">
                                <label class="tm-hide-sm">&nbsp;</label>
                                <button
                                        type="submit"
                                        name="update"
                                        value="${account.account_id}"
                                        class="btn btn-primary btn-block text-uppercase"
                                >
                                    Update Your Profile
                                </button>
                            </div>
                            <div class="form-group col-12">
                                <button
                                        type="submit"
                                        name="make-admin"
                                        value="${account.account_id}"
                                        class="btn btn-primary btn-block text-uppercase"
                                    ${account.username.equals(sessionScope.account.getUsername())? 'disabled' : ''}
                                >
                                    <input type="hidden" name="role" value="${account.role_id}">
                                        ${account.role_id == 0? 'Make Admin' : 'Remove Admin'}
                                </button>
                            </div>
                            <div class="form-group col-12">
                                <button
                                        type="submit"
                                        name="disable-enable"
                                        value="${account.account_id}"
                                        class="btn btn-primary btn-block text-uppercase"
                                    ${account.username.equals(sessionScope.account.getUsername())? 'disabled' : ''}
                                >
                                    <input type="hidden" name="status" value="${account.status? 1 : 0}">
                                        ${account.status? 'Disable Account' : 'Enable Account'}
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </c:forEach>
    </div>
    <footer class="tm-footer row tm-mt-small">
        <div class="col-12 font-weight-light">
            <p class="text-center text-white mb-0 px-4 small">
                Copyright &copy; <b>2018</b> All rights reserved.

                Design: <a rel="nofollow noopener" href="https://templatemo.com" class="tm-footer-link">Template Mo</a>
            </p>
        </div>
    </footer>
</div>

<script src="js/jquery3-3.3.1.min.js"></script>
<!-- https://jquery.com/download/ -->
<script src="js/bootstrap3.min.js"></script>
<!-- https://getbootstrap.com/ -->
<script>
    function changeAccount(id) {
        $('.account-wrapper').hide();
        $('#account-' + id).show();
    }
</script>
</body>
</html>
