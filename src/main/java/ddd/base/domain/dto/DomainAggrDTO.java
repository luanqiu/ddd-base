package ddd.base.domain.dto;

import java.util.List;

import lombok.Data;

/**
 * Created by luanqiu on 2019/4/24.
 */
@Data
public class DomainAggrDTO extends BaseDTO {

  private static final long serialVersionUID = 3570762692300573568L;

  private List<EntityDTO> entitys;

  private List<AggrDTO> aggrs;

  private List<VODTO> vos;

  private List<ContentDTO> contents;

  private List<DomainServiceDTO> domainServices;

  private List<AppServiceDTO> appServices;

}
