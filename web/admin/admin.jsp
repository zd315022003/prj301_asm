<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Product Admin - Dashboard HTML Template</title>
    <%@include file="admin-component/admin-import.jsp"%>
</head>

<body id="reportsPage">
    <div class="" id="home">
        <%@include file="admin-component/header.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col">
                    <p class="text-white mt-5 mb-5">Welcome back, <b>Admin</b></p>
                </div>
            </div>
            <!-- row -->
            <div class="row tm-content-row">
                <div class="col-sm-12 col-md-12 col-lg-8 col-xl-8 tm-block-col">
                    <div class="tm-bg-primary-dark tm-block">
                        <h2 class="tm-block-title">Sold count by Branding</h2>
                        <canvas id="barChart"></canvas>
                    </div>
                </div>
                <div class="col-sm-12 col-md-12 col-lg-8 col-xl-8 tm-block-col">
                    <div class="tm-bg-primary-dark tm-block tm-block-taller">
                        <h2 class="tm-block-title">Storage Information</h2>
                        <div id="pieChartContainer">
                            <canvas id="pieChart" class="chartjs-render-monitor" width="200" height="200"></canvas>
                        </div>
                    </div>
                </div>
                <div class="col-12 tm-block-col">
                    <div class="tm-bg-primary-dark tm-block tm-block-taller tm-block-scroll">
                        <h2 class="tm-block-title">Orders List</h2>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">ORDER NO.</th>
                                    <th scope="col">OPERATORS</th>
                                    <th scope="col">LOCATION</th>
                                    <th scope="col">PHONE NUMBER</th>
                                    <th scope="col">EMAIL</th>
                                    <th scope="col">ORDER DATE</th>
                                </tr>
                            </thead>
                            <tbody>
                            <jsp:useBean id="orders" scope="request" type="java.util.List<model.Orders>"/>
                            <c:forEach items="${orders}" var="order">
                                <tr>
                                    <th scope="row"><b>#${order.order_id}</b></th>
                                    <td><b>${order.first_name} ${order.last_name}</b></td>
                                    <td><b>${order.address}</b></td>
                                    <td><b>${order.phone_number}</b></td>
                                    <td><b>${order.email}</b></td>
                                    <td>${order.order_date}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <footer class="tm-footer row tm-mt-small">
            <div class="col-12 font-weight-light">
                <p class="text-center text-white mb-0 px-4 small">
                    Copyright &copy; <b>2018</b> All rights reserved.

                    Design: <a rel="nofollow noopener" href="https://templatemo.com" class="tm-footer-link">Template
                        Mo</a>
                </p>
            </div>
        </footer>
    </div>

    <script>

    </script>

    <script src="js/jquery3-3.3.1.min.js"></script>
    <!-- https://jquery.com/download/ -->
    <script src="js/moment.min.js"></script>
    <!-- https://momentjs.com/ -->
    <script src="js/Chart.min.js"></script>
    <!-- http://www.chartjs.org/docs/latest/ -->
    <script src="js/bootstrap3.min.js"></script>
    <!-- https://getbootstrap.com/ -->
    <!-- <script src="js/tooplate-scripts.js"></script> -->
    <script>
        Chart.defaults.global.defaultFontColor = 'white';
        let ctxLine,
            ctxBar,
            ctxPie,
            optionsLine,
            optionsBar,
            optionsPie,
            configLine,
            configBar,
            configPie,
            lineChart;
        barChart, pieChart;
        // DOM is ready
        $(function () {
            drawBarChart(); // Bar Chart
            drawPieChart(); // Pie Chart

            $(window).resize(function () {
                updateBarChart();
            });
        })




    </script>

    <!-- chart js -->
    <script>
        const width_threshold = 480;

        function drawBarChart() {
            if ($("#barChart").length) {
                ctxBar = document.getElementById("barChart").getContext("2d");

                optionsBar = {
                    responsive: true,
                    scales: {
                        yAxes: [
                            {
                                barPercentage: 0.2,
                                ticks: {
                                    beginAtZero: true
                                },
                                scaleLabel: {
                                    display: true,
                                    labelString: "Branding"
                                }
                            }
                        ]
                    }
                };

                optionsBar.maintainAspectRatio =
                    $(window).width() >= width_threshold;

                /**
                 * COLOR CODES
                 * Red: #F7604D
                 * Aqua: #4ED6B8
                 * Green: #A8D582
                 * Yellow: #D7D768
                 * Purple: #9D66CC
                 * Orange: #DB9C3F
                 * Blue: #3889FC
                 */

                configBar = {
                    type: "horizontalBar",
                    data: {
                        labels: [
                            <c:forEach items="${brandNames}" var="brandName">
                                "<c:out value="${brandName}"/>",
                            </c:forEach>
                        ],
                        datasets: [
                            {
                                label: "Sold count of branding",
                                data: [
                                    <c:forEach items="${brandCounts}" var="brandHit">
                                        <c:out value="${brandHit}"/>,
                                    </c:forEach>
                                ],
                                backgroundColor: [
                                    <c:forEach items="${brandCounts}" var="brandHit">
                                        "#3889FC",
                                    </c:forEach>
                                ],
                                borderWidth: 0
                            }
                        ]
                    },
                    options: optionsBar
                };

                barChart = new Chart(ctxBar, configBar);
            }
        }

        function drawPieChart() {
            <jsp:useBean id="productInfo" scope="request" type="dto.ProductInfoDTO"/>
            if ($("#pieChart").length) {
                var chartHeight = 300;

                $("#pieChartContainer").css("height", chartHeight + "px");

                ctxPie = document.getElementById("pieChart").getContext("2d");

                optionsPie = {
                    responsive: true,
                    maintainAspectRatio: false,
                    layout: {
                        padding: {
                            left: 10,
                            right: 10,
                            top: 10,
                            bottom: 10
                        }
                    },
                    legend: {
                        position: "top"
                    }
                };

                configPie = {
                    type: "pie",
                    data: {
                        datasets: [
                            {
                                data: [${productInfo.soldCount}, ${productInfo.remainingCount},],
                                backgroundColor: ["#F7604D", "#4ED6B8", "#A8D582", "#FFFF00"],
                                label: "Storage"
                            }
                        ],
                        labels: [
                            "Sold count (${productInfo.soldCount})",
                            "Remaining count (${productInfo.remainingCount})",

                        ]
                    },
                    options: optionsPie
                };

                pieChart = new Chart(ctxPie, configPie);
            }
        }

        function updateBarChart() {
            if (barChart) {
                barChart.options = optionsBar;
                barChart.update();
            }
        }

    </script>
</body>

</html>