$.soap({
    url: 'http://192.168.31.71:9763/services/ServiceShop/',
    namespaceURL: 'http://servicesshop.alma.fr'
});

$.soap({
  data:{client: sessionStorage.getItem("idClient")},
  method:'getShoppingCart',
  success:function(response) {
    var products = $(response.toJSON()["#document"]["ns:getShoppingCartResponse"]["ns:return"]).toArray();
    var totalPrice = 0.0;
    $.each(products, function (arrayIndice, productCart) {
      var quantity = productCart.quantity["_"];  
      var idProduct = productCart.productId["_"]
      
      $.soap({
        data:{id: idProduct},
        method:'getProduct',
        success:function(response) {
          var product = $(response.toJSON()["#document"]["ns:getProductResponse"]["ns:return"]).toArray();
          
          
          $.each(product[0], function(indice,value){
            if(indice.indexOf("product")>1 && indice.indexOf("productSpecified") < 1) {
               
               totalPrice += Math.round(parseFloat(quantity)*parseFloat(product[0][indice]["price"]["_"])*100)/100;
               $("#productTable").append(
                '<tr id="ligne-'+idProduct+'"> \
                <td>'+product[0][indice]["name"]["_"] +'</td> \
                <td> <span  id="quantity'+idProduct+'">'+quantity+'</span></td> \
                <td>'+product[0][indice]["price"]["_"] + ' ' + product[0][indice]["currency"]["_"] + '</td> \
                <td> \
                  <button class="btn btn-danger btn-md" id="'+idProduct+'" onclick="Delete(this.id,'+ product[0][indice]["price"]["_"]+')"> \
                        <span class="glyphicon glyphicon-minus"></span> Delete \
                    </button> \
                  </td> \
               </tr>'
             );
            }
          });
        }
      })
    });

  $(document).ajaxStop(function () {
       
        $("#cartFoot").replaceWith(

                '<tfoot id ="cartFoot"> \
                <tr class="success"> \
              <td colspan="2">Total</td> \
              <td><span id=\'TotalPrice\'>'+totalPrice+' EUR</span></td> \
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
  });
   
  } 
})

function Delete(id,price) {
  $.soap({
    data:{
      client: sessionStorage.getItem("idClient"),
      productId: id,
      quantity: 1
    },
    method:'remove',
    success: function(response) {

      var quantity = response.toJSON()["#document"]["ns:removeResponse"]["ns:return"];

      $("#quantity"+id).html(quantity);
      var newTotalPrice = parseFloat($("#TotalPrice").text().split(" ")[0])-parseFloat(price);
      
      if(newTotalPrice  > 0.0){
            $(document).ajaxStop(function () {
              if(parseInt(quantity) == 0){
                $("#ligne-"+id).remove();  
              }
                
              $("#cartFoot").replaceWith(

                        '<tfoot id="cartFoot"> \
                        <tr class="success"> \
                      <td colspan="2">Total</td> \
                      <td><span id=\'TotalPrice\'>'+Math.round(newTotalPrice*100)/100+' EUR</span></td> \
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
           });
        }else if(newTotalPrice  <= 0.0) {
           $(document).ajaxStop(function () {
              $("#productTable").replaceWith(

                    " <tbody id='productTable'></tbody>"
            );
              $("#cartFoot").replaceWith(

                "<tfoot id='cartFoot'></tfoot>"
            );
           });
          
        }
    }
  });
}