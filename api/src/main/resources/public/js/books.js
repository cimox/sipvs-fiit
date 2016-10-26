/**
 * Created by cimo on 10/25/2016.
 */

// var datePickers = document.getElementsByClassName('datepicker');
// Array.prototype.forEach.call(datePickers, function (datepicker) {
//     new Pikaday({
//         field: datepicker,
//         format: 'YYYY-MM-DD'
//     });
// });

    // Get the modal
var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];


// When the user clicks on <span> (x), close the modal
span.onclick = function () {
    modal.style.display = "none";
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};

showSignResult = function (message, removeBtn) {
    if (removeBtn == true) $('a.download-signed').hide();
    else $('a.download-signed').show();
    modal.style.display = "block";
    $('p.sign-result').text(message);
};

// add book button
$('a.add-book').on('click', function () {
    if ($('div.book-details').length < 10) {
        $('div.book-details').last().clone().appendTo($('form'));
    }
    else {
        alert('You reached maximum number of books');
    }
});
// remove book button
$('form').on('click', 'a.button.remove-book', function () {
    if ($('div.container.book-details').length > 1) {
        $(this).parent().parent().parent().remove();
    }
});

readFormData = function () {
    formData = {};
    // read personal infomarion
    formData.firstName = $('input#firstName').val();
    formData.lastName = $('input#lastName').val();
    formData.email = $('input#email').val();
    formData.notification = $('input#notification').val();
//         read address
    formData.address = {};
    formData.address.street = $('input#street').val();
    formData.address.streetNumber = $('input#streetNumber').val();
    formData.address.city = $('input#city').val();
    formData.address.postalCode = $('input#postalCode').val();
    formData.address.country = $('input#country').val();

    readBookDetails = function () {
        books = [];
        $('.book-details').each(function () {
            book = {};
            book.bookTitle = $('#bookTitle', this).val();
            book.dateFrom = $('#dateFrom', this).val();
            book.dateTo = $('#dateTo', this).val();
            books.push(book);
        });
        return books;
    };
    formData.books = readBookDetails();

    return formData;
};
createXML = function (fn) {
    console.log('creating xml');
    var formData = readFormData();

    $.ajax({
        type: "POST",
        url: "/create",
        dataType: "json",
        data: JSON.stringify(formData),
        success: function () {
            if (fn != undefined) fn();
            console.log('XML file created');
        }
    });
};

// create XML
$('form').on('click', 'a.button.create-xml', function () {
    var result = createXML();

    $(this).removeClass('button-primary');
    enableButtons = function () {
        $('form a.button.save-xml').addClass('button-primary');
        $('form a.button.save-html').addClass('button-primary');
        $('form a.button.validate-xml').addClass('button-primary');
        $('form a.button.sign').addClass('button-primary');
    };
    setTimeout(enableButtons, 1000);
});

// save XML
$('form').on('click', 'a.button.save-xml', function (e) {
    console.log('saving xml...');
    _saveXML = function () {
        window.location.href('/xml');
    };
    createXML(_saveXML);
});

// validate XML
$('form').on('click', 'a.button.validate-xml', function (e) {
    console.log('validating XML');

    _validate = function () {
        $.get('/validate', function (validationResult) {
            showSignResult(validationResult, true);
        });
    };

    createXML(_validate);
});

// save HTML
$('form').on('click', 'a.button.save-html', function (e) {
    console.log('saving HTML...');
    _saveHTML = function () {
        window.location.href('/html');
    };
    createXML(_saveHTML);
});


// sign document
$('form').on('click', 'a.button.sign', function (e) {
    //TODO: remove setTimeout, it's shitty solution
    console.log('signing document...');
    _sign = function () {
        var xmlContent, xsdContent, xslContent, xsdURI, xsdNSURI, xslURI;
        $.get('/xml-content', function (data) {
            xmlContent = data;
            console.log('xml content: ' + data);

            var oXML = new ActiveXObject("DSig.XadesSigAtl");
            var oXMLPlugin = new ActiveXObject("DSig.XmlPluginAtl");
            console.log('oXML loaded');
            loadXSD();
            loadXSL();

            setTimeout(function () {
                console.log('2s...');
                doSign(oXML, oXMLPlugin, xmlContent, xsdContent, xsdURI, xsdNSURI, xslContent, xslURI);
            }, 1500);
            // setTimeout(doSign(oXML, oXMLPlugin, xmlContent, xsdContent, xsdURI, xsdNSURI, xslContent, xslURI), 2000);
        });

        loadXSD = function () {
            $.get('/xsd-content', function (data) {
                xsdContent = data;
                // console.log('xsd content: ' + xsdContent);

                xsdURI = 'http://localhost:4567/data/schema.xsd';
                xsdNSURI = 'http://some.uri.org';
                console.log('XSD loaded');
            });
        };
        loadXSL = function () {
            $.get('/xsl-content', function (data) {
                xslContent = data;
                // console.log('xsd content: ' + xsdContent);

                xslURI = 'http://localhost:4567/data/transform.xslt';
                console.log('XSL loaded');
            });
        };

        doSign = function (oXML, oXMLPlugin, xml, xsd, xsdURI, xsdNSURI, xsl, XSLURI) {
            console.log('calling DSign');

            var obj = null, obj_new = null;
            obj = oXMLPlugin.CreateObject2('objectId', 'Book rental', xml, xsd, xsdNSURI, xsdURI, xsl, xslURI, 'HTML');
            obj_new = oXMLPlugin.CreateObject2('objectId', 'Book rental 2', xml, xsd, xsdNSURI, xsdURI, xsl, xslURI, 'HTML');

            if (obj == null) {
                showSignResult(oXMLPlugin.ErrorMessage + '\n' + oXML.ErrorMessagee, true);
                return;
            }

            var addObj = oXML.AddObject(obj);
            addObj = oXML.AddObject(obj_new);
            if (addObj != 0) {
                showSignResult(oXMLPlugin.ErrorMessage + '\n' + oXML.ErrorMessage, true);
                return;
            }

            var res = oXML.Sign('signatureId10', 'sha256', 'urn:oid:1.3.158.36061701.1.2.1');
            if (res == 0) {
                $.ajax({
                    type: "POST",
                    url: "/save-signed",
                    dataType: "json",
                    data: oXML.SignedXMLWithEnvelope,
                    success: function () {
                        console.log('signed XML saved');
                    }
                });
                showSignResult("Success", false);
            }
            else {
                showSignResult(oXMLPlugin.ErrorMessage + '\n' + oXML.ErrorMessagee, true);
            }
        };
    };
    createXML(_sign);
});