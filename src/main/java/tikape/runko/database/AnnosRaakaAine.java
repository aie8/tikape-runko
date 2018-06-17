/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.database;

import java.util.ArrayList;
import tikape.runko.domain.RaakaAine;
import tikape.runko.domain.Annos;
/**
 *
 * @author antti
 */
public class AnnosRaakaAine {
   
    private int RaakaAineId;
    private int AnnosId;
    private int jarjestys;
    private int maara;
    private String ohje;
    String RaakaAineNimi;

    public AnnosRaakaAine(int annos_id, int raaka_aine_id, int jarjestys,
            int maara, String ohje,String raakaAineNimi) {
        this.RaakaAineId = raaka_aine_id;
        this.AnnosId=annos_id;
        this.jarjestys=jarjestys;
        this.maara=maara;
        this.ohje=ohje;
        this.RaakaAineNimi=raakaAineNimi;
    }
    
    
    public Integer getRaakaAineId() {
        return RaakaAineId;
    }
    
    public int getAnnosId(){
        return AnnosId;
    }

 //   public void setId(Integer id) {
  //      this.id = id;
  //  }

    public int getJarjestys() {
        return jarjestys;
    }
    
    public int getMaara() {
        return maara;
    }    
        public String getOhje() {
        return ohje;
    }
    
        public String getRaakaAineNimi(){
            return RaakaAineNimi;
        }

   // public void setNimi(String nimi) {
  //      this.nimi = nimi;
  //  }
  //  public ArrayList<Annos.reseptiRaakaAine> getreseptiRaakaAineet(){
   //     return this.RaakaAineLista;
 //   }
}


