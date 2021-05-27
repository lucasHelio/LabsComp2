import java.util.ArrayList;

/**
 *  Esta classe implementa um sistema de mensagens curtas estilo Twitter.
 *  É preciso cadastrar um usuário, identificado pelo seu e-mail, para que tuítes possam ser feitos.
 *  Usuários começam como iniciantes, depois são promovidos a senior e a ninja, em função do número de tuítes.
 *  Existe um tamanho máximo permitido por mensagem (constante TAMANHO_MAXIMO_TUITES).
 *  As mensagens podem conter hashtags (palavras iniciadas por #), que são detectadas automaticamente.
 *  Os tuítes podem conter, além da mensagem de texto, um anexo qualquer.
 *  Há um método para retornar, a qualquer momento, qual a hashtag mais usada em toda a história do sistema.
 */
public class TuiterLite<T> {
    private ArrayList<Usuario> contasRegistradas;
    public static ArrayList<Integer> quantHashtags;
    public static ArrayList<String> Hashtags;
    private static String hashtagMaisComum;

    public static final int TAMANHO_MAXIMO_TUITES = 120;

    public TuiterLite(){
        contasRegistradas = new ArrayList<>();
        Hashtags = new ArrayList<>();
        quantHashtags = new ArrayList<>();

    }
    /**
     * Cadastra um usuário, retornando o novo objeto Usuario criado.
     * Se o email informado já estiver em uso, não faz nada e retorna null.
     * @param nome O nome do usuário.
     * @param email O e-mail do usuário (precisa ser único no sistema).
     * @return O Usuario criado.
     */
    public Usuario cadastrarUsuario(String nome, String email) { // Deveria ser void, não?
        Usuario novoUsuario = new Usuario(nome, email);
        for(Usuario pesquisandoContas : contasRegistradas) {
            if (pesquisandoContas.getEmail().equals(novoUsuario.getEmail())) // Se o usuario existir..
                return contasRegistradas.get(contasRegistradas.indexOf(pesquisandoContas)); // retorna usuário existente
        }
        contasRegistradas.add(novoUsuario);// adiciona novo usuario

        return novoUsuario;
    }

    /**
     * Tuíta algo, retornando o objeto Tuíte criado.
     * Se o tamanho do texto exceder o limite pré-definido, não faz nada e retorna null.
     * Se o usuário não estiver cadastrado, não faz nada e retorna null.
     *
     * @param usuario O autor do tuíte
     * @param texto O texto desejado
     * @return Um "tuíte", que será devidamente publicado no sistema
     */
    public Tuite tuitarAlgo(Usuario usuario, String texto) {
        if(texto.length()<=TAMANHO_MAXIMO_TUITES) {
            for (Usuario pesquisandoContas : contasRegistradas) {
                if ((pesquisandoContas.getEmail().compareTo(usuario.getEmail()))==0 ) {
                    usuario.setTuites(usuario.getTuites()+1); // acrescentando mais 1 tuite

                    if(usuario.getTuites()>=usuario.MIN_TUITES_SENIOR && usuario.getTuites() < usuario.MIN_TUITES_NINJA){
                        usuario.setNivel(NivelUsuario.SENIOR);
                    }
                    if(usuario.getTuites()>= usuario.MIN_TUITES_NINJA){
                        usuario.setNivel(NivelUsuario.NINJA);
                    }
                    return new Tuite(usuario, texto);
                }
            }
        }
        return null;

    }

    /**
     * Retorna a hashtag mais comum dentre todas as que já apareceram.
     * A cada tuíte criado, hashtags devem ser detectadas automaticamente para que este método possa funcionar.
     * @return A hashtag mais comum, ou null se nunca uma hashtag houver sido tuitada.
     */
    public String getHashtagMaisComum() {
        return this.hashtagMaisComum;
    }

    public static void setHashtagMaisComum(ArrayList<Integer> quantHashtags) {
        if (quantHashtags.size() > 0) {
            int quantHashMaisComum = 0;
            int indexHashMaisComum = 0;
            for (Integer check : quantHashtags) {
                if (check > quantHashtags.get(quantHashMaisComum)) {
                    quantHashMaisComum = check;
                    indexHashMaisComum = quantHashtags.indexOf(quantHashMaisComum);
                }
            }
            hashtagMaisComum = Hashtags.get(indexHashMaisComum);
        }
    }


    // Mainzinho bobo, apenas ilustrando String.split(regexp), e o String.startsWith()

//    public static void main(String[] args) {
//        String frase = "Testando algo,sdf com #hashtags no meio #teste vamos ver!fdfgf";
//        String[] palavras = frase.split("[\\s,!]");
//        for (String palavra : palavras) {
//            if (palavra.startsWith("#")) {
//                System.out.println(palavra);
//            }
//        }
//    }
}
