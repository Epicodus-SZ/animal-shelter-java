import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.List;
import org.sql2o.*;

enum Gender { MALE, FEMALE; }
enum Animal_Type { CAT, DOG; }

public class Animal {
  private int id;
  private String name;
  private Gender gender;
  private Animal_Type animal_type;
  private LocalDateTime admit;
  private String breed;

  public Animal(String name, Gender gender, Animal_Type animal_type, String breed){
    this.name = name;
    this.gender = gender;
    this.animal_type = animal_type;
    this.breed = breed;
    this.admit = LocalDateTime.now();
  }

  public String getName(){
    return name;
  }

  public int getId(){
    return id;
  }

  public String getBreed(){
    return breed;
  }

  @Override
  public boolean equals(Object otherAnimal){
    if (!(otherAnimal instanceof Animal)) {
      return false;
    } else {
      Animal newAnimal = (Animal) otherAnimal;
      return this.getName().equals(newAnimal.getName()) &&
             this.getId() == newAnimal.getId() &&
             this.getBreed().equals(newAnimal.getBreed());
    }
  } //end of equals

  public static List<Animal> all() {
     String sql = "SELECT id, name, gender, animal_type, breed, admit FROM animals";
     try(Connection con = DB.sql2o.open()) {
       return con.createQuery(sql).executeAndFetch(Animal.class);
     }
   }

  public void save() {
    Timestamp timestamp = Timestamp.valueOf(this.admit); //need to convert LocalDateTime to Timestamp
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animals (name, gender, animal_type, breed, admit) VALUES (:name, CAST(:gender AS gender), CAST(:animal_type AS animal_type), :breed, :admit);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("gender", this.gender)
        .addParameter("animal_type", this.animal_type)
        .addParameter("breed", this.breed)
        .addParameter("admit", timestamp)
        .executeUpdate()
        .getKey();
    }
}
}
