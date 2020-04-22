import java.io.*;

public class test {
    public static void main(String[] args) {
        File sampleFile = new File("sample.txt");
        byte[] content = new byte[] {'J', 'a', 'v', 'a'};
        try (OutputStream outputStream = new FileOutputStream(sampleFile, true)) {
            outputStream.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
