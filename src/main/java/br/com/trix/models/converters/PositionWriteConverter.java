package br.com.trix.models.converters;

import br.com.trix.models.Position;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 20/02/16.
 */
@WritingConverter
public class PositionWriteConverter implements Converter<Position, DBObject> {

  @Override
  public DBObject convert(Position position) {
    if(position != null){
      Double x = Double.valueOf(position.getLat());
      Double y = Double.valueOf(position.getLng());
      return new BasicDBObject("type" , "Point" ).append("coordinates" , new Double[]{ x , y });
    }
    return null;
  }
}
