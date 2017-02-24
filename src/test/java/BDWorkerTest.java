import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by alta0816 on 20.02.2017.
 */
public class BDWorkerTest {
    private static BDWorker worker;

    @BeforeClass
    public static void setUpBeforeClass() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        worker = (BDWorker) context.getBean("worker");
    }

    @Test
    public void testConnect() {
        worker.connect("localhost", "1337");
    }

    @Test
    public void testConnect() {
        worker.createDatabase("localhost");
    }

    @Test
    public void testConnect() {
        worker.connect("localhost", "1337");
    }

    @Test
    public void testConnect() {
        worker.connect("localhost", "1337");
    }

    @Test
    public void testConnect() {
        worker.connect("localhost", "1337");
    }

    @Test
    public void testConnect() {
        worker.connect("localhost", "1337");
    }

    @Test
    public void testConnect() {
        worker.connect("localhost", "1337");
    }

    @Test
    public void testConnect() {
        worker.connect("localhost", "1337");
    }

    @Test
    public void testConnect() {
        worker.connect("localhost", "1337");
    }
}
