/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.domain;

import java.util.ArrayList;
/**
 *
 * @author antti
 */
public class Annos {
    private Integer id;
    private String nimi; 
    private ArrayList<reseptiRaakaAine> RaakaAineLista;
    public Annos(Integer id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }
private class reseptiRaakaAine {
    private RaakaAine RaakaAine;
    private int jarjestys;
    private int maara;
    private String ohje;

    public reseptiRaakaAine(RaakaAine RaakaAine, int jarjestys,
            int maara, String ohje) {
        this.RaakaAine = RaakaAine;
        this.jarjestys=jarjestys;
        this.maara=maara;
        this.ohje=ohje;
    }
}

    public reseptiRaakaAine addRaakaAine(RaakaAine raakaAine, int jarjestys,int maara, String ohje){
        reseptiRaakaAine RA=new reseptiRaakaAine(raakaAine,jarjestys,
            maara, ohje);
        RaakaAineLista.add(RA);
        return RA;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }
    

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    public ArrayList<reseptiRaakaAine> getreseptiRaakaAineet(){
        return this.RaakaAineLista;
    }
}

