/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.domain;

/**
 *
 * @author antti
 */
public class AnnosRaakaAine {
    private RaakaAine RaakaAine;
    private Annos Annos; 
    private int jarjestys;
    private int maara;
    private String ohje;

    public AnnosRaakaAine(RaakaAine RaakaAine,Annos Annos, int jarjestys,
            int maara, String ohje) {
        this.RaakaAine = RaakaAine;
        this.Annos=Annos;
        this.jarjestys=jarjestys;
        this.maara=maara;
        this.ohje=ohje;
    }

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getNimi() {
//        return nimi;
//    }
//
//    public void setNimi(String nimi) {
//        this.nimi = nimi;
//    }

    
    
}
