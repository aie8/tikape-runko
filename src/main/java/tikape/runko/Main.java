package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.Database;
import tikape.runko.database.OpiskelijaDao;
import tikape.runko.database.AnnosDao;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:opiskelijat.db");
        database.init();

        AnnosDao annosDao = new AnnosDao(database);

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("viesti", "tervehdys");

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        get("/opiskelijat", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("annokset", annosDao.findAll());

            return new ModelAndView(map, "opiskelijat");
        }, new ThymeleafTemplateEngine());

        get("/annokset/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("annos", annosDao.findOne(Integer.parseInt(req.params("id"))));
  //          map.put("raaka_aineet", annosDao.findReseptiRaakaAineet(Integer.parseInt(req.params("id"))));
            return new ModelAndView(map, "opiskelija");
        }, new ThymeleafTemplateEngine());
    }
}
