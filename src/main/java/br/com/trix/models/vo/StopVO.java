package br.com.trix.models.vo;

import br.com.trix.models.Position;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 25/02/16.
 */
public class StopVO {

  private String name;
  private Position position;

  public StopVO() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }
}
