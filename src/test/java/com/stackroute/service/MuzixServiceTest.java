package com.stackroute.service;

import com.stackroute.domain.Muzix;
import com.stackroute.exceptions.MuzixAlreadyExistsException;
import com.stackroute.exceptions.MuzixTrackNotFoundException;
import com.stackroute.repository.MuzixRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MuzixServiceTest {

    private Muzix muzix;

    @Mock
    private MuzixRepository muzixRepository;

    @InjectMocks
    private MuzixServiceImpl muzixService;
    List<Muzix> list = null;

    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        muzix = new Muzix();
        muzix.setComment("Enrique song");
        muzix.setTrackId(1);
        muzix.setTrackName("Freak");
        list = new ArrayList<>();
        list.add(muzix);
    }

    @Test
    public void saveMuzixTestSuccess() throws MuzixAlreadyExistsException {

        when(muzixRepository.save((Muzix) any())).thenReturn(muzix);
        Muzix savedMuzix = muzixService.saveMuzix(muzix);
        Assert.assertEquals(muzix, savedMuzix);

        //verify here verifies that userRepository save method is only called once
        verify(muzixRepository, times(1)).save(muzix);
    }

    @Test(expected = MuzixAlreadyExistsException.class)
    public void saveUpdateTestFailure() throws MuzixAlreadyExistsException {
        when(muzixRepository.save((Muzix) any())).thenReturn(null);
        Muzix savedMuzix = muzixService.saveMuzix(muzix);
        System.out.println("savedMuzix" + savedMuzix);
        Assert.assertEquals(muzix, savedMuzix);

       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/


    }

    //get all items in list

    @Test
    public void getAllMuzixs() {

        muzixRepository.save(muzix);
        //stubbing the mock to return specific data
        when(muzixRepository.findAll()).thenReturn(list);
        List<Muzix> muzixlist = muzixService.getAllMuzixs();
        Assert.assertEquals(list, muzixlist);
    }

//update test

    @Test
    public void updateMuzixTestSuccess() {
        when(muzixRepository.findById(anyInt())).thenReturn(Optional.of(muzix));
        when(muzixRepository.save((Muzix) any())).thenReturn(muzix);
        muzix.setComment("Enriquee songs");
        Muzix updateMuzix = muzixService.updateMuzix(muzix.getTrackId(), muzix.getComment());
        Assert.assertEquals(muzix, updateMuzix);

        //verify here verifies that userRepository save method is only called once
        verify(muzixRepository, times(1)).save(muzix);
    }

    @Test(expected = MuzixAlreadyExistsException.class)
    public void updateMuzixTestFailure() throws MuzixAlreadyExistsException {
        when(muzixRepository.save((Muzix) any())).thenReturn(null);
        Muzix savedMuzix = muzixService.saveMuzix(muzix);
        System.out.println("savedMuzix" + savedMuzix);
        Assert.assertEquals(muzix, savedMuzix);

    }

    //Remove or Deleting by ID

    @Test
    public void removeMuzixTestSuccess() throws MuzixTrackNotFoundException {
        when(muzixRepository.existsById(anyInt())).thenReturn(true);
        when(muzixRepository.findAll()).thenReturn(list);
        List<Muzix> output = muzixService.removeMuzix(muzix.getTrackId());
        Assert.assertEquals(list, output);
        verify(muzixRepository, times(1)).deleteById(1);
    }

    @Test(expected = MuzixTrackNotFoundException.class)
    public void removeMuzixTestFailure() throws MuzixTrackNotFoundException {
        when(!muzixRepository.existsById(5)).thenReturn(true);
        when(muzixRepository.findAll()).thenReturn(list);
        List<Muzix> output = muzixService.removeMuzix(muzix.getTrackId());
        Assert.assertEquals(list, output);


       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/

    }


    //gettrackbyname

    @Test
    public void getTrackByNameTestSuccess() {
        muzixRepository.save(muzix);
        //stubbing the mock to return specific data
        when(muzixRepository.findBytrackName(muzix.getTrackName())).thenReturn(list);
        List<Muzix> muzixlist = muzixService.trackByName(muzix.getTrackName());
        Assert.assertEquals(list, muzixlist);

    }
}
