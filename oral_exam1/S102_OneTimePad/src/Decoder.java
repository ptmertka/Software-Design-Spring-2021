public class Decoder {

    private static final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H','I', 'J', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private KeyFile keyFileToUse;

    private MessageFile messageFileToUse;

    public Decoder(KeyFile keyFile, MessageFile messageFile){
        keyFileToUse= keyFile;
        messageFileToUse = messageFile;
    }

    public void decode(){

    }
}
