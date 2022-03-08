import org.testng.annotations.Test;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class Main {

    @Test
    public void webTest() {
        //WebTest.WebTest webTest = new WebTest.WebTest();
        //webTest.testFlex01(true);
        //webTest.testFlex02(false);
        //webTest.testShouldBeCrashed();

    }


        public static void main(final String[] args)
        {
            ClassLoader loader = Test.class.getClassLoader();
            System.out.println(loader.getResource("foo/Test.class"));

            URL main = Main.class.getResource("Main.class");
            if (!"file".equalsIgnoreCase(main.getProtocol()))
                throw new IllegalStateException("Main class is not stored in a file.");
            File path = new File(main.getPath());
            System.out.println(loader.getResource("foo/Test.class"));



            StringBuffer buffer = new StringBuffer();
            for (URL url :
                    ((URLClassLoader) (Thread.currentThread()
                            .getContextClassLoader())).getURLs()) {
                buffer.append(new File(url.getPath()));
                buffer.append(System.getProperty("path.separator"));
            }
            String classpath = buffer.toString();
            int toIndex = classpath
                    .lastIndexOf(System.getProperty("path.separator"));
            classpath = classpath.substring(0, toIndex);
            ProcessBuilder builder = new ProcessBuilder("java",
                    "-classpath", classpath, "com.a.b.c.TestProgram");

        }

    public static class CurrentPhysicalPath extends java.io.File
    {
        CurrentPhysicalPath()
        {
            super(".");//
        }
        String getResult()
        {
            return toString();//
        }
        public static void main(String[] args)
        {
            CurrentPhysicalPath cpp = new CurrentPhysicalPath();//
            System.out.println(cpp.getResult());
        }
    }







}

