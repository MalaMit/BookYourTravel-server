package pk.ap.bookyourtravelserwer.util;

public interface DtoAssembler<T,S> {
    T assemble(S entityObject);
}
