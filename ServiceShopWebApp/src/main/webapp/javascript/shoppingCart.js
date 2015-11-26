$.soap({
    url: 'http://192.168.31.71:9763/services/ServiceShop/',
    namespaceURL: 'http://servicesshop.alma.fr'
});

$.soap({
  data:{client: sessionStorage.getItem("idClient")},
  method:'getShoppingCart',
  success:function(response) {
    var products = $(response.toJSON()["#document"]["ns:getShoppingCartResponse"]["ns:return"]).toArray();
    $.each(products, function (arrayIndice, productCart) {
      var quantity = productCart.quantity["_"];  
      
      $.soap({
        data:{id: productCart.productId["_"]},
        method:'getProduct',
        success:function(response) {
          var product = $(response.toJSON()["#document"]["ns:getProductResponse"]["ns:return"]).toArray();
          
          var totalPrice = 0.0;
          $.each(product[0], function(indice,value){
            if(indice.indexOf("product")>1 && indice.indexOf("productSpecified") < 1) {
               
               totalPrice += Math.round(parseFloat(quantity)*parseFloat(product[0][indice]["price"]["_"])*100)/100;
               $("#productTable").append(
                '<tr> \
                <td>'+product[0][indice]["name"]["_"] +'</td> \
                <td>'+quantity+'</td> \
                <td>'+product[0][indice]["price"]["_"] + ' ' + product[0][indice]["currency"]["_"] + '</td> \
                <td> \
                  <button class="btn btn-danger btn-md"> \
                        <span class="glyphicon glyphicon-minus"></span> Delete \
                    </button> \
                  </td> \
               </tr>'
             );
            }
          });

          $("#cartFoot").replaceWith(

            '<tfoot id ="cartFoot"> \
            <tr class="success"> \
          <td colspan="2">Total</td> \
          <td>'+totalPrice+' EUR</td> \
          <td> \
          <a href="purchasing.jsp">\
          <button class="btn btn-success btn-md" id="payingButton"> \
                <span class="glyphicon  glyphicon-ok"></span> Paying \
            </button> \
            </a> \
          </td> \
          </tr> \
          </tfoot>'

          );
             
        }
      })
    });
  }
});

