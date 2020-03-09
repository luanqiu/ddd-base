package ddd.base.domain.specification;

/**
* 规约模式 - 校验的通用接口
*author  wenhe
*date 2020/3/2
*/
public interface ISpecification<T> {

  default boolean isSatisfied(T candidate){return true;}

  /**
   * 并且&&关系
   * @param specification
   * @return
   */
  ISpecification<T> and(ISpecification<T> specification);

  /**
   * 或||关系
   * @param specification
   * @return
   */
  ISpecification<T> or(ISpecification<T> specification);

  /**
   * 不等于!=关系
   * @param specification
   * @return
   */
  ISpecification<T> not(ISpecification<T> specification);

}
