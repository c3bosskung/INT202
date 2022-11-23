<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Classic Model Online</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.6.1/font/bootstrap-icons.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <style>
        .div-link {
            cursor: pointer;
            border: 1px burlywood solid;
        }

        .div-link:hover {
            background-color: bisque;
        }

        .cart-info {
            margin-left: -1em;
            border-radius: 12px;
            background-color: bisque;
            position: absolute;
            z-index: 100;
            border: none;
            padding-left: 5px;
            padding-right: 5px;
            display: none;
        }
    </style>

    <script>
        function loadOffice(officeCode) {
            setLoading('on')
            const xhttp = new XMLHttpRequest()
            xhttp.onload = () => {
                setLoading('off')
                document.getElementById('body-content').innerHTML = xhttp.responseText
            }
            xhttp.open('GET', 'office-list?officeCode=' + officeCode)
            xhttp.send()
        }

        function loadProduct(page, pageSize = document.getElementById('itemsPage').value) {
            setLoading('on')
            const xhttp = new XMLHttpRequest()
            xhttp.onload = () => {
                setLoading('off')
                document.getElementById('body-content').innerHTML = xhttp.responseText;
            }
            xhttp.open("GET", "product-list?page=" + page + "&pageSize=" + pageSize)
            xhttp.send()
        }

        function setLoading(on_off) {
            let loading = document.getElementById("loading")
            if(on_off == 'on') {
                loading.classList.remove('d-none')
                loading.classList.add('d-inline')
            } else {
                loading.classList.remove('d-inline')
                loading.classList.add('d-none')
            }
        }

    </script>

</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand text-warning" href="javascript:void(0)">Classic Model</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="mynavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="javascript:loadOffice('')">Office</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="javascript:loadProduct(1,15)">Product</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="javascript:void(0)">Order History</a>
                </li>
                <li class="nav-item ml-4">
                    <a id="login-menu" class="nav-link text-light" href="javascript:showLoginForm()"><i
                            class="bi bi-box-arrow-in-right"></i> Login</a>
                </li>
            </ul>
            <div style="margin-right: 20px">
                <img src="assets/images/cart.png" width="42" onclick="viewCart()"/>
                <button id="noOfItemInCart" class="cart-info" onclick="viewCart()"></button>
            </div>
            <form class="d-flex">
                <input id="searchBox" class="form-control me-2" type="text" placeholder="Search">
                <button class="btn btn-primary" type="button" onclick="search(thisContent)">Search</button>
            </form>
        </div>
    </div>
</nav>
<div class="container" id="body-content">
    <jsp:include page="assets/home-info.html"/>
</div>
<div class="d-flex justify-content-center modal d-none" id="loading">
    <div class="spinner-border text-primary"
         style="margin-top: 10%; width: 6rem; height: 6rem;"></div>
</div>
</body>
</html>