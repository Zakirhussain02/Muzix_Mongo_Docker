/*
methods defined for different operations..
*/

package com.stackroute.service;

import com.stackroute.domain.Muzix;
import com.stackroute.exceptions.MuzixAlreadyExistsException;
import com.stackroute.exceptions.MuzixTrackNotFoundException;

import java.util.List;

public interface MuzixService {

    public Muzix saveMuzix(Muzix muzix) throws MuzixAlreadyExistsException;
    public List<Muzix> getAllMuzixs();
    public Muzix updateMuzix(int trackId, String comment);
    public void removeMuzix(int trackId) throws MuzixTrackNotFoundException;


    public List<Muzix> trackByName(String trackName);

}
