import org.sql2o.*;
import java.util.List;

enum Animal_Pref { CAT, DOG; }

public class Customer {
  private String name;
  private String phone;
  private Animal_Pref animal_pref;
  private String breed_pref;
  private int id;

  //the constructor
  public Customer(String name, String phone, Animal_Pref animal_pref, String breed_pref){
    this.name = name;
    this.phone = phone;
    this.animal_pref = animal_pref;
    this.breed_pref = breed_pref;
  }

  public String getName(){
    return name;
  }

  public String getPhone(){
    return phone;
  }

  public String getBreed_pref(){
    return breed_pref;
  }

  public static List<Customer> all() {
     String sql = "SELECT id, name, phone, animal_pref, breed_pref FROM customers";
     try(Connection con = DB.sql2o.open()) {
       return con.createQuery(sql).executeAndFetch(Customer.class);
     }
   }
} // end of Customer class
