
if(sessionStorage.getItem("idClient") == null){
  sessionStorage.setItem("idClient",Math.floor((Math.random() * 100000) + 1));  
}

$.soap({
    url: 'http://192.168.1.72:9763/services/ServiceShop/',
    namespaceURL: 'http://servicesshop.alma.fr'
});

$.soap({
  data:{},
  method:'getProducts',
  success:function(response) {
    // Clear products
    $("#products").html(" ");

    // Populate products
    var products = response.toJSON()["#document"]["ns:getProductsResponse"]["ns:return"];
    $.each(products, function (id, product) {
      $("#products").append('<div class="col-sm-4"> \
      <div class="panel panel-primary product"> \
        <div class="panel-heading">' + product.product.name + '</div> \
        <div class="panel-body"><img src="http://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div> \
        <div class="panel-footer"> \
          <p>' + product.product.price + ' ' + product.product.currency + ' \
          <input type="number" class="form-control" name="quantity" value="1" min="0"/></p> \
          <button class="btn btn-success btn-md addition" id="' + product.ID["_"] + '"> \
            <span class="glyphicon glyphicon-plus"></span> add to your cart \
          </button> \
        </div> \
      </div> \
    </div>');
    });

    // Add Action
    $('.addition').click(function(product) {
      $.soap({
        data:{
          client: sessionStorage.getItem("idClient"),
          idProduct:product.target.id,
          quantity:$(".product #" + product.target.id).parent().find("input[name='quantity']")[0].value
        },
        method:'add',
        success:function (response) {
          console.log(response.toJSON()["#document"]["ns:addResponse"]["ns:return"]);
        }
      })
    });
  }
});


