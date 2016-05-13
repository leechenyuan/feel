package test.me.leechanyuan.business.service.util;

import com.google.common.io.Files;
import me.leechenyuan.common.utils.MimeUtil;
import me.leechenyuan.common.utils.image.ImageUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.Pipe;
import org.im4java.process.StandardStream;
import org.junit.Test;
import org.springframework.util.MimeTypeUtils;

import java.io.*;

/**
 * * Created by L on 2016/5/12 23:05.
 */
public class ImageTest {
    private static ConvertCmd getConvertCmd(){
        //IM4JAVA是同时支持ImageMagick和GraphicsMagick的，如果为true则使用GM，如果为false支持IM。
        ConvertCmd cmd = new ConvertCmd(true);
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.indexOf("win") >= 0) {  //linux下不要设置此值，不然会报错
            cmd.setSearchPath("C:\\Program Files (x86)\\GraphicsMagick-1.3.23-Q16");
        }
        return cmd;
    }
    private static ConvertCmd getConvertCmd(String binPath){
                //IM4JAVA是同时支持ImageMagick和GraphicsMagick的，如果为true则使用GM，如果为false支持IM。
        ConvertCmd cmd = new ConvertCmd(true);
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.indexOf("win") >= 0) {  //linux下不要设置此值，不然会报错
            cmd.setSearchPath(binPath);
        }
        return cmd;
    }

    public static void drawImg(String picPath, String drawPicPath, int width, int height) throws IOException, InterruptedException, IM4JavaException {
        IMOperation op = new IMOperation();
        op.addImage();
        op.resize(width, height);
        op.font("Arial").fill("red").draw("text 100,100 www.taobao.com");
        op.quality(85d);
        op.addImage();
        ConvertCmd cmd = getConvertCmd();
        cmd.setErrorConsumer(StandardStream.STDERR);
        cmd.run(op, picPath, drawPicPath);
    }

    public static byte[] resizeImage(Integer height, Integer width, byte[] b) throws IOException, InterruptedException {
        IMOperation op = new IMOperation();
        op.addImage("-");
        op.resize(height, width);
        op.addImage("-");

        ByteArrayInputStream sbs = new ByteArrayInputStream(b);
        Pipe pipeIn = new Pipe(sbs, null);

        ByteArrayOutputStream fos = new ByteArrayOutputStream();
        Pipe pipeOut = new Pipe(null, fos);

        // set up command
        ConvertCmd convert = getConvertCmd();
        convert.setInputProvider(pipeIn);
        convert.setOutputConsumer(pipeOut);

        try {
            convert.run(op);
        } catch (IM4JavaException e) {
            // FIXME Auto-generated catch block
            e.printStackTrace();
        } finally {
            fos.close();
            sbs.close();
        }
        return fos.toByteArray();
    }

    @Test
    public void main() throws IOException, InterruptedException {
        try {
            drawImg("E:\\temp\\image\\bueaty.jpg", "E:\\temp\\image\\bueaty_draw.jpg", 300, 400);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
        String src = "E:\\temp\\image\\bueaty.jpg";
        String desc = "E:\\temp\\image\\bueaty_resize.jpg";
        byte [] descBytes = resizeImage(100,100, IOUtils.toByteArray(new FileReader(src)));
        IOUtils.write(descBytes,new FileWriter(desc));
    }

    @Test
    public void testImageUtil() throws IOException {
        String src = "E:\\temp\\image\\bueaty.jpg";
        String descImage = "E:\\temp\\image\\bueaty_imageUtil.jpg";
        InputStream in = new FileInputStream(src);
        InputStream desc = ImageUtil.resize(in, MimeUtil.getMimeType(src),100,100);
        byte [] buff = new byte[desc.available()];
        desc.read(buff);
        Files.write(buff,new File(descImage));
//        不能将desc转成byte [] 写入wrtier，否则保存的图片会破损，原因未明.
//        Writer writer = new FileWriter(descImage);
//        IOUtils.write(IOUtils.toByteArray(desc), writer);
//        writer.flush();
//        IOUtils.closeQuietly(writer);
    }
}
