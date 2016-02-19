package br.com.trix.repositories.converters;

import br.com.trix.models.Position;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
@ReadingConverter
public class PositionReadingConverter implements Converter<DBObject, Position> {

  @Override
  public Position convert(DBObject dbObject) {
    return null;
  }

}
