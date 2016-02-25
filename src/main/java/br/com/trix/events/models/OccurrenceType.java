package br.com.trix.events.models;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 23/02/16.
 */
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum OccurrenceType {

  NEAREST_STOP,
  OUT_OF_ROUTE;

}
