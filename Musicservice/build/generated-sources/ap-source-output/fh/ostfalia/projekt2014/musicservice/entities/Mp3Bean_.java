package fh.ostfalia.projekt2014.musicservice.entities;

import fh.ostfalia.projekt2014.musicservice.entities.Mp3ArtistBean;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-03-11T10:33:36")
@StaticMetamodel(Mp3Bean.class)
public class Mp3Bean_ { 

    public static volatile SingularAttribute<Mp3Bean, byte[]> mp3File;
    public static volatile SingularAttribute<Mp3Bean, Mp3ArtistBean> mp3ArtistBean;
    public static volatile SingularAttribute<Mp3Bean, Integer> mp3Id;
    public static volatile SingularAttribute<Mp3Bean, String> mp3Title;

}