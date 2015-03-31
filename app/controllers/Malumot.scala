package controllers





import play.api.mvc._
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import models._
import play.api.Logger

import scala.slick.lifted.TableQuery


class Malumot  extends Controller{

    val malumotlar = TableQuery[UserTable]

    def list = DBAction { implicit rs =>
        Logger.info(s"SHOW_ALL = ${malumotlar.list}")
        Ok(views.html.list(malumotlar.list))
    }


    def showAddForm = Action {
        Ok(views.html.registr())
    }

    def registr = DBAction { implicit i =>
        val set = i.body.asFormUrlEncoded
        val name = set.get("name")(0)
        val surname = set.get("surname")(0)
        val email = set.get("email")(0)
        val login = set.get("login")(0)
        val parol = set.get("parol")(0)

        println("Name: " + name)
        println("Name: " + name)
        val UserId = (malumotlar returning malumotlar.map(_.id)) += Chat(None, name, surname, email,login,parol)
        Redirect(routes.Malumot.list())
    }


    def remove(id: Int) = DBAction { implicit request =>
        malumotlar.filter(_.id === id).delete;
        Redirect(routes.Malumot.list())
    }
    def profil=Action{
        Ok(views.html.profil("Eldor"))
    }
}
