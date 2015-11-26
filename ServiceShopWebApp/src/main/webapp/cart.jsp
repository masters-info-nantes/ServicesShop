<!DOCTYPE html>
<html lang="en">
<head>
  <title>ServiceShop</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>

<div class="jumbotron">
  <div class="container text-center">
    <h1>Service Shop</h1>      
    <p>More you pay, more we are happy !</p>
  </div>
</div>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">Service SHOP</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="index.jsp">Products</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li class="active"><a href="cart.jsp"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
      </ul>
    </div>
  </div>
</nav>
  <div class="container">
  <h3>Products available in your cart</h3>
  <p></p>            
  <table class="table">
    <thead>
      <tr>
        <th>Product name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody id="productTable">
    </tbody>
    <tfoot id ="cartFoot">
    </tfoot>
  </table>
</div>	
</body>
<footer>
<script type="text/javascript" src="bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="bower_components/jquery.soap/jquery.soap.js"></script>
<script type="text/javascript" src="bower_components/jquery-xml2json/src/xml2json.js"></script>
<script type="text/javascript" src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="javascript/shoppingCart.js"></script>
</html>