package saribundo;

public class ItemTidakDitemukanException extends Exception {
    public ItemTidakDitemukanException(String pesan) {
        super(pesan);
    }
}