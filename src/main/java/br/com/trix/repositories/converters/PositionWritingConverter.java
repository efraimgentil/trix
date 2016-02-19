package br.com.trix.repositories.converters;

import br.com.trix.models.Position;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@WritingConverter
public class PositionWritingConverter implements Converter<Position , DBObject> {

  @Override
  public DBObject convert(Position position) {
    new BasicDBObject("type" , "Point");
    return new BasicDBObject();// [type: 'Point', coordinates: [source.x, source.y]]);
  }

}
