package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Location;
import model.Modele;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-01T12:36:30")
@StaticMetamodel(Vehicule.class)
public class Vehicule_ { 

    public static volatile ListAttribute<Vehicule, Location> locationList;
    public static volatile SingularAttribute<Vehicule, String> matricule;
    public static volatile SingularAttribute<Vehicule, Double> prixJour;
    public static volatile SingularAttribute<Vehicule, String> couleur;
    public static volatile SingularAttribute<Vehicule, Integer> id;
    public static volatile SingularAttribute<Vehicule, Modele> idModele;

}