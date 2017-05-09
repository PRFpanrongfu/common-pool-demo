package andon.demo;

import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * Created by Caozheng on 2017/5/9.
 */
public class FakeObjectFactory extends BaseKeyedPooledObjectFactory<String, FakeObject> {

    @Override
    public FakeObject create(String key) throws Exception{

        return new FakeObject(key);
    }

    @Override
    public PooledObject<FakeObject> wrap(FakeObject object){
        return new DefaultPooledObject<>(object);
    }
}
