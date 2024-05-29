import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertyFile {
    public String getPropertyFile(String key) throws IOException {
        Properties p = new Properties();
        FileInputStream fis = new FileInputStream(Objects.requireNonNull(PropertyFile.class.getClassLoader().getResource("data.properties")).getFile());
        p.load(fis);
        String value = p.getProperty(key);
        return value;
    }
}
