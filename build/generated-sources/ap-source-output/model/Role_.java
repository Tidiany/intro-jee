package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Utilisateur;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-01T12:36:30")
@StaticMetamodel(Role.class)
public class Role_ { 

    public static volatile SingularAttribute<Role, String> libelle;
    public static volatile SingularAttribute<Role, String> description;
    public static volatile SingularAttribute<Role, Integer> id;
    public static volatile ListAttribute<Role, Utilisateur> utilisateurList;

}