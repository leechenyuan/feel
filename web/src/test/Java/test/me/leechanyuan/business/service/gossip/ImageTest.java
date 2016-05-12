package test.me.leechanyuan.business.service.gossip;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.StandardStream;
import org.junit.Test;

import java.io.IOException;

/**
 * * Created by L on 2016/5/12 23:05.
 */
public class ImageTest {
    public static void drawImg(String picPath,String drawPicPath,int width, int height) throws IOException, InterruptedException, IM4JavaException {
		IMOperation op = new IMOperation();
		op.addImage();
		op.resize(width, height);
		op.font("Arial").fill("red").draw("text 100,100 www.taobao.com");
		op.quality(85d);
		op.addImage();
		//IM4JAVA是同时支持ImageMagick和GraphicsMagick的，如果为true则使用GM，如果为false支持IM。
		ConvertCmd cmd = new ConvertCmd(true);
		String osName = System.getProperty("os.name").toLowerCase();
		if(osName.indexOf("win")>=0) {  //linux下不要设置此值，不然会报错
		    cmd.setSearchPath("C:\\Program Files (x86)\\GraphicsMagick-1.3.23-Q16");
		   }
		cmd.setErrorConsumer(StandardStream.STDERR);
		cmd.run(op, picPath , drawPicPath);
	}

	@Test
	public  void main(){
		try {
			drawImg("E:\\temp\\image\\bueaty.jpg", "E:\\temp\\image\\bueaty_draw.jpg", 300,400);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
