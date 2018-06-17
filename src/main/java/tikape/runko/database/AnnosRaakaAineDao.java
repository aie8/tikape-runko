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
public class AnnosRaakaAineDao  {
  
    private Database database;    
    
    public AnnosRaakaAineDao(Database database) {
        this.database = database;
    }

//    @Override
    public AnnosRaakaAine findOne(Integer rkey, Integer akey) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM AnnosRaakaAine WHERE raaka_aine_id = (?) and annos_id =(?)");
        stmt.setObject(1, rkey);
        stmt.setObject(2,akey);

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
        
        PreparedStatement stmt1 = connection.prepareStatement("SELECT nimi FROM RaakaAine WHERE id = ?");
        stmt1.setObject(1, RaakaAineId);

        ResultSet rs1 = stmt1.executeQuery();
        boolean hasOne1 = rs1.next();
        if (!hasOne1) {
            return null;
        }
        String raaka_aine_nimi= rs1.getString("nimi");
       AnnosRaakaAine o=new AnnosRaakaAine(AnnosId, RaakaAineId,  jarjestys,
            maara, ohje, raaka_aine_nimi);

       rs1.close();
        stmt1.close();
               rs.close();
        stmt.close();
        connection.close();

        return o;
    }

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
        
                PreparedStatement stmt1 = connection.prepareStatement("SELECT nimi FROM RaakaAine WHERE id = ?");
        stmt1.setObject(1, RaakaAineId);
               ResultSet rs1 = stmt1.executeQuery();
        boolean hasOne1 = rs1.next();
        if (!hasOne1) {
            return null;
        }
                String raaka_aine_nimi= rs1.getString("nimi");


       rs1.close();
        stmt1.close();
         annosRaakaAineet.add(new AnnosRaakaAine(AnnosId, RaakaAineId,  jarjestys,
            maara, ohje, raaka_aine_nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return annosRaakaAineet;
    }
    public List<AnnosRaakaAine> findAllInAnnos(int key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM AnnosRaakaAine where annos_id=(?) ORDER BY jarjestys");
        stmt.setInt(1,key );
        ResultSet rs = stmt.executeQuery();
        List<AnnosRaakaAine> annosRaakaAineet = new ArrayList<>();
        while (rs.next()) {
        int RaakaAineId=rs.getInt("raaka_aine_id");
        int AnnosId=rs.getInt("annos_id");
        int jarjestys=rs.getInt("jarjestys");
        int maara=rs.getInt("maara");
        String ohje=rs.getString("ohje");
                PreparedStatement stmt1 = connection.prepareStatement("SELECT nimi FROM RaakaAine WHERE id = ?");
        stmt1.setObject(1, RaakaAineId);
               ResultSet rs1 = stmt1.executeQuery();
        boolean hasOne1 = rs1.next();
        if (!hasOne1) {
            return null;
        }
                String raaka_aine_nimi= rs1.getString("nimi");


       rs1.close();
        stmt1.close();
         annosRaakaAineet.add(new AnnosRaakaAine(AnnosId, RaakaAineId,  jarjestys,
            maara, ohje, raaka_aine_nimi));
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

    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
        public Integer getNumber(Integer rkey) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) as lkm FROM AnnosRaakaAine WHERE raaka_aine_id = (?)");
        stmt.setObject(1, rkey);
        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }


        Integer retval= new Integer(rs.getInt("lkm"));
   
       
               rs.close();
        stmt.close();
        connection.close();

        return retval;
    }
    
}

