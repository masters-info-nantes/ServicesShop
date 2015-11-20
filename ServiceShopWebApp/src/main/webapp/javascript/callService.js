
$.soap({
    url: 'http://172.28.0.164:9763/services/ServiceShop/',
    namespaceURL: 'http://servicesshop.alma.fr'
});

$.soap({
  data:{},
  method:'getProducts',
  success:function(response) {
    var products = response.toJSON()["#document"]["ns:getProductsResponse"]["ns:return"]
    $.each(products, function (id, product) {
      $("#products").append('<div class="col-sm-4"> \
      <div class="panel panel-primary"> \
        <div class="panel-heading">' + product.product.name + '</div> \
        <div class="panel-body"><img src="http://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div> \
        <div class="panel-footer"> \
          <p>' + product.product.price + ' ' + product.product.currency + '</p> \
          <button class="btn btn-success btn-md addition"> \
            <span class="glyphicon glyphicon-plus"></span> add to your cart \
          </button> \
        </div> \
      </div> \
    </div>');
    });
  }
})



$('.addition').click(function() {

});
