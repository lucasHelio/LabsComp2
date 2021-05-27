import java.lang.reflect.TypeVariable;
import java.util.ArrayList;

public class Tuite<T> {

    private final Usuario autor;
    private final String texto;
    private T anexo;


    // hashtags?
    // objeto anexado?

    public Tuite(Usuario autor, String texto) {
        this.autor = autor;
        this.texto = texto;

        String[] textoTemporario = getTexto().split("[\\s,!]"); //splita o texto em palavras

        for (String palavraEscolhida : textoTemporario) {
            if (palavraEscolhida.startsWith("#")) {
                for (String hashtagArmazenada : TuiterLite.Hashtags){
                    if(palavraEscolhida.equals(hashtagArmazenada)){ // econtrou uma hashtag já adicionada
                        adicionarHashtagJaExistente(palavraEscolhida);
                        break;
                    }
                }
                adicionaHashtagNaoExistente(palavraEscolhida);//adicionou uma nova hashtag
            }
        }
        TuiterLite.setHashtagMaisComum(TuiterLite.quantHashtags);

    }

    public void anexarAlgo(T anexo) {

        this.anexo = anexo;
    }

    public Object getAnexo() {

        return this.anexo;
    }

    public Usuario getAutor() {
        return this.autor;
    }

    public String getTexto() {
        return this.texto;
    }

    public ArrayList<String> getHashtags() {
        return TuiterLite.Hashtags;
    }

    public void adicionarHashtagJaExistente(String hashtag) {

        for (String pesquisaHashtag : TuiterLite.Hashtags) {
            if (pesquisaHashtag.equals(hashtag)) {//hashtag já existe
                TuiterLite.quantHashtags.set(TuiterLite.Hashtags.indexOf(hashtag), TuiterLite.quantHashtags.get(TuiterLite.Hashtags.indexOf(hashtag))+1);
                break;
            }
        }

    }
    public void adicionaHashtagNaoExistente(String palavra){
        TuiterLite.Hashtags.add(palavra);
        TuiterLite.quantHashtags.add(1);
    }


}

