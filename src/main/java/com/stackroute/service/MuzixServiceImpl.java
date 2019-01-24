
package com.stackroute.service;

        import com.stackroute.domain.Muzix;
        import com.stackroute.exceptions.MuzixAlreadyExistsException;
        import com.stackroute.exceptions.MuzixTrackNotFoundException;
        import com.stackroute.repository.MuzixRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.Optional;

@Service
public class MuzixServiceImpl implements MuzixService
{

    private MuzixRepository muzixRepository;

    //constructor

    @Autowired
    public MuzixServiceImpl(MuzixRepository muzixRepository){
        this.muzixRepository=muzixRepository;
    }

    //saving user by ID

    @Override
    public Muzix saveMuzix(Muzix muzix) throws MuzixAlreadyExistsException {

        if(muzixRepository.existsById(muzix.getTrackId())){
            throw new MuzixAlreadyExistsException("user already exists");
        }

        Muzix savedUser = muzixRepository.save(muzix);

        if(savedUser == null)
            throw new MuzixAlreadyExistsException("user already exists");

        return savedUser;
    }


    //getting all users

    @Override
    public List<Muzix> getAllMuzixs() {
        return muzixRepository.findAll();
    }

    //updating users

    @Override
    public Muzix updateMuzix(int trackId,String comment) {
        Muzix muzix = muzixRepository.findById(trackId).get();
        muzix.setComment(comment);
        return muzixRepository.save(muzix);
    }

    // Deleting the track by ID
    @Override
    public void removeMuzix(int trackId) throws MuzixTrackNotFoundException{
        if (!muzixRepository.existsById(trackId)) {
            throw new MuzixTrackNotFoundException("Sorry!The Track is Empty");
        }
            muzixRepository.deleteById(trackId);


        }
        @Override
    public List<Muzix> trackByName(String trackName) {
        return muzixRepository.findBytrackName(trackName);
    }
}
