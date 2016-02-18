package br.com.trix.models;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by efraimgentil<efraimgentil@gmail.com> on 18/02/16.
 */
public class Parada implements Serializable {

  @Id
  private String id;
  private String nome;
  private Posicao posicao;

  public Parada() {
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Parada{");
    sb.append("id='").append(id).append('\'');
    sb.append(", nome='").append(nome).append('\'');
    sb.append(", posicao=").append(posicao);
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Parada parada = (Parada) o;
    if (posicao != null ? !posicao.equals(parada.posicao) : parada.posicao != null) return false;
    return true;
  }

  @Override
  public int hashCode() {
    return posicao != null ? posicao.hashCode() : 0;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Posicao getPosicao() {
    return posicao;
  }

  public void setPosicao(Posicao posicao) {
    this.posicao = posicao;
  }

}
