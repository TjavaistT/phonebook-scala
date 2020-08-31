const params = location.search;
let url = "http://localhost:9000/phones";
if (params.length > 0)
    url += ("/searchBySubstr" + params);

$.ajax({
    type: "GET",
    url: url,
    crossDomain: true,
    dataType: 'json',
}).then(function (data) {
    let result = '<div class="item"><ol>';
    data.forEach(function (item) {
        result = `${result}<li><div><p><b>Name:</b> ${item.name}</p><p><b>Phone number: </b><a href=${"tel:"+ item.number}>${item.number}</a></p>`;
        result = `${result}<button class="DeleteButton" onclick="onClickDelete(this.id)" id=${item.id}><i class="fa fa-trash"></i></button >`;
        result = `${result}<button class="EditButton" id=${item.id} onclick=onClickEdit(this.id)><i class="fa fa-pencil-square-o"></i></button></div></li>`;
    });
    result += '</ol></div>';
    const decorateRes = document.createElement("div");
    decorateRes.innerHTML = result;
    document.body.appendChild(decorateRes);
});


function onClickDelete(id) {
    $.ajax({
        type: "DELETE",
        url: `http://localhost:9000/phone/${id}`,
        crossDomain: true,
        dataType: 'json',
        data: {'action': 'delete'},
    });
    location.reload();
}

function onClickEdit(id='') {
    location.href=`editForm.html#${id}`;
}