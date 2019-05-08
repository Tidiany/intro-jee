package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Marque;
import model.Vehicule;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-01T12:36:30")
@StaticMetamodel(Modele.class)
public class Modele_ { 

    public static volatile SingularAttribute<Modele, Marque> idMarque;
    public static volatile SingularAttribute<Modele, String> libelle;
    public static volatile ListAttribute<Modele, Vehicule> vehiculeList;
    public static volatile SingularAttribute<Modele, Integer> id;

}