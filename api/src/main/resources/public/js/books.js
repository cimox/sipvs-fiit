/**
 * Created by cimo on 10/25/2016.
 */

var datePickers = document.getElementsByClassName('datepicker');
Array.prototype.forEach.call(datePickers, function (datepicker) {
    new Pikaday({
        field: datepicker,
        format: 'YYYY-MM-DD'
    });
});

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
createXML = function () {
    console.log('creating xml');
    var formData = readFormData();

    $.ajax({
        type: "POST",
        url: "/create",
        dataType: "json",
        data: JSON.stringify(formData),
        success: function () {
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
    };
    setTimeout(enableButtons, 1000);
});

// save XML
$('form').on('click', 'a.button.save-xml', function (e) {
    createXML();
    console.log('saving xml...' + $(this).attr('href'));
});

// validate XML
$('form').on('click', 'a.button.validate-xml', function (e) {
    createXML(); // siltently create XML on background before validation
    var result = false;
    $.get('/validate', function (validationResult) {
        alert(validationResult);
    });
});

// save HTML
$('form').on('click', 'a.button.save-html', function (e) {
    createXML();
    console.log('saving HTML...');
});

// sign document
$('form').on('click', 'a.button.sign', function (e) {
    createXML();
    console.log('signing document...');
});