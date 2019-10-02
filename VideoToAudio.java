package ServiceSend;

import com.xuggle.mediatool.*;
import com.xuggle.mediatool.event.IAddStreamEvent;
import com.xuggle.mediatool.event.IAudioSamplesEvent;
import com.xuggle.mediatool.event.ICloseEvent;
import com.xuggle.mediatool.event.IOpenCoderEvent;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IStreamCoder;

public class VideoToAudio {

    public void convertVideoToAudio() {

        String inputFilename = "/Users/hywel/Documents/home/pictures/Test.mp4";
        String outputFilename = "/Users/hywel/Documents/home/pictures/Audio.mp3";

        IMediaReader reader = ToolFactory.makeReader(inputFilename);
        IMediaWriter writer = ToolFactory.makeWriter(outputFilename);
        int sampleRate = 44100;
        int channels = 1;
        writer.addAudioStream(0, 0, ICodec.ID.CODEC_ID_MP3, channels, sampleRate);
        while (reader.readPacket() == null) ;
    }
}