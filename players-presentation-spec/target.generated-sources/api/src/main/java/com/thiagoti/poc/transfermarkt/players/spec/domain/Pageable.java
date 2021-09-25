package com.thiagoti.poc.transfermarkt.players.spec.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * minimal Pageable query parameters
 */
@ApiModel(description = "minimal Pageable query parameters")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class Pageable   {
  @JsonProperty("page")
  private Integer page;

  @JsonProperty("size")
  private Integer size;

  public Pageable page(Integer page) {
    this.page = page;
    return this;
  }

  /**
   * Get page
   * @return page
  */
  @ApiModelProperty(value = "")


  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public Pageable size(Integer size) {
    this.size = size;
    return this;
  }

  /**
   * Get size
   * @return size
  */
  @ApiModelProperty(value = "")


  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pageable pageable = (Pageable) o;
    return Objects.equals(this.page, pageable.page) &&
        Objects.equals(this.size, pageable.size);
  }

  @Override
  public int hashCode() {
    return Objects.hash(page, size);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Pageable {\n");
    
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

