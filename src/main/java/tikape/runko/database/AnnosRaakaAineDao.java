/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Annos;
import tikape.runko.domain.RaakaAine;

/**
 *
 * @author antti
 */
public class AnnosRaakaAineDao implements Dao<AnnosRaakaAine, Integer> {
  
    private Database database;    
    
    public AnnosRaakaAineDao(Database database) {
        this.database = database;
    }

    @Override
    public AnnosRaakaAine findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM AnnosRaakaAine WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }


        int RaakaAineId=rs.getInt("raaka_aine_id");
        int AnnosId=rs.getInt("annos_id");
        int jarjestys=rs.getInt("jarjestys");
        int maara=rs.getInt("maara");
        String ohje=rs.getString("ohje");
        
        
       AnnosRaakaAine o=new AnnosRaakaAine(AnnosId, RaakaAineId,  jarjestys,
            maara, ohje);

       rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<AnnosRaakaAine> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM AnnosRaakaAine");

        ResultSet rs = stmt.executeQuery();
        List<AnnosRaakaAine> annosRaakaAineet = new ArrayList<>();
        while (rs.next()) {
        int RaakaAineId=rs.getInt("raaka_aine_id");
        int AnnosId=rs.getInt("annos_id");
        int jarjestys=rs.getInt("jarjestys");
        int maara=rs.getInt("maara");
        String ohje=rs.getString("ohje");

         annosRaakaAineet.add(new AnnosRaakaAine(AnnosId, RaakaAineId,  jarjestys,
            maara, ohje));
        }

        rs.close();
        stmt.close();
        connection.close();

        return annosRaakaAineet;
    }
    public List<AnnosRaakaAine> findAllInAnnos(int key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM AnnosRaakaAine where annos_id=(?)");
        stmt.setInt(1,key );
        ResultSet rs = stmt.executeQuery();
        List<AnnosRaakaAine> annosRaakaAineet = new ArrayList<>();
        while (rs.next()) {
        int RaakaAineId=rs.getInt("raaka_aine_id");
        int AnnosId=rs.getInt("annos_id");
        int jarjestys=rs.getInt("jarjestys");
        int maara=rs.getInt("maara");
        String ohje=rs.getString("ohje");

         annosRaakaAineet.add(new AnnosRaakaAine(AnnosId, RaakaAineId,  jarjestys,
            maara, ohje));
        }

        rs.close();
        stmt.close();
        connection.close();

        return annosRaakaAineet;
    }

  //  @Override
    public AnnosRaakaAine saveOrUpdate(AnnosRaakaAine object) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO AnnosRaakaAine (Annos_id ,raaka_aine_id ,jarjestys , maara , ohje) VALUES (?, ?, ?,?,?)");
            stmt.setInt(1, object.getAnnosId());
            stmt.setInt(2, object.getRaakaAineId());
            stmt.setInt(3,object.getJarjestys());
            stmt.setInt(4,object.getMaara());
            stmt.setString(5, object.getOhje());
            stmt.executeUpdate();
        }

        return null;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

