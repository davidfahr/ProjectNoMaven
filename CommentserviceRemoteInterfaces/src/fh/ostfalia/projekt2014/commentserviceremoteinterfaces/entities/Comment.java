package fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities;

/**
 * 
 * @author M.Tönjes, D.Fahr, Y.Weißflog 
 * Interface der Entität Kommentar
 */
public interface Comment {

    /**
     * siehe Implementierung in CommentBean
     */
    public Long getcId();

    /**
     * siehe Implementierung in CommentBean
     */
    public String getcText();

    /**
     * siehe Implementierung in CommentBean
     */
    public Long getcMp3Id();

    /**
     * siehe Implementierung in CommentBean
     */
    public Long getCmp3ArtistId();
}
