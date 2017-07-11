import java.time.LocalDateTime;
import java.util.List;
import org.sql2o.*;

enum Gender {
  M, F
}

enum Type {
  CAT, DOG
}

public class Animal {
  private int id;
  private String name;
  private Gender gender;
  private Type animal_type;
  private LocalDateTime admitted;
  private String breed;

  public Animal(String name, Gender gender, Type animal_type, String breed){
    this.name = name;
    this.gender = gender;
    this.animal_type = animal_type;
    this.breed = breed;
    this.admitted = LocalDateTime.now();
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
             this.getBreed() == newAnimal.getBreed();
    }
  } //end of equals

  public static List<Animal> all() {
     String sql = "SELECT id, name, gender, breed FROM animals";
     try(Connection con = DB.sql2o.open()) {
       return con.createQuery(sql).executeAndFetch(Animal.class);
     }
   }

  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO animals (name, gender, breed) VALUES (:name, :gender, :breed);";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("gender", this.gender)
      .addParameter("breed", this.breed)
      .executeUpdate()
      .getKey();
  }
}
}
