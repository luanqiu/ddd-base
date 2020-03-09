package ddd.base.domain.specification;

/**
* CompositeSpecification
*author  wenhe
*date 2020/3/2
*/
public abstract class CompositeSpecification<T> implements ISpecification<T>{

  @Override
  public ISpecification and(ISpecification specification) {
    return new AndSpecification<>(this,specification);
  }

  @Override
  public ISpecification or(ISpecification specification) {
    return new OrSpecification<>(this,specification);
  }

  @Override
  public ISpecification not(ISpecification specification) {
    return new NotSpecification<>(this,specification);
  }

}
