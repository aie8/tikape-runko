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

public class AnnosDao implements Dao<Annos, Integer> {

    private Database database;

    public AnnosDao(Database database) {
        this.database = database;
    }
//
    @Override
    public Annos findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Annos WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");

        Annos o = new Annos(id, nimi);

       rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<Annos> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Annos");

        ResultSet rs = stmt.executeQuery();
        List<Annos> annokset = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");

            annokset.add(new Annos(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return annokset;
    }
    public List<RaakaAine> findReseptiRaakaAineet(Integer key) throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT id,nimi from RaakaAine inner join AnnosRaakaAine on RaakaAine.id=AnnosRaakaAine.raaka_aine_id inner WHERE AnnosRaakaAine.annos_id=(?)");
        stmt.setObject(1, key);
        ResultSet rs = stmt.executeQuery();
        List<RaakaAine> resepti=  new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");

            resepti.add(new RaakaAine(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return resepti;
    }
    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

}
