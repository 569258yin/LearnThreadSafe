package com.yh.execute;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * ����CompletionServiceʵ���ύ����������õ�һ��future����ÿһ������get
 * �ڲ�����һ��BlockingQueue���ڱ���future
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
            //���������ж�״̬
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
