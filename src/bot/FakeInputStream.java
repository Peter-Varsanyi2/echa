package bot;

import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

public class FakeInputStream extends InputStream {

	private Queue<String> input;
	 
    public FakeInputStream(Queue<String> input) {
        this.input = input;
    }
 
    @Override
    public int read(byte[] bytes, int i, int i1) throws IOException {
        if(input.isEmpty()) {
            return -1;
        }
 
        int byteLocation = 0;
        for(byte b : input.remove().getBytes()) {
            bytes[byteLocation] = b;
            byteLocation++;
        }
        bytes[byteLocation] = "\n".getBytes()[0];
        return byteLocation + 1;
    }
 
	public static InputStreamBuilder fakeInputStream() {
        return new InputStreamBuilder();
    }

	@Override
	public int read() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}
}
