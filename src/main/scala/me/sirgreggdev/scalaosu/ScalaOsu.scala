package me.sirgreggdev.scalaosu

import com.google.gson.Gson
import me.sirgreggdev.scalaosu.obj.{Beatmap, Replay, Score, User}

import scala.io.Source

class ScalaOsu(val key: String) {
  def getUser(username: String, mode: String = "0"): Option[User] = {
    val gson: Gson = new Gson

    try {
      Some[User](gson.fromJson(getJson("https://osu.ppy.sh/api/get_user?k=" + key + "&u=" + username + "&type=string&m=" + mode), classOf[Array[User]])(0))
    } catch {
      case _: Exception => None
    }
  }

  def getBeatmap(id: String, mode: String = "0"): Option[Beatmap] = {
    val gson: Gson = new Gson

    try {
      Some[Beatmap](gson.fromJson(getJson("https://osu.ppy.sh/api/get_beatmaps?k=" + key + "&b=" + id + "&m=" + mode), classOf[Array[Beatmap]])(0))
    } catch {
      case _: Exception => None
    }
  }

  def getScore(userID: String, beatmapID: String, mode: String = "0"): Option[Score] = {
    val gson: Gson = new Gson

    try {
      Some[Score](gson.fromJson(getJson("https://osu.ppy.sh/api/get_scores?k=" + key + "&u=" + userID + "&type=id&b=" + beatmapID + "&m=" + mode), classOf[Array[Score]])(0))
    } catch {
      case _: Exception => None
    }
  }

  def getBest(userID: String, mode: String = "0"): Option[Score] = {
    val gson: Gson = new Gson

    try {
      Some[Score](gson.fromJson(getJson("https://osu.ppy.sh/api/get_user_best?k=" + key + "&u=" + userID + "&type=string&m=" + mode), classOf[Array[Score]])(0))
    } catch {
      case _: Exception => None
    }
  }

  /* Not yet implemented

  def getMatch: Unit = {

  }
  */

  def getReplay(userID: String, beatmapID: String, mode: String = "0"): Option[Replay] = {
    val gson: Gson = new Gson

    try {
      Some[Replay](gson.fromJson(getJson("https://osu.ppy.sh/api/get_replay?k=" + key + "&u=" + userID + "&b=" + beatmapID + "&m=" + mode), classOf[Replay]))
    } catch {
      case _: Exception => None
    }
  }

  private def getJson(url: String): String = {
    Source.fromURL(url).mkString
  }
}
