$(function () {

    $("div.contact").on("click", ".editContact", function (e) {
        stopLinkAndEvent(e);
        createEditForm(e.target.closest("div.contact"));
    });

    $("button.addContact").on("click", function (e) {
        stopLinkAndEvent(e);
        addContact();
    });

});

function stopLinkAndEvent(e) {
    e.preventDefault();
    e.stopImmediatePropagation();
}

function createEditForm(contact) {

    let field_names = $("div[data-type=field][data-name=name]", contact);

    $(field_names).each(function (indx, field_name){
        $(field_name).replaceWith(
            '<input type="text" class="col-4" name="' + $(field_name).data("name") + '" value="' + $.trim($(field_name).text()) + '"/>'
        );
    });

    var field_number = $("div[data-type=field][data-name=phone]", contact);

    $(field_number).each(function (indx, field_number)
    {
        $(field_number).replaceWith(
            '<input type="text" class="col-4" name="' + $(field_number).data("name") + '" value="' + $.trim($(field_number).text()) + '"/>'
        );
    });

    toEditContactForm(contact);

    replaceToSaveEditedButton(contact);
}


function toEditContactForm(contact) {
    var contactid = $(contact).data("contactid");
    $(contact).wrap('<form name="editContact" method="post" class="editContactForm col-12" action="/' + $.trim(contactid) + '" />');
}

function replaceToSaveEditedButton(contact) {
    $("a.editContact", contact).replaceWith('<button name="saveContact" type="submit" class="btn btn-primary"> Сохранить </button>');
}

function addContact() {
    $("#addRow .modal-title").html('Добавить контакт');
    $('#addRow #addForm').find(":input").val("");
    $("#addRow").modal();
}