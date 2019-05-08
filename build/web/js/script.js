/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function imageIsLoaded(e) {
    //$("#file").css("color","green");
    //$('#image_preview').css("display", "block");
    $('#image').attr('src', e.target.result);
    $('#image').attr('width', '150px');
    $('#image').attr('height', '150px');
}
$('#photo').change(function () {
    //alert('ok');
    var assetsBaseDir = "image/";

    $("#message").empty(); // To remove the previous error message
    var file = this.files[0];

    var imagefile = file.type;
    //alert(imagefile);
    var match = ["image/jpeg", "image/png", "image/jpg"];
    if (!((imagefile == match[0]) || (imagefile == match[1]) || (imagefile == match[2]))) {
        //alert('no match');
        $('#image').attr('src', assetsBaseDir + 'noprofil.png');
        $('#image').css('color', 'red');
        $("#message").html("Selectionner une image valide, Note : Seules jpeg, jpg and png Images sont autorisé");
        return false;
    } else {
        //alert('match');
        var reader = new FileReader();
        reader.onload = imageIsLoaded;
        reader.readAsDataURL(this.files[0]);
    }
});
function initFormInscription() {
    $("#action").val("inscription");
    let src_image = "image/noprofil.png";
    $("#idUser").val("");
    $("#nom").val("");
    $("#prenom").val("");
    $("#role").val("0");
    $("#image").attr("src", src_image);
}
$("#showModalInscription").click(function () {
    initFormInscription();
    $("#id-inscription").modal("show");
});
$("#closeFormInscription").click(function () {
    initFormInscription();
    $("#id-inscription").modal("hide");
});
$("#closeConfirmation").click(function () {
    $("#id-confirm-supp").modal("hide");
});
function edit(id) {
    let elmt = $("#editBtn" + id);
    let idUser = elmt.attr("idUser");
    let nom = elmt.attr("nom");
    let prenom = elmt.attr("prenom");
    let photo = elmt.attr("photo");
    let roleId = elmt.attr("role");

    $("#action").val("edit");

    let src_image = "image/" + photo;
    $("#idUser").val(idUser);
    $("#nom").val(nom);
    $("#prenom").val(prenom);
    $("#role").val(roleId);
    $("#image").attr("src", src_image);
    $("#id-inscription").modal("show");
}
function deleteUser(id) {
    $("#idUserDel").val(id);
    let nom = $("#delUserBtn" + id).attr("nom");
    let prenom = $("#delUserBtn" + id).attr("prenom");
    $("#userName").html(prenom + " " + nom);
    $("#id-confirm-supp").modal("show");
}

$("#showNewLocationModal").click(function () {
//    alert("showing new location modal...");
    $("#newLocationModal").modal("show");
});
$("#marqueSelect").change(function () {
//    alert("Changed...");
//    alert("ok");
    $("#modele").empty();
    $("#modele").append("<option value=''>Selectionnez une modele</option>");
    let idMarque = $("#marqueSelect").val();
//    alert("idMarque " + idMarque);
    if (idMarque !== undefined && idMarque !== "") {
        $.ajax({
            type: 'GET',
            url: "user",
            dataType: 'text',
            data: {
                action: "getModelesByMarque",
                idMarque: idMarque
            },
            success: function (data, textStatus, jqXHR) {
                console.log("Result: ",data);
//                alert(data);
                $("#modele").append(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
//                alert(textStatus);
            }
        });
    }
});
$("#closeNewLocationModal").click(function () {
//    alert("closing...");
    $("#newLocationModal").modal("hide");
});


