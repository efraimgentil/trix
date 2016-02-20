package br.com.trix.models.converters;

import br.com.trix.models.Position;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.geo.Point;
import org.springframework.util.Assert;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 20/02/16.
 */
@ReadingConverter
public class PositionReadConverter implements Converter<DBObject, Position> {

  @Override
  public Position convert(DBObject dbObject) {
    if(dbObject != null){
      BasicDBList list = (BasicDBList) dbObject.get("coordinates");
      Double lat =  new Double(String.valueOf(list.get(0)) );
      Double lng =  new Double(String.valueOf(list.get(1)) );
      return new Position( lat, lng);
    }
    return null;
  }

}
