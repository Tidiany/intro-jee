<%@include file="jspf/header.jspf" %>
<h1 class="titre">
    List des utilisateurs!
    <span class="pull-right">
        <button id="showModalInscription" class="btn btn-sm btn-primary">
            Nouvelle utilisateur
        </button>
    </span>
</h1>
<span class="msg msg-success">${message}</span>
<!--Liste des utilisateurs-->
<table class="table table-striped table-hover">
    <tr>
        <th>Photo</th>
        <th>Login</th>
        <th>Prenom</th>
        <th>Nom</th>
        <th>Role</th>
        <th>Option</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><img src="image/${user.photo}" alt="Profil" class="img-thumbnail img-40"/> </td>
            <td>${user.login}</td>
            <td>${user.prenom}</td>
            <td>${user.nom}</td>
            <td>${user.role.libelle}</td>
            <td>
                <a href="#" id="editBtn${user.id}" onclick="edit(${user.id})" 
                   class="btn btn-sm btn-outline-warning" 
                   idUser="${user.id}" 
                   nom="${user.nom}" 
                   prenom="${user.prenom}" 
                   role="${user.role.id}" 
                   photo="${user.photo}">
                    Modifier
                </a>
                <a href="#" class="btn btn-sm btn-outline-danger" 
                   id="delUserBtn${user.id}" 
                   nom="${user.nom}" 
                   prenom="${user.prenom}" 
                   onclick="deleteUser(${user.id})">
                    Supprimer
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
<!--Liste des utilisateurs-->
<%@include file="jspf/footer.jspf" %>

<!--Modal Inscription....................................-->
<div class="modal fade" id="id-inscription" tabindex="-1" role="dialog" aria-labelledby="titrePopUp" aria-hidden="true"> 
    <div class="modal-dialog"> 
        <div class="modal-content"> 
            <!-- le titre de la popup --> 
            <div class="modal-header"> 
                <h4 class="modal-title" id="titrePopUp">Inscription </h4>
                <!-- lla croix de fermeture de la popup --> 
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> 
            </div> 

            <form method="post" action="" enctype="multipart/form-data">
                <!-- le contenu HTML de la popup --> 
                <div class="modal-body"> 
                    <div class="row">                            
                        <div class="col-sm-4">
                            <div id="photoCadre">
                                <img id="image" class="shadow-0-3" src="image/noprofil.png" alt="Profil" width="150px" height="150px"/>
                                <span id="message"></span>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <div class="form-group">            
                                <label for="prenom">Prenom</label>
                                <input class="form-control" type="text" name="prenom" id="prenom"/>
                                <input type="hidden" id="action" name="form" value="inscription">
                                <input type="hidden" id="idUser" name="idUser">
                            </div>
                            <div class="form-group">            
                                <label for="nom">Nom</label>
                                <input class="form-control" type="text" name="nom" id="nom"/>
                            </div>
                        </div>
                    </div>    
                    <div class="form-group">            
                        <label for="photo"></label>
                        <input class="form-control" type="file" name="photo" id="photo"/>
                    </div>
                    <div class="form-group">            
                        <label for="role"></label>
                        <select class="form-control" name="role" id="role">
                            <option value="0">Selectionner un role</option>
                            <c:forEach items="${roles}" var="role">
                                <option value="${role.id}">${role.libelle}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <!-- le pied de page de la popup --> 
                <div class="modal-footer"> 
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                    <button type="button" id="closeFormInscription" class="btn btn-danger">Fermer</button>
                </div> 
            </form>
        </div> 
    </div> 
</div>
<!--Modal Inscription....................................-->

<!--Modal Confirmation de Supression....................................-->
<div class="modal fade" id="id-confirm-supp" tabindex="-1" role="dialog" aria-labelledby="titrePopUp" aria-hidden="true"> 
    <div class="modal-dialog"> 
        <div class="modal-content"> 
            <!-- le titre de la popup --> 
            <div class="modal-header"> 
                <h4 class="modal-title" id="titrePopUp">Confirmation </h4>
                <!-- lla croix de fermeture de la popup --> 
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> 
            </div> 

            <form method="post" action="">
                <!-- le contenu HTML de la popup --> 
                <div class="modal-body"> 
                    <span class="msg">
                        Voulez vous vraiment supprimer l'utilisateur <span id="userName"></span>?
                    </span>
                    <input type="hidden" name="form" value="delete"/>
                    <input type="hidden" id="idUserDel" name="idUser"/>
                </div>

                <!-- le pied de page de la popup --> 
                <div class="modal-footer"> 
                    <button type="submit" id="validConfirmation" class="btn btn-primary">Oui</button>
                    <button type="button" id="closeConfirmation" class="btn btn-danger">Non</button>
                </div> 
            </form>
        </div> 
    </div> 
</div>
<!--Modal Confirmation de Supression....................................-->
