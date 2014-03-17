package fh.ostfalia.projekt2014.musicserviceentities;

/**
 * Entity Schnittstelle, damit eine einheitliche Schnittstelle zur Entit Mp3
 * vorhanden ist.
 *
 * @author M.Tönjes, D.Fahr, Y.Weißflog
 */
public interface Mp3 {

    public int getArtistId();

    public String getArtistName();

    public int getMp3Id();

    public String getMp3Title();

    public byte[] getMp3File();

}
