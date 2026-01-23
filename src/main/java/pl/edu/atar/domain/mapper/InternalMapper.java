package pl.edu.atar.domain.mapper;

public interface InternalMapper<T, U> {

    U map(T original);
}
