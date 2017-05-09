package andon.demo;

import java.util.Random;
import java.util.UUID;

/**
 * Created by Caozheng on 2017/5/9.
 */
public class FakeObject {

    private String id;
    private String name;

    public FakeObject(String name){
        this.name = name;

        id = UUID.randomUUID().toString();
        System.out.println("A new object is created: " + id);

    }

    @Override
    public String toString(){
        return "name: " + name + " id:" + id;
    }

}
