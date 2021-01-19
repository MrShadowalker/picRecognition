package demo;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.imageaudit.model.v20191230.ScanImageRequest;
import com.aliyuncs.imageaudit.model.v20191230.ScanImageResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import common.GlobalConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 图片内容安全
 * 图片内容安全支持检测的场景包括有图片智能鉴黄、图片涉恐涉政识别、图文违规识别、图片二维码识
 * 别、图片不良场景识别和图片logo识别等。
 * @author Shadowalker
 */
public class ScanImageDemo {

    private static DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", GlobalConstants.OSS_ACCESS_KEY_ID, GlobalConstants.OSS_ACCESS_KEY_SECRET);
    private static IAcsClient client = new DefaultAcsClient(profile);

    public static void main(String[] args) {
        ScanImageRequest request = new ScanImageRequest();
        List<ScanImageRequest.Task> taskList = new ArrayList<ScanImageRequest.Task>();
        ScanImageRequest.Task task1 = new ScanImageRequest.Task();
        // 数据ID。需要保证在一次请求中所有的ID不重复。
        task1.setDataId(UUID.randomUUID().toString());
        // 待检测图像的URL。支持HTTP和HTTPS协议。当前仅支持上海地域的OSS链接。
        task1.setImageURL("https://visionapi-test.oss-cn-shanghai.aliyuncs.com/TB1k8mYCpY7gK0jSZKzXXaikpXa-692-440%5B1%5D.jpg");
        taskList.add(task1);
        request.setTasks(taskList);
        // 指定图片检测的应用场景
        List<String> sceneList = new ArrayList<String>();
        // 图片智能鉴黄
        sceneList.add("porn");
        // 图片涉恐涉政识别
        sceneList.add("terrorism");
        // 图文违规识别
        sceneList.add("ad");
        // 图片不良场景识别
        sceneList.add("live");
        // 图片logo识别
        sceneList.add("logo");
        request.setScenes(sceneList);
        try {
            ScanImageResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }
}
