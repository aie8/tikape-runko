package tikape.runko.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

//    public Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(databaseAddress);
 //   }
public  Connection getConnection() throws Exception {
    String dbUrl = System.getenv("JDBC_DATABASE_URL");
    if (dbUrl != null && dbUrl.length() > 0) {
        return DriverManager.getConnection(dbUrl);
    }

    return DriverManager.getConnection("jdbc:sqlite:opiskelijat.db");
}
    public void init() {
        List<String> lauseet = sqliteLauseet();

        // "try with resources" sulkee resurssin automaattisesti lopuksi
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            // suoritetaan komennot
            for (String lause : lauseet) {
                System.out.println("Running command >> " + lause);
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
            System.out.println("Error >> " + t.getMessage());
        }
    }

    private List<String> sqliteLauseet() {
        ArrayList<String> lista = new ArrayList<>();

        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
                lista.add("CREATE TABLE RaakaAine (id integer PRIMARY KEY, nimi varchar(200))");
                lista.add("CREATE TABLE AnnosRaakaAine (id_integer PRIMARY KEY,Annos_id integer,raaka_aine_id integer,jarjestys integer, maara integer, ohje varchar(2000), FOREIGN KEY (annos_id) REFERENCES Annos(id),FOREIGN KEY (raaka_aine_id) REFERENCES RaakaAine(id))");
        lista.add("CREATE TABLE Annos (id integer PRIMARY KEY, nimi varchar(255));");

        lista.add("CREATE TABLE RaakaAine (id integer PRIMARY KEY, nimi varchar(200)");
  //      lista.add("INSERT INTO Opiskelija (nimi) VALUES ('Platon');");
   //     lista.add("INSERT INTO Opiskelija (nimi) VALUES ('Aristoteles');");
  //      lista.add("INSERT INTO Opiskelija (nimi) VALUES ('Homeros');");

        return lista;
    }
}
