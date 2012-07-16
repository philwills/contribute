package com.gu.contribute.api.dispatcher

import net.liftweb.json.Printer._
import org.scalatra._
import org.joda.time.DateTime
import net.liftweb.json._
import org.bson.types.ObjectId
import com.gu.management.Loggable
import java.lang.{Integer => JInteger}

class JsonDispatcher extends ScalatraServlet with ScalatraKernel with Loggable {

  implicit val formats = DefaultFormats + DateTimeSerializer + MongoIdSerializer

  override protected def renderPipeline = ({
    case p: Product => {
      contentType = "application/json; charset=utf-8"
      val decomposed = Extraction.decompose(p)
      val rendered = render(decomposed)
      Printer.compact(rendered).getBytes("UTF-8")
    }
    case jv: JValue => Printer.compact(render(jv)).getBytes("UTF-8")
    contentType = "application/json; charset=utf-8"
  }: RenderPipeline) orElse super.renderPipeline

  object DateTimeSerializer extends Serializer[DateTime] {
    private val DateTimeClass = classOf[DateTime]

    def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), DateTime] = {
      case (TypeInfo(DateTimeClass, _), json) => json match {
        case JInt(millis) => new DateTime(millis.longValue)
        case x => throw new MappingException("Can't convert " + x + " to DateTime")
      }
    }

    def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
      case t: DateTime => JInt(t.getMillis)
    }
  }

  object MongoIdSerializer extends Serializer[ObjectId] with Loggable {

    private val ObjectIdClass = classOf[ObjectId]

    def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), ObjectId] = {
      case (TypeInfo(ObjectIdClass, _), json) => {
        json match {
          case JString(s) if (ObjectId.isValid(s)) => new ObjectId(s)
          case x => throw new MappingException("Can't convert " + x + " to ObjectId")
        }
      }
    }

    def serialize(implicit formats: Formats): PartialFunction[Any, JValue] = {
      case objectId: ObjectId => {
        Extraction.decompose(objectId.toString)
      }
    }
  }

}