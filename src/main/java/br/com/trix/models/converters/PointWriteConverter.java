package br.com.trix.models.converters;

import br.com.trix.models.Position;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.geo.Point;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 20/02/16.
 */
@WritingConverter
public class PointWriteConverter implements Converter<Point, DBObject> {

  @Override
  public DBObject convert(Point position) {
    if(position != null){
      Double x = Double.valueOf(position.getX());
      Double y = Double.valueOf(position.getY());
      return new BasicDBObject("type" , "Point" ).append("coordinates" , new Double[]{ x , y });
    }
    return null;
  }
}
