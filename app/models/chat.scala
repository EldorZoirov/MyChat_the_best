package models

import play.api.db.slick.Config.driver.simple._

case class Chat(id: Option[Int],
                name: String,
                surname: String,
                email: String,
                 login: String,
                 parol: String)

class UserTable(tag: Tag) extends Table[Chat](tag, "Users") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def name = column[String]("name", O.Default(""))

  def surname = column[String]("surname", O.Default(""))

  def email = column[String]("email", O.Default(""))
  def login = column[String]("login", O.Default(""))
  def parol = column[String]("parol", O.Default(""))

  def * = (id.?, name, surname, email,login,parol) <>(Chat.tupled, Chat.unapply _)

}


