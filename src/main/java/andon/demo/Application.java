package andon.demo;

import org.apache.commons.pool2.KeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static java.lang.System.out;

/**
 * Created by Caozheng on 2017/5/9.
 */
public class Application {

    private static KeyedObjectPool<String, FakeObject> pool;

    private static GenericKeyedObjectPoolConfig config;

    static{
        config = new GenericKeyedObjectPoolConfig();
        pool = new GenericKeyedObjectPool<>(new FakeObjectFactory(), config);
    }

    private static GenericKeyedObjectPool<String, FakeObject> getPool(Consumer<GenericKeyedObjectPoolConfig> action){
        GenericKeyedObjectPoolConfig config
                = new GenericKeyedObjectPoolConfig();

        if(action != null){
            action.accept(config);
        }

        GenericKeyedObjectPool<String, FakeObject> pool
                = new GenericKeyedObjectPool<>(new FakeObjectFactory(), config);

        return pool;

    }


    /**
     * 打印所有配置的默认信息
     */
    private static void printDefaultConfig(){
        out.println("MinEvictableIdleTimeMillis : " + config.getMinEvictableIdleTimeMillis());
        out.println("TimeBetweenEvictionRunsMillis : " + config.getTimeBetweenEvictionRunsMillis());
        out.println("TestWhileIdle : " + config.getTestWhileIdle());
        out.println("BlockWhenExhausted : " + config.getBlockWhenExhausted());
        out.println("EvictionPolicyClassName : " + config.getEvictionPolicyClassName());
        out.println("SoftMinEvictableIdleTimeMillis : " + config.getSoftMinEvictableIdleTimeMillis());
        out.println("MaxTotal : " + config.getMaxTotal());
        out.println("MaxTotalPerKey : " + config.getMaxTotalPerKey());
        out.println("MaxIdlePerKey : " + config.getMaxIdlePerKey());
        out.println("MaxWaitMillis : " + config.getMaxWaitMillis());
        out.println("Fairness : " + config.getFairness());
        out.println("Lifo : " + config.getLifo());
        out.println("NumTestsPerEvictionRun : " + config.getNumTestsPerEvictionRun());
        out.println("TestOnBorrow : " + config.getTestOnBorrow());
        out.println("TestOnCreate : " + config.getTestOnCreate());
        out.println("TestOnReturn : " + config.getTestOnReturn());

    }



    /**
     * 尝试耗尽池中元素，最终会抛出 java.util.NoSuchElementException
     * 注意如果不设置MaxWaitMillis则会永远等待下去
     */
    private static void exhausted() {
        GenericKeyedObjectPool<String, FakeObject> pool =
                getPool(p -> {
                    p.setMaxTotalPerKey(3);
                    p.setMaxWaitMillis(1000);
                });

        try {
            for (int i = 0; i < 5; i++) {
                FakeObject object = pool.borrowObject("a");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        exhausted();
    }
}
