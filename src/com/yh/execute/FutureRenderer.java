package com.yh.execute;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 利用future实现异步渲染界面
 *
 * //问题：
 * 很多图片只能全部加载完毕才能显示出来
 * Created by kevinyin on 2017/8/5.
 */
public class FutureRenderer {

    private final ExecutorService exec = Executors.newSingleThreadExecutor();

    void renderPage(CharSequence source) {
        final List<ImageInfo> imageInfos = scanForImageInfo(source);
        Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
            @Override
            public List<ImageData> call() throws Exception {
                List<ImageData> result = new ArrayList<>();
                for (ImageInfo imageInfo:
                        imageInfos) {
                    result.add(imageInfo.downLoadImage());
                }
                return result;
            }
        };
        Future<List<ImageData>> future = exec.submit(task);
        renderText(source);
        
        try {
            List<ImageData> imageData =  future.get();
            for (ImageData data :
                    imageData) {
                renderIamage(data);
            }
        } catch (InterruptedException e) {
            //重新设置中断状态
            Thread.currentThread().interrupt();
            future.cancel(true);
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private void renderIamage(ImageData data) {
    }

    private void renderText(CharSequence source) {
    }

    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        return new ArrayList<>();
    }


    class ImageInfo{
        public ImageData downLoadImage() {
            return new ImageData();
        }
    }

    class ImageData{

    }
}
