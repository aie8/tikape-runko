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

public class RaakaAineDao implements Dao<RaakaAine, Integer> {

    private Database database;

    public RaakaAineDao(Database database) {
        this.database = database;
    }
//
    @Override
    public RaakaAine findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM RaakaAine WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");

        RaakaAine o = new RaakaAine(id, nimi);

       rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<RaakaAine> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM RaakaAine");

        ResultSet rs = stmt.executeQuery();
        List<RaakaAine> raakaAineet = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");

            raakaAineet.add(new RaakaAine(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return raakaAineet;
    }
    
    public RaakaAine saveOrUpdate(RaakaAine object) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO RaakaAine (nimi) VALUES (?)");
            stmt.setString(1, object.getNimi());
            stmt.executeUpdate();
        }

        return null;
    }
    
    
    
//    public List<RaakaAine> findReseptiRaakaAineet(Integer key) throws SQLException {
//
//        Connection connection = database.getConnection();
//        PreparedStatement stmt = connection.prepareStatement("SELECT id,nimi from RaakaAine inner join AnnosRaakaAine on RaakaAine.id=AnnosRaakaAine.raaka_aine_id inner WHERE AnnosRaakaAine.annos_id=(?)");
//        stmt.setObject(1, key);
//        ResultSet rs = stmt.executeQuery();
//        List<RaakaAine> resepti=  new ArrayList<>();
//        while (rs.next()) {
//            Integer id = rs.getInt("id");
//            String nimi = rs.getString("nimi");
//
//            resepti.add(new RaakaAine(id, nimi));
//        }
//
//        rs.close();
//        stmt.close();
//        connection.close();
//
//        return resepti;
//    }
    @Override
    public void delete(Integer key) throws SQLException {
                try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM RaakaAine WHERE id=(?)");
            stmt.setInt(1, key);
            stmt.executeUpdate();
        }

        
    }

}
