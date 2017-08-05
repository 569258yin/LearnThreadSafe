package com.yh.execute;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 利用CompletionService实现提交多个任务，最后得到一组future，对每一个调用get
 * 内部附加一个BlockingQueue用于保存future
 * Created by kevinyin on 2017/8/5.
 */
public class Renderer {

    private final ExecutorService exec = Executors.newSingleThreadExecutor();

    void renderPage(CharSequence source) {
        final List<ImageInfo> imageInfos = scanForImageInfo(source);
        CompletionService<ImageData> completionService = new ExecutorCompletionService<ImageData>(exec);
        for (ImageInfo info :
                imageInfos) {
            completionService.submit(new Callable<ImageData>() {
                @Override
                public ImageData call() throws Exception {
                    return info.downLoadImage();
                }
            });
        }
        renderText(source);
        
        try {
            for (int t = 0 , n = imageInfos.size(); t < n; t++) {
                Future<ImageData> f = completionService.take();
                ImageData imageData = f.get();
                renderIamage(imageData);
            }
        } catch (InterruptedException e) {
            //重新设置中断状态
            Thread.currentThread().interrupt();
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
