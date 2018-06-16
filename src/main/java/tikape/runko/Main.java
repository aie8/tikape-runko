package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.Database;
import tikape.runko.database.OpiskelijaDao;
import tikape.runko.database.AnnosDao;
import tikape.runko.database.AnnosRaakaAine;
import tikape.runko.database.AnnosRaakaAineDao;
import tikape.runko.database.RaakaAineDao;
import tikape.runko.domain.Annos;
import tikape.runko.domain.RaakaAine;
public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:opiskelijat.db");
        database.init();

        RaakaAineDao raakaAineDao=new RaakaAineDao(database);
        AnnosDao annosDao = new AnnosDao(database);
        AnnosRaakaAineDao annosRaakaAineDao=new AnnosRaakaAineDao(database);
        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("viesti", "tervehdys");

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        get("/opiskelijat", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("annokset", annosDao.findAll());
            map.put("raaka_aineet", raakaAineDao.findAll());
            return new ModelAndView(map, "opiskelijat");
        }, new ThymeleafTemplateEngine());

        get("/annokset/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("annos", annosDao.findOne(Integer.parseInt(req.params("id"))));
            map.put("raaka_aineet", annosRaakaAineDao.findAllInAnnos(Integer.parseInt(req.params("id"))));
            return new ModelAndView(map, "opiskelija");
        }, new ThymeleafTemplateEngine());
        
        post("/opiskelijat", (req, res) -> {
            int annos_id = Integer.parseInt(req.queryParams("annos_valinta"));
           
            int raaka_aine_id=Integer.parseInt(req.queryParams("raakaaine_valinta"));
            int maara= Integer.parseInt(req.queryParams("maara"));
            int jarjestys=Integer.parseInt(req.queryParams("jarjestys"));
            String ohje=req.queryParams("ohje");
            annosRaakaAineDao.saveOrUpdate(new AnnosRaakaAine(annos_id, raaka_aine_id, jarjestys, maara, ohje));
            

               res.redirect("/opiskelijat");
    return "";
        });
        
                post("/uusi_annos", (req, res) -> {
             String uusi_annos=req.queryParams("uusi_annos");
            annosDao.saveOrUpdate(new Annos(1,uusi_annos));
                        res.redirect("/opiskelijat");
    return "";
        });
                        post("/uusi_aine", (req, res) -> {
             String uusi_aine=req.queryParams("uusi_aine");
            raakaAineDao.saveOrUpdate(new RaakaAine(1,uusi_aine));
                        res.redirect("/opiskelijat");
    return "";
        });
        
        
    }
}
