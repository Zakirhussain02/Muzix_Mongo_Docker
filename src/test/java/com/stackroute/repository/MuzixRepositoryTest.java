package com.stackroute.repository;

import com.stackroute.domain.Muzix;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class MuzixRepositoryTest {

    @Autowired
    private MuzixRepository muzixRepository;
    private Muzix muzix;

    @Before
    public void setUp()
    {
        muzix = new Muzix();
        muzix.setTrackId(1);
        muzix.setTrackName("Baby");
        muzix.setComment("JB song");

    }

    @After
    public void tearDown(){

        muzixRepository.deleteAll();
    }


    /*
    save muzix
     */
    @Test
    public void testSaveMuzix(){
        muzixRepository.save(muzix);
        Muzix fetchUser = muzixRepository.findById(muzix.getTrackId()).get();
        Assert.assertEquals(1,fetchUser.getTrackId());
    }

    /*
    save all failure
     */
    @Test
    public void testSaveMuzixFailure(){
        Muzix testUser = new Muzix(1,"Baby","JB song");
        muzixRepository.save(muzix);
        Muzix fetchUser = muzixRepository.findById(muzix.getTrackId()).get();
        Assert.assertNotSame(testUser,muzix);
    }

    /*
    get all muzic
     */
    @Test
    public void testGetAllMuzix(){
        Muzix u = new Muzix(2,"Freak","Enrique song");
        Muzix u1 = new Muzix(3,"HeartBeat","Enrique Songs");
        muzixRepository.save(u);
        muzixRepository.save(u1);

        List<Muzix> list = muzixRepository.findAll();
        Assert.assertEquals("HeartBeat",list.get(1).getTrackName());
    }

    /*
    delete by name
     */
    @Test
    public void testdeleteMuzix(){
        muzixRepository.save(muzix);
        muzixRepository.deleteById(1);
        Assert.assertEquals(false,muzixRepository.existsById(1));
    }


    /*
    find by Track name
     */
    @Test
    public void testfindByTrackName(){
        Muzix u1 = new Muzix(3,"HeartBeat","Enrique Songs");
        muzixRepository.save(u1);

        List<Muzix> list = muzixRepository.findBytrackName("HeartBeat");
        Assert.assertEquals("HeartBeat",list.get(0).getTrackName());
    }


}
