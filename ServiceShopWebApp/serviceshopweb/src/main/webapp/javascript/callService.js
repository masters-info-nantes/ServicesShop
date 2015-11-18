


$('#addition').click(function() {

    var val1= $("#valueA").val();
    var val2= $("#valueB").val();

    $.soap({
        url: 'http://192.168.1.72:9763/services/AdditionClass/',
        namespaceURL: 'http://addition.soa.com'
    });
    $.soap({
        method: 'addition',
        data: {
            a: val1,
            b: val2
        },
        soap12: true,
        success: function (reponse) {
            console.log(reponse);
            console.log(reponse.toString());
        },
        error: function (reponse) {
            console.log('error');
            console.log(reponse);
            console.log(reponse.toString());
        }
    });
});