package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Client;
import model.Vehicule;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-01T12:36:30")
@StaticMetamodel(Location.class)
public class Location_ { 

    public static volatile SingularAttribute<Location, Date> date;
    public static volatile SingularAttribute<Location, Client> idClient;
    public static volatile SingularAttribute<Location, Vehicule> idVehicule;
    public static volatile SingularAttribute<Location, Double> montant;
    public static volatile SingularAttribute<Location, Integer> id;
    public static volatile SingularAttribute<Location, Integer> nbJour;
    public static volatile SingularAttribute<Location, String> remarque;

}