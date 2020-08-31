
let id = null;

function generateForm() {
    id = window.location.hash.substr(1);
    if (id.length > 0) {
        $.ajax({
            type: "GET",
            url: `http://localhost:9000/phone/${id}`,
            crossDomain: true,
            dataType: 'json',
        }).then(function (data) {
            createForm(data);
        });
    }
    else {
        createForm({name: "", number: ""})
    }
}

generateForm();

function createForm(data) {
    let result = '<form class="item" action="javascript:formSubmission(nameField.value, number.value)" onsubmit="return isValid(nameField.value, number.value)">';
    result = `${result}<label for="nameField"><b>Name:</b></label><br>`;
    result =`${result}<input type="text" id="nameField" name="nameField" class="inputField" value="${data.name}"><br>`;
    result = `${result}<label for="number"><b>Phone number:</b></label><br>`;
    result = `${result}<input type="number" id="number" name="number" class="inputField" value="${data.number}"><br><br>`;
    result = `${result}<input type="submit" class="EditButton" value="CREATE"></form>`;
    const decorateRes = document.createElement( "div");
    decorateRes.innerHTML = result;
    document.body.appendChild(decorateRes);
}

function isValid(name, number) {
    if (!(name.length > 0 && number.length > 0)) {
        alert("Please fill all fields");
        return false;
    }
    return true;
}

function formSubmission(name, number) {
    const url = (id.length > 0) ? `http://localhost:9000/phone/${id}` : "http://localhost:9000/phones/createNewPhone";

    $.ajax({
        type: "POST",
        url: url,
        crossDomain: true,
        dataType: 'json',
        data: `name=${name}&phoneNumber=${number}`
    });

    location.href=`main.html`;
}
